package app;

import java.awt.Polygon;
import java.awt.Rectangle;

public class SpaceShip extends Polygon {

	private double xVel = 0, yVel = 0;

	int panelWidth = MainFrame.width;
	int panelHeight = MainFrame.height;

	private double xCenter = panelWidth / 2, yCenter = panelHeight / 2;

	public static int[] polyXArray = { -13, 14, -13, -5, -13 };
	public static int[] polyYArray = { -15, 0, 15, 0, -15 };

	private int width = 27, height = 31;

	private double x = xCenter + this.polyXArray[0];
	private double y = yCenter + this.polyYArray[0];

	private double rotationAngle = 0, movingAngle = 0;

	public SpaceShip() {
		super(polyXArray, polyYArray, 5);
	}

	public void increaseX(double x) {
		xCenter += x;
	}

	public void increaseY(double y) {
		yCenter += y;
	}

	public void increaseXVel(double x) {
		xVel += x;
	}

	public void increaseYVel(double y) {
		yVel += y;
	}

	public void decreaseXVel(double x) {
		xVel -= x;
	}

	public void decreaseYVel(double y) {
		yVel -= y;
	}

	public void increaseMovAngle(double num) {
		movingAngle += num;
	}

	public void decreaseMovAngle(double num) {
		movingAngle -= num;
	}

	public double moveAngleX(double num) {
		return (double) (Math.cos(num * Math.PI / 180));
	}

	public double moveAngleY(double num) {
		return (double) (Math.sin(num * Math.PI / 180));
	}

	public void increaseRotationAngle() {
		if (rotationAngle >= 355) {
			rotationAngle = 0;
		} else {
			rotationAngle += 5;
		}
	}

	public void decreaseRotationAngle() {
		if (rotationAngle < 0) {
			rotationAngle = 355;
		} else {
			rotationAngle -= 5;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) xCenter - 14, (int) yCenter - 14, width, height);
	}

	public double getShipNoseX() {
		return this.xCenter + Math.cos(rotationAngle) * 14;
	}

	public double getShipNosey() {
		return this.yCenter + Math.sin(rotationAngle) * 14;
	}

	public void move() {

		increaseX(xVel);

		if (xCenter < 0) {
			xCenter = panelWidth;
		} else if (xCenter > panelWidth) {
			xCenter = 0;
		}

		increaseY(yVel);
		if (yCenter < 0) {
			yCenter = panelHeight;
		} else if (yCenter > panelHeight) {
			yCenter = 0;
		}
	}

	public double getXCenter() {
		return xCenter;
	}

	public double getYCenter() {
		return yCenter;
	}

	public void setXCenter(double x) {
		xCenter = x;
	}

	public void setYCenter(double y) {yCenter = y;}

	public double getRotationAngle() {
		return rotationAngle;
	}

	public void setRotationAngle(double rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	public double getMovingAngle() {
		return movingAngle;
	}

	public void setMovingAngle(double movingAngle) {
		this.movingAngle = movingAngle;
	}

	public double getxVel() {
		return xVel;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
