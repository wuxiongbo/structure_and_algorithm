package src.main.java.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序  不完整版
 */
public class ShellSort1 {
	public static void main(String[] args) {
		int a[] = new int[]{ 9, 8, 7, 0, 1, 3, 2 };

		int n = a.length;

		for (int add = n / 2; add >= 1; add /= 2) {

			// 这里没有再包一层for循环，按组来遍历。但不影响结果。这是为什么呢？
			// int i = add 相当于 int i = 0 + add; 也就是说，每次增量，都只排序了第一组。
			// 虽然多个组中，只排了一组，但是最终，增量都会变成1。 即，直接插入排序。这一步会保证结果是有序的。
			// 可见，此版本代码并不完善。

			insertSort(a,add);

		}

		System.out.println(Arrays.toString(a));

	}

	static void insertSort(int [] a,int add){
		int n = a.length;
		for (int i = add; i < n; i++) {
			int data = a[i];
			int j = i - add;

			// 写法一
			for (; j >= 0; j -= add) {
				if (a[j] > data) {
					a[j + add] = a[j]; // 较大的数 往后移动
				}
				else {
					break;
				}
			}
			// 写法二
//				for(;j >= 0 && a[j] > data; j -= add) {
//					a[j + add] = a[j];
//				}

			a[j + add] = data;

		}
	}
}
