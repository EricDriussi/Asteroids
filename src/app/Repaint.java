package app;

public class Repaint implements Runnable {

	MainFrame board;
	
	public Repaint(MainFrame frame) {
		this.board = frame;
	}
	
	@Override
	public void run() {
		
		board.repaint();

	}

}
