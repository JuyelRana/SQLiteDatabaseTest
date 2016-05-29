package com.rana.juyelrana.sqlitedatabasetest;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Initilize all UI component
    private EditText etName, etPhone, etEmail, etFaculty;
    private Button btnAdd, btnUpdate, btnDelete;

    //Initialize students listview
    private ListView studentsList;
    private int currentIndex;

    //Create an object of DbHelper Class
    private DbHelper dbHelper;

    //Just for show students as a listview

    //initialize a listview to store allstudents list from database
    private List<StudentLists> studentListsList = new ArrayList<>();

    //Create an object of Custom Adapter
    private StudentsListAdapter studentsListAdapter;
    // end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call DbHelper class
        dbHelper = new DbHelper(this);

        //Call viewStudents method to show all students data in the listview
        viewStudents();

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etFaculty = (EditText) findViewById(R.id.etFaculty);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);


        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:
                // add Students to database
                addStudents();
                viewStudents();
                break;

            case R.id.btnUpdate:
                //Update Students data
                updateStudent();
                viewStudents();

                break;

            case R.id.btnDelete:
                //Delete Students data
                deleStudent();
                viewStudents();
                break;
        }

    }

    public void addStudents() {

        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String email = etEmail.getText().toString();
        String faculty = etFaculty.getText().toString();

        //Set students data to Students Constructor to store database
        Students students = new Students(name, phone, email, faculty);

        //Set student data to DbHelper insertStudent method to insert student data
        long inserted = dbHelper.insertStudent(students);

        //if the data is not inserted then return it a long value -1
        if (inserted == -1) {
            Toast.makeText(getApplicationContext(), "Students not inserted", Toast.LENGTH_LONG).show();
        } else {

            //if insertion is successfull to database
            Toast.makeText(getApplicationContext(), "Students Successfully inserted.", Toast.LENGTH_LONG).show();
        }

    }

    public void viewStudents() {

        //Here get all the students data by calling DbHelper getAllStudents method
        final Cursor result = dbHelper.getAllStudents();

        // if there have no students then result.getCount() return 0
        if (result.getCount() == 0) {

            //Show message as alert Dialog
//            showMessage("Error", "No Data Found");

            Toast.makeText(getApplicationContext(), "No Sudent found", Toast.LENGTH_LONG).show();

            return;

        } else {

            // Manipulate ListView
            studentsList = (ListView) findViewById(R.id.studentList);

            //Custom ListAdapter that create only for show all students data
            studentsListAdapter = new StudentsListAdapter(this, studentListsList);

            //Setting ListView's adapter to the custom list adapter we've created
            studentsList.setAdapter(studentsListAdapter);

            //if more result have occure
            while (result.moveToNext()) {

                StudentLists lists = new StudentLists();

                lists.setName(result.getString(1));
                lists.setPhone(result.getString(2));
                lists.setEmail(result.getString(3));
                lists.setFaculty(result.getString(4));

                //Add all students data to StudentsLists ArrayList from database
                studentListsList.add(lists);


            }

            //set onitemclicklistener to set listview item data to update or delete
            studentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(getApplicationContext(),"Clicked"+item,Toast.LENGTH_LONG).show();


                    //Get the actual position where is clicked
                    if (result.moveToPosition(position)) {

                        //Set student specific data to change or delete
                        etName.setText(result.getString(1));
                        etPhone.setText(result.getString(2));
                        etEmail.setText(result.getString(3));
                        etFaculty.setText(result.getString(4));

                        currentIndex = position;

                    }

                }
            });


        }

    }

    public void updateStudent() {

        Cursor resultForUpdate = dbHelper.getAllStudents();

        if (resultForUpdate.moveToPosition(currentIndex)) {

            String id = resultForUpdate.getString(0);

            String name = etName.getText().toString();
            String phone = etPhone.getText().toString();
            String email = etEmail.getText().toString();
            String faculty = etFaculty.getText().toString();

            if (dbHelper.updateStudent(id, name, phone, email, faculty)) {

                Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_LONG).show();

            }


        }

    }

    public void deleStudent() {

        Cursor resultForDelete = dbHelper.getAllStudents();

        if (resultForDelete.moveToPosition(currentIndex)) {

            String id = resultForDelete.getString(0);

            if (dbHelper.deleteStudent(id)) {

                Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(getApplicationContext(), "Delete Failed", Toast.LENGTH_LONG).show();
            }
        }


    }

//    public void showMessage(String title, String message) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.show();
//
//    }
}
