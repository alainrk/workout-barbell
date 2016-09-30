package it.alaindev.barbell;

import java.util.Map;

/**
 * Created by narko on 29/09/16.
 */

public class Utils {

    public static Map.Entry<String, String> createMapEntry (final String k, final String v) {
        return new Map.Entry<String, String>() {
            @Override
            public String getKey() {
                return k;
            }

            @Override
            public String getValue() {
                return v;
            }

            @Override
            public String setValue(String object) {
                // TODO What do i have to do here?
                return null;
            }
        };
    }

    public static boolean isValidAge(String s) {
        int age;
        try {
            age = Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }

        return (age < 120 && age > 0);
    }
}
