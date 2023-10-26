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
	boolean[] colStatus;
	boolean[] rowStatus;
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

		colStatus = new boolean[solutionGridDimY];
		rowStatus = new boolean[solutionGridDimX];
		this.setStatuses();

		hints = new NonogramHints(this);
	}

	NonogramGrid(Game g, int[][] solutionGrid) {
		this.game = g;
		this.solutionGridDimX = solutionGrid.length;
		this.solutionGridDimY = solutionGrid[0].length;
		this.solutionGrid = solutionGrid;

		this.playerGrid = new int[solutionGridDimX][solutionGridDimY];

		colStatus = new boolean[solutionGridDimY];
		rowStatus = new boolean[solutionGridDimX];
		this.setStatuses();

		hints = new NonogramHints(this);
	}

	NonogramGrid(Game g, int x, int y) {
		this.game = g;
		solutionGrid = new int[x][y];
		colStatus = new boolean[solutionGridDimY];
		rowStatus = new boolean[solutionGridDimX];
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

		colStatus = new boolean[solutionGridDimY];
		rowStatus = new boolean[solutionGridDimX];
		this.setStatuses();

		hints = new NonogramHints(this);
	}

	private void setStatuses() {
		Arrays.fill(colStatus, false);
		Arrays.fill(rowStatus, false);
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

	public void checkSolution() {
		// make this check line by line instead
		/*
		 * okay my ideas: we make an array that records which lines are wrong and
		 * constantly update or we make this method take a line and do it line by line
		 * we should have it highlight red if it's wrong
		 * 
		 * eventually, i would like this to be based on what does and does not
		 * contradict with the hints
		 */

		System.out.println(solutionGrid.length);
		System.out.println(solutionGrid[0].length);
		System.out.println(playerGrid.length);
		System.out.println(playerGrid[0].length);

		System.out.println(solutionGridDimX);
		System.out.println(solutionGridDimY);

		boolean status;
		for (int i = 0; i < solutionGrid.length; i++) {
			status = true;
			for (int j = 0; j < solutionGrid[0].length; j++) {
				int playerSquare = 0;
				if (playerGrid[i][j] == 3) {
					playerSquare = 0;
				} else {
					playerSquare = playerGrid[i][j];
				}
				if (playerSquare > 0 && solutionGrid[i][j] != playerSquare) {
					status = false;
				}
			}
			rowStatus[i] = status;
		}

		for (int i = 0; i < solutionGrid.length; i++) {
			status = true;
			for (int j = 0; j < solutionGrid[0].length; j++) {
				int playerSquare = 0;
				if (playerGrid[i][j] == 3) {
					playerSquare = 0;
				} else {
					playerSquare = playerGrid[i][j];
				}
				if (playerSquare > 0 && solutionGrid[i][j] != playerSquare) {
					status = false;
				}
			}
			colStatus[i] = status;
		}

		int correct = 0;
		for (boolean i : rowStatus) {
			if (i) {
				correct++;
			}
		}

		for (boolean i : colStatus) {
			if (i) {
				correct++;
			}
		}

		if (correct == (colStatus.length + rowStatus.length)) {
			correctness = true;
			game.setProgress();
		} else {
			correctness = false;
		}
	}

	public boolean checkSolution(int[] row) {
		Integer match = matchRow(row);
		ArrayList<Integer> hintRow;
		if (match == null) {
			System.out.println("no row matches");
			return false;
		}
		hintRow = hints.getXHints().get(match);

		ArrayList<Integer> place = new ArrayList<>();
		int hintBlock = 0;
		for (int square : row) {
			if (square == 1) {
				hintBlock++;
			} else if (square == 0) {
				if (hintBlock > 0) {
					place.add(hintBlock);
				}
				hintBlock = 0;
			}
		}

		if (place.size() != hintRow.size()) {
			System.out.println("row contradiction");
			return false;
		}

		for (int i = 0; i < place.size(); i++) {
			if (place.get(i) != hintRow.get(i)) {
				System.out.println("row contradiction");
				return false;
			}
		}

		return true;
	}

	public boolean getRowStatus(int i) {
		return rowStatus[i];
	}

	public boolean getColStatus(int i) {
		return colStatus[i];
	}

	public boolean isCorrect() {
		return correctness;
	}

	private Integer matchRow(int[] row) {
		for (int i = 0; i < solutionGridDimX; i++) {
			int matches = 0;
			for (int j = 0; j < row.length; j++) {
				if (row[j] == playerGrid[i][j]) {
					matches++;
				} else {
					matches = 0;
				}
			}
			if (matches == solutionGridDimX) {
				return i;
			}
		}
		return null;
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
