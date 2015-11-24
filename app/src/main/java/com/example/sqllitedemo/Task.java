package com.example.sqllitedemo;


import java.util.Date;

public class Task {
    private int _id;
    private String taskname;
    private Date dueDate;
    private int priority;
    private int status;

    public Task(){}

    public Task(String taskname, Date dueDate, int priority, int status){

        this.taskname = taskname;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    public int get_id(){ return _id;}
    public void set_id(int id){
        this._id = id;
    }

    public String gettaskname(){return taskname;}
    public void set_taskname(String taskname){
        this.taskname = taskname;
    }

    public Date getDueDate(){
        return dueDate;
    }
    public void set_dueDate(Date dueDate){
        this.dueDate = dueDate;
    }

    public void set_Priority(int priority){ this.priority = priority ;}
    public int getPriority() { return priority;}

    public void set_Status(int status){ this.status = status ;}
    public int getStatus() { return status;}



}
