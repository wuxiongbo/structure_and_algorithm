package structure.queue;

/**
 * 基于 数组数据结构 实现的 环形队列算法
 * 这里并不是物理内存空间上的真正环形，而是逻辑上的环形
 *
 *   说明：tail指向的位置，是不存储元素的。(即，6位置 元素为空)
 *
 *   普通状态
 *   -----------------------------------------
 *   [ 0 ] [ 1 ] [ 2 ] [ 3 ] [ 4 ] [ 5 ] [ 6 ]
 *   -----------------------------------------
 *    |                                    |
 *   head                                tail
 *                                      (null)
 *   出队  头指针后移
 *   -----------------------------------------
 *   [ 0 ] [ 1 ] [ 2 ] [ 3 ] [ 4 ] [ 5 ] [ 6 ]
 *   -----------------------------------------
 *           |                             |
 *         head                          tail
 *                                      (null)
 *   入队  尾指针后移
 *   -----------------------------------------
 *   [ 0 ] [ 1 ] [ 2 ] [ 3 ] [ 4 ] [ 5 ] [ 6 ]
 *   -----------------------------------------
 *     |     |
 *   tail  head
 *  (null)
* */
public class CircleArrayQueue {

	private Integer data[];		// 数据
	private int head = 0;		//头 索引位置
	private int tail = 0;		//尾 索引位置
	private int n = 0;		    //数组大小，即，最大的空间
	private int size;		//如何判断队列是否已满？方案之一是添加size字段，表示已存了几个元素
	
	public CircleArrayQueue(int cap){
		data = new Integer[cap];
		n = cap;
	}
	
	public void push(int m){		//入队列  O(1)		//排序
		if((tail + 1) % n == head){	
			return;
		}
		data[tail] = m;	// 1 2 3 4 5 优先队列的关键点，在这里进行了排序，讲树形结构的时候会具体介绍排序的实现
		tail = (tail + 1) % n;//循环队列。赋值在尾指针位置，完成后，尾指针后移一位。循环队列队满时，数组实际空出一位。
//        tail++;
        // 如果这里是做tail++处理，
        // 那么，当数组空间n为7的情况下，
        // tail=7就到边界了，再添加元素时，tail=8，明显就数组越界了，
        // 怎么解决这个问题呢？
        // 我们找到规律(7+1)%8==0;这其中的+1 就是向后移动一位
	}
	
	public int pop(){		//出队列 1 2 3 4 5 O(1) 很大的空间浪费
		//要判断空，怎么判断空？
		if(isEmpty())
		    return -1;		//用-1表示空
		int m = data[head];
        data[head]=null;    //出栈后清空；
		head = (head + 1) % n;
		return m;
	}

	public boolean isEmpty(){
		if(head == tail)
		    return true;
		return false;
	}

}
