import java.util.ArrayList;
public class NonogramHints {
	int[][] solutionGrid;
	ArrayList<ArrayList<Integer>> xHints;
	ArrayList<ArrayList<Integer>> yHints;
	int dimX;
	int dimY;
	
	NonogramHints(NonogramGrid n){
		solutionGrid = n.getGrid();
		xHints = new ArrayList<>();
		yHints = new ArrayList<>();
		dimX = n.getGridDimX();
		dimY = n.getGridDimY();
		
		for(int[] line: solutionGrid) {
			ArrayList<Integer> place = new ArrayList<>();
			int hintBlock = 0;
			for(int square: line) {
				if(square==1) {
					hintBlock++;
				} else if(square==0) {
					if(hintBlock>0) {
						place.add(hintBlock);
					}
					hintBlock = 0;
				}
			}
			if (hintBlock>0) {
				place.add(hintBlock);
			}
			xHints.add(place);
		}
		
		for(int i=0; i<solutionGrid[0].length; i++) {
			ArrayList<Integer> place = new ArrayList<>();
			int hintBlock = 0;
			for(int z=0; z<solutionGrid.length; z++) {
				if(solutionGrid[z][i]==1) {
					hintBlock+=1;
				} else if(solutionGrid[z][i]==0) {
					if(hintBlock>0) {
						place.add(hintBlock);
					}
					hintBlock = 0;
				}
			}
			if (hintBlock>0) {
				place.add(hintBlock);
			}
			yHints.add(place);
		}
		
	}
	
	public ArrayList<ArrayList<Integer>> getXHints(){
		return xHints;
	}
	
	public ArrayList<ArrayList<Integer>> getYHints(){
		return yHints;
	}
	
	public String toString() {
		return "Rows: " + xHints.toString() + "\n" + "Cols: " + yHints.toString() + "\n";
	}
}



