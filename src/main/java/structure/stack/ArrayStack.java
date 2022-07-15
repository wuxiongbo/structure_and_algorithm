package src.main.java.structure.stack;

public class ArrayStack<Item> implements MyStack<Item> {
	
	private Item [] a = (Item[]) new Object[1];		//最好就是开始的时候就设置大小
	private int n = 0;		//大小 初始的元素个数
	
	public ArrayStack(int cap) {
		a = (Item[]) new Object[cap];
	}

	public MyStack<Item> push(Item item) {		//时间复杂度 O(1)
		
		judgeSize(); //入栈前，先判断是否需要扩容。
		a[n++] = item; //入栈完成
		
		return null;
	}

    /**
     * 判断是否需要 扩/减容
     */
	private void judgeSize(){
		if(n >= a.length){  // ‘元素个数’ 已经超出了 ‘数组的容量’
			resize(2 * a.length);

		//初始10个位置，扩容两次后，有10*2*2=40个位置。后续出栈了20个了，剩下20个。空间就很浪费。因此需要减容
		}else if(n > 0 && n <= a.length / 2){ //元素个数小于数组容量的一半
			resize(a.length / 2);
		}
	}

    /**
     * 给定容量，执行扩/减容，并转移元素到新数组。
     * @param size
     */
	private void resize(int size){	//O(n)  扩容
		Item[] temp = (Item[]) new Object[size];
		for(int i = 0 ; i < n; i ++){
			temp[i] = a[i];
		}
		a = temp;
	}

	public Item pop() {	//O(1)  出栈
		if(isEmpty()){
			return null;
		}
		//思考：为什么不使用item[n--] ，而使用item[--n] 呢？ 元素个数跟元素索引的关系
        //提示：--n 和 n-- 的区别，--n 是先把n减了在用，n-- 是先用了再减
		Item item = a[--n];
		a[n] = null;	    //问：为什么这一步直接使用n呢？ 答：因为上一行代码中，n已经--了
		return item;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n == 0;
	}

}
