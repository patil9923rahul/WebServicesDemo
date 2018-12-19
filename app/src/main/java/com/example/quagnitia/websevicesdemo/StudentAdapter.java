package com.example.quagnitia.websevicesdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by V@iBh@V on 7/20/2017.
 */

public class StudentAdapter extends BaseAdapter {
    Context context;
    ArrayList<Student> studentList;
    Holder holder;
    LayoutInflater lf;
    public StudentAdapter(Context context, ArrayList<Student> studentList) {
        this.context=context;
        this.studentList=studentList;
        lf= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView=lf.inflate(R.layout.student_row,parent,false);
            holder=new Holder();
            holder.txtName= (TextView) convertView.findViewById(R.id.txtName);

            convertView.setTag(holder);
        }
        else
        {
            holder= (Holder) convertView.getTag();
        }

        Student student=studentList.get(position);
        holder.txtName.setText(student.getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student=studentList.get(position);
                int id=student.getId();
                String name=student.getName();
                String city=student.getCity();
                String birthdate=student.getBirthdate();
                int age=student.getAge();
                String gender=student.getGender();

                Intent i=new Intent(context,StudentDetailActivity.class);
                i.putExtra("ID",id);
                i.putExtra("NAME",name);

                i.putExtra("Gender",gender);
                i.putExtra("CITY",city);
                i.putExtra("BIRTHDATE",birthdate);
                i.putExtra("AGE",age);
                context.startActivity(i);
            }
        });
        return convertView;
    }

    private class Holder
    {
        TextView txtName;
    }
}

