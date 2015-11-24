package com.example.sqllitedemo;

import java.util.ArrayList;

/**
 * Created by anurashukla on 11/19/2015.
 */
public class Priority {
    public int id;
    public String name;

    public Priority(int id, String name){
        this.id = id;
        this.name = name;
    }
    public String toString(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public static ArrayList<Priority> getAll(){
        ArrayList<Priority> all = new ArrayList<Priority>();

        all.add(new Priority(1,"High"));
        all.add(new Priority(2,"Medium"));
        all.add(new Priority(3,"Low"));

        return all;
    }
}