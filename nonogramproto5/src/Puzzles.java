import java.util.ArrayList;
import java.util.List;

public class Puzzles {
	List<int[][]> puzzles;
	
	Puzzles(){
		puzzles = new ArrayList<int[][]>();
		puzzles.add(new int[][] {
			{1, 0, 1, 0, 1},
			{0, 0, 1, 0, 0},
			{0, 1, 0, 1, 1},
			{0, 0, 1, 1, 1},
			{0, 0, 1, 1, 1},
		});
		puzzles.add(new int[][] {
			{0, 0, 0, 1, 1, 1, 1, 0, 1, 0},
			{0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
			{1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
			{1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
			{0, 1, 1, 0, 1, 1, 0, 1, 1, 1},
			{1, 1, 0, 0, 0, 0, 1, 0, 0, 1},
			{0, 0, 1, 1, 1, 1, 0, 1, 1, 1},
			{0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
			{0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
			{0, 0, 0, 1, 1, 1, 1, 1, 1, 1}
		});
	}
	
	public void addPuzzle(int[][] puzzle) {
		puzzles.add(puzzle);
	}
	
	public int[][] getPuzzle(int progress){
		if(puzzles.size() > progress) {
			return puzzles.get(progress);
		} 
		return null;
	}
	
}



