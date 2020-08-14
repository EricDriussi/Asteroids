package app;

import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Gun extends SpaceStuff {

	public static int[] polyXArray = { -3, 3, 3, -3, -3 };
	public static int[] polyYArray = { -3, -3, 3, 3, -3 };

	public Gun(double xc, double yc, double angle) {
		super(polyXArray, polyYArray, polyXArray.length);

		this.xCenter = xc;
		this.yCenter = yc;

		this.movingAngle = angle;

		setVelocity(movingAngle);

	}

	@Override
	public void move() {
		incrementX(xVel);
		if (xCenter < 0 || xCenter > panelWidth) {
			this.reset();

		}
		incrementY(yVel);
		if (yCenter < 0 || yCenter > panelHeight) {
			this.reset();
		}
	}

	private void setVelocity(double movingAngle) {
		this.xVel = (double) (Math.cos(movingAngle * Math.PI / 180) * 10);
		this.yVel = (double) (Math.sin(movingAngle * Math.PI / 180) * 10);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) xCenter - width, (int) yCenter - height, width, height);
	}
}
