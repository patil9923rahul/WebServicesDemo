package com.example.quagnitia.websevicesdemo.Webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.quagnitia.websevicesdemo.OnTaskCompleteListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by V@iBh@V on 7/19/2017.
 */

public class Executor extends AsyncTask<String,Void,String>
{
    ProgressDialog pd;
    Context context;
    OnTaskCompleteListener listener;
    String tag="";

    public Executor(Context context, String tag) {
        this.context=context;
        listener= (OnTaskCompleteListener) context;
        this.tag=tag;
    }

    @Override
    protected void onPreExecute() {
        pd=new ProgressDialog(context);
        pd.setCancelable(false);
        pd.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String[] stringurl)
    {
        String url=stringurl[0];
        String jsonString="";
        try
        {
            URL linkutl=new URL(url);
            HttpURLConnection conn=(HttpURLConnection) linkutl.openConnection();
            int responsecode=conn.getResponseCode();
            if (responsecode== HttpsURLConnection.HTTP_OK);
            {
                InputStream linkinStream=conn.getInputStream();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                int j=0;
                while ((j=linkinStream.read()) !=-1)
                {
                    baos.write(j);
                }
                byte[] data=baos.toByteArray();
                jsonString=new String(data);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (pd.isShowing())
        {
            pd.dismiss();
        }
        listener.OnTaskCompleted(s,tag);
    }
}
