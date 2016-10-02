package it.alaindev.barbell;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by narko on 02/10/16.
 */

public class SharedUtils {

    private static SharedPreferences getSharedPref (Context context) {
        return context.getSharedPreferences(Constants.PREF_FILE, Context.MODE_PRIVATE);
    }

    public static String getFbUserIdx (Context context) {
        return getSharedPref(context).getString(Constants.FIREBASE_INDEX_USER, "");
    }
}
