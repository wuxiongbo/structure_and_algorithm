package structure.graph;

import java.util.Scanner;

public class DFS {

	int n; // 地图的行
	int m; // 地图的列
	int dx; // 安琪的位置x
	int dy; // 安琪的位置y
	int[][] data; // 邻接矩阵

	int minStep = Integer.MAX_VALUE; // 要走的最小步数,求最小的数。最开始，赋值一个最大的数

	boolean[][] mark; // 标记数据走过的位置
	int[][] next = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public DFS(int n, int m, int dx, int dy, int[][] data, boolean[][] mark) {
		this.n = n;
		this.m = m;
		this.dx = dx;
		this.dy = dy;
		this.data = data;
		this.mark = mark;
	}

	public void dfs(int x, int y, int step) { // x,y表示我的位置，step,当前走过的路径长度

		// 如果到达重点
		if (x == dx && y == dy) {		//枚举了所有的路径
			if (step < minStep) {
				minStep = step;
			}
			return;
		}

		for (int i = 0; i < next.length; i++) {
			int nextX = x + next[i][0];
			int nextY = y + next[i][1];

			if (nextX < 1 || nextX > n || nextY < 1 || nextY > m) {
				continue;
			}

			if (data[nextX][nextY] == 0 && !mark[nextX][nextY]) { // 表示可以走 每个点都有4个方向，
				// 这里有三行代码，是最重要的。

				mark[nextX][nextY] = true;  //
				dfs(nextX, nextY, step + 1);
				mark[nextX][nextY] = false; // 回溯，这行代码很关键。不回溯的话，其他路径经过这个点就走不了了。

			}
		}
	}

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int n = 5;
		int m = 4;

		int[][] data = new int[n + 1][m + 1];


		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				data[i][j] = cin.nextInt();
			}
		}
		
		boolean[][] mark = new boolean[n+1][m+1];
		int x = cin.nextInt();
		int y = cin.nextInt();
		mark[x][y] = true;		//我的起始位置


		int dx = cin.nextInt();
		int dy = cin.nextInt();
		DFS dfs = new DFS(n, m, dx, dy, data, mark);

		dfs.dfs(x, y, 0);

		System.out.println(dfs.minStep);
	}
}
/*
0 0 1 0
0 0 0 0
0 0 1 0
0 1 0 0
0 0 0 1
4 3
1 1
 * 
 */
