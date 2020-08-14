package app;

import java.awt.BorderLayout;
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

	public MainFrame() {

		this.setSize(width, height);
		this.setTitle("Asteroids!!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComponent panel = new GamePanel();
		this.add(panel, BorderLayout.CENTER);

		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
		executor.scheduleAtFixedRate(new Repaint(this), 0L, 20L, TimeUnit.MILLISECONDS);

		this.setVisible(true);

	}

}
