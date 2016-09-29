package it.alaindev.barbell;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by narko on 27/09/16.
 */

public class User {
    private String uid;
    private String name;
    private int age;
    private int weight;
    private int height;
    private int activity; // level of activity (1/4)
    private int numwos; // number of workouts in week
    private int minutes; // minutes in wo


    public User() {
    }

    public User(String uid, String name, int age, int weight, int height, int activity, int numwos, int minutes) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.activity = activity;
        this.numwos = numwos;
        this.minutes = minutes;
    }

    public String toString() {
        return "User "+uid+", "+name+". Age: "+age+", Weight: "+weight+", Height: "+height+", Activity: "+activity+", NumWOs: "+numwos+", Minutes: "+minutes;
    }

    public ArrayList<Map.Entry<String, String>> toMapEntryList() {
        ArrayList<Map.Entry<String, String>> al = new ArrayList<>();
        al.add(Utils.createMapEntry("uid", uid));
        al.add(Utils.createMapEntry("name", name));
        al.add(Utils.createMapEntry("age", Integer.toString(age)));

        return al;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }


    public int getActivity() {
        return activity;
    }

    public int getNumwos() {
        return numwos;
    }

    public int getMinutes() {
        return minutes;
    }
}
