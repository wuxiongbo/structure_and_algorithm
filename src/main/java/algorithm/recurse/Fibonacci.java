package src.main.java.algorithm.recurse;

/**
 * 递归 之 斐波那契
 * 0 1 1 2 3 5 8 13 ...
 */
public class Fibonacci {

	private static int data[]; // 初始换全部是0

	// 1.斐波那契：递归法
	// 求解公式：f(n)=f(n-1)+f(n-2)
    // 终止条件：n<=2  f(n)=1
    // 分析一段代码好坏，有两个指标，时间复杂度和空间复杂度
	public static int fab(int n) { //时间复杂度、空间复杂度 都是 O(2^n)
		if (n <= 2)
			return 1; // 递归的终止条件
		return fab(n - 1) + fab(n - 2); // 继续递归的过程
	}

    // 2.斐波那契，第一种优化方案：不使用递归。
    //       所有使用递归的代码，一定可以改成不使用递归的。
	public static int noFab(int n) { // 不用递归 O(n)
		// 循环
		if (n <= 2)
			return 1;
		int a = 1; //第一个数的值
		int b = 1; //第二个数的值
		int c = 0;
		for (int i = 3; i <= n; i++) {

			c = a + b; //第三个数的值

			a = b;
			b = c;
		}
		return c;
	}

    // 3.斐波那契，第二种优化方案：用数组来做缓存
	public static int fab2(int n) { //  时间复杂度降为O(n)，空间也降至为O(n)
		if (n <= 2)
			return 1; // 递的终止条件
		if (data[n] > 0) { //数组初始化值为0，当不为0的时候，说明该下标已经有值。
			return data[n];
		}
		int res = fab2(n - 1) + fab2(n - 2); // 继续递的过程
		data[n] = res; //归的时候缓存赋值。
		return res;
	}

    // 4.斐波那契，第三种优化方案：尾递归
    /**
     * arg3:  n    第几个数。
     * arg1:  pre  上一次运算出来的结果   previous result
     * arg2:  res  当前运算出来结果      result
     *
     * 不停地往下带入结果，pre(上次结果)用来计算下个方法中的res(当前结果)
     *
	 * f(1)代表位置1处的数的大小
	 * f(2)代表位置2处的数的大小
     * f(3)代表位置3处的数的大小
	 * ... 以此类推
	 * 斐波那契数列定义： 后面一个数大小为前两个数之和。
	 * 所以， f(3) = f(2)+f(1)
	 *
	 *
	 * n=5:
     *    pre  f(1)  已知
     *    res  f(2)  已知
     * n=4:
     *    pre  f(2)
     *    res  f(3) = f(1) + f(2)
     * n=3:
     *    pre  f(3)
     *    res  f(4) = f(2) + f(3)
     * n=2:
     *    pre  f(4)
     *    res  f(5) = f(3) + f(4)    return f(5)
     *
     * 总结规律：
     *       |----> 本次调用的pre = 上次调用的res
     *    -->|
     *       |----> 本次调用的res = 上次调用的pre + 上次调用的res
     *
     * 即：
	 *    per = res,
	 *    res = pre + res
	 *    初始的时候  pre表示第一个数的值
	 *              res表示第二个数的值
	 *              n  表示第n个数。
	 *    图形分析，参考 非递归
     */
	public static int tailfab(int pre,int res,int n) { // 分析一段代码好坏，有两个指标：时间复杂度、空间复杂度。都是：O(n)
		if (n <= 2)
			return res; // 递归的终止条件
		return tailfab(res, pre + res, n - 1); //JDK源码很多用到 ‘尾递归’ 思想
	}
    // 尾递归。 逻辑拆解
    public static int tailfab1(int pre,int res,int n) {
        if (n <= 2)
            return res; // 递归的终止条件
        res = tailfab1(res, pre + res, n - 1);
        return res;
    }
	// 尾递归。 逻辑进一步拆解
	public static int tailfab2(int pre,int res,int n) {
		if (n <= 2)
			return res; // 递归的终止条件


		int newRes = pre + res;  // 计算新结果(关键)。每次都在这里，把上次计算的结果带进去了。
		pre = res;               // pre 指向旧结果。
		res = newRes;            // res 指向新结果。
		n = n - 1;               // 倒推

		res = tailfab2(pre, res, n);

		return res;
	}

    // 求N的阶乘: 普通递归
    public static int fac(int n) { // 5=5*4*3*2*1=> f(n) =
        if (n <= 1)
            return 1;
        return n * fac(n - 1);
    }

    // 求N的阶乘: 尾递归
    // 5 : res          //开始的时候。我们知道1的阶乘是1。所以我们将1的结果 递 下去。
    // 4 : res = 5*res
    // 3 : res = 4*res
    // 2 : res = 3*res
    // 1 : res = 2*res  // res = 2*3*4*5*1;
    //每次 ‘递’ ，都把中间结果带下去了。
	public static int tailFac(int n, int res) { // 尾递归 传结果，res用来保存当前结果
		System.out.println(n + ":" + res);	// 这个地方打印出来的值是怎么样的？
		if (n <= 1)
			return res;
		return tailFac(n - 1, n * res);	//尾递归其实就相当于。倒着算
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 45; i++) {
			long start = System.currentTimeMillis();

			System.out.println(i + ":" + tailfab(1,1,i));

			long end = System.currentTimeMillis() ;
			System.out.println("所花费的时间为"+(end-start)+"ms");
		}

		//tailFac(5, 1); //5的阶乘，阶乘是从1开始。
		
		/*
		 * for (int i = 1; i <= 45; i++) {
		 *   long start =
		 *   System.currentTimeMillis();
		 *   System.out.println(i + ":" + fab(i) +
		 *    " 所花费的时间为" + (System.currentTimeMillis() - start) + "ms");
		 * }
		 */

		/*for (int i = 1; i <= 45; i++) {
			long start = System.currentTimeMillis();
			System.out.println(i + ":" + noFab(i) + " 所花费的时间为"
					+ (System.currentTimeMillis() - start) + "ms");
		}
		System.out.println("==================================");
		System.out.println("加了缓存的情况");
		for (int i = 1; i <= 45; i++) {
			data = new int[46];
			long start = System.currentTimeMillis();
			System.out.println(i + ":" + fab2(i) + " 所花费的时间为"
					+ (System.currentTimeMillis() - start) + "ms");
		}*/
	}
}
