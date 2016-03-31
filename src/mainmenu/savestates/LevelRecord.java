package mainmenu.savestates;

/**
 * Represents how
 *
 */
public class LevelRecord {

    private boolean isUnlocked = false;

    LevelRecord(String levelname) {

    }

    // Getters & setters

    /**
     * @return the isUnlocked
     */
    public boolean isUnlocked() {
        return isUnlocked;
    }

    /**
     * @param isUnlocked
     *            the state to set
     */
    public void setUnlocked(boolean isUnlocked) {
        this.isUnlocked = isUnlocked;
    }

}
