package src.main.java.algorithm.sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Writer;

//计数排序。 
//分析：0~900范围。根据取值范围，创建新数组。转int类型后，取值范围变为0~9w
//它仅适用于一定范围的整数排序。
//在取值范围不是很大的情况下，它的性能在某些情况甚至快过那些O(nlogn)的排序，例如 快速排序、归并排序。
//在底层算法中用的不多，但是在项目中用的比较多。
public class CountSort {
	public static void main(String[] args) throws Exception {
		String str = null; //临时记录行数据
		String fileName = "200w.txt";
		InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		int data[] = new int[2_100_002];
		int i = 0;
		while ((str = br.readLine()) != null) {
			double a = Double.valueOf(str);
			a = a * 100;
			data[i++] = (int) a;
			// System.out.println((int) a);
		}
		System.out.println("数据读取完毕，size为" + i);
		long start = System.currentTimeMillis();
		countSort(data, 0, 90_000);
		System.out.println("快排消耗的时间为:" + (System.currentTimeMillis() - start) + "ms");
	}

	public static void countSort(int data[], int min, int max) throws Exception {
		int counts[] = new int[max + 1];
		for (int i = 0; i < data.length; i++) {
			counts[data[i]]++;
		}

		File file = new File("200w-csort.txt");
        if(!file.exists()){
            file.createNewFile();
        }
		Writer out = new FileWriter(file);

		for (int i = 0; i <= max; i++) { // 遍历数组，输出数组元素的下标值
			if (counts[i] > 0) {
				for (int j = 0; j < counts[i]; j++) { //元素的值是几，就输出几次。仅输出出现0次以上的下标
					out.write(((double) (i / 100.0)) + "\r\n");
				}
			}
		}
		out.close();
	}
    
}
