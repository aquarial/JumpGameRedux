package mainmenu.savestates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.Resources;
import mainmenu.game.model.ModelHistory;

/**
 * Represents the player's progress through JumpGame. Holds unlocked levels,
 * times, and maybe more.
 */
public class GameSave {

    /** levelname -> LevelRecord */
    Map<String, LevelRecord> levelrecords;

    /**
     * Creates an empty game save
     */
    public GameSave() {
        levelrecords = new HashMap<>();
        for (String name : Resources.getLevelNames()) {
            String withoutEnding = name.substring(0, name.length() - 4);
            levelrecords.put(withoutEnding, new LevelRecord(withoutEnding));
        }
    }

    /**
     * @return List of levels names
     */
    public List<String> getLevelNames() {
        return new ArrayList<String>(levelrecords.keySet());
    }

    /**
     * Updates the records for the level the history was played on
     * 
     * @param history
     *            History of a completed level
     */
    public void updateLevelRecord(ModelHistory history) {
        LevelRecord level = levelrecords.get(history.getLevelName());
        level.updateRecords(history);
    }

}
