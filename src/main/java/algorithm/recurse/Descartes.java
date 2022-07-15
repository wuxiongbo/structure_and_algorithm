package src.main.java.algorithm.recurse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归 之 笛卡尔乘积算法
 */
public class Descartes {

    private static int count;

    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> listSub1 = new ArrayList<String>();
        List<String> listSub2 = new ArrayList<String>();
        List<String> listSub3 = new ArrayList<String>();

        listSub1.add("1");
        listSub1.add("2");

        listSub2.add("3");
        listSub2.add("4");

        listSub3.add("a");
        listSub3.add("b");

        list.add(new ArrayList<>());
        list.add(listSub1);
        list.add(listSub2);
        list.add(listSub3);

//        List<List<String>> result2 = new ArrayList<List<String>>();
//                descartes2(list, result2 , 0, new ArrayList<String>());

        List<List<String>> result3 =
                descartes3(list,  new ArrayList<List<String>>(), 0, new ArrayList<String>());

        System.out.println("方法调用次数："+count);
        for (List<String> e : result3) {
            System.out.println(e);
        }
    }
    /**
     * <p>
     * Discription:笛卡尔乘积算法
     * 把一个
     * List{[1,2],
     *      [3,4],
     *      [a,b]}
     *
     * 转化成
     * List{[1,3,a],[1,3,b],
     *      [1,4,a],[1,4,b],
     *      [2,3,a],[2,3,b],
     *      [2,4,a],[2,4,b]}数组输出
     *
     * 3个位，每个位2种情况。 2^3=8;
     * </p>
     *
     * @param dimValue 原List
     * @param result   通过乘积转化后的数组
     * @param layer    中间参数
     * @param curList  中间参数
     */
    private static void descartes(List<List<String>> dimValue,
                                  List<List<String>> result,
                                  int layer,
                                  List<String> curList) {
        if (layer < dimValue.size() - 1) {
            if (dimValue.get(layer).size() == 0) {
                descartes(dimValue, result, layer + 1, curList);
            } else {
                for (int i = 0; i < dimValue.get(layer).size(); i++) {
                    List<String> list = new ArrayList<>(curList);
                    list.add(dimValue.get(layer).get(i));
                    descartes(dimValue, result, layer + 1, list);
                }
            }
        } else {
            if (dimValue.get(layer).size() == 0) {
                result.add(curList);
            } else {
                for (int i = 0; i < dimValue.get(layer).size(); i++) {
                    List<String> list = new ArrayList<>(curList);
                    list.add(dimValue.get(layer).get(i)); //添加最后一层元素
                    result.add(list);
                }
            }
        }
    }

    /**
     * 为了便于理解，做了些简化
     *
     * 方法调用次数：7
     *
     * @param dimValue 原List
     * @param result   转化后的List
     * @param layer    原List中的第几组数据(0开始数)
     * @param curList  处于添加元素中间状态的List     cur  Current
     */
    //
    private static void descartes2(@NotNull List<List<String>> dimValue,
                                   List<List<String>> result,
                                   int layer,
                                   List<String> curList) {
        count++;
        List<String> childList = dimValue.get(layer);//第layer组子集合

        if (layer != dimValue.size() - 1) { //不是最后一层 子集合

            if(childList.size()==0) descartes2(dimValue, result, layer + 1, curList);

            for (int i = 0; i < childList.size(); i++) { //** 遍历 第layer组 子集合

                List<String> newCurList = new ArrayList<>(curList);  //创建新子集
                newCurList.add(childList.get(i));  //新子集添加；第layer组集合的元素

                descartes2(dimValue, result, layer + 1, newCurList); //newCurList 处于添加中间状态的集合
            }

        } else { //最后一层 子集合

            for (int i = 0; i < childList.size(); i++) {

                List<String> list = new ArrayList<>(curList);  //每种情况都要创建一个新的子集合
                list.add(childList.get(i));

                result.add(list);  //** 把最终构建好的新子集 添加到结果中
            }

        }

    }


    /**
     * 为了便于理解，进一步做简化
     *
     * 尾递归  方法调用次数：7
     * @param dimValue 原List
     * @param result   转化后的List
     * @param layer    原List中的第几组数据(0开始数)
     * @param curList  处于添加元素中间状态的List
     */
    private static List<List<String>> descartes3(@NotNull List<List<String>> dimValue,
                                                 List<List<String>> result,
                                                 int layer,
                                                 List<String> curList) {
        count++;
        List<String> childList = dimValue.get(layer);//第layer组子集合
        if (layer == dimValue.size() - 1) { //最后一层 子集合
            for (int i = 0; i < childList.size(); i++) {

                List<String> newCurList = new ArrayList<>(curList);//创建新子集
                newCurList.add(childList.get(i));

                result.add(newCurList);  //** 把最终构建好的新子集 添加到结果中
            }
            return result; // 最后一层元素添加完毕， 则直接返回 不执行后面的逻辑
        }

        if(childList.size()==0) descartes3(dimValue, result, layer + 1, curList);

        for (int i = 0; i < childList.size(); i++) { //** 遍历子集合

            List<String> newCurList = new ArrayList<>(curList);//每种情况都要创建一个新的子集合
            newCurList.add(childList.get(i));  //新子集添加；第layer组集合的元素
            //list表示处于添加中间状态的集合
            descartes3(dimValue, result, layer + 1, newCurList);
        }

        return result;
    }

}
