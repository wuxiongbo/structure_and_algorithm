package src.main.java.algorithm.dynamic;

/**
 * 动态规划  dynamic programming
 * 购物车问题
 */
public class CardDp {

	public static void main(String[] args) {
		//一共6个物品，价值分别是。
        //             1,2,3,4,5,6
		int value[] = {1,2,3,4,5,9}; //购物车那个问题 只需要一个价值就行了，重量都没有。
		
		int v = 17;//总价值(容量)为17元的购物车。
		int n = 6;
		int[][] car = new int[n+1][v+1];  //n表示是物品，v表示价值,初始化全是0

		for(int i = 1; i<= n; i++){	//每次加的物品（编号）
			for(int cv = 1 ; cv <= v ; cv ++){//分割购物车容量

				//物品的价值小于分割购物车容量。表示这个物品可以装进当前分割购物车
				if(value[i-1] <= cv){
                    car[i][cv] = Math.max(
                            value[i-1] + car[i-1][cv-value[i-1]],
                            car[i-1][cv]
							);
				}

				//物品的价值大于分割购物车容量。这个物品不能装进当前分割购物车
				else{
				    //延续当前容量下，已装物品的总价值。
                    car[i][cv] = car[i-1][cv];
				}
			}
		}
		System.out.println("最大价值："+car[n][v]); //最后一个物品，最后一个分割。即，最大价值。

		// 打印数组结构
        for (int i = 0; i <=n ; i++) {
            for (int cw = 0; cw <= v; cw++) {
                System.out.printf("%-4s", car[i][cw]);
            }
            System.out.println();
        }
/*
横， v：价值。 纵， n：物品（编号）
  | 0   1   2   3   4   5   6   7   8  v
 -------------------------------------
n | 0   0   0   0   0   0   0   0   0
1 | 0   1   1   1   1   1   1   1   1
2 | 0   1   2   3   3   3   3   3   3
3 | 0   1   2   3   4   5   6   6   6
4 | 0   1   2   3   4   5   6   7   8
5 | 0   1   2   3   4   5   6   7   8
6 | 0   1   2   3   4   5   6   7   8

* */
		// 打印 商品信息。i 物品编号、 v 当前购物车价值。
		// 思路： 倒推
        for (int i = n; i >0 ; i--) {
        	// 同一个分割的购物车。 如果发现当前物品，跟上列 总价值不同时，说明有当前物品加入
            if(car[i][v] != car[i-1][v]){
                System.out.println("商品"+i+"："+value[i-1]+"￥");
                // 减去当前物品的价值。 下次查找剩下分割购物车的最大价值。
                v -= value[i-1];
            }
        }

	}
}
