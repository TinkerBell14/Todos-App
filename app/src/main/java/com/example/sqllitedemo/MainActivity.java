package com.example.sqllitedemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import java.util.ArrayList;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    private MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         dbHandler = new MyDBHandler(this, null, null, 7);
        ArrayList<Task> tasks = dbHandler.getTasks();
        Task[] arrTask = tasks.toArray(new Task[tasks.size()]);

        ListView taskList = (ListView)findViewById(R.id.taskList);
        ListAdapter adapter = new CustomAdapter(this,arrTask);
        taskList.setAdapter(adapter);

      /*  taskList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Task task = (Task) parent.getItemAtPosition(position);
                        viewTask(task);

                    }

                }
        );
*/
        taskList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Task task = (Task) parent.getItemAtPosition(position);
                        //Toast.makeText(MainActivity.this, task.gettaskname(),Toast.LENGTH_LONG).show();
                        viewTask(task);
                    }
                }


        );

        taskList.setOnItemLongClickListener(
                new ListView.OnItemLongClickListener(){
                    public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {

                        final Task task = (Task) parent.getItemAtPosition(position);
                        AlertDialog.Builder alert = new AlertDialog.Builder(
                                context);
                        alert.setTitle("Confirm");
                        alert.setMessage("Are you sure to delete the Task?");
                        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //do your work here

                                int taskId = task.get_id();
                                dbHandler.deleteTask(taskId);
                                finish();
                                startActivity(getIntent());

                                //dialog.dismiss();

                            }
                        });
                        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });

                        alert.show();

                        return true;
                    }

                }

        );
    }

    public void viewTask(Task task){
        Intent i = new Intent(this, Edit.class);
        i.putExtra("id",task.get_id());
        startActivity(i);
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

        if(id == R.id.action_add){
            Intent i = new Intent(this, Add.class);
            startActivity(i);
        }



        return super.onOptionsItemSelected(item);
    }


}
