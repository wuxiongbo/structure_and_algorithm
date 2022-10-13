package algorithm.sort;

import java.util.Arrays;

/**
 *  希尔排序  完整版
 */
public class ShellSort2 {
    public static void main(String[] args) {
        int [] a = { 7,8,9,0,4,3,1,2,5,10 };

        shellSort(a);

        System.out.println(Arrays.toString(a));
    }

    /**
     * 首先，这是 插入排序 的代码。  稍作修改，之前与相邻比较。现，将1改为add。
     * @param a      原数组
     * @param x      代表第几组逻辑分组
     * @param add    代表当前增量
     */
    static void insertSort(int [] a,int x,int add){
        int n = a.length;
        for(int i = x + add; i < n; i += add) {
            int data = a[i];
            int j = i - add;

            for(;j >= 0 && a[j] > data; j -= add) {
                a[j + add] = a[j];
            }
            a[j + add] = data;

        }
    }

    /**
     * 然后，我们在 插入排序 的基础上稍加改进，变成希尔排序。
     * @param array
     */
    public static void shellSort(int [] array) { //希尔排序的增量
        int n = array.length;
        //增量初始值n/2。使用希尔增量的方式，每次折半。n=10; add=5,2,1;
        for(int add = n/2; add>=1; add /= 2) {

            for(int x = 0; x < add; x++) { //x表示第几组。如：增量 add=2时，一共有两个分组 分组序号分别是 x=0,1; 增量 add=1时，共一个分组 x=0;
                insertSort(array,x,add);
            }

        }
    }


}
