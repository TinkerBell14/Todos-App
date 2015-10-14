package com.example.sqllitedemo;


public class Task {
    private int _id;
    private String taskname;

    public Task(){}

    public Task(String taskname){
        this.taskname = taskname;
    }

    public int get_id(){
        return _id;
    }

    public void set_id(int id){
        this._id = id;
    }
    public String gettaskname(){
        return taskname;
    }
    public void set_taskname(String taskname){
        this.taskname = taskname;
    }


}
