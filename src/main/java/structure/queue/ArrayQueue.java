package src.main.java.structure.queue;

import java.util.Arrays;

public class ArrayQueue {

	private Integer data[];		// 数据
	private int head = 0;		//头索引
	private int tail = 0;		//尾索引
	private int length = 0;		//数组的大小 最大的空间
	private int size;		    //用来记录当前已经存了几个数了
	
	public ArrayQueue(int cap){
		data = new Integer[cap];
		length = cap;
	}
	
	public void push(int e){		//入队列  O(1)
		//判断我们这个队列是不是已经满了
        //思考：这里为什么不用 length-1 呢？
		if(tail == length){ //说明数组完全存满了。
			//在这里才去移动数据
            // 时间复杂度有 最好、最坏。
            // 最好的情况下是O(1),最坏的情况下才是O(n);
            // 在项目中如何来进行准确的估算呢？
            // 这里，就一个平均时间复杂度。
            // 假设数组的n=1000，那么前999个都是O(1) n*2/n,最坏的情况只有一次。
            // 这样的话，总的来讲还是很高效的
            // 当然，这里可以更改底层数据结构为链表,但是，链表是不能使用CPU缓存的。

			if(head==0){ //头部元素前面没多余的空间了。
				System.out.println("头元素溢出");
				for (int i = 0; i < data.length-1; i++) {
					data[i] = data[i+1];
				}
				data[tail-1] = e;
				return;
			}

			// 实现如下：
			int p = head;
			int t = 0;
			for(;p < tail; p++,t++){
				data[t] = data[p];
				data[p]=null; // 可加 可不加
			}
			data[t] = e; //新元素 入队。
			tail = t+1;
			head=0;
			return;
		}
		data[tail] = e;	// 1 2 3 4 5 入队
		tail++;
	}

	//此实现存在一个问题，就是，有很大的空间浪费。图解，见文档
	public int pop(){		//出队列 1 2 3 4 5 O(1)
		//要判断空，怎么判断空？
		if(isEmpty())
		    return -1;		 //表示空
		int m = data[head];  //先让元素出队，再自加。
        data[head]=null;     //将 出队的元素 所在位置清空。 也可以不清空。我个人觉得清空好点。
		head ++ ;
		return m;
	}
	
	public int pop2(){		//出队列 1 2 3 4 5
		//要判断空，怎么判断空？
		if(isEmpty())
		    return -1;		//表示空

		int e = data[head]; //出队
		head ++ ;
		//数组移动，前面腾出了空间，后面的数据往前面移。
        //如果每次出队列后，后面的数据都往前移动，时间复杂度变化：O(n)-》O(n), 不是个很好的方案。
        //最优解决方案：我们在入队 push 的时候 增加一个判断，当没有空间了的时候，我们再集中移动一次(从而避免出队一次移一次)
		return e;
	}
	
	public boolean isEmpty(){
		if(head == tail)
		    return true;
		return false;
	}

	@Override
	public String toString() {
		return "ArrayQueue{" +
				"data=" + Arrays.toString(data) +
				", head=" + head +
				", tail=" + tail +
				'}';
	}
}
