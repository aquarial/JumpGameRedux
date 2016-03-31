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
     * @return List of unlocked levels
     */
    public List<String> getUnlockedLevels() {
        List<String> unlockedlevels = new ArrayList<String>();
        for (Map.Entry<String, LevelRecord> entry : levelrecords.entrySet()) {
            if (entry.getValue().isUnlocked()) {
                unlockedlevels.add(entry.getKey());
            }
        }
        Collections.sort(unlockedlevels);
        return unlockedlevels;
    }

    /**
     * @param levelname
     * @return Whether this game save has unlocked a level
     */
    public boolean isLevelUnlocked(String levelname) {
        return levelrecords.get(levelname).isUnlocked();
    }

    /**
     * Sets a level to unlocked by name
     * 
     * @param levelname
     */
    public void unlockALevel(String levelname) {
        levelrecords.get(levelname).setUnlocked(true);
    }

}
