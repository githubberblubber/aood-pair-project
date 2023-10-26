import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class NonogramScreen{
	JFrame screen;
	JPanel screenGrid, toggleMode;
	NonogramScreenHintsX hintsX;
	NonogramScreenHintsY hintsY;
	JButton checkButton;
	NonogramGrid g;
	Game game;
	Color beige = new Color(226, 195, 145);
    Color dustyBlue = new Color(155, 190, 199);
	
	NonogramScreen(NonogramGrid g, Game game){
		this.g = g;
		this.game = game;
		setUp();
	}
	NonogramScreen(NonogramGrid g){
		this.g = g;
		setUp();
	}
    private void setUp() {
   	 	this.screen = new JFrame();
        this.screen.setSize(768, 768);
        this.screen.setLayout(null);
        this.screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        this.toggleMode = new ToggleMenu(g);
        this.screen.add(this.toggleMode, 0);
        
        this.checkButton = new checkButton(g);
        this.screen.add(this.checkButton);
      
        this.hintsX = new NonogramScreenHintsX(g);
        this.screen.add(this.hintsX);
        
        this.hintsY = new NonogramScreenHintsY(g);
        this.screen.add(this.hintsY);
        
        this.screenGrid = new NonogramScreenGrid(g, this.hintsX, this.hintsY);
        this.screen.add(this.screenGrid);
        
        this.screen.getContentPane().setBackground(dustyBlue);
        this.screen.setVisible(true);
    }
	public class checkButton extends JButton{
		NonogramGrid grid;
		int leftCornerX;
		int leftCornerY;
		int gridRegionX;
		int gridRegionY;
		int gridWidth;
		int gridHeight;
		int gridSizeX;
		int gridSizeY;
		int gridThickness;
		int squareLength;
		checkButton(NonogramGrid grid){
			this.grid = grid;
			this.leftCornerX = 750;
			this.leftCornerY = 750;
			this.gridWidth = 200;
			this.gridHeight = 30;
			this.setBounds(600, 710, 100, 100);
			this.addMouseListener(new mouse());
		}
		public void paintComponent(Graphics g) {
            g.setColor(Color.blue);
			g.fillRect(0, 0, gridWidth, gridHeight);
		}
		public class mouse extends MouseFiller{
			public void mouseClicked(MouseEvent e) {
				System.out.println(grid.checkWholeSolution());
			}
		}
	}
}