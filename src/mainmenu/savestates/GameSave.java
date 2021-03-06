package mainmenu.savestates;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.Resources;
import mainmenu.game.model.ModelHistory;

/**
 * Represents the player's progress through JumpGame. Holds unlocked levels,
 * times, and maybe more.
 */
public class GameSave {

    /**
     * levelname -> LevelRecord
     */
    Map<String, LevelRecord> levelrecords;

    /**
     * Creates an empty game save
     */
    public GameSave() {
        levelrecords = new LinkedHashMap<>();
        for (String name : Resources.getLevelNames()) {
            levelrecords.put(name, new LevelRecord(name));
        }
    }

    /**
     * @return List of levels names
     */
    public List<String> getLevelNames() {
        return new ArrayList<String>(levelrecords.keySet());
    }

    /**
     * Updates the records for the level the history was played on.
     * <p>
     * Checks for null
     *
     * @param history History of a completed level
     */
    public void updateLevelRecord(ModelHistory history) {
        if (history != null) {
            LevelRecord level = levelrecords.get(history.getLevelName());
            level.updateRecords(history);
        }
    }

    /**
     * @return time formatted as mm:ss.
     */
    public String fastestTimeForLevel(String levelname) {
        return levelrecords.get(levelname).fastestTimeToString();
    }

    /**
     * @return jumps formatted as 000.
     */
    public String leastJumpsForLevel(String levelname) {
        return levelrecords.get(levelname).leastJumpsToString();
    }

}
