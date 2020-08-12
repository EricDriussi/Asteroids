package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class GamePanel extends JComponent {

	public static ArrayList<Asteroid> asteroidList = new ArrayList<>();
	public static ArrayList<Gun> gunList = new ArrayList<>();

	int[] xArray = Asteroid.sPolyXArray;
	int[] yArray = Asteroid.sPolyYArray;

	int width = MainFrame.width;
	int height = MainFrame.height;

	SpaceShip ship = new SpaceShip();

	public GamePanel() {

		for (int i = 0; i < 10; i++) {
			int randomX = (int) (Math.random() * (MainFrame.width - 40) + 1);
			int randomY = (int) (Math.random() * (MainFrame.height - 40) + 1);

			asteroidList.add(new Asteroid(Asteroid.getpolyXArray(randomX), Asteroid.getpolyYArray(randomY), 13, randomX,
					randomY));
			Asteroid.list = asteroidList;
		}

	}

	@Override
	public void paint(Graphics g) {
		// super.paint(g);

		Graphics2D settings = (Graphics2D) g;

		AffineTransform identity = new AffineTransform();

		settings.setColor(Color.BLACK);

		settings.fillRect(0, 0, width, height);

		settings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		settings.setPaint(Color.WHITE);

		for (Asteroid as : asteroidList) {
			if (as.onScreen) {

				as.move(ship, gunList);
				settings.draw(as);
			}
		}

		if (MainFrame.keyHeld && MainFrame.keyCode == KeyEvent.VK_D) {

			ship.increaseRotationAngle();

		} else if (MainFrame.keyHeld && MainFrame.keyCode == KeyEvent.VK_A) {

			ship.decreaseRotationAngle();

		} else if (MainFrame.keyHeld && MainFrame.keyCode == KeyEvent.VK_W) {

			ship.setMovingAngle(ship.getRotationAngle());

			ship.increaseXVel(ship.moveAngleX(ship.getMovingAngle()) * 0.1);
			ship.increaseYVel(ship.moveAngleY(ship.getMovingAngle()) * 0.1);

		} else if (MainFrame.keyHeld && MainFrame.keyCode == KeyEvent.VK_S) {

			ship.setMovingAngle(ship.getRotationAngle());

			ship.decreaseXVel(ship.moveAngleX(ship.getMovingAngle()) * 0.1);
			ship.decreaseYVel(ship.moveAngleY(ship.getMovingAngle()) * 0.1);
			
		}else if(MainFrame.keyHeld && MainFrame.keyCode == KeyEvent.VK_SPACE) {
			
			gunList.add(new Gun(ship.getShipNoseX(), ship.getShipNosey(), ship.getRotationAngle()));
			
			System.out.println("yes");
			
		}
		

		ship.move();

		settings.setTransform(identity);

		settings.translate(ship.getXCenter(), ship.getYCenter());
		settings.rotate(Math.toRadians(ship.getRotationAngle()));

		settings.draw(ship);
		
		for (Gun gun : gunList) {
			gun.move();
			if (gun.onScreen) {
				settings.setTransform(identity);

				settings.translate(gun.getxCenter(), gun.getyCenter());

				settings.draw(gun);
			}
		}

	}

}


































