package com.example.sqllitedemo;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.text.method.DateTimeKeyListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Date;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 15;
    private static final String DATABASE_NAME = "todotasks.db";
    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TASKNAME = "taskname";
    public static final String COLUMN_DUEDATE = "dueDate";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String COLUMN_STATUS = "status";

    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "Create table " + TABLE_TASKS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DUEDATE + " Text," +
                COLUMN_TASKNAME + " Text ," +
                COLUMN_PRIORITY + " Text ," +
                COLUMN_STATUS + " Text " +
                ")";

        db.execSQL(query);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists " + TABLE_TASKS);
        onCreate(db);
    }

    // Add New row to add task
    public void addTask(Task task){

        SQLiteDatabase db = getWritableDatabase();


       //SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASKNAME,task.gettaskname());
        values.put(COLUMN_DUEDATE,dateFormat.format(task.getDueDate()));
        values.put(COLUMN_PRIORITY,Integer.toString(task.getPriority()));
        values.put(COLUMN_STATUS,Integer.toString(task.getStatus()));


        long val = db.insertOrThrow(TABLE_TASKS, null, values);
        db.close();
    }


    // Get tasks from the table
    public ArrayList<Task> getTasks(){
        //DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SQLiteDatabase db = getWritableDatabase();

        Cursor cur = db.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='" + TABLE_TASKS + "'",null);
        if(cur != null){
            int tb = cur.getCount();
            String s = "dfd";
        }

        String query = "Select * from " + TABLE_TASKS + " where 1";
        ArrayList<Task> tasks = new ArrayList<>();
        Date startDate;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        if(c.moveToFirst()) do {
            Task task = new Task();
            try {
            task.set_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
            task.set_taskname(c.getString(c.getColumnIndex(COLUMN_TASKNAME)));
            task.set_Priority(Integer.parseInt(c.getString(c.getColumnIndex(COLUMN_PRIORITY))));
            task.set_Status(Integer.parseInt(c.getString(c.getColumnIndex(COLUMN_STATUS))));


            String startDateString = c.getString(c.getColumnIndex(COLUMN_DUEDATE));
            startDate = dateFormat.parse(startDateString);
            task.set_dueDate(startDate);
            } catch(ParseException e){

            }

            tasks.add(task);
        } while (c.moveToNext());

        db.close();
        return tasks;

    }

    public Task getTask(int id){
        //DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * from " + TABLE_TASKS + " where id=" + id;
        Date startDate;

        Cursor c = db.rawQuery(query, null);
        if(c != null)
            c.moveToFirst();
       // c.moveToFirst();
        Task task = new Task();
        try {


            task.set_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
            task.set_taskname(c.getString(c.getColumnIndex(COLUMN_TASKNAME)));
            task.set_Priority(Integer.parseInt(c.getString(c.getColumnIndex(COLUMN_PRIORITY))));
            task.set_Status(Integer.parseInt(c.getString(c.getColumnIndex(COLUMN_STATUS))));


            String startDateString = c.getString(c.getColumnIndex(COLUMN_DUEDATE));
            startDate = dateFormat.parse(startDateString);
            task.set_dueDate(startDate);
        }catch(ParseException e){

        }
            db.close();





        return task;

    }

    public void updateTask(Task task){

        SQLiteDatabase db = getWritableDatabase();
        String query = "Update " + TABLE_TASKS +
                " set " + COLUMN_TASKNAME +"='" + task.gettaskname() +
                "',"+ COLUMN_DUEDATE +"='" + dateFormat.format(task.getDueDate()) +
                "',"+ COLUMN_PRIORITY +"='" + Integer.toString(task.getPriority()) +
                "',"+ COLUMN_STATUS +"='" + Integer.toString(task.getStatus()) +
                "' where id=" + task.get_id();
        db.execSQL(query);

        db.close();


    }

    public void deleteTask(int id){

        SQLiteDatabase db = getWritableDatabase();
        String query = "Delete from " + TABLE_TASKS + " where " + COLUMN_ID + "="  + id;
        db.execSQL(query);

        db.close();

    }

}
