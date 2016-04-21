package mainmenu.game.model;

import java.util.ArrayList;
import java.util.List;

public class ModelHistory {

    private String levelname;
    private List<double[]> jumpHistory;

    public ModelHistory(String level_name) {
        levelname = level_name;
        jumpHistory = new ArrayList<>();
    }

    public String getLevelName() {
        return levelname;
    }

    public void addToJumpHistory(double[] new_data) {
        jumpHistory.add(new_data);
    }

    public List<double[]> getJumpHistory() {
        return jumpHistory;
    }

}
