package com.example.sqllitedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter {
    public CustomAdapter(Context context, Task[] tasks) {
        super(context, R.layout.custom_row,tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        View customView = inflator.inflate(R.layout.custom_row, parent, false);

        Task singleTask = (Task) getItem(position);
        TextView row_Text = (TextView) customView.findViewById(R.id.taskName);
        TextView row_Id = (TextView) customView.findViewById(R.id.taskId);
        //ImageView row_Image = (ImageView) customView.findViewById(R.id.row_Image);

        row_Text.setText(singleTask.gettaskname());
        row_Id.setText(String.valueOf(singleTask.get_id()));
        //row_Image.setImageResource(singleTask.getImage());

        return customView;

    }
}