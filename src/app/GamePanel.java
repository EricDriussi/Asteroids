package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class GamePanel extends JComponent {

	static CopyOnWriteArrayList<Asteroid> asteroidList = new CopyOnWriteArrayList<>();
	static CopyOnWriteArrayList<Gun> gunList = new CopyOnWriteArrayList<>();
	static SpaceShip ship = new SpaceShip();

	int width = MainFrame.width;
	int height = MainFrame.height;

	public GamePanel() {

		for (int i = 0; i < 10; i++) {
			int randomX = (int) (Math.random() * (width - 40) + 1);
			int randomY = (int) (Math.random() * (height - 40) + 1);

			asteroidList.add(new Asteroid(Asteroid.getpolyXArray(randomX), Asteroid.getpolyYArray(randomY), 13, randomX,
					randomY));
		}

		this.setActionMap(myActionMap());
		this.setInputMap(JComponent.WHEN_FOCUSED, myInputMap());

	}

	private InputMap myInputMap() {
		InputMap out = new InputMap();
		out.put(KeyStroke.getKeyStroke("W"), "forewards");
		out.put(KeyStroke.getKeyStroke("S"), "backwards");
		out.put(KeyStroke.getKeyStroke("D"), "right");
		out.put(KeyStroke.getKeyStroke("A"), "left");
		out.put(KeyStroke.getKeyStroke("SPACE"), "fire");

		return out;
	}

	private ActionMap myActionMap() {
		ActionMap out = new ActionMap();
		out.put("backwards", ship.goBack());
		out.put("forewards", ship.go());
		out.put("right", ship.rightT());
		out.put("left", ship.leftT());
		out.put("fire", ship.fire());

		return out;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D settings = (Graphics2D) g;

		AffineTransform identity = new AffineTransform();

		settings.setColor(Color.BLACK);

		settings.fillRect(0, 0, width, height);

		settings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		settings.setPaint(Color.WHITE);

		checkCol();

		for (Asteroid as : asteroidList) {

			settings.draw(as);
		}

		ship.move();

		settings.setTransform(identity);

		settings.translate(ship.getXCenter(), ship.getYCenter());
		settings.rotate(Math.toRadians(ship.getRotationAngle()));

		settings.draw(ship);

		for (Gun gun : gunList) {
			gun.move();
			settings.setTransform(identity);

			settings.translate(gun.getXCenter(), gun.getYCenter());

			settings.draw(gun);
		}

	}

	void checkCol() {
		for (Asteroid check : GamePanel.asteroidList) {

			Rectangle checkBounds = check.getBounds();

			for (Asteroid otherAsteroid : GamePanel.asteroidList) {

				Rectangle otherBounds = otherAsteroid.getBounds();

				if (otherAsteroid != check && otherBounds.intersects(checkBounds)) {


					int tempX = check.xDir;
					int tempY = check.yDir;

					check.xDir = otherAsteroid.xDir;
					check.yDir = otherAsteroid.yDir;

					otherAsteroid.xDir = tempX;
					otherAsteroid.yDir = tempY;

				}

			}
			check.move();
			
			Rectangle shipBox = GamePanel.ship.getBounds();
			if (checkBounds.intersects(shipBox)) {

				GamePanel.ship.resetShip();

				GamePanel.ship.setxVel(0);
				GamePanel.ship.setyVel(0);

			}
			
			for (Gun gun : GamePanel.gunList) {
				if (checkBounds.contains(gun.getXCenter(), gun.getYCenter())) {
					GamePanel.gunList.remove(gun);
					GamePanel.asteroidList.remove(check);
				}
			}
		}
	}
}
