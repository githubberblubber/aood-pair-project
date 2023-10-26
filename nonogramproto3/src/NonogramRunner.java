public class NonogramRunner{
	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		int[][] test =
			{
					{1, 0, 1, 0, 1},
					{0, 0, 1, 0, 0},
					{0, 1, 0, 1, 1},
					{0, 0, 1, 1, 1},
					{0, 0, 1, 1, 1},
			};
		Game g = new Game();
		NonogramGrid n = new NonogramGrid(g, test);
		NonogramScreen s = new NonogramScreen(n, g);
		System.out.println(new NonogramHints(n));
		int[][] test2 =
			{
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

			};
		Game g1 = new Game();
		NonogramGrid n1 = new NonogramGrid(g1, test2);
		NonogramScreen s1 = new NonogramScreen(n1, g1);
	}
}
