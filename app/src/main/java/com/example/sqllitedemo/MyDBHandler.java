package com.example.sqllitedemo;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "todotasks.db";
    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TASKNAME = "taskname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "Create table " + TABLE_TASKS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASKNAME + " Text " +
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
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASKNAME,task.gettaskname());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TASKS, null, values);
        db.close();
    }


    // Get tasks from the table
    public ArrayList<Task> getTasks(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * from " + TABLE_TASKS + " where 1";
        ArrayList<Task> tasks = new ArrayList<>();

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        if(c.moveToFirst()){
            do{
                Task task = new Task();
                task.set_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
                task.set_taskname(c.getString(c.getColumnIndex(COLUMN_TASKNAME)));
                tasks.add(task);
            }while(c.moveToNext());
        }

        db.close();
        return tasks;

    }

    public Task getTask(int id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * from " + TABLE_TASKS + " where id=" + id;


        Cursor c = db.rawQuery(query, null);
        if(c != null)
            c.moveToFirst();
       // c.moveToFirst();

        Task task = new Task();
        task.set_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
        task.set_taskname(c.getString(c.getColumnIndex(COLUMN_TASKNAME)));


        db.close();
        return task;

    }

    public void updateTask(Task task){

        SQLiteDatabase db = getWritableDatabase();
        String query = "Update " + TABLE_TASKS + " set taskname='" + task.gettaskname() + "' where id=" + task.get_id();
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
