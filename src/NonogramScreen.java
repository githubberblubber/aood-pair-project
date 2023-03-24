import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class NonogramScreen{
	JFrame screen;
	JPanel screenGrid;
	NonogramGrid g;
	NonogramScreen(){
		this.screen = new JFrame();
		this.screen.setSize(600, 800);
		this.screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.screenGrid = new nonogramScreenGrid(g);
		this.screen.add(this.screenGrid);
		
		screen.setVisible(true);
		
	}
	
	NonogramScreen(NonogramGrid g){
		this.screen = new JFrame();
		this.screen.setSize(600, 800);
		this.screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.screenGrid = new nonogramScreenGrid(g);
		this.screen.add(this.screenGrid);
		this.g = g;

		screen.setVisible(true);
	}
	
	public class nonogramScreenGrid extends JPanel{
		NonogramGrid grids;
		int leftCornerX;
		int leftCornerY;
		int gridWidth;
		int gridHeight;
		int gridSize;
		int gridThickness;
		int squareLength;
		nonogramScreenGrid(NonogramGrid grids){
			this.grids = grids;
			this.leftCornerX = 50;
			this.leftCornerY = 50;
			this.gridWidth = 500;
			this.gridHeight = 500;
			this.gridSize = grids.getGridDimX();
			this.gridThickness = 5;
			this.squareLength = ((gridWidth - gridThickness) / gridSize) - gridThickness;
			this.setBounds(leftCornerX, leftCornerY, gridWidth, gridHeight);
			this.addMouseListener(new mouse());
		}
		public void paintComponent(Graphics g) {
            g.setColor(Color.red);
			g.fillRect(leftCornerX, leftCornerY, gridWidth, gridHeight);
			int pointerX = leftCornerX;
			int pointerY = leftCornerY;
			for (int[] line: grids.getPlayerGrid()) {
                pointerY += gridThickness;
                for (int square: line) {
                    pointerX += gridThickness;
                    if (square==1)
                        g.setColor(Color.black);
                    else if(square==0)
                        g.setColor(Color.white);
                    g.fillRect(pointerX, pointerY, squareLength, squareLength);
                    pointerX += squareLength;
                }	
                pointerX = leftCornerX;
                pointerY += squareLength;
			}
		}
		public class mouse extends mouseFiller{
			public void mouseClicked(MouseEvent e) {
				int row = (e.getY() - gridThickness - leftCornerX) / (gridThickness + squareLength);
				int col = (e.getX() - gridThickness - leftCornerY) / (gridThickness + squareLength);
				if (row < grids.getGridDimX() && col < grids.getGridDimX()) {
					grids.setPlayerGrid(row, col);
					repaint();
				}
			}
		}
	}
}
