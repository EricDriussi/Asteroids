package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class GamePanel extends JComponent {

	public ArrayList<Asteroid> list = new ArrayList<>();
	
	int[] xArray = Asteroid.sPolyXArray;
	int[] yArray = Asteroid.sPolyYArray;
	
	int width = MainFrame.width;
	int height = MainFrame.height;
	
	
	public GamePanel() {
		
		for (int i = 0; i < 50; i++) {
			int randomX = (int) (Math.random() * (MainFrame.width-40)+1);
			int randomY = (int) (Math.random() * (MainFrame.height-40)+1);
			
			list.add(new Asteroid(Asteroid.getpolyXArray(randomX), Asteroid.getpolyYArray(randomY), 13, randomX, randomY));
		}
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		//super.paint(g);
		
		Graphics2D settings = (Graphics2D) g;
		
		settings.setColor(Color.BLACK);

		settings.fillRect(0, 0, width, height);
		
		settings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		settings.setPaint(Color.WHITE);

		for (Asteroid as : list) {
			as.move();
			settings.draw(as);
		}
		
	}
	
}






















