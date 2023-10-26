package fullstack;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import driver.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class NonogramScreen{
	static JFrame screen;
	JPanel screenGrid, toggleMode;
	NonogramScreenHintsX hintsX;
	NonogramScreenHintsY hintsY;
	NonogramGrid g;
	Game game;
	Color beige = new Color(226, 195, 145);
    Color dustyBlue = new Color(155, 190, 199);
	static NextPuzzleButton nextPuzzleButton;

	NonogramScreen(NonogramGrid g, Game game){
		this.g = g;
		this.game = game;
		setUp();
	}
	public NonogramScreen(NonogramGrid g){
		this.g = g;
		setUp();
	}
    private void setUp() {
   	 	screen = new JFrame();
        screen.setSize(768, 768);
        screen.setLayout(null);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        this.toggleMode = new ToggleMenu(g);
        screen.add(this.toggleMode, 0);
      
        this.hintsX = new NonogramScreenHintsX(g);
        screen.add(this.hintsX);
        
        this.hintsY = new NonogramScreenHintsY(g);
        screen.add(this.hintsY);
        
        screenGrid = new NonogramScreenGrid(g, this.hintsX, this.hintsY);
        screen.add(screenGrid);
        
        nextPuzzleButton = new NextPuzzleButton();
    	nextPuzzleButton.setText("Next Puzzle");
    	nextPuzzleButton.setBounds(0, 0, 200, 50);
    	nextPuzzleButton.setVisible(false);
        screen.add(nextPuzzleButton);
        
        screen.getContentPane().setBackground(dustyBlue);
        screen.setVisible(true);   
    }
    public static void win() {
    	nextPuzzleButton.setVisible(true);
    }
    public static class NextPuzzleButton extends JButton implements ActionListener {
    	NextPuzzleButton(){
    		this.addActionListener(this);
    	}
    	public void actionPerformed(ActionEvent e) {
    		Game.win();
		}
    }
}