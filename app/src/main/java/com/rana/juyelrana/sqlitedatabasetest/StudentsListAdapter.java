package com.rana.juyelrana.sqlitedatabasetest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JuyelRana on 5/22/2016.
 */
public class StudentsListAdapter extends BaseAdapter {

    private Activity activity;
    private List<StudentLists> studentListsList;
    private LayoutInflater inflater;

    public StudentsListAdapter(Activity activity, List<StudentLists> studentListsList) {

        this.activity = activity;
        this.studentListsList = studentListsList;
    }


    @Override
    public int getCount() {
        return studentListsList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentListsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }if(convertView == null){
            convertView = inflater.inflate(R.layout.student_lists, null);
        }

        //Initialize custom listview
        TextView name  = (TextView) convertView.findViewById(R.id.txtName);
        TextView phone = (TextView) convertView.findViewById(R.id.txtPhone);
        TextView email = (TextView) convertView.findViewById(R.id.txtEmail);
        TextView faculty = (TextView)convertView.findViewById(R.id.txtFaculty);

        StudentLists studentLists = studentListsList.get(position);

        //Set the the students data to custom listview
        name.setText(studentLists.getName());
        phone.setText(studentLists.getPhone());
        email.setText(studentLists.getEmail());
        faculty.setText(studentLists.getFaculty());

        return convertView;
    }
}
