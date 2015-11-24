package com.example.sqllitedemo;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import android.app.DatePickerDialog;
import android.app.Dialog;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.app.Fragment;
import java.util.Date;

public class Add extends AppCompatActivity {

    EditText editTaskName;
    MyDBHandler dbHandler;
    DatePicker datePicker;
    Spinner spinner;
    Spinner status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        /*Fill Priority Spinner*/
        spinner = (Spinner)findViewById(R.id.priority);
        ArrayList<Priority> priority = Priority.getAll();
        ArrayAdapter<Priority> priorityAdapter = new ArrayAdapter<Priority>(this,R.layout.support_simple_spinner_dropdown_item, priority);
        spinner.setAdapter(priorityAdapter);

        /*Fill Status Spinner*/
        status = (Spinner)findViewById(R.id.status);
        ArrayList<Status> statusList = Status.getAll();
        ArrayAdapter<Status> statusAdapter = new ArrayAdapter<Status>(this,R.layout.support_simple_spinner_dropdown_item, statusList);
        status.setAdapter(statusAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_save){
            editTaskName =  (EditText) findViewById(R.id.editTaskName);
            datePicker = (DatePicker)findViewById(R.id.datePicker);
            spinner = (Spinner)findViewById(R.id.priority);
            status = (Spinner)findViewById(R.id.status);

            dbHandler = new MyDBHandler(this, null, null, 15);
            String taskname = editTaskName.getText().toString();
            Date date = Utility.getDateFromDatePicker(datePicker);

            if(taskname.length() > 0) {
                Priority prio = (Priority)spinner.getSelectedItem();
                Status stat = (Status)status.getSelectedItem();
                Task task = new Task(taskname,date, prio.getId(),stat.getId());
                dbHandler.addTask(task);

                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }

        }

        if(id == R.id.action_cancel){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }


   
}
