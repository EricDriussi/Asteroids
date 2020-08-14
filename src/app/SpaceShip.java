package app;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class SpaceShip extends SpaceStuff {

	public static int[] polyXArray = { -13, 13, -13, -5, -13 };
	public static int[] polyYArray = { -15, 0, 15, 0, -15 };

	private int width = 26, height = 30;

	private double rotationAngle = 0;

	public SpaceShip() {
		super(polyXArray, polyYArray, polyXArray.length);
		resetShip();
	}

	@Override
	public void move() {

		incrementX(xVel);

		if (xCenter < 0) {
			xCenter = panelWidth;
		} else if (xCenter > panelWidth) {
			xCenter = 0;
		}

		incrementY(yVel);
		if (yCenter < 0) {
			yCenter = panelHeight;
		} else if (yCenter > panelHeight) {
			yCenter = 0;
		}
	}

	public void resetShip() {
		xCenter = panelWidth / 2;
		yCenter = panelHeight / 2;

	}

	public void moreSpeed(double n) {
		xVel += moveAngleX(movingAngle) * 0.1;
		yVel += moveAngleY(movingAngle) * 0.1;
	}

	public void lessSpeed(double n) {
		xVel -= moveAngleX(movingAngle) * 0.1;
		yVel -= moveAngleY(movingAngle) * 0.1;

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

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) xCenter - (width / 2), (int) yCenter - (height / 2), width, height);
	}

	public double getShipNoseX() {
		return this.xCenter + Math.cos(rotationAngle) * 14;
	}

	public double getShipNosey() {
		return this.yCenter + Math.sin(rotationAngle) * 14;
	}

	public double getRotationAngle() {
		return rotationAngle;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public AbstractAction goBack() {
		return new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				movingAngle = rotationAngle;
				lessSpeed(movingAngle);

			}
		};
	}

	public AbstractAction go() {
		return new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				movingAngle = rotationAngle;
				moreSpeed(movingAngle);

			}
		};
	}

	public AbstractAction rightT() {
		return new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				increaseRotationAngle();

			}
		};
	}

	public AbstractAction leftT() {
		return new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				decreaseRotationAngle();

			}
		};
	}

	public AbstractAction fire() {
		return new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GamePanel.gunList.add(new Gun(getShipNoseX(), getShipNosey(), rotationAngle));
			}
		};
	}
}
