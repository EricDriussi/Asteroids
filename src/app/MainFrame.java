package app;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static int width = 1000;
	public static int height = 800;
	
	public static boolean keyHeld = false;
	
	public static int keyCode;

	public static void main(String[] args) {

		new MainFrame();
	}

	public MainFrame() {

		this.setSize(width, height);
		this.setTitle("Asteroids!!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				
				keyHeld = false;

			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_W) {
					keyCode = e.getKeyCode();
					keyHeld = true;
					
				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					keyCode = e.getKeyCode();
					keyHeld = true;
					
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					keyCode = e.getKeyCode();
					keyHeld = true;
					
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					keyCode = e.getKeyCode();
					keyHeld = true;
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					keyCode = e.getKeyCode();
					keyHeld = true;
				}

			}
		});
		
		JComponent panel = new GamePanel();
		this.add(panel, BorderLayout.CENTER);

		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

		executor.scheduleAtFixedRate(new Repaint(this), 0L, 20L, TimeUnit.MILLISECONDS);

		this.setVisible(true);

	}

}
