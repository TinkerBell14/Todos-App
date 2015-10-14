package com.example.sqllitedemo;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;
import android.util.Log;

import static com.example.sqllitedemo.R.id.action_editButton;

public class ViewTask extends AppCompatActivity {

    TextView taskId;
    MyDBHandler dbHandler;

    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        int id = getIntent().getIntExtra("id",-1);
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 7);
        Task task = dbHandler.getTask(id);
        TextView taskName = (TextView)findViewById(R.id.taskName);
        TextView taskId = (TextView)findViewById(R.id.taskId);

        taskName.setText(task.gettaskname());
        taskId.setText(String.valueOf(task.get_id()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Log.v(TAG, "getItemId:" + id);
        Log.v(TAG, "MenuItem:" + R.id.action_editButton);

        if(id == R.id.action_editButton){
            TextView taskId = (TextView)findViewById(R.id.taskId);
            int Id = Integer.parseInt(taskId.getText().toString());

            Intent i = new Intent(this, Edit.class);
            i.putExtra("id", Id);
            startActivity(i);
        }

        if(id == R.id.action_delete){
            TextView taskId = (TextView)findViewById(R.id.taskId);
            int Id = Integer.parseInt(taskId.getText().toString());

            dbHandler = new MyDBHandler(this, null, null, 7);
            dbHandler.deleteTask(Id);

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }




        return super.onOptionsItemSelected(item);
    }
}
