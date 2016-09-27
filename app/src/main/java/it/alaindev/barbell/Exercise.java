package it.alaindev.barbell;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by narko on 26/09/16.
 */

public class Exercise {
    private int type;
    private String name;
    private boolean biglift;

    public Exercise(int type, boolean biglift, String name) {
        this.type = type;
        this.biglift = biglift;
        this.name = name;
    }

    public Exercise() {}

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBiglift() {
        return biglift;
    }

    public void setBiglift(boolean biglift) {
        this.biglift = biglift;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("type", type);
        result.put("biglift", biglift);
        result.put("name", name);

        return result;
    }
}
