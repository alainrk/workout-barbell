package it.alaindev.barbell;

import android.text.InputType;

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
    private int hardwo; // intensity in wo (1/4)


    public User() {
    }

    public User(String uid, String name, int age, int weight, int height, int activity, int numwos, int minutes, int hardwo) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.activity = activity;
        this.numwos = numwos;
        this.minutes = minutes;
        this.hardwo = hardwo;
    }

    public String toString() {
        return "User "+uid+", "+name+". Age: "+age+", Weight: "+weight+", Height: "+height+", Activity: "+activity+", NumWOs: "+numwos+", Minutes: "+minutes+", Intensity: "+hardwo;
    }

    public ArrayList<Map.Entry<String, String>> toMapEntryList() {
        ArrayList<Map.Entry<String, String>> al = new ArrayList<>();
        //al.add(Utils.createMapEntry("uid", uid));
        al.add(Utils.createMapEntry("name", name));
        al.add(Utils.createMapEntry("age", Integer.toString(age)));
        al.add(Utils.createMapEntry("weight", Integer.toString(weight)));
        al.add(Utils.createMapEntry("height", Integer.toString(height)));
        al.add(Utils.createMapEntry("activity", Integer.toString(activity)));
        al.add(Utils.createMapEntry("numwos", Integer.toString(numwos)));
        al.add(Utils.createMapEntry("minutes", Integer.toString(minutes)));
        al.add(Utils.createMapEntry("hardwo", Integer.toString(hardwo)));

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

    public int getHardwo() {
        return hardwo;
    }

    // Static stuff related to user

    public static final String USER_PARAM_NAME_DESC = "Name";
    public static final String USER_PARAM_AGE_DESC = "Age";
    public static final String USER_PARAM_WEIGHT_DESC = "Weight";
    public static final String USER_PARAM_HEIGHT_DESC = "Height";
    public static final String USER_PARAM_ACTIV_DESC = "Daily activity";
    public static final String USER_PARAM_NUMWOS_DESC = "Workouts per week";
    public static final String USER_PARAM_MINUTES_DESC = "Workout time duration";
    public static final String USER_PARAM_HARDWO_DESC = "Workout intensity";

    public static final String USER_PARAM_NAME = "name";
    public static final String USER_PARAM_AGE = "age";
    public static final String USER_PARAM_WEIGHT = "weight";
    public static final String USER_PARAM_HEIGHT = "height";
    public static final String USER_PARAM_ACTIV = "activity";
    public static final String USER_PARAM_NUMWOS = "numwos";
    public static final String USER_PARAM_MINUTES = "minutes";
    public static final String USER_PARAM_HARDWO = "hardwo";

    public static String convertParamsDesc (String desc) {
        switch (desc) {
            case USER_PARAM_NAME:
                return USER_PARAM_NAME_DESC;
            case USER_PARAM_AGE:
                return USER_PARAM_AGE_DESC;
            case USER_PARAM_WEIGHT:
                return USER_PARAM_WEIGHT_DESC;
            case USER_PARAM_HEIGHT:
                return USER_PARAM_HEIGHT_DESC;
            case USER_PARAM_ACTIV:
                return USER_PARAM_ACTIV_DESC;
            case USER_PARAM_NUMWOS:
                return USER_PARAM_NUMWOS_DESC;
            case USER_PARAM_MINUTES:
                return USER_PARAM_MINUTES_DESC;
            case USER_PARAM_HARDWO:
                return USER_PARAM_HARDWO_DESC;
        }
        return "";
    }

    public static String getDescriptionParamsDesc (String desc) {
        switch (desc) {
            case USER_PARAM_NAME:
                return "Insert your name";
            case USER_PARAM_AGE:
                return "Insert your age";
            case USER_PARAM_WEIGHT:
                return "Insert your weight (kg)";
            case USER_PARAM_HEIGHT:
                return "Insert your height (cm)";
            case USER_PARAM_ACTIV:
                return "Daily activity: 1 = Sedentary, 4 = Hard effort jobs";
            case USER_PARAM_NUMWOS:
                return "Number of workouts per week";
            case USER_PARAM_MINUTES:
                return "Average workout duration";
            case USER_PARAM_HARDWO:
                return "Workout intensity: 1 = Light, 4 = Very hard";
        }
        return "";
    }

    public static int getInputTypeParamsDesc (String desc) {
        switch (desc) {
            case USER_PARAM_NAME:
                return InputType.TYPE_CLASS_TEXT;
            case USER_PARAM_AGE:
                return InputType.TYPE_CLASS_NUMBER;
            case USER_PARAM_WEIGHT:
                return InputType.TYPE_CLASS_NUMBER;
            case USER_PARAM_HEIGHT:
                return InputType.TYPE_CLASS_NUMBER;
            case USER_PARAM_ACTIV:
                return InputType.TYPE_CLASS_NUMBER;
            case USER_PARAM_NUMWOS:
                return InputType.TYPE_CLASS_NUMBER;
            case USER_PARAM_MINUTES:
                return InputType.TYPE_CLASS_NUMBER;
            case USER_PARAM_HARDWO:
                return InputType.TYPE_CLASS_NUMBER;
        }
        return InputType.TYPE_CLASS_TEXT;
    }
}
