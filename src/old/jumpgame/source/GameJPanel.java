package gameTimeJump;

import gameTimeJump.Level.Line;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class GameJPanel extends JPanel {
	private static final long serialVersionUID = 2323L;
	double screenX;
	double screenY;
	double diffToX1;
	int change = 1;
	int getOutOfHere;
	double percentOfChange;
	Level currentLevel;
	Player player;

	public void setUpOneGame(int level) {
		screenX = 0;
		screenY = 0;
		getOutOfHere = 1;
		currentLevel = new Level(level);
		player = new Player(currentLevel.startingPoint.x, currentLevel.startingPoint.y);
		percentOfChange = 10;

	}

	public void paintComponent(Graphics g) {
		percentOfChange += change;
		if (percentOfChange == 100 || percentOfChange == 0) {
			change *= -1;
		}

		g.setColor(Color.GREEN);
		if (currentLevel.scrolling) {
			screenX = -player.xPos + 400;
			screenY = -player.yPos + 300;
			g.fillRect(390, 290, 20, 20);
		} else {
			g.fillRect((int) (player.xPos - 10 + screenX), (int) (player.yPos - 10 + screenY), 20, 20);
		}

		g.setColor(Color.BLACK);
		for (int i = 0; i < currentLevel.listOfLines.size(); i++) {
			Line l = currentLevel.listOfLines.get(i);
			g.drawLine((int) (l.x1 + ((l.maxXChange / 100) * percentOfChange) + screenX), (int) (l.y1 + screenY),
					(int) (l.x2 + ((l.maxXChange / 100) * percentOfChange) + screenX), (int) (l.y2 + screenY));

		}

		g.setColor(new Color(135, 206, 250));
		g.fillRect((int) (currentLevel.winZone.x + screenX), (int) (currentLevel.winZone.y + screenY),
				(int) (currentLevel.winZone.width), (int) (currentLevel.winZone.height));

	}

	// Player Class Here
	public class Player {
		int stuckOn = -1;
		public int xPos;
		public int yPos;
		boolean stuck = false;
		double yMovement = 0;
		double xMovement = 0;

		public Player(int x, int y) {
			xPos = x;
			yPos = y;
		}

		public void trySticking() {
			for (Line a : currentLevel.listOfLines) {
				if (a.touchingPlayer(xPos, yPos, percentOfChange)) {
					switch (a.safeSide) {
						case 'u':
							yPos = (int) (a.y2 - 10);
							break;
						case 'd':
							yPos = (int) (a.y2 + 10);
							break;
						case 'l':
							xPos = (int) (a.x1 - 10);
							break;
						case 'r':
							xPos = (int) (a.x1 + 10);
					}
					stuck = true;
					stuckOn = currentLevel.listOfLines.indexOf(a);
					diffToX1 = xPos - a.x1;

					yMovement = 0;
					xMovement = 0;
				}
				a.x1 = a.x1 - (a.maxXChange / 100F * percentOfChange);
				a.x2 = a.x2 - (a.maxXChange / 100F * percentOfChange);
			}

		}

		public void passOneTurn() {
			if (!stuck) {
				double max_Speed = 6;

				yMovement += 0.2;
				if (yMovement >= max_Speed) {
					yMovement = max_Speed;
				}
				if (yMovement <= -max_Speed) {
					yMovement = -max_Speed;
				}

				if (xMovement < -max_Speed) {
					xMovement = -max_Speed;
				}
				if (xMovement > max_Speed) {
					xMovement = max_Speed;
				}

				yPos += yMovement;
				xPos += xMovement;

				xMovement *= 0.999;

			} else {
				Line temp = currentLevel.listOfLines.get(stuckOn);
				// percentOfChange;
				// change;
				if (temp.maxXChange != 0) {
					/*
					 * if (!(temp.x1 + ((temp.maxXChange / 100) *
					 * percentOfChange) < xPos) || !(temp.x2 + ((temp.maxXChange
					 * / 100) * percentOfChange) > xPos)) {
					 * 
					 * stuck = false;
					 * 
					 * }
					 */
					xPos = (int) ((temp.maxXChange / 100 * percentOfChange) + diffToX1 + temp.x1);
				}

			}
		}

		public void tryToJumpAtMouse(MouseEvent e, Point point) {

			if (stuck) {
				if (stuckOn != -1) {

				}
				Line li = currentLevel.listOfLines.get(stuckOn);
				boolean canIJump = true;
				Point locOnScreen = e.getLocationOnScreen();
				// Change location On screen
				locOnScreen.x -= screenX;
				locOnScreen.y -= screenY;
				switch (li.safeSide) {
					case 'u':
						if (locOnScreen.y - point.y - 5 > player.yPos) {
							canIJump = false;
						}
						break;
					case 'd':
						if (locOnScreen.y - point.y < player.yPos) {
							canIJump = false;
						}
						break;
					case 'l':
						if (locOnScreen.x - point.x - 5 > player.xPos) {
							canIJump = false;
						}
						break;
					case 'r':
						if (locOnScreen.x - point.x + 5 < player.xPos) {
							canIJump = false;

						}
				}
				if (canIJump) {
					jumpOnMyWaywardSon(e, point, locOnScreen);
				}
			}
		}

		private void jumpOnMyWaywardSon(MouseEvent e, Point point, Point screenLoc) {
			double rightPower = screenLoc.x - point.x - xPos;
			double downPower = screenLoc.y - point.y - yPos;

			// total_Power = 10;s
			double total_Power = Math.sqrt(Math.pow(rightPower, 2) + Math.pow(downPower, 2)) / 10;
			if (total_Power > 9) {
				total_Power = 9;
			}

			double k = total_Power / (Math.abs(rightPower) + Math.abs(downPower));

			yMovement = k * downPower;
			xMovement = k * rightPower;

			Level.jumpCount++;
			stuck = false;
		}
	}

	public void checkIfWin() {
		int x1 = currentLevel.winZone.x / getOutOfHere;
		int y1 = currentLevel.winZone.y;
		int x2 = currentLevel.winZone.width;
		int y2 = currentLevel.winZone.height;

		if ((y1 <= player.yPos && player.yPos <= y1 + y2) && (x1 <= player.xPos && player.xPos <= x1 + x2)) {
			throw new IllegalStateException(currentLevel.levelNumber + "");
		}

	}

	public void throwTheException(String thing) {
		getOutOfHere = 0;
	}

}
