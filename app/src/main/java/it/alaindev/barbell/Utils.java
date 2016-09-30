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

    public static Integer getIfValidInt(String s) {
        Integer i;
        try {
            i = Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return null;
        } catch(NullPointerException e) {
            return null;
        }

        return i;
    }
}
