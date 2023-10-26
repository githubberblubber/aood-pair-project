import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fullstack.NonogramGrid;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class NonogramScreen {
    JFrame screen;
    JPanel screenGrid;
    NonogramGrid g;
    NonogramScreen() {
        this.screen = new JFrame();
        this.screen.setSize(600, 800);
        this.screenGrid = new NonogramScreenGrid(g);
        this.screen.add(this.screenGrid);

        screen.setVisible(true);

    }
    
    NonogramScreen(NonogramGrid g) {
        this.screen = new JFrame();
        this.screen.setSize(600, 800);
        this.screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenGrid = new NonogramScreenGrid(g);
        this.screen.add(this.screenGrid);
        this.g = g;

        screen.setVisible(true);
    }
    
    public class NonogramScreenGrid extends JPanel {
        NonogramGrid grids;
        int leftCornerX;
        int leftCornerY;
        int gridWidth;
        int gridHeight;
        int gridThickness;
        int squareLength;
        int gridDimX;
        int gridDimY;
        //		int[][][] gridSquareCoordinates;
        NonogramScreenGrid(NonogramGrid grids) {
            this.leftCornerX = 100;
            this.leftCornerY = 100;
            this.gridWidth = 500;
            this.gridHeight = 500;
            this.setBounds(leftCornerX, leftCornerY, gridWidth, gridHeight);
            this.grids = grids;

            gridThickness = 5;
            squareLength = 25;
            gridDimX = grids.getGridDimX();
            gridDimY = grids.getGridDimY();

            this.addMouseListener(new mouse());
            //			gridSquareCoordinates = new int[gridSize][gridSize][];
            //			
            //			int pointerX = 0;
            //			int pointerY = 0;
            //			for(int i = 0; i < gridSize; i++) {
            //				pointerX = gridThickness;
            //				pointerY += gridThickness;
            //				for (int j = 0; i < gridSize; j++) {
            //					int[] coordinates = {pointerX, pointerY, pointerX + squareLength, pointerY + squareLength}; 
            //					//leftX, topY, rightX, bottomY // this is to mark the bounds of a square 
            //					// although computationally inefficient, gridSquareCoordinates can be looped through to find 
            //					// whether the mouse click is within coordinates
            //					gridSquareCoordinates[j][i] = coordinates;
            //					pointerX += squareLength + gridThickness;
            //				}
            //				pointerY += squareLength;
            //			}
        }
        
        public void paintComponent(Graphics g) {
            g.setColor(Color.red);
            g.fillRect(100, 100, 400, 400);
            int pointerX = 100;
            int pointerY = 100;
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
                pointerX = 100;
                pointerY += squareLength;
            }
        }
        
        public class mouse implements MouseListener {
            public void mouseClicked(MouseEvent e) {
                int row = (e.getY() - gridThickness - leftCornerX) / (gridThickness + squareLength);
                int col = (e.getX() - gridThickness - leftCornerY) / (gridThickness + squareLength);
                //				System.out.println(row + " " + col);
                if (row < grids.getGridDimX() && col < grids.getGridDimY()) {
                    grids.setPlayerGrid(row, col);
                    repaint();
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        }
    }
}

