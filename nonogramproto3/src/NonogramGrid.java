import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;

public class NonogramGrid {
	int solutionGridDimX;
	int solutionGridDimY;
	int[][] solutionGrid;
	int[][] playerGrid;
	int mode = 1;
	int[] colStatus;
	int[] rowStatus;
	boolean correctness = false;
	Game game;
	Puzzles puzzles;
	BufferedImage buffered;
	NonogramHints hints;

	/*
	 * white - 0 black - 1 eliminate - 2 mark - 3
	 */

	NonogramGrid(Game g, int solutionGridDimX, int solutionGridDimY, String file) {
		game = g;
		this.solutionGridDimX = solutionGridDimX;
		this.solutionGridDimY = solutionGridDimY;
		solutionGrid = new int[solutionGridDimX][solutionGridDimY];
		Image image;
		try {
			image = ImageIO.read(new File(file));
		} catch (Exception ex) {
		}
		// this.nonogramFromImage(image);

		this.playerGrid = new int[solutionGridDimX][solutionGridDimY];

		colStatus = new int[solutionGridDimY];
		rowStatus = new int[solutionGridDimX];
		this.setStatuses();

		hints = new NonogramHints(this);
	}

	NonogramGrid(Game g, int[][] solutionGrid) {
		this.game = g;
		this.solutionGridDimX = solutionGrid.length;
		this.solutionGridDimY = solutionGrid[0].length;
		this.solutionGrid = solutionGrid;

		this.playerGrid = new int[solutionGridDimX][solutionGridDimY];

		colStatus = new int[solutionGridDimY];
		rowStatus = new int[solutionGridDimX];
		this.setStatuses();

		hints = new NonogramHints(this);
	}

	NonogramGrid(Game g, int x, int y) {
		this.game = g;
		solutionGrid = new int[x][y];
		colStatus = new int[solutionGridDimY];
		rowStatus = new int[solutionGridDimX];
		this.setStatuses();

		this.playerGrid = new int[solutionGridDimX][solutionGridDimY];

		hints = new NonogramHints(this);
	}

	NonogramGrid(Game g) {
		game = g;
		puzzles = g.getPuzzles();
		solutionGrid = g.getPuzzles().getPuzzle(g.progress);
		this.solutionGridDimX = solutionGrid.length;
		this.solutionGridDimY = solutionGrid[0].length;

		this.playerGrid = new int[solutionGridDimX][solutionGridDimY];

		colStatus = new int[solutionGridDimY];
		rowStatus = new int[solutionGridDimX];
		this.setStatuses();

		hints = new NonogramHints(this);
	}

	private void setStatuses() {
		Arrays.fill(colStatus, 1);
		Arrays.fill(rowStatus, 1);
	}

	public int getGridDimX() {
		return solutionGridDimX;
	}

	public int getGridDimY() {
		return solutionGridDimY;
	}

	public int[][] getGrid() {
		return solutionGrid;
	}

	public int[][] getPlayerGrid() {
		return playerGrid;
	}

	public void setPlayerGrid(int x, int y) {
		playerGrid[x][y] = mode;
	}

	// NonogramGrid may potentially have image input for solutionGrid arrays

	public void setMode(int i) {
		mode = i;
	}

	public int getMode() {
		return mode;
	}

