package com.example.quagnitia.websevicesdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StudentDetailActivity extends AppCompatActivity {
TextView txtName,txtAge,txtCity,txtGender,txtBirthdate,txtid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        initUI();

        Intent i=getIntent();
        if(i!=null)
        {
            int id=i.getExtras().getInt("ID");
            String name=i.getExtras().getString("NAME");
            String city=i.getExtras().getString("CITY");
            String gender=i.getExtras().getString("Gender");
            String birthdate=i.getExtras().getString("BIRTHDATE");
            int age=i.getExtras().getInt("AGE");

            txtid.setText(""+id);
            txtAge.setText(""+age);
            txtCity.setText(city);
            txtGender.setText(gender);
            txtBirthdate.setText(birthdate);
            txtName.setText(name);

        }
    }


    private void initUI()
    {
        txtName= (TextView) findViewById(R.id.txtName);
        txtAge= (TextView) findViewById(R.id.txtAge);
        txtCity= (TextView) findViewById(R.id.txtCity);
        txtGender= (TextView) findViewById(R.id.txtGender);
        txtBirthdate= (TextView) findViewById(R.id.txtBirthdate);
        txtid= (TextView) findViewById(R.id.txtid);
    }
}
