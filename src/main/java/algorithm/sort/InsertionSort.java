package src.main.java.algorithm.sort;

import java.util.Arrays;

// 插入排序
public class InsertionSort {

	/**
	 * 
         1.将数组分成 已排序段 和 未排序段。初始化时 已排序端 只有一个元素
         2.从 未排序段 取元素插入到 已排序段，并保证插入后仍然有序
         3.重复执行上述操作，直到未排序段元素全部加完。
	 *   下列代码的思考：
     *   1)内层for循环为什么是‘从后往前’遍历，而不‘从前往后’遍历呢？
     *     从后往前找，在比较大小的时候，同时进行元素的位移。
     *     如果从前往后找，插入了。后面的还是要遍历，进行元素位移。 从而增加了时间复杂度。
     *   2)内层循环结束后，j的值为什么要+1呢？
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = { 9, 8, 7, 0, 1, 3, 2 };

		//for(){		//希尔排序。这里多个分段。外面再包一层for循环
		insertionSort(a);
	//	}
		
	}

	//这里面会有几层循环？ 2
	//时间复杂度：n^2
	//最好的情况：什么情况下? O(n); O(1)
	public static void insertionSort(int[] a){
		int n = a.length;
		for(int i = 1;i < n;i++){ //为什么i要从1开始？ 因为第一个不用排序。我们把数组从i分割开，0~i区间的 被认为是已经排好序的。
			int data = a[i]; //第一步：拿到元素‘8’
			int j = i - 1;    //第二步提示：j是i的前一个位置
			for(;j>=0;j--){ //从 ‘拿到元素的前一个位置’ 开始，从后往前数。从尾到头 1+2+3+4+5+...+n=>
				if(a[j] > data){ //如果前面的数a[j]比拿到的元素data大，
					//第二步：把9放到8的位置
					a[j+1] = a[j]; //则将i前面的元素a[j]后移；说明：数组是被i指针分割开的，前面是已排序的，后面是未排序的。
				}else{ //如果前面的数比拿到的元素小。则停止遍历查找。
					//前面已经是排好序的，所以若找到一个比它还小的，肯定就不用找了，因为前面的更小。
					break; //O(1) 优化思路：这个break执行的越多，是不是效率就越高?
					// 走if分支的时候，j指向原来的9所在的位置。
					// 循环的if分支走完后，当前循环完成，j则自减一次。如果走else分支，break了，则不会自减。
					// 所以,下面的第三步中，j需要+1 才表示原来的9的位置。
				}
			}
			a[j+1] = data; //第三步：把8放到9的位置。注意：上面的for循环走完后，j会自减一次。所以这里a[j+1]才是正常的位置。
			System.out.println("第" +i +"次的排序结果为："+Arrays.toString(a));
		}
	}


	/*原始数组：        [9, 8, 7, 0, 1, 3, 2]
	* 第1次的排序结果为：[8, 9, 7, 0, 1, 3, 2]    8
      第2次的排序结果为：[7, 8, 9, 0, 1, 3, 2]    7
      第3次的排序结果为：[0, 7, 8, 9, 1, 3, 2]    0
      第4次的排序结果为：[0, 1, 7, 8, 9, 3, 2]    1
      第5次的排序结果为：[0, 1, 3, 7, 8, 9, 2]    3
      第6次的排序结果为：[0, 1, 2, 3, 7, 8, 9]    9
    *
    * 第1次的排序，详细过程分析：
    * 1.拿到元素8
    *   int data = 8
    *   [9, 8, 7, 0, 1, 3, 2]
    * 2.把9放到8的位置，元素往前移动的时候，会留下当前元素的‘影子’
    *   a[j] = 9
    *   [9, 9, 7, 0, 1, 3, 2]
    * 3.把8放到9的位置。 覆盖
    *   int data = 8
    *   [8, 9, 7, 0, 1, 3, 2]
	* */

}
