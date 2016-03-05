package mainmenu.game.model;

import java.util.Comparator;
import java.util.Optional;

import mainmenu.game.model.level.Point;

public class OptionalPointComparator implements Comparator<Optional<Point>> {

	@Override
	public int compare(Optional<Point> o1, Optional<Point> o2) {
		if (o1.isPresent() == false && o2.isPresent() == false) {
			return 0;
		}

		if (o1.isPresent() == false && o2.isPresent() == true) {
			return -1;
		}

		if (o1.isPresent() == true && o2.isPresent() == false) {
			return 1;
		}

		Point p1 = o1.get();
		Point p2 = o2.get();

		return Double.compare(Math.pow(p1.getX(), 2) + Math.pow(p1.getY(), 2),
				Math.pow(p2.getX(), 2) + Math.pow(p2.getY(), 2));
	}

}
