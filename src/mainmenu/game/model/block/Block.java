package mainmenu.game.model.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import mainmenu.game.model.comparator.PointComparator;
import mainmenu.game.model.level.Point;
import mainmenu.game.model.level.Quad;

public class Block extends Quad {

    /**
     * Construct a Block from a Quad
     */
    public static Block fromQuad(Quad q, BlockType type) {
        return new Block(q.getX1(), q.getY1(), q.getX2(), q.getY2(), type);
    }

    /**
     * Construct a Block by passing values
     */
    private Block(double x1, double y1, double x2, double y2, BlockType type) {
        super(x1, y1, x2, y2);
        blockType = type;
    }

    private BlockType blockType;

    /**
     * Whether or not the other is partly inside this.
     *
     * @param other another Quad
     * @return true if part is inside, false otherwise
     */
    public boolean containsQuad(Quad other) {
        //@formatter:off
        return
                this.x1 < other.getX2() &&
                this.x2 > other.getX1() &&
                this.y1 < other.getY2() &&
                this.y2 > other.getY1();
        //@formatter:on
    }

    /**
     * Whether or not the argument is inside the Block.
     *
     * @param corners a rectangle defined by the corners
     * @return true if part is inside, false otherwise
     */
    public boolean containsCornerArray(double[] corners) {
        //@formatter:off
        return
                this.x1 < corners[2] &&
                this.x2 > corners[0] &&
                this.y1 < corners[3] &&
                this.y2 > corners[1];
        //@formatter:on
    }

    /**
     * This method answers the question:<br>
     * <i>"I have a Jumper moving at a velocity, and I know it's going to hit
     * this StickyBlock. Calculate distance the Jumper will move before hitting
     * this StickyBlock."</i>
     * <p>
     * A simple idea with a very complicated implementation.
     * <p>
     * I first figure out what direction the Jumper is moving in (Up, Down,
     * Left, Right). It can be moving in two at the same time too. I then call 2
     * of 8 functions that calculate the distance from the specified corner of
     * the Jumper to the specified edge of this Quad for each way the jumper is
     * moving. Then I find the smallest movement (the first obstruction by this
     * Quad) that is not zero (still hits this Quad).
     *
     * @param corners   the Jumper Quad
     * @param xvelocity velocity of other
     * @param yvelocity velocity of other
     * @return Point represents how far until other hits this block
     */
    public Optional<Point> calculatePushingQuadToThis(double[] corners, double xvelocity, double yvelocity) {

        double angle = Math.atan2(yvelocity, xvelocity);
        double m = yvelocity / xvelocity;

        List<Point> movements = new ArrayList<>();

        if ((angle < Math.PI / 2) && (angle > -1 * Math.PI / 2)) {
            movements.add(upperRightCornerToLeftLine(corners, m));
            movements.add(lowerRightCornerToLeftLine(corners, m));
            // System.out.print("RIGHT"); // other.x2 to this.x1
        }

        if ((angle < Math.PI) && (angle > 0)) {
            movements.add(upperRightCornerToBottomtLine(corners, m));
            movements.add(upperLeftCornerToBottomLine(corners, m));
            // System.out.print("UP"); // other.y2 to this.y1
        }

        if ((angle > -1 * Math.PI) && (angle < 0)) {
            movements.add(lowerLeftCornerToTopLine(corners, m));
            movements.add(lowerRightCornerToTopLine(corners, m));
            // System.out.print("DOWN"); // other.y1 to this.y2
        }

        if ((angle > Math.PI / 2) || (angle < -1 * Math.PI / 2)) {
            movements.add(upperLeftCornerToRightLine(corners, m));
            movements.add(lowerLeftCornerToRightLine(corners, m));
            // System.out.print("LEFT"); // other.x1 to this.x2
        }

        //@formatter:off
        return movements.stream()
            .filter(Objects::nonNull)
            .min(new PointComparator());
        //@formatter:off
    }

    /**
     * Calculates the movement from the corner of <code>other</code> specified
     * in the name to push it up against the specified line of <code>this</code>
     * .
     * <p>
     * Returns <tt>(0, 0)</tt> if other can't be pushed against
     * <code>this</code> at the velocity it's going at.
     *
     * @param other Rectangle of object pushed against
     * @param slope Angle other is moving at
     * @return null if can't be pushed against other
     */
    private Point lowerRightCornerToTopLine(double[] other, double slope) {

        // this.y2 - other[1] = m * ( SOLUTION - other[2])
        double x = (this.y2 - other[1]) / slope + other[2];

        if ((this.x1 <= x) && (x <= this.x2)) {
            double y = slope * (x - other[2]) + other[1];
            return new Point(x - other[2], y - other[1], "lrt");
        }

        return null;
    }

    /**
     * Calculates the movement from the corner of <code>other</code> specified
     * in the name to push it up against the specified line of <code>this</code>
     * .
     * <p>
     * Returns <tt>(0, 0)</tt> if other can't be pushed against
     * <code>this</code> at the velocity it's going at.
     *
     * @param other Rectangle of object pushed against
     * @param slope Angle other is moving at
     * @return null if can't be pushed against other
     */
    private Point lowerRightCornerToLeftLine(double[] other, double slope) {

        // SOLUTION - other[1] = slope * (this.x1 - other[2])
        double y = slope * (this.x1 - other[2]) + other[1];

        if ((this.y1 <= y) && (y <= this.y2)) {
            double x = this.x1;
            return new Point(x - other[2], y - other[1], "lrl");
        }

        return null;
    }

