package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Score implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Integer> info;
    private Integer lastTry;
    private Integer currentTry;

    public Score(ArrayList<Integer> info, Integer lastTry) {
        this.info = info;
        this.lastTry = lastTry;
        currentTry = 0;
    }

    public ArrayList<Integer> getInfo() {
        return info;
    }

    public void addScore(int points) {
        currentTry += points;
    }

    public void updateScore() {
        lastTry = currentTry;
        currentTry = 0;

        info.add(lastTry);
        Collections.sort(info);
        if (info.size() > 3) {
            info.remove(0);
        }
    }

    public Integer getLastTry() {
        return lastTry;
    }

    public Integer getCurrentTry() {
        return currentTry;
    }
}
