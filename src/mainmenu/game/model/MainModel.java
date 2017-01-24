package mainmenu.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mainmenu.game.model.block.Block;
import mainmenu.game.model.block.BlockType;
import mainmenu.game.model.comparator.PairComparator;
import mainmenu.game.model.level.LevelData;
import mainmenu.game.model.level.Point;
import mainmenu.game.model.level.Quad;

/**
 * @author karl
 */
public class MainModel {

    private Jumper jumper;
    private List<Block> blocks;
    private ModelHistory history;

    /**
     * Constructs a Model based on a level
     */
    public MainModel(String levelname) {
        LevelData level = LevelData.parseLevelFromName(levelname);

        this.history = new ModelHistory(levelname);

        this.jumper = new Jumper(level.getPlayerPosition());

        this.blocks = new ArrayList<>();
        for (Quad quad : level.getQuadData()) {
            blocks.add(Block.fromQuad(quad, BlockType.STICKY));
        }
        blocks.add(Block.fromQuad(level.getFinishQuad(), BlockType.FINISH));

    }

    /**
     * @return If the player has reached the finish block
     */
    public boolean levelComplete() {
        return history.getPlayerFinished();
    }

    /**
     * Updates the model based on time passed. Moves the Jumper/Player.
     *
     * @param deltaTime The change in time the model updates on
     */
    public void updateModel(double deltaTime) {

        if (!jumper.isStuck()) {
            Point newPosition = jumper.calculateUpdate(deltaTime);
            double[] futureJumperCorners = Jumper.calculateCornersAtPosition(newPosition);

            boolean encounteredBlock = false;
            for (Block blockade : blocks) {
                if (blockade.containsCornerArray(futureJumperCorners)) {
                    encounteredBlock = true;
                    break;
                }
            }

            if (encounteredBlock) {
                double dx = jumper.getXvelocity();
                double dy = jumper.getYvelocity();
                double[] currentJumperQuad = jumper.getCurrentCorners();

                ArrayList<Pair<Block, Point>> minMovements = new ArrayList<>();
                for (Block blockade : blocks) {
                    if (blockade.containsCornerArray(futureJumperCorners)) {

                        Optional<Point> newMinMovement = blockade.calculatePushingQuadToThis(currentJumperQuad, dx, dy);
                        if (newMinMovement.isPresent()) {
                            minMovements.add(new Pair<>(blockade, newMinMovement.get()));
                        }

                    }

                }

                // if encountered platform...
                // find the most we can move before hitting something
                // (the min distance) that's not zero
                Optional<Pair<Block, Point>> minimum = minMovements.stream().min(new PairComparator());

                if (minimum.isPresent()) {
                    // Now I can use the type of block here to react to each
                    // type uniquely
                    Pair<Block, Point> impact = minimum.get();

                    if (impact.getItem1().getBlockType() == BlockType.FINISH) {
                        history.setPlayerJustFinished();
                    }

                    // general block stuff:
                    Point min = impact.getItem2();
                    jumper.setVelocityToZero();
                    jumper.moveBy(min.getX(), min.getY());
                    jumper.setStuck(true);

                }

            } else {
                // if no block was encounterd, the player continues free fall
                jumper.update(deltaTime);
            }
        } else {
            // the player is stuck on a block

            // if I ever add moving platforms I can update the player position
            // based on how the block moves
        }

    }

    /**
     * Tries to launch the player off of a stuck block with an angle and power.
     */
    public void addJumpPowerToJumper(double angle, double power) {
        if (jumper.isStuck()) {
            history.addToJumpHistory(new double[]{history.getTimeFromStart(), angle, power});
            jumper.setAngularVelocity(angle, power);
            jumper.setStuck(false);
        }
    }

    /**
     * @return ModelHistory of what happened
     */
    public ModelHistory getHistory() {
        return history;
    }

    /**
     * @return Player's current x coordinate
     */
    public double getPlayerXPos() {
        return jumper.getXposition();
    }

    /**
     * Note: the model graphing coordinates. Positive is up.
     *
     * @return Player's current y coordinate
     */
    public double getPlayerYPos() {
        return jumper.getYposition();
    }

    /**
     * @return Player width in coordinate units.
     */
    public double getPlayerWidth() {
        return Jumper.JUMPER_WIDTH;
    }

    /**
     * @return List of blocks in the level
     */
    public List<Block> getBlockData() {
        return blocks;
    }

    /**
     * @return The level this model actually models
     */
    public String getLevelName() {
        return history.getLevelName();
    }

    /**
     * For use by MainModel while the level is being played
     * <p>
     * in mm:ss:ms
     *
     * @return Difference (in a formatted string) from the start of the level
     */
    public String getFormattedTimeFromStart() {
        if (history.getNumberOfJumpsSoFar() == 0) {
            return "00:00";
        }

        double time = history.getTimeFromStart();
        double maxTime = 59.59;
        if (time < maxTime) {
            return String.format("%02d:%02d", (int) (time % 60), (int) (time * 100 % 100));
        } else {
            return "59:59";
        }
    }

    /**
     * @return # of jumps in 000 format
     */
    public String getFormattedJumpsFromStart() {
        return String.format("%02d", history.getNumberOfJumpsSoFar());
    }
}