    /**
     * Calculates the movement from the corner of <code>other</code> specified
     * in the name to push it up against the specified line of <code>this</code>
     * .
     * <p>
     * Returns <tt>(0, 0)</tt> if other can't be pushed against
     * <code>this</code> at the velocity it's going at.
     *
     * @param other Rectangle of object pushed against
     * @param slope Angle other is moving at
     * @return null if can't be pushed against other
     */
    private Point lowerLeftCornerToTopLine(double[] other, double slope) {

        // this.y2 - other[1] = m * ( SOLUTION - other[0])
        double x = (this.y2 - other[1]) / slope + other[0];

        if ((this.x1 <= x) && (x <= this.x2)) {
            double y = this.y2;
            return new Point(x - other[0], y - other[1], "llt");
        }

        return null;
    }

    /**
     * Calculates the movement from the corner of <code>other</code> specified
     * in the name to push it up against the specified line of <code>this</code>
     * .
     * <p>
     * Returns <tt>(0, 0)</tt> if other can't be pushed against
     * <code>this</code> at the velocity it's going at.
     *
     * @param other Rectangle of object pushed against
     * @param slope Angle other is moving at
     * @return null if can't be pushed against other
     */
    private Point lowerLeftCornerToRightLine(double[] other, double slope) {

        // SOLUTION - other[1] = m * (other[2] - this.x1)
        double y = slope * (this.x2 - other[0]) + other[1];

        if ((this.y1 <= y) && (y <= this.y2)) {
            double x = this.x2;
            return  new Point(x - other[0], y - other[1], "llr");
        }

        return null;
    }

    /**
     * Calculates the movement from the corner of <code>other</code> specified
     * in the name to push it up against the specified line of <code>this</code>
     * .
     * <p>
     * Returns <tt>(0, 0)</tt> if other can't be pushed against
     * <code>this</code> at the velocity it's going at.
     *
     * @param other Rectangle of object pushed against
     * @param slope Angle other is moving at
     * @return null if can't be pushed against other
     */
    private Point upperLeftCornerToBottomLine(double[] other, double slope) {

        // this.y1 = m * (SOLUTION - other[0]) + other[3]
        double x = (this.y1 - other[3]) / slope + other[0];

        if ((this.x1 <= x) && (x <= this.x2)) {
            double y = this.y1;
            return  new Point(x - other[0], y - other[3], "ulb" );
        }

        return null;
    }

    /**
     * Calculates the movement from the corner of <code>other</code> specified
     * in the name to push it up against the specified line of <code>this</code>
     * .
     * <p>
     * Returns <tt>(0, 0)</tt> if other can't be pushed against
     * <code>this</code> at the velocity it's going at.
     *
     * @param other Rectangle of object pushed against
     * @param slope Angle other is moving at
     * @return null if can't be pushed against other
     */
    private Point upperLeftCornerToRightLine(double[] other, double slope) {

        // SOLUTION = m * (this.x2 - other[0]) + other[3]
        double y = slope * (this.x2 - other[0]) + other[3];

        if ((this.y1 <= y) && (y <= this.y2)) {
            double x = this.x2;
            return  new Point(x - other[0], y - other[3], "ulr" );
        }

        return null;
    }

    /**
     * Calculates the movement from the corner of <code>other</code> specified
     * in the name to push it up against the specified line of <code>this</code>
     * .
     * <p>
     * Returns <tt>(0, 0)</tt> if other can't be pushed against
     * <code>this</code> at the velocity it's going at.
     *
     * @param other Rectangle of object pushed against
     * @param slope Angle other is moving at
     * @return null if can't be pushed against other
     */
    private Point  upperRightCornerToBottomtLine(double[] other, double slope) {

        // this.y1 - other[3] = m * (SOLUTION - other[2])
        double x = (this.y1 - other[3]) / slope + other[2];

        if ((this.x1 <= x) && (x <= this.x2)) {
            double y = this.y1;
            return  new Point(x - other[2], y - other[3], "urb") ;
        }

        return null;
    }

    /**
     * Calculates the movement from the corner of <code>other</code> specified
     * in the name to push it up against the specified line of <code>this</code>
     * .
     * <p>
     * Returns <tt>(0, 0)</tt> if other can't be pushed against
     * <code>this</code> at the velocity it's going at.
     *
     * @param other Rectangle of object pushed against
     * @param slope Angle other is moving at
     * @return null if can't be pushed against other
     */
    private Point  upperRightCornerToLeftLine(double[] other, double slope) {

        // YY = m * (this.x1 - other[2]) + other[3]
        double y = slope * (this.x1 - other[2]) + other[3];

        if ((this.y1 <= y) && (y <= this.y2)) {
            double x = this.x1;
            return new Point(x - other[2], y - other[3], "url") ;
        }

        return null;
    }

    /**
     * @return BlockType
     */
    public BlockType getBlockType() {
        return this.blockType;
    }

}