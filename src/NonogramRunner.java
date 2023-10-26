import fullstack.NonogramGrid;
import fullstack.NonogramScreen;

public class NonogramRunner{
	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		int[][] first =
			{
					{0, 0,  1, 0, 0},
					{0,  1, 0,  1, 0},
					{ 1,  1,  1,  1,  1},
					{ 1, 0, 0, 0,  1},
					{ 1, 0, 0, 0,  1},
			};
		NonogramGrid n = new NonogramGrid(first);
		NonogramScreen s = new NonogramScreen(n);
		System.out.print(n);
	}
}
