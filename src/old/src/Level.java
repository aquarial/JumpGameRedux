package old.src;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Level {
	static public ArrayList<Double> levelTimes;
	static public ArrayList<Integer> levelClicks;
	static public int jumpCount;
	public ArrayList<Line> listOfLines;
	public Point startingPoint;
	public Rectangle winZone;
	public int levelNumber;
	boolean scrolling;

	public Level(int i) {
		try {
			levelTimes.get(0);
			levelClicks.get(0);
		} catch (NullPointerException npe) {
			levelTimes = new ArrayList<Double>();
			levelClicks = new ArrayList<Integer>();
		} catch (IndexOutOfBoundsException ioobe) {
		}

		new Line(i);

	}

	/*
	 * Procedure for making a new level:
	 * 
	 * Make a new method named Level_n() for your nth level. Fill it with the
	 * proper code to create each line (ONLY STRAIGHT LINES), and/or use my
	 * makeRectangle() method. Then, add a line of code to the first Line
	 * constructor, following the pattern you can hopefully see there (Fg 1.)
	 * After that, go to the GTJMainMenu class, and, using the design page, add
	 * another button to the levelSelectorPanel. Make each button smaller if you
	 * have to. Then, in the same class, search for "(Fg 2)". In the method that
	 * string is found in, you must follow the pattern seen there with the
	 * button you created in an earlier step. And that's it, you have created a
	 * button
	 * 
	 * This has been a review for me if I ever want to add more levels after I
	 * have forgotten where everything is.
	 * 
	 * England prevails (V for Vendetta)
	 * 
	 * - Karl Stephan, 2014/03/07, 11:21:05 (before the clocks are set one hour
	 * earlier)
	 */

	public class Line {
		double x1;
		double x2;
		double y1;
		double y2;
		char safeSide;
		double maxXChange;

		// (Fg 1)
		public Line(int i) {

			startingPoint = new Point(0, 0);
			winZone = new Rectangle(0, 0, 0, 0);
			listOfLines = new ArrayList<Line>();
			levelNumber = i;
			scrolling = false;

			switch (i) {

				case 1:
					Level_1();
					break;
				case 2:
					Level_2();
					break;
				case 3:
					Level_3();
					break;
				case 4:
					Level_4();
					break;
				case 5:
					Level_5();
					break;
				case 6:
					Level_6();
					break;
				case 7:
					Level_7();
			}
		}

		public Line(double a, double b, double c, double d, char e) {
			x1 = a / 15 * 800;
			y1 = b / 15 * 800;
			x2 = c / 15 * 800;
			y2 = d / 15 * 800;
			safeSide = e;
			maxXChange = 0;

		}

		public Line(double a, double b, double c, double d, double f) {
			x1 = a / 15 * 800;
			y1 = b / 15 * 800;
			x2 = c / 15 * 800;
			y2 = d / 15 * 800;
			safeSide = 'u';
			maxXChange = f / 15 * 800;
		}

		public boolean touchingPlayer(double x, double y, double percentOfChange) {

			x1 = x1 + (this.maxXChange / 100F * percentOfChange);
			x2 = x2 + (this.maxXChange / 100F * percentOfChange);

			int theNum = 5;
			int otherNum = 10;
			switch (safeSide) {
				case 'u':
					if ((x1 <= x && x <= x2) || (x2 <= x && x <= x1)) {
						if (y + otherNum >= y1 && y - theNum <= y1) {
							return true;
						}
					}
					break;
				case 'd':
					if ((x1 <= x && x <= x2) || (x2 <= x && x <= x1)) {
						if (y - otherNum <= y1 && y + theNum >= y1) {
							return true;
						}
					}
					break;
				case 'l':
					if ((y1 <= y && y <= y2) || (y2 <= y && y <= y1)) {
						if (x + otherNum >= x1 && x - theNum <= x1) {
							return true;
						}
					}
					break;
				case 'r':
					if ((y1 <= y && y <= y2) || (y2 <= y && y <= y1)) {
						if (x - otherNum <= x1 && x + theNum >= x1) {
							return true;
						}
					}
			}
			return false;
		}

		public boolean touchingPlayer2(double x, double y, double percentOfChange) {
			x1 = x1 + (this.maxXChange / 100F * percentOfChange);
			x2 = x2 + (this.maxXChange / 100F * percentOfChange);

			if (this.safeSide == 'u') {
				if ((x1 <= x && x <= x2) || (x2 <= x && x <= x1)) {
					if (y + 10 >= y1 && y - 2 <= y1) {
						return true;
					}
				}
			}
			return false;
		}

		public void Level_1() {

			startingPoint = new Point(100, 425);
			winZone = new Rectangle(586, 106, 640 - 585, 141 - 105);

			listOfLines.add(new Line(1, 1, 4, 1, 'd'));
			listOfLines.add(new Line(4, 1, 4, 2, 'l'));
			listOfLines.add(new Line(4, 2, 8, 2, 'd'));
			listOfLines.add(new Line(8, 2, 8, 4, 'l'));
			listOfLines.add(new Line(8, 4, 9, 4, 'd'));
			listOfLines.add(new Line(9, 4, 9, 5, 'l'));
			listOfLines.add(new Line(9, 5, 10, 5, 'd'));
			listOfLines.add(new Line(10, 5, 10, 8, 'l'));
			listOfLines.add(new Line(10, 8, 11, 8, 'd'));
			listOfLines.add(new Line(11, 8, 11, 2, 'r'));
			// 10 is below this:
			listOfLines.add(new Line(11, 2, 12, 2, 'd'));
			listOfLines.add(new Line(12, 2, 12, 9, 'l'));
			listOfLines.add(new Line(12, 9, 8, 9, 'u'));
			listOfLines.add(new Line(8, 9, 8, 7, 'r'));
			listOfLines.add(new Line(8, 7, 6, 7, 'u'));
			listOfLines.add(new Line(6, 7, 6, 5, 'r'));
			listOfLines.add(new Line(6, 5, 4, 5, 'u'));
			listOfLines.add(new Line(4, 5, 4, 7, 'l'));
			listOfLines.add(new Line(4, 7, 3, 7, 'u'));
			listOfLines.add(new Line(3, 7, 3, 9, 'l'));
			// 20 is below this:
			listOfLines.add(new Line(3, 9, 1, 9, 'u'));
			listOfLines.add(new Line(1, 9, 1, 1, 'r'));

			makeRectangle(2, 2, 1, 3, true);
		}

		public void Level_2() {

			startingPoint = new Point(85, 425);
			winZone = new Rectangle(540, (int) (.9 * 900 / 15), 206, 85);

			// Full Box:

			makeRectangle(1, 1, 13, 9, false);

			makeRectangle(5, 2, 2, 1, true);
			// Box to the right of start:
			makeRectangle(2, 7, 1, 3, true);
			makeRectangle(5, 8, 2, 1, true);
			makeRectangle(8, 7, 3, 2, true);
			makeRectangle(8, 4, 1.5, 1, true);
			// Box to jump to
			makeRectangle(3.5, 4.756, 2, 1, true);
			makeRectangle(11.3, 5.9, 14 - 11.3, .25, true);
			makeRectangle(2, 2, .1, 2, true);

		}

		public void Level_3() {
			startingPoint = new Point(50, 50);
			winZone = new Rectangle(toInt(10), toInt(8), toInt(2), toInt(1));

			makeRectangle(.1, .1, 14.4, 10.1, false);
			makeRectangle(.1, 3, 2, .5, true);
			makeRectangle(4.8, 3.8, .5, .2, true);
			makeRectangle(3, 1, 2, 1, true);
			makeRectangle(9.5, .5, 2, 1, true);
			makeRectangle(7, 2, 1, 2.5, true);
			makeRectangle(2, 4.5, 2, 2.5, true);
			makeRectangle(4, 4.5, 5, .25, true);
			makeRectangle(7, 4.75, 1, 2.25, true);
			makeRectangle(8.5, 3, 3.5, .75, true);
			makeRectangle(12, 2, .3, 1.75, true);
			makeRectangle(2, 8, 2, 1, true);
			makeRectangle(3.5, 9, .5, 1.2, true);
			makeRectangle(5, 5.5, 1, 4, true);
			makeRectangle(7, 8, 1, 2.2, true);

			listOfLines.add(new Line(9, 6, 13, 6, 'u'));
			listOfLines.add(new Line(13, 6, 13, 9, 'r'));
			listOfLines.add(new Line(13, 9, 12, 9, 'd'));
			listOfLines.add(new Line(9, 6, 9, 9, 'l'));
			listOfLines.add(new Line(9, 9, 10, 9, 'd'));

		}

		public void Level_4() {
			startingPoint = new Point(toInt(7.5), toInt(9));
			winZone = new Rectangle(toInt(11), toInt(1), toInt(2), toInt(3));

			makeRectangle(.1, .1, 14.4, 10.1, false);
			makeRectangle(.1, 2, 1, 1, true);
			makeRectangle(1.5, 4, .5, 1, true);
			makeRectangle(1.5, 6, 3.5, .5, true);
			makeRectangle(2, 1, 2, 1, true);
			makeRectangle(3, 3.5, 3, .5, true);
			makeRectangle(3, 7.5, 2, .5, true);
			makeRectangle(5, 1, 3, .5, true);
			makeRectangle(7.5, 6, .5, 2, true);
			makeRectangle(10, 6, .5, 2, true);
			makeRectangle(12.5, 6, .5, 2, true);

			listOfLines.add(new Line(.1, 8, 3, 8, 'd'));
			listOfLines.add(new Line(12, 5, 14.4, 5, 'd'));
		}

		public void Level_5() {
			startingPoint = new Point(toInt(1.5), toInt(7.5));
			winZone = new Rectangle(toInt(10), toInt(.1), toInt(3.4), toInt(1));

			makeRectangle(.1, .1, 13.3, 10.1, false);

			makeRectangle(1, 8, 2, 1, true);
			makeRectangle(10, 7.5, 3.4, .5, true);
			makeRectangle(10, 4, 2, .5, true);
			makeRectangle(1, 2, 1.5, .3, true);

			listOfLines.add(new Line(12, 4, 12, 6, 'r'));
			listOfLines.add(new Line(1, 2, 1, 4, 'l'));
			listOfLines.add(new Line(11, 3, 13.4, 3, 'd'));

			// 23 Lines above this comment.

			listOfLines.add(new Line(3.5, 8, 6, 8, 3));
			listOfLines.add(new Line(7, 4, 9, 4, -6.9));
			listOfLines.add(new Line(3, 2, 5, 2, 6));

		}

		public void Level_6() {
			startingPoint = new Point(toInt(.3), toInt(.3));
			winZone = new Rectangle(toInt(13.5), toInt(9.2), toInt(1), toInt(1));
			scrolling = true;

			makeRectangle(.1, .1, 14.4, 10.1, false);

			makeRectangle(12.8, 1, .5, 9.2, true);
			makeRectangle(12, 1, .8, .3, true);

			listOfLines.add(new Line(1, 9, 12.8, 9, 'd'));
			listOfLines.add(new Line(1, 1, 1, 9, 'l'));
			listOfLines.add(new Line(.1, 1, 1, 1, 'u'));
			listOfLines.add(new Line(3, 3, 4, 3, -1));
			listOfLines.add(new Line(2, 3.5, 3, 3.5, .5));
			listOfLines.add(new Line(3, 5, 4, 5, 1.5));
			listOfLines.add(new Line(2, 6, 3, 6, 2));
			listOfLines.add(new Line(3, 7, 5, 7, -2));
			listOfLines.add(new Line(5, 6.5, 7, 6.5, 1.5));
			listOfLines.add(new Line(6, 8, 8, 8, -3));
			listOfLines.add(new Line(8, 6, 10, 6, -.2));
			listOfLines.add(new Line(10, 2, 11, 2, -1));
			listOfLines.add(new Line(10, 3.5, 11, 3.5, .25));
			listOfLines.add(new Line(10, 5, 12, 5, -2));

			listOfLines.add(new Line(5, 0, 5, 6, 'l'));

		}

		public void Level_7() {
			scrolling = true;
			startingPoint = new Point(toInt(.5), toInt(25) - 10);
			winZone = new Rectangle(toInt(0), toInt(0), toInt(2), toInt(4));

			makeRectangle(0, 0, 35, 25, false);

			makeRectangle(2, 23, 2, .55, true);
			makeRectangle(1, 19, 1, 2.5, true);
			makeRectangle(2, 16, 3, .5, true);
			makeRectangle(4, 19, 1, 1, true);
			// 5 is below this comment
			makeRectangle(5, 19, 1, 3, true);
			makeRectangle(4, 21, 1, 1, true);
			makeRectangle(7, 23, 2, 1, true);
			makeRectangle(8, 22, 1, 1, true);
			makeRectangle(2, 13, 3, 1, true);
			// 10 is below this comment
			makeRectangle(8, 18, 2, 2, true);
			listOfLines.add(new Line(12, 25, 12, 23, 'l'));
			listOfLines.add(new Line(12, 23, 13, 23, 'u'));
			listOfLines.add(new Line(13, 23, 13, 22, 'l'));
			listOfLines.add(new Line(13, 22, 15, 22, 'u'));
			// 15 is below this comment
			listOfLines.add(new Line(15, 22, 15, 18, 'l'));
			listOfLines.add(new Line(13, 21, 13, 19, 'r'));
			makeRectangle(12, 19, 1, .5, true);
			listOfLines.add(new Line(15, 18, 16, 18, 'u'));
			listOfLines.add(new Line(16, 18, 16, 25, 'r'));
			// 20 is below this comment
			makeRectangle(17.5, 19, 2.5, 5, true);
			makeRectangle(22, 20, 1, 3, true);
			makeRectangle(24, 23, 1, 1, true);
			makeRectangle(26, 23, 2, 1, true);
			makeRectangle(31, 20, 1, 3, true);
			// 25 is below this comment
			makeRectangle(33, 20, 1, 1, true);
			makeRectangle(25, 19, 3, 2, true);
			makeRectangle(12, 16, 2, 1, true);
			makeRectangle(10, 10, .5, 5, true);
			makeRectangle(13, 11, 3, .5, true);
			// 30 is below this comment
			makeRectangle(15, 13.5, 5, 2.5, true);
			makeRectangle(21, 16, 3, 2.5, true);
			makeRectangle(22, 12.5, 3, 1.5, true);
			makeRectangle(26, 16, 2, 1, true);
			makeRectangle(27, 17, 1, 1, true);
			// 35 is below this comment
			makeRectangle(30, 17, 4, 1, true);
			makeRectangle(33, 16, 1, 1, true);
			makeRectangle(27, 13, 2, 1, true);
			makeRectangle(31, 13, 3, 1, true);
			makeRectangle(31, 10, 3, 1, true);
			// 40 is below this comment
			makeRectangle(32, 8, 2, 1, true);
			makeRectangle(33, 6, 1, 1, true);
			makeRectangle(32, 3, 2, 1, true);
			listOfLines.add(new Line(0, 10, 20, 10, 'd'));
			listOfLines.add(new Line(32, 1, 32, 3, 'r'));
			// 45 is below this comment
			makeRectangle(31, 1, 1, 1, true);
			listOfLines.add(new Line(2, 0, 2, 4, 'r'));
			makeRectangle(26, 3, 2, 1, true);
			makeRectangle(22, 4, 1, 1, true);
			makeRectangle(19, 6, 2, 1, true);
			// 50 is below this comment
			makeRectangle(17, 8.5, 4, .5, true);
			makeRectangle(15, 7, 1, 1, true);
			makeRectangle(16.5, 5, .5, 1, true);
			makeRectangle(15, 3, 2, 1, true);
			makeRectangle(16, 2, 1, 1, true);
			// 55 is below this comment
			makeRectangle(13, 5, 1, 1, true);
			makeRectangle(10, 2, 2, 2, true);
			makeRectangle(11, 6, 1, 1, true);
			makeRectangle(10, 6, 1, 3, true);
			makeRectangle(11, 8, 1, 1, true);
			// 60 is below this comment
			makeRectangle(7, 7, 1, 2, true);
			makeRectangle(5, 2, 2, 2, true);
			makeRectangle(5, 5, 1, 1, true);
			makeRectangle(3, 7.5, 2, .5, true);
			makeRectangle(1, 6.5, 2, .5, true);
			// 65 is below this comment
			makeRectangle(1, 9, 1, 1, true);

		}

		public void makeRectangle(double x, double y, double width, double height, boolean faceOut) {
			// One Box:
			if (faceOut) {
				listOfLines.add(new Line(x, y, x + width, y, 'u'));
				listOfLines.add(new Line(x + width, y, x + width, y + height, 'r'));
				listOfLines.add(new Line(x + width, y + height, x, y + height, 'd'));
				listOfLines.add(new Line(x, y + height, x, y, 'l'));
			} else {
				listOfLines.add(new Line(x, y, x + width, y, 'd'));
				listOfLines.add(new Line(x + width, y, x + width, y + height, 'l'));
				listOfLines.add(new Line(x + width, y + height, x, y + height, 'u'));
				listOfLines.add(new Line(x, y + height, x, y, 'r'));
			}
		}

		public int toInt(double b) {
			return (int) (b / 15F * 800);
		}

	}

}
