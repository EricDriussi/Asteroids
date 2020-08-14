package app;

import java.awt.Polygon;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public abstract class SpaceStuff extends Polygon {

	int panelWidth = MainFrame.width;
	int panelHeight = MainFrame.height;

	double xCenter, yCenter;

	int width, height;

	double movingAngle = 0;

	double xVel, yVel;

	public SpaceStuff(int[] polyXArray, int[] polyYArray, int pointNum) {
		super(polyXArray, polyYArray, pointNum);

	}

	@Override
	public abstract Rectangle getBounds();

	abstract void move();

	void incrementX(double n) {
		xCenter += n;
	}

	void incrementY(double n) {
		yCenter += n;
	}

	double getXCenter() {
		return xCenter;
	}

	double getYCenter() {
		return yCenter;
	}

	public double moveAngleX(double num) {
		return (double) (Math.cos(num * Math.PI / 180));
	}

	public double moveAngleY(double num) {
		return (double) (Math.sin(num * Math.PI / 180));
	}

}
