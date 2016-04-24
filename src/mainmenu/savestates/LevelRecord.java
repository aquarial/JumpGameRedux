package mainmenu.savestates;

import mainmenu.game.model.ModelHistory;

/**
 * Represents how
 *
 */
public class LevelRecord {

    ModelHistory fastestTime;
    ModelHistory leastJumps;

    LevelRecord(String levelname) {
        ModelHistory defaultRecords = new ModelHistory(levelname, 99 * 60 + 59, 999);
        fastestTime = defaultRecords;
        leastJumps = defaultRecords;
    }

    // Getters & setters

    /**
     * Updates the records with recently completed level
     * 
     * @param history
     *            The completed level history
     */
    void updateRecords(ModelHistory history) {
        if (history.getPlayerFinished()) {

            if (history.getTimeOfFinish() < fastestTime.getTimeOfFinish()) {
                fastestTime = history;
            }

            if (history.getNumberOfJumpsSoFar() < leastJumps.getNumberOfJumpsSoFar()) {
                leastJumps = history;
            }

        }
    }

    /**
     * Formats time into mm:ss.
     * 
     * @return
     */
    String fastestTimeToString() {
        double speed = fastestTime.getTimeOfFinish();
        return String.format("%02d:%02d", (int) speed / 60, (int) speed % 60);
    }

    /**
     * Formats jumps into 000.
     * 
     * @return
     */
    String leastJumpsToString() {
        int jumps = leastJumps.getNumberOfJumpsSoFar();
        return String.format("%03d", jumps);
    }
}
