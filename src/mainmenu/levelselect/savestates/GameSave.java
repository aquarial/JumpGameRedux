package mainmenu.levelselect.savestates;

import java.util.Collections;
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
        levelrecords = Collections.emptyMap();
        for (String name : Resources.getLevelNames()) {
            String withoutEnding = name.substring(0, name.length() - 4);
            levelrecords.put(withoutEnding, new LevelRecord(withoutEnding));
        }
    }

}
