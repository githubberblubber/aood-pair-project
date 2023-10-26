public class Game {
	NonogramGrid grid;
	Puzzles puzzles;
	int mode;
	int x = 5;
	int y = 5;
	int progress = 0;
	boolean complete;

	/*
	 * either: each level is one game OR the game controls the progression of levels
	 */

	Game() {
		complete = false;
		puzzles = new Puzzles();
		mode = 1;
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

	public NonogramGrid getGrid() {
		if(puzzles.getPuzzle(progress)==null) {
			complete = true;
			return null;
		} else {
			grid = new NonogramGrid(this, puzzles.getPuzzle(progress));
			return grid;
		}
	}
	
	public void setProgress() {
		progress++;
	}

}



