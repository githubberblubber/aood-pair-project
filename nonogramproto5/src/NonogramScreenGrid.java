import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class NonogramScreenGrid extends JPanel{
		NonogramGrid grids;
		NonogramScreenHintsX x;
		NonogramScreenHintsY y;
		int leftCornerX;
		int leftCornerY;
		int gridRegionX;
		int gridRegionY;
		int gridWidthMax;
		int gridHeightMax;
		int gridWidth;
		int gridHeight;
		int gridSizeX;
		int gridSizeY;
		int gridThickness;
		int squareLength;
		NonogramScreenGrid(NonogramGrid grids, NonogramScreenHintsX x, NonogramScreenHintsY y){
			this.grids = grids;
			this.x = x;
			this.y = y;
			this.leftCornerX = 200;
			this.leftCornerY = 200;
			this.gridRegionX = 500;
			this.gridRegionY = 500;
			this.gridWidthMax = 500;
			this.gridHeightMax = 500;
			this.gridSizeX = grids.getGridDimX();
			this.gridSizeY = grids.getGridDimY();
			this.gridThickness = 5;
			int widthSquare = ((gridWidthMax - gridThickness) / gridSizeX) - gridThickness;
			int heightSquare = ((gridHeightMax - gridThickness) / gridSizeY) - gridThickness;
			if (widthSquare < heightSquare)
				this.squareLength = widthSquare;
			else 
				this.squareLength = heightSquare;
			gridWidth = (squareLength + gridThickness) * grids.getGridDimX() + gridThickness;
			gridHeight = (squareLength + gridThickness) * grids.getGridDimY() + gridThickness;
			this.setBounds(leftCornerX, leftCornerY, 500, 500);
			this.addMouseListener(new mouse());
		}
		public void paintComponent(Graphics g) {
            g.setColor(Color.red);
			g.fillRect(0, 0, gridWidthMax, gridHeightMax);
			int pointerX = 0;
			int pointerY = 0;
			for (int[] line: grids.getPlayerGrid()) {
                pointerY += gridThickness;
                for (int square: line) {
                    pointerX += gridThickness;
            		Color[] buttonColors = {Color.white, Color.black, Color.green, Color.red};
            		g.setColor(buttonColors[square]);
                    g.fillRect(pointerX, pointerY, squareLength, squareLength);
                    pointerX += squareLength;
                }	
                pointerX = 0;
                pointerY += squareLength;
			}
		}
		public class mouse extends MouseFiller{
			public void mouseClicked(MouseEvent e) {
				int pix_col = (e.getX() - gridThickness);
				int pix_row = (e.getY() - gridThickness);
				int col = pix_col / (gridThickness + squareLength);
				int row = pix_row / (gridThickness + squareLength);
				if (pix_col > -1 && pix_row > -1 && row < grids.getGridDimY() 
					&& col < grids.getGridDimX() && row > -1 && col > -1) {
					grids.setPlayerGrid(row, col);
					grids.checkLineSolution(row, 0);
					grids.checkLineSolution(col, 1);
					x.updateRowStatus(grids.getRowStatus());
					y.updateColStatus(grids.getColStatus());
					if(grids.checkWholeSolution()) {
						NonogramScreen.win();
						System.out.println(true);
					}
					repaint();
				}
			}
		}
		
	}