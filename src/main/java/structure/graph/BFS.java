package structure.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS {

	class Point {
		int x;
		int y;
	}

	int n; // 地图的行
	int m; // 地图的列
	int dx; // 安琪的位置x
	int dy; // 安琪的位置y
	int[][] data; // 邻接矩阵
	boolean[][] mark; // 标记数据 走过的位置

	public void bfs(int x, int y) { // x he y表示你当前的位置,就是求（x，y）->(dx,dy)能不能到
		if(x < 1 || x > n || y < 1 || y > m) {
			return ;
		}
		if(x == dx && y == dy) {
			System.out.println("true");
			return ;
		}
		mark[x][y] = true;

		Queue<Point> queue = new ArrayBlockingQueue<Point>(n * m); // 因为最多也就是n*m个点

		// 加入起点。
		Point start = new Point();
		start.x = x;
		start.y = y;
		queue.add(start);

		// 下、左、上、右
		int[][] next = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };	//ACM想到的

		while (!queue.isEmpty()) {		//O(n)

			Point point = queue.poll(); // 拿出队列的第一个点

			// 上、下、左、右
			for(int i = 0 ; i < next.length; i++) {
				int next_x = point.x + next[i][0];
				int next_y = point.y + next[i][1];

				if(next_x < 1 || next_x > n || next_y < 1 || next_y > m) {
					continue;
				}

				// 相邻点 为 0 ，且 没有走过。
				if(data[next_x][next_y] == 0 && !mark[next_x][next_y]) {  //表示可以走

					if(next_x == dx && next_y == dy) {		//表示已经到了。
						System.out.println("true");
						return ;
					}

					Point newPoint = new Point();
					newPoint.x = next_x;
					newPoint.y = next_y;
					mark[next_x][next_y] = true;
					queue.add(newPoint);
				}
			}
			
		}
		System.out.println("false");
		return ;

	}

}
