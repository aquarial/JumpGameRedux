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

            if (fastestTime == null || history.getTimeOfFinish() < fastestTime.getTimeOfFinish()) {
                fastestTime = history;
            }

            if (leastJumps == null || history.getNumberOfJumpsSoFar() < leastJumps.getNumberOfJumpsSoFar()) {
                leastJumps = history;
            }

        }
    }
}
