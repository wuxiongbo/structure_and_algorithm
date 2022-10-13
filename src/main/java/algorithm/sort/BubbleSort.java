package algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author 10027088
 */
public class BubbleSort {
    public static void main(String[] args) {

        int[] data = {4, 5, 6, 3, 2, 1};
        int n = data.length;

        //1 2
        for (int i = 0; i < n - 1; i++) {   //排序的次数
            boolean flag = false;
            for (int j = 0; j < (n - 1) - i; j++) {    //具体冒泡 n - 1 - i。减去已排好的。 6,5,4,3,2,1 交换次数最多
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);

                    flag = true;//优化思路
                }
            }

            if (!flag) {
                break;
            }
        }


        System.out.println(Arrays.toString(data));
    }


    private static void swap(int[] x, int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

}
// a:2 b:3
// 3 2 => a:3 b:2
// 用加减
//a = a + b => a = 3+2 =5;
//b = a - b => b = 5-3 =2;
//a = a - b => a = 5-2 =3;