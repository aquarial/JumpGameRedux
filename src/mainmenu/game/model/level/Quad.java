package mainmenu.game.model.level;

public class Quad {

    protected double x1;
    protected double y1;
    protected double x2;
    protected double y2;

    /**
     * Construct a quad from an array instead of passing doubles
     *
     * @param corners Array with <b>LENGTH OF 4</b>
     */
    public Quad(double[] corners) {
        this(corners[0], corners[1], corners[2], corners[3]);
    }

    /**
     * Construct a quad from each value separately
     */
    public Quad(double x1, double y1, double x2, double y2) {
        // Keep quad positive, helps calculating if a point is inside
        if (y2 < y1) {
            double tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        if (x2 < x1) {
            double tmp = x1;
            x1 = x2;
            x2 = tmp;
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        if (y2 - y1 == 0) {
            throw new NullPointerException();
        }
    }

    /**
     * @return the x1
     */
    public double getX1() {
        return x1;
    }

    /**
     * @return the y1
     */
    public double getY1() {
        return y1;
    }

    /**
     * @return the x2
     */
    public double getX2() {
        return x2;
    }

    /**
     * @return the y2
     */
    public double getY2() {
        return y2;
    }

}
