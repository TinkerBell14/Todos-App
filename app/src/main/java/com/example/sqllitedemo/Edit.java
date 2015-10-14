package com.example.sqllitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends AppCompatActivity {

    EditText editTaskName;
    TextView taskId;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        int id = getIntent().getIntExtra("id", 0);
        dbHandler = new MyDBHandler(this, null, null, 7);
        Task task = dbHandler.getTask(id);

        editTaskName = (EditText)findViewById(R.id.editTaskName);
        taskId = (TextView) findViewById(R.id.taskId);

        editTaskName.setText(task.gettaskname());
        taskId.setText(String.valueOf(task.get_id()));

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

            dbHandler = new MyDBHandler(this, null, null, 7);
            Task task = new Task();
            task.set_id(Integer.parseInt(taskId.getText().toString()));
            task.set_taskname(editTaskName.getText().toString());

            dbHandler.updateTask(task);

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);


        }

        if(id == R.id.action_deleteButton){

            taskId = (TextView) findViewById(R.id.taskId);
            dbHandler = new MyDBHandler(this, null, null, 7);
            dbHandler.deleteTask(Integer.parseInt(taskId.getText().toString()));
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        if(id == R.id.action_cancel){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
