package com.example.quagnitia.websevicesdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.quagnitia.websevicesdemo.Webservice.Executor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnTaskCompleteListener {
    Context context = this;
    ListView list;
    ArrayList<Student> studentList;
    String url = "https://www.dropbox.com/s/9uuyt042bwi2una/StudentJson.txt?dl=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        callStudentws();
    }

    private void callStudentws() {
        Executor executor = new Executor(context, "StudentWs");
        executor.execute(url);
    }

    @Override
    public void OnTaskCompleted(String results, String tag) {
        if (tag.equals("StudentWs")) {
            studentList = new ArrayList<>();
            try {
                JSONObject mainobj = new JSONObject(results);
                JSONArray studentArray = mainobj.getJSONArray("student");
                for (int i = 0; i < studentArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) studentArray.get(i);
                    int id = jsonObject.getInt("id");
                    String name = jsonObject.getString("name");
                    String city = jsonObject.getString("city");
                    String Gender = jsonObject.getString("Gender");
                    int age = jsonObject.getInt("age");
                    String birthdate = jsonObject.getString("birthdate");

                    Student s = new Student();
                    s.setId(id);
                    s.setName(name);
                    s.setAge(age);
                    s.setCity(city);
                    s.setGender(Gender);
                    s.setBirthdate(birthdate);

                    studentList.add(s);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            StudentAdapter sd = new StudentAdapter(context, studentList);
            list.setAdapter(sd);
        }
    }
}
