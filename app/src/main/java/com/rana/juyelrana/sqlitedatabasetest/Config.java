package com.rana.juyelrana.sqlitedatabasetest;

/**
 * Created by JuyelRana on 5/22/2016.
 */
public class Config {

    //Database name
    public static final String DB_NAME = "students";

    //Database version
    public static final int VERSION = 1;

    //Database table name
    public static final String TABLE_NAME = "register";

    // all the column name
    public static final String ID = "st_id";
    public static final String NAME = "st_name";
    public static final String PHONE = "st_phone";
    public static final String EMAIL = "st_email";
    public static final String FACULTY = "st_faculty";

    public static final String TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + PHONE + " TEXT, " + EMAIL + " TEXT, " + FACULTY + " TEXT)";
    //public static final String SELECT_SQL = "select * from "+TABLE_NAME;
    public static final String SELECT_SQL = "SELECT * FROM "+TABLE_NAME+" ORDER BY "+ID+" DESC";


}
