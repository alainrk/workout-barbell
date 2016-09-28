package it.alaindev.barbell;

/**
 * Created by narko on 27/09/16.
 */

public class User {
    private String uid;
    private String name;
    private int age;
    private int weight;
    private int height;

    public User() {
    }

    public User(String uid, String name, int age, int weight, int height) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
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
}
