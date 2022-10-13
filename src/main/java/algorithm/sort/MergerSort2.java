package algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Arrays.sort
 * 排序
 *
 * @author Xander Wu
 * @date 2022/10/13 19:07
 */
public class MergerSort2 {

    private static void legacyMergeSort(Object[] a, Comparator c) {
        Object[] aux = a.clone();
        mergeSort(aux, a, 0, a.length, c);
    }

    private static void mergeSort(Object[] src,
                                  Object[] dest,
                                  int low,
                                  int high,
                                  Comparator c) {

        int length = high - low;

        // 递归出口
        if (length < 7) {
            for (int i = low; i < high; i++) {
                for (int j = i; j > low && c.compare(dest[j - 1], dest[j]) > 0; j--) {
                    swap(dest, j, j - 1);
                }
            }
            return;
        }


        // Recursively sort halves of dest into src

        int mid = (low + high) >>> 1;

        mergeSort(dest, src, low, mid, c);
        mergeSort(dest, src, mid, high, c);


        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid - 1], src[mid]) <= 0) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }


        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {

            if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0) {
                dest[i] = src[p++];
            } else {
                dest[i] = src[q++];
            }

        }


    }

    private static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }


    public static void main(String[] args) {
        Comparator<Integer> c = (o1, o2) -> o1 - o2;

//        Object[] data = {9, 5, 6, 8, 0, 3, 7, 1};

        Object[] data = {9, 5, 6, 8, 0, 3};


        legacyMergeSort(data, c);

        System.out.println(Arrays.toString(data));



    }

}
