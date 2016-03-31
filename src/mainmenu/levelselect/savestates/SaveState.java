package mainmenu.levelselect.savestates;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.Resources;

/**
 * Represents the player's progress through JumpGame. Holds unlocked levels,
 * times, and maybe more.
 *
 */
public class SaveState {

    List<LevelRecord> levelrecords;

    public SaveState() {

        Resources.getLevelNames().stream()
            .map(level -> new LevelRecord(level))
            .collect(Collectors.toList());

        levelrecords = new ArrayList<>();
        List<String> levels = Resources.getLevelNames();

        for (String level : levels) {
            levelrecords.add(new LevelRecord(level));
        }
    }

}
