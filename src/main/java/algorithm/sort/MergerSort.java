package algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergerSort {

    private static int[] temp;        //借助一个临时数组，用来保存合并的数据


    /**
     * 拆分：
     * left、right 表示数组逻辑分组的两端。初始状态是原数组的左右顶端。
     *
     * @param data
     * @param left
     * @param right
     */
    public static void mergerSort(int[] data, int left, int right) {
        if (left < right) { // 当 left = right 时，说明只有一个数了，此时不用再拆了

            // mid 表示用于拆分逻辑数组的临界点。
//            int mid = (left + right) / 2;
            int mid = (left + right) >>> 1;


            // 第一步：开始拆分。
            mergerSort(data, left, mid);           // 拆分到最底层时，到了递归出口。开始执行下一行代码，不再继续递归
            mergerSort(data, mid + 1, right);  // 拆分到最底层时，到了递归出口。开始执行下一行代码，不再继续递归

            // 第二步：开始合并。 拆分完毕后，接下来就要进行合并，即 ‘归’ 的过程。也叫做 ‘回溯’
            merger(data, left, mid, right); //最底层，开始往上合并。
        }
        return; //不满足上述条件，则返回。这里是递归出口。
    }

    /**
     * 合并：
     * <p>
     * 定义两个指针 point1、point2， 分别指向 原数组的 左边数组、右边数组 起始位置
     * 再定义一个指针 loc， 用于指向 临时数组 当前保存了数据所到的位置。
     *
     * @param data  左边逻辑数组。下面简称 ‘左数组’
     * @param left  左数组的 左边界(指针的起始位置)
     * @param mid   左数组的 右边界(指针移动的终止条件)
     *              <p>
     *              右边逻辑数组。下面简称 ‘右数组’
     *              mid+1    右数组的 左边界(指针的起始位置)
     * @param right 右数组的 右边界(指针移动的终止条件)
     */
    public static void merger(int[] data, int left, int mid, int right) {

        //不能在这里建临时数组。否则，在数据量大了之后，merger函数多次调用，会非常耗时！！

        int pointL = left;        // 左数组指针：表示的是左边逻辑数组的第一个数的位置
        int pointR = mid + 1;     // 右数组指针：表示的是右边逻辑数组的第一个数的位置

        int loc = left;           // 临时数组指针：表示 临时数组 已存储到的位置。

        while (pointL <= mid && pointR <= right) {  //两个数组的指针均未到达边界。
            // 将元素放入临时数组。
            // 正序：小的往前放

            if (data[pointL] < data[pointR]) { // 左数组的 元素较小
                temp[loc] = data[pointL];
                pointL++;                      // 左数组 游标右移
            }
            else {                            // 右数组的 元素较小
                temp[loc] = data[pointR];
                pointR++;                     // 右数组 游标右移
            }
            loc++; // 已存储空间+1

            // 以上代码可简写为
            // temp[loc ++] = data[pointL] < data[pointR] ? data[pointL ++] : data[pointR ++];
        }

        //接下来要干嘛呢？合并排序完成了吗？
        // 两个数组中，可能有一个的指针会先到达边界，此时，另一个数组中，还有一个剩余的元素。
        // 所以，接下来需要将剩下的元素放入临时数组。 至于，哪个先到达，哪个后到达，是不确定的，所以两个数组都要判断。
        while (pointL <= mid) {   //左边数组 未到达边界（还有一个剩余的元素）
            temp[loc] = data[pointL];
            loc++;
            pointL++;
            // 以上代码可简写为
            // temp[loc++] = data[pointL++];
        }
        while (pointR <= right) {  //右边数组 未到达边界（还有一个剩余的元素）
            temp[loc] = data[pointR];
            loc++;
            pointR++;
            // 以上代码可简写为
            // temp[loc++] = data[pointR++];
        }

        //把 临时数组 中排好序的那部分数据（逻辑数组），覆盖到原数组。
//        for (int i = left; i <= right; i++) {
//            data[i] = temp[i];
//        }
        // 以上代码可简写为
        System.arraycopy(temp, left, data, left, right - left + 1);  // right - left + 1  为逻辑数组的长度
    }

    public static void main(String[] args) {

        int[] data = {9, 5, 6, 8, 0, 3, 7, 1};
        temp = new int[data.length];

        mergerSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));

        //JDK里面的排序源码很多地方用到这个思想。

    }
}
