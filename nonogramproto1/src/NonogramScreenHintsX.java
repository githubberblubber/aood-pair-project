import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class NonogramScreenHintsX extends JPanel{
		NonogramGrid grids;
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
		NonogramHints hints;
		NonogramScreenHintsX(NonogramGrid grids){
			this.grids = grids;
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
			hints = new NonogramHints(grids);
			int numberBlockLength = leftCornerX / hints.xHints.size();
			
			gridWidth = (squareLength + gridThickness) * grids.getGridDimX() + gridThickness;
			gridHeight = (squareLength + gridThickness) * grids.getGridDimY() + gridThickness;
			this.setBounds(leftCornerX, 0, gridWidthMax, leftCornerY);
			this.addMouseListener(new mouse());
		}
		public void paintComponent(Graphics g) {
//          g.setColor(Color.black);
//			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.red);
			int x = (gridThickness + squareLength) / 2;
			int y = 200 - 3 * gridThickness;
			int fontSize = (gridThickness + squareLength) / 3;
			for(int i = 0; i < hints.getYHints().size(); i++) {
				ArrayList<Integer> hintRow = hints.getYHints().get(i);
				for (int j = hintRow.size() - 1; j >= 0; j--) {
					g.setFont(new Font("Sans Serif", Font.PLAIN, fontSize));
					g.drawString(hintRow.get(j).toString(), x, y);
					y -= fontSize;
				}
				x += gridThickness + squareLength;
				y = 200 - 3 * gridThickness;
			}
		}
		public class mouse extends MouseFiller{
			public void mouseClicked(MouseEvent e) {
				
			}
		}
	}