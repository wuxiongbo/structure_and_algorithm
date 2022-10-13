package algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序： 尾递归
 */
public class QuiklySort {

	/**
	 *
	 * @param data    原数组
	 * @param left    左边开始找的位置
	 * @param right   右边开始找的位置
	 */
	public static void qSort(int[] data, int left, int right) {

		int base = data[left]; // base 基准数，取序列的第一个,不能用data[0]
		int ll = left;  // 表示从左边开始找的位置
		int rr = right; // 表示从右边开始找的位置

		while (ll < rr) {
			// 从后往前找 比基准数小的数
			while (ll < rr && data[rr] >= base) {
				rr--;
			}
			// 找到了比基准数小的数
			if (ll < rr) {
				swap(data,ll,rr); // ll 为基准数的初始索引位置。
				ll++;
			}

			// 从前往后找 比基准数大的数
			while (ll < rr && data[ll] <= base) {
				ll++;
			}
			// 找到了比基准数大的数
			if (ll < rr) {
				swap(data,ll,rr); // rr 为交换后，基准数的索引位置。
				rr--;
			}
		}

		// 接下来肯定是递归。那么，这里的递归该怎么写呢？
		// 既然分成了三部分，那么，左、右 分别继续快排。 实现如下：

		if (left < ll)   // 注意：这里一定要加条件，不然递归就栈溢出了！！！
			qSort(data, left, ll - 1); // [left,ll-1] ， 基准数左边部分
		if (ll < right)  // 注意：这里一定要加条件，不然递归就栈溢出了！！！
			qSort(data, ll + 1, right); // [ll+1,right]， 基准数右边部分

	}

	static void swap(int[] data, int ll, int rr){
		int temp = data[rr];
		data[rr] = data[ll];
		data[ll] = temp;
	}


	public static void sort(int[] arr){
        qSort(arr,0,arr.length-1);
    }

    public static void main(String[] args) {
        int[] arr = {45, 28, 80, 90, 50, 16, 100 ,10};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
