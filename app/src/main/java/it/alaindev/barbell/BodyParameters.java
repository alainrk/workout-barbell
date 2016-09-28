package it.alaindev.barbell;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by narko on 27/09/16.
 */

public class BodyParameters {

    private int weight; // kg
    private int height; // cm
    private int age;    // years

    public BodyParameters () {}

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("weight", weight);
        result.put("height", height);
        result.put("age", age);

        return result;
    }
}
