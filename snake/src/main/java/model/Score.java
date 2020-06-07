package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Score implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Integer> info;
    private Integer lastTry;

    public Score(ArrayList<Integer> info, Integer lastTry) {
        this.info = info;
        this.lastTry = lastTry;
    }

    public ArrayList<Integer> getInfo() {
        return info;
    }

    public void addScore(Integer i) {
        lastTry = i;
        info.add(i);
        Collections.sort(info);

        if (info.size() > 3) {
            info.remove(0);
        }
    }

    public Integer getLastTry() {
        return lastTry;
    }
}
