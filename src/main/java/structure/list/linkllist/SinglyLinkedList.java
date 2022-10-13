package structure.list.linkllist;

//单向链表
public class SinglyLinkedList {
	
	private SNode head;  //只需要定义一个头部，就可以将链表串起来。
    private SNode tail;  //链表的尾结点

    //还可以定义以下属性，这里为了简化演示。就没定义
//	private int size = 0;  //链表长度

    /**
     * 插入头结点
     * @param data  插入的数据
     */
	public void insertHead(int data){//O(1)
		SNode newNode = new SNode(data);

		//如果原来就有数据呢？

		newNode.next = head; //栈内存的引用

        head = newNode;
		
		//插入O(1)
	}

    /**
     * 删除头结点。
     */
	public void deleteHead(){//O(1)
	    //千万不能是head=null;
        SNode oldHead = head;
		head = head.next;
        oldHead.next=null;//GC回收
	}

    public void add(int data){
        SNode newNode = new SNode(data);
	    if(head ==null){
            head = newNode;
        }else{
	        tail.next=newNode;
        }
        tail = newNode;
    }

    /**
     * 插入到指定索引
     * @param data      待插入数据。
     * @param position  索引位置。
     */
    public void insertNth(int data,int position){ //假设定义在第N个结点插入 O(n)
        if(position == 0) {	//1.判断是否 插入头部
            insertHead(data);
        }else{   //2.不是头的话，就需要遍历链表
            SNode cur = head; //cur表示，待插入结点的前面一个结点。
            for(int i = 1; i < position ; i++){//i=1，表示从头结点的后一个结点开始遍历计算索引；注意i不能等于position
                cur = cur.next;		//一直往后遍历。-》c++里面，往后找指针的写法是 p->p.next;
            }

            SNode newNode = new SNode(data);

            //注意，下面的赋值顺序一定不能对调。否则可能断链找不到了。
            newNode.next = cur.next;	//先将，新加的点指向后面的结点  保证不断链
            cur.next = newNode;			//再把当前的点指向新加的点
        }
    }

    /**
     * 删除指定索引
     * @param position 待删除元素 所在的索引
     */
	public void deleteNth(int position){//O(n)
		if(position == 0) {
			deleteHead();
		}else{
			SNode cur = head;

			for(int i = 1; i < position ; i ++){//注意i不能等于position
				cur = cur.next;
			}

            SNode deleteNode = cur.next; //
			cur.next = deleteNode.next; //cur.next 表示的是删除的结点。当前结点直接指向待删除结点的后一个结点。
            deleteNode.next=null;//GC回收
		}
	}
	//查找元素
	public SNode find(int data){ //O(n)
		SNode cur = head;
		int index = 0;
		while(cur != null){
			if(cur.value == data){
                System.out.println("index:"+index);
			    break;
			}
			cur = cur.next;
            index++;
		}
		return cur;
	}
	
	public void print(){
		SNode cur = head;
		while(cur != null){
			System.out.print(cur.value );
			if(cur.next!=null){
                System.out.print(" -> ");
            }
			cur = cur.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		SinglyLinkedList myList = new SinglyLinkedList();

        System.out.println("初始化链表");
		myList.insertHead(5);
		myList.insertHead(7);
		myList.insertHead(10);
        myList.insertHead(11);
        myList.insertHead(13);
		myList.print(); // 10 -> 7 -> 5

        System.out.println("删除结点");
		myList.deleteNth(2);
		myList.print(); // 7 -> 5

        System.out.println("删除头结点");
		myList.deleteHead();
		myList.print(); // 5

        System.out.println("插入新结点");
		myList.insertNth(17, 1);
		myList.print(); // 5 -> 11

        System.out.println("查找结点位置");
        myList.find(11);

        System.out.println("删除结点");
		myList.deleteNth(1);
		myList.print(); // 5
	}

}

//定义数据结构。单向链表的结点
class SNode {
	
	int value;		//值

	SNode next;	//下一个的指针

	SNode(int value){
		this.value = value;
		this.next = null;
	}
}
