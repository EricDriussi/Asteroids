package app;

import java.awt.Polygon;
import java.awt.Rectangle;

public class Gun extends Polygon {

	int panelWidth = MainFrame.width;
	int panelHeight = MainFrame.height;

	private double xCenter = 0, yCenter = 0;

	public static int[] polyXArray = { -3, 3, 3, -3, -3 };
	public static int[] polyYArray = { -3, -3, 3, 3, -3 };

	private int width = 6, height = 6;

	public boolean onScreen = false;

	private double movingAngle = 0;

	private double xVel = 5, yVel = 5;

	public Gun(double xc, double yc, double angle) {
		super(polyXArray, polyYArray, 5);
		this.xCenter = xc;
		this.yCenter = yc;
		this.movingAngle = angle;

		this.onScreen = true;

		this.xVel = XMovAngle(movingAngle) * 10;
		this.yVel = YMovAngle(movingAngle) * 10;

	}

	private double XMovAngle(double movingAngle) {

		return (double) (Math.cos(movingAngle * Math.PI / 180));
	}

	private double YMovAngle(double movingAngle) {
		return (double) (Math.sin(movingAngle * Math.PI / 180));
	}

	public void incrementX(double num) {
		xCenter += num;
	}

	public void incrementy(double num) {
		yCenter += num;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) xCenter - 6, (int) yCenter - 6, width, height);
	}

	public void move() {
		if (onScreen) {
			incrementX(xVel);
			if (xCenter < 0 || xCenter > panelWidth) {
				onScreen = false;
			}
			incrementy(yVel);
			if (yCenter < 0 || yCenter > panelHeight) {
				onScreen = false;
			}
		}
	}

	// -----------------------------------------------------------------------------------------------

	public double getxCenter() {
		return xCenter;
	}

	public void setxCenter(double xCenter) {
		this.xCenter = xCenter;
	}

	public double getyCenter() {
		return yCenter;
	}

	public void setyCenter(double yCenter) {
		this.yCenter = yCenter;
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

}
