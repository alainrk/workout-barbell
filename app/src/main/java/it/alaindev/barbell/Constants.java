package it.alaindev.barbell;

import android.text.InputType;

/**
 * Created by narko on 26/09/16.
 */

public class Constants {

    public static final String PREF_FILE = "barbellsharedpreference";
    public static final String PREF_GOOGLEACCOUNT_ISLOGGED = "PREF_GOOGLEACCOUNT_ISLOGGED";
    public static final String PREF_GOOGLEACCOUNT_NAME = "PREF_GOOGLEACCOUNT_NAME";
    public static final String PREF_GOOGLEACCOUNT_EMAIL = "PREF_GOOGLEACCOUNT_EMAIL";
    public static final String PREF_GOOGLEACCOUNT_ID = "PREF_GOOGLEACCOUNT_ID";
    public static final String PREF_GOOGLEACCOUNT_PHOTOURI = "PREF_GOOGLEACCOUNT_PHOTOURI";
    public static final String SIGNIN_LOGOUT = "SIGNIN_LOGOUT";

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
            case Constants.USER_PARAM_NAME:
                return USER_PARAM_NAME_DESC;
            case Constants.USER_PARAM_AGE:
                return USER_PARAM_AGE_DESC;
            case Constants.USER_PARAM_WEIGHT:
                return USER_PARAM_WEIGHT_DESC;
            case Constants.USER_PARAM_HEIGHT:
                return USER_PARAM_HEIGHT_DESC;
            case Constants.USER_PARAM_ACTIV:
                return USER_PARAM_ACTIV_DESC;
            case Constants.USER_PARAM_NUMWOS:
                return USER_PARAM_NUMWOS_DESC;
            case Constants.USER_PARAM_MINUTES:
                return USER_PARAM_MINUTES_DESC;
            case Constants.USER_PARAM_HARDWO:
                return USER_PARAM_HARDWO_DESC;
        }
        return "";
    }

    public static String getDescriptionParamsDesc (String desc) {
        switch (desc) {
            case Constants.USER_PARAM_NAME:
                return "";
            case Constants.USER_PARAM_AGE:
                return "";
            case Constants.USER_PARAM_WEIGHT:
                return "";
            case Constants.USER_PARAM_HEIGHT:
                return "";
            case Constants.USER_PARAM_ACTIV:
                return "";
            case Constants.USER_PARAM_NUMWOS:
                return "";
            case Constants.USER_PARAM_MINUTES:
                return "";
            case Constants.USER_PARAM_HARDWO:
                return "";
        }
        return "";
    }

    public static int getInputTypeParamsDesc (String desc) {
        switch (desc) {
            case Constants.USER_PARAM_NAME:
                return InputType.TYPE_CLASS_TEXT;
            case Constants.USER_PARAM_AGE:
                return InputType.TYPE_CLASS_TEXT;
            case Constants.USER_PARAM_WEIGHT:
                return InputType.TYPE_CLASS_TEXT;
            case Constants.USER_PARAM_HEIGHT:
                return InputType.TYPE_CLASS_TEXT;
            case Constants.USER_PARAM_ACTIV:
                return InputType.TYPE_CLASS_TEXT;
            case Constants.USER_PARAM_NUMWOS:
                return InputType.TYPE_CLASS_TEXT;
            case Constants.USER_PARAM_MINUTES:
                return InputType.TYPE_CLASS_TEXT;
            case Constants.USER_PARAM_HARDWO:
                return InputType.TYPE_CLASS_TEXT;
        }
        return InputType.TYPE_CLASS_TEXT;
    }
}
