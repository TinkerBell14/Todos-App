package com.example.sqllitedemo;

import java.util.ArrayList;

/**
 * Created by anurashukla on 11/19/2015.
 */
public class Status {
    public int id;
    public String name;

    public Status(int id, String name){
        this.id = id;
        this.name = name;
    }
    public String toString(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public static ArrayList<Status> getAll(){
        ArrayList<Status> all = new ArrayList<Status>();

        all.add(new Status(1,"Done"));
        all.add(new Status(2,"To_do"));

        return all;
    }
}
