import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Game {
	static Puzzles puzzles;
	static int mode;
	static int x = 5;
	static int y = 5;
	static int progress = 0;
	static boolean complete;

	/*
	 * either: each level is one game OR the game controls the progression of levels
	 */
	
	public static JFrame frontScreen;
	static JButton startButton;
	public static NonogramScreen level;
	public static void main(String[] args) {
		run();
		complete = false;
		puzzles = new Puzzles();
		mode = 1;
	}
	
	public static void run() {
		frontScreen = new JFrame();
		frontScreen.setSize(768, 768);
		frontScreen.setLayout(null);
		startButton = new StartButton();
		frontScreen.add(startButton);
		frontScreen.setVisible(true);
		frontScreen.setDefaultCloseOperation(frontScreen.EXIT_ON_CLOSE);
	}
	public static class StartButton extends JButton implements ActionListener{
		StartButton(){
			this.setBounds(frontScreen.getWidth() / 2 - 100, frontScreen.getHeight() / 2 - 25, 200, 50);
			this.setText("Nono-ing grams");
			this.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			frontScreen.setVisible(false);
			System.out.println(true);
			NonogramScreen level = new NonogramScreen(new NonogramGrid(puzzles.getPuzzle(progress)));
		}
	}
	
	public static void win() {
		setProgress();
		level = new NonogramScreen(new NonogramGrid(puzzles.getPuzzle(progress)));
	}
	
	public void setMode(int m) {
		mode = m;
	}

	public int getMode() {
		return mode;
	}
	
	public Puzzles getPuzzles() {
		return puzzles;
	}
	
	static public void setProgress() {
		progress++;
	}

}



