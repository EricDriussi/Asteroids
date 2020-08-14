package app;

import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Asteroid extends SpaceStuff {

	int xDir = 1;
	int yDir = 1;

	int width = 26;
	int height = 31;

	int[] xArray, yArray;

	public static int[] sPolyXArray = { 10, 17, 26, 34, 27, 36, 26, 14, 8, 1, 5, 1, 10 };
	public static int[] sPolyYArray = { 0, 5, 1, 8, 13, 20, 31, 28, 31, 22, 16, 7, 0 };

	public Asteroid(int[] xArray, int[] yArray, int points, int randomX, int randomY) {

		super(xArray, yArray, points);

		this.xDir = (int) (Math.random() * 4 + 1);
		this.yDir = (int) (Math.random() * 4 + 1);

		this.xCenter = randomX;
		this.yCenter = randomY;

	}

	@Override
	public void move() {

		int uLeftXPos = super.xpoints[0];

		int uLeftYPos = super.ypoints[0];

		if (uLeftXPos < 0 || (uLeftXPos + 25) > panelWidth)
			xDir = -xDir;

		if (uLeftYPos < 0 || (uLeftYPos + 50) > panelHeight)
			yDir = -yDir;

		for (int i = 0; i < super.xpoints.length; i++) {

			super.xpoints[i] += xDir;
			super.ypoints[i] += yDir;

		}

	}

	public Rectangle getBounds() {
		return new Rectangle(super.xpoints[0], super.ypoints[0], width, height);
	}

	public static int[] getpolyXArray(int randomStartXPos) {

		int[] tempPolyXArray = (int[]) sPolyXArray.clone();

		for (int i = 0; i < tempPolyXArray.length; i++) {

			tempPolyXArray[i] += randomStartXPos;

		}

		return tempPolyXArray;

	}

	public static int[] getpolyYArray(int randomStartYPos) {

		int[] tempPolyYArray = (int[]) sPolyYArray.clone();

		for (int i = 0; i < tempPolyYArray.length; i++) {

			tempPolyYArray[i] += randomStartYPos;

		}

		return tempPolyYArray;

	}

}