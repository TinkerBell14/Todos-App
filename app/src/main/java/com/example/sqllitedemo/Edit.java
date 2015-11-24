package com.example.sqllitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Edit extends AppCompatActivity {

    EditText editTaskName;
    DatePicker datePicker;
    TextView taskId;
    MyDBHandler dbHandler;
    Spinner spinner;
    Spinner status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        dbHandler = new MyDBHandler(this, null, null,15);

        int id = getIntent().getIntExtra("id", 0);

        Task task = dbHandler.getTask(id);
        editTaskName = (EditText)findViewById(R.id.editTaskName);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        taskId = (TextView) findViewById(R.id.taskId);
        spinner = (Spinner)findViewById(R.id.priority);
        status = (Spinner)findViewById(R.id.status);

        Date due = task.getDueDate();
        Calendar cal = Calendar.getInstance();// get calendar instance
        cal.setTime(due);//set the calendar dat

        taskId.setText(String.valueOf(task.get_id()));
        editTaskName.setText(task.gettaskname());

        datePicker.setMinDate(cal.getTimeInMillis() - 1000);
        datePicker.init(cal.get(cal.YEAR), cal.get(cal.MONTH), cal.get(cal.DAY_OF_MONTH), null);


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

        spinner.setSelection(task.getPriority() - 1);
        status.setSelection(task.getStatus() - 1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_editButton){
            editTaskName =  (EditText) findViewById(R.id.editTaskName);
            taskId = (TextView) findViewById(R.id.taskId);
            datePicker = (DatePicker)findViewById(R.id.datePicker);
            spinner = (Spinner)findViewById(R.id.priority);
            status = (Spinner)findViewById(R.id.status);
            //dbHandler = new MyDBHandler(this, null, null, 7);
            Task task = new Task();
            task.set_id(Integer.parseInt(taskId.getText().toString()));
            task.set_taskname(editTaskName.getText().toString());

            Date date = Utility.getDateFromDatePicker(datePicker);
            task.set_dueDate(date);

            Priority priority = (Priority)spinner.getSelectedItem();
            task.set_Priority(priority.id);

            Status statusObj = (Status)status.getSelectedItem();
            task.set_Status(statusObj.id);


            dbHandler.updateTask(task);

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);


        }

        if(id == R.id.action_deleteButton){

            taskId = (TextView) findViewById(R.id.taskId);
           // dbHandler = new MyDBHandler(this, null, null, 7);
            dbHandler.deleteTask(Integer.parseInt(taskId.getText().toString()));
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        if(id == R.id.action_cancel){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }

        if(id == R.id.action_share){
            String Id = taskId.getText().toString();
            Task task = dbHandler.getTask(Integer.parseInt(Id));

            String taskDetails = "You have to " + task.gettaskname() + " on " +  task.getDueDate() +
                    ". Its a " + task.getPriority() + " priority task and the status is "  + task.getStatus() + ".";

            Intent intent = Utility.Share(SocialApps.Apps.WHATSAPP, taskDetails);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public Date getDateFromDatePicket(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
