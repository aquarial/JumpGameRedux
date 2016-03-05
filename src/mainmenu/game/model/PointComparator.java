package mainmenu.game.model;

import java.util.Comparator;

import mainmenu.game.model.level.Point;

public class PointComparator implements Comparator<Point> {

	@Override
	public int compare(Point p1, Point p2) {

		return Double.compare(Math.pow(p1.getX(), 2) + Math.pow(p1.getY(), 2),
				Math.pow(p2.getX(), 2) + Math.pow(p2.getY(), 2));
	}

}
