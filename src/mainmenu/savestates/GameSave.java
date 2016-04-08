package mainmenu.savestates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.Resources;

/**
 * Represents the player's progress through JumpGame. Holds unlocked levels,
 * times, and maybe more.
 *
 */
public class GameSave {

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

}
