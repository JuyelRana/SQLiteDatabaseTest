package com.rana.juyelrana.sqlitedatabasetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JuyelRana on 5/22/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {

        super(context, Config.DB_NAME, null, Config.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create table
        db.execSQL(Config.TABLE_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // backup
        // alter tables
        // insert

    }

    public long insertStudent(Students students) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        //Store student data to database
        cv.put(Config.NAME, students.getName());
        cv.put(Config.PHONE, students.getPhone());
        cv.put(Config.EMAIL, students.getEmail());
        cv.put(Config.FACULTY, students.getFaculty());

        long inserted = database.insert(Config.TABLE_NAME, "", cv);
        database.close();
        return inserted;
    }

    public Cursor getAllStudents() {

        SQLiteDatabase database = this.getWritableDatabase();
        //Get all students data
        Cursor result = database.rawQuery(Config.SELECT_SQL, null);

        return result;
    }

    public boolean updateStudent(String id,String name, String phone, String email,String faculty){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(Config.NAME,name);
        cv.put(Config.PHONE,phone);
        cv.put(Config.EMAIL,email);
        cv.put(Config.FACULTY,faculty);

        database.update(Config.TABLE_NAME,cv,"st_id = ?",new String[]{id});

        return true;

    }

    public boolean deleteStudent(String id){

        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(Config.TABLE_NAME,Config.ID+"=?",new String[]{id});

        return true;
    }


}