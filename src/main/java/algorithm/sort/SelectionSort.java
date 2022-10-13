package algorithm.sort;

import java.util.Arrays;

/**
 * @author Xander Wu
 * @date 2022/10/13 19:52
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] data = {4, 5, 6, 3, 2, 1};
        int n = data.length;


        for (int i = 0; i <= n - 1; i++) {
            for (int j = i; j > 0 && data[j - 1] - data[j] > 0; j--) {
                swap(data, j, j - 1);
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
