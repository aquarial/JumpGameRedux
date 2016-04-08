package mainmenu.savestates;

import java.util.ArrayList;
import java.util.Collections;
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
        levelrecords = Collections.emptyMap();
        for (String name : Resources.getLevelNames()) {
            String withoutEnding = name.substring(0, name.length() - 4);
            levelrecords.put(withoutEnding, new LevelRecord(withoutEnding));
        }
    }

    /**
     * @return List of levels
     */
    public List<String> getLevels() {
        List<String> unlockedlevels = new ArrayList<String>();
        for (Map.Entry<String, LevelRecord> entry : levelrecords.entrySet()) {
            unlockedlevels.add(entry.getKey());
        }
        Collections.sort(unlockedlevels);
        return unlockedlevels;
    }

}
