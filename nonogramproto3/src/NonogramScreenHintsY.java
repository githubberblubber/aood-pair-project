import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class NonogramScreenHintsY extends JPanel{
		NonogramGrid grids;
		int[] colStatus;
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
		NonogramScreenHintsY(NonogramGrid grids){
			this.grids = grids;
			this.colStatus = new int[grids.getGridDimY()];
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
			this.setBounds(0, leftCornerY, leftCornerX, gridHeightMax);
			this.addMouseListener(new mouse());
		}
		public void paintComponent(Graphics g) {
			g.setColor(new Color(155, 190, 199));
	        g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.green);
			int y = (gridThickness + squareLength) / 2;
			int x = 200 - 5 * gridThickness;
			int fontSize = (gridThickness + squareLength) / 3;
			for(int i = 0; i < hints.getXHints().size(); i++) {
				ArrayList<Integer> hintRow = hints.getXHints().get(i);
				if (colStatus[i] == 0) {
					g.setColor(Color.red);
				} else if (colStatus[i] == 1){
					g.setColor(Color.black);
				} else {
					g.setColor(Color.green);
				}
				for (int j = hintRow.size() - 1; j >= 0; j--) {
					g.setFont(new Font("Sans Serif", Font.PLAIN, fontSize));
					g.drawString(hintRow.get(j).toString(), x, y);
					x -= fontSize;
				}
				y += gridThickness + squareLength;
				x = 200 - 5 * gridThickness;
			}
		}
		public class mouse extends MouseFiller{
			public void mouseClicked(MouseEvent e) {
				
			}
		}
		public void updateColStatus(int[] cols) {
			colStatus = cols;
			for (int i: colStatus) {
				System.out.println(i + " ");
			}
			repaint();
		}
	}