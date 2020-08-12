package app;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

//Extending the Polygon class because I'm drawing Polygons

@SuppressWarnings("serial")
public class Asteroid extends Polygon {

	int x, y; // int uLeftXPos, uLeftYPos;

	int xDir = 1;
	int yDir = 1;

	public boolean onScreen = false;

	int width = 26;
	int height = 31;

	int frameWidth = MainFrame.width;
	int frameHeight = MainFrame.height;

	// Will hold the x & y coordinates for the Polygons

	int[] xArray, yArray;

	
	public static int[] sPolyXArray = { 10, 17, 26, 34, 27, 36, 26, 14, 8, 1, 5, 1, 10 };
	public static int[] sPolyYArray = { 0, 5, 1, 8, 13, 20, 31, 28, 31, 22, 16, 7, 0 };

	public static ArrayList<Asteroid> list = new ArrayList<>();

	public Asteroid(int[] xArray, int[] yArray, int points, int randomX, int randomY) {

		super(xArray, yArray, points);

		onScreen = true;

		this.xDir = (int) (Math.random() * 4 + 1);

		this.yDir = (int) (Math.random() * 4 + 1);


		this.x = randomX;

		this.y = randomY;

	}
	
	public Rectangle getBounds() {
		return new Rectangle(super.xpoints[0], super.ypoints[0], width, height);
	}

	public void move(SpaceShip ship, ArrayList<Gun> gunList) {
		
		Rectangle toCheck = this.getBounds();
		
		for (Asteroid i : list) {
			
			Rectangle otherAsteroid = i.getBounds();
			
			if (i != this && otherAsteroid.intersects(toCheck)) {
				
					int tempX = this.xDir;
					int tempY = this.yDir;
					
					this.xDir = i.xDir;
					this.yDir = i.yDir;
					
					i.xDir = tempX;
					i.yDir = tempY;
				
			}
			
			Rectangle shipBox = ship.getBounds();
			if (otherAsteroid.intersects(shipBox)) {
				
				ship.setXCenter(frameWidth/2);
				ship.setYCenter(frameHeight/2);
				
				ship.setxVel(0);
				ship.setyVel(0);
				
			}
			for (Gun gun : gunList) {
				if (gun.onScreen) {
					if (otherAsteroid.contains(gun.getxCenter(), gun.getyCenter())) {
						i.onScreen = false;
						gun.onScreen = false;
					}
				}
			}
		}


		int uLeftXPos = super.xpoints[0];

		int uLeftYPos = super.ypoints[0];


		if (uLeftXPos < 0 || (uLeftXPos + 25) > frameWidth)
			xDir = -xDir;

		if (uLeftYPos < 0 || (uLeftYPos + 50) > frameHeight)
			yDir = -yDir;


		for (int i = 0; i < super.xpoints.length; i++) {

			super.xpoints[i] += xDir;
			super.ypoints[i] += yDir;

		}

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