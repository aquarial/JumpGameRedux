package mainmenu.game.model;

import java.util.ArrayList;
import java.util.List;

public class ModelHistory {

    private String levelname;
    private List<double[]> jumpHistory;
    private double startTime;
    private double finishTime;

    public ModelHistory(String level_name) {
        levelname = level_name;
        jumpHistory = new ArrayList<>();
        startTime = System.currentTimeMillis();
        finishTime = -1;
    }

    public String getLevelName() {
        return levelname;
    }

    public void setFinishTime() {
        finishTime = System.currentTimeMillis();
    }

    public double getTimeFromStart() {
        return (System.currentTimeMillis() - startTime) / 1000.0;
    }

    public double getTimeOfFinish() {
        return (finishTime - startTime) / 1000.0;
    }

    public void addToJumpHistory(double[] new_data) {
        jumpHistory.add(new_data);
    }

    public int getNumberOfJumpsSoFar() {
        return jumpHistory.size();
    }

    public List<double[]> getJumpHistory() {
        return jumpHistory;
    }

}
