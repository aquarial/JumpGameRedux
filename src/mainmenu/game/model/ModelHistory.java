package mainmenu.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ModelHistory knows: <br>
 * 1 how many jumps since the begining <br>
 * 2 how long it's been since the begining <br>
 * 3 if the player finished the level <br>
 * 4 how long it took <br>
 * 5 how many jumps they took <br>
 * 
 */
public class ModelHistory {

    /** Which level the history is on */
    private String levelname;
    /** A list of <timeofjump, angle, power> elements */
    private List<double[]> jumpHistory;
    /** When the level started */
    private long startTime;
    /** When the level finishd or <code>-1<code> */
    private long finishTime;

    /**
     * Starts the record of hisrtory.
     * 
     * @param level_name
     */
    public ModelHistory(String level_name) {
        levelname = level_name;
        jumpHistory = new ArrayList<>();
        startTime = System.currentTimeMillis();
        finishTime = -1;
    }

    /**
     * @return Which level this history takes place on
     */
    public String getLevelName() {
        return levelname;
    }

    /**
     * For use by MainModel while the level is being played.
     * 
     * @return Difference (in double seconds) from the start of the level
     * @see #getTimeOfFinish
     */
    public double getTimeFromStart() {
        return (System.currentTimeMillis() - startTime) / 1000.0;
    }

    /**
     * If the player did not finish, this method returns -1;
     * 
     * @return Difference (in double seconds) from the start to the finish of
     *         the level
     */
    public double getTimeOfFinish() {
        if (getPlayerFinished()) {
            return (finishTime - startTime) / 1000.0;
        } else {
            return -1;
        }
    }

    /**
     * For use by MainModel while the level is being played.
     * 
     * @return Number of jumps taken so far
     */
    public int getNumberOfJumpsSoFar() {
        return jumpHistory.size();
    }

    /**
     * Get the list of data directly
     * 
     * @return Raw jump history data
     */
    public List<double[]> getJumpHistory() {
        return jumpHistory;
    }

    /**
     * @return true once the player has finished.
     */
    public boolean getPlayerFinished() {
        return finishTime > -1;
    }

    /**
     * Internally records the time the player finished at.
     */
    public void setPlayerJustFinished() {
        finishTime = System.currentTimeMillis();
    }

    /**
     * Adds the jump onto the historical record.
     * 
     * @param nextDataPoint
     *            Must be in <code>{timeofjump, angle, power}<code> format
     */
    public void addToJumpHistory(double[] nextDataPoint) {
        if (nextDataPoint.length == 3) {
            jumpHistory.add(nextDataPoint);
        }
    }

}