	public boolean checkWholeSolution() {
		for(int i = 0; i < playerGrid.length; i++) {
			for(int j = 0; j < playerGrid[0].length; j++) {
				if(playerGrid[i][j] != solutionGrid[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void checkLineSolution(int index, int axis) {
		//returns a 0 if wrong, 1 if incomplete, 2 if correct
		if (axis == 1) {
			rowStatus[index] = 2;
			for(int i = 0; i < this.solutionGridDimY; i++) {
				if (playerGrid[i][index] != solutionGrid[i][index]) {
					if (playerGrid[i][index] == 1) {
						rowStatus[index] = 0;
						i = solutionGridDimY;
					} else {
						rowStatus[index] = 1;
					}
				}
			} 
		} else if (axis == 0) {
			colStatus[index] = 2;
			for(int i = 0; i < this.solutionGridDimX; i++) {
				if (playerGrid[index][i] != solutionGrid[index][i]) {
					if (playerGrid[index][i] == 1) {
						colStatus[index] = 0;
						i = solutionGridDimY;
					} else {
						colStatus[index] = 1;
					}
}
			}
		}
		/*
		 * if axis = 1, cols; if axis = 0, rows
		 * if there is anything wrong with a particular row or col, we can just highlight all the hints red and let the player figure it out
		 */
	}
//	public int checkLineSolution(int index, int axis) {
//		//returns a 0 if wrong, 1 if incomplete, 2 if correct
//		if (axis == 0) {
//			for(int i = 0; i < this.solutionGridDimY; i++) {
//				if (playerGrid[i][index] != solutionGrid[index][i]) {
//					if (playerGrid[index][i] == 1) {
//						rowStatus[index] = 0;
//					} else {
//						rowStatus[index] = 1;
//					}
//				}
//			} 
//			rowStatus[index] = 2;
//		} else if (axis == 1) {
//			for(int i = 0; i < this.solutionGridDimX; i++) {
//				if (playerGrid[i][index] != solutionGrid[i][index]) {
//					if (playerGrid[i][index] == 1) {
//						colStatus[index] = 0;
//					} else {
//						colStatus[index] = 1;
//					} // this works because solutionGrid has only black (1) or white (0). 
//					// First checks if there is a difference
//					// If player's square is black when it should be white, it is wrong
//					// If player's square is white or red when it should be black, it is incomplete
//				}
//			}
//		}
//		return colStatus[index] = 2;
//		/*
//		 * if axis = 0, rows; if axis = 1, columns
//		 * if there is anything wrong with a particular row or col, we can just highlight all the hints red and let the player figure it out
//		 */
//	}

	public int[] getRowStatus() {
		return rowStatus;
	}

	public int[] getColStatus() {
		return colStatus;
	}

	public boolean isCorrect() {
		return correctness;
	}
	/*
	 * private void nonogramFromImage(Image image) { image =
	 * image.getScaledInstance(solutionGridDimX, solutionGridDimY,
	 * Image.SCALE_SMOOTH); ImageFilter filter = new GrayFilter(true, 30);
	 * ImageProducer producer = new FilteredImageSource(image.getSource(), filter);
	 * buffered = (BufferedImage) Toolkit.getDefaultToolkit().createImage(producer);
	 * for(int x=0; x<buffered.getHeight();x++) { for(int y=0;
	 * y<buffered.getWidth(); y++) { int start = buffered.getRGB(x, y); //invert
	 * color buffered.setRGB(x, y, start); } } }
	 * 
	 * private void reduceWeakEdges(BufferedImage image) { int lowerEdgeThreshold =
	 * 200; int upperEdgeThreshold = 255; for(int x=0; x<image.getHeight();x++) {
	 * for(int y=0; y<image.getWidth(); y++) { if(image.getRGB(x,
	 * y)<=lowerEdgeThreshold) { image.setRGB(x, y, 1); } else { image.setRGB(x, y,
	 * 0 ); } } }
	 * 
	 * }
	 */

	public String toString() {
		String s = "playerGrid \n";
		for (int[] line : playerGrid) {
			for (int square : line) {
				if (square == 0) {
					s = s + ". ";
				} else if (square == 1) {
					s = s + "* ";
				} else if (square == 2) {
					s = s + "x ";
				} else if (square == 3) {
					s = s + "| ";
				}
			}
			s = s + "\n";
		}
		s = s + "\n" + "solutionGrid \n";
		for (int[] line : solutionGrid) {
			for (int square : line) {
				if (square == 0) {
					s = s + ". ";
				} else if (square == 1) {
					s = s + "* ";
				} else if (square == 2) {
					s = s + "x ";
				}
			}
			s = s + "\n";
		}
		return s;
	}

}
