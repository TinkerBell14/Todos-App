package com.example.sqllitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Add extends AppCompatActivity {

    EditText editTaskName;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


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
            dbHandler = new MyDBHandler(this, null, null, 7);
            String taskname = editTaskName.getText().toString();

            if(taskname.length() > 0) {
                Task task = new Task(taskname);
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
