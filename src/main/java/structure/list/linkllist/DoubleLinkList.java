package src.main.java.structure.list.linkllist;

// 双向链表
public class DoubleLinkList {

	private DNode head;		// 头
	private DNode tail;		// 尾
	
	DoubleLinkList(){
		head = null;
		tail = null;
	}

    //插入头结点
	public void inserHead(int data){
		DNode newNode = new DNode(data);
		if(head == null){
			tail = newNode;
		}else{
			head.pre = newNode;
			newNode.next = head;
		}
		head = newNode;
	}
	//删除头结点
	public void deleteHead(){
		if(head == null) return ;	//没有数据
		if(head.next == null){		//就一个点
			tail = null;
		}else{
			head.next.pre = null;	
		}
		head = head.next;
	}

	//删除指定元素
	public void deleteKey(int data){
		DNode current = head;
		while (current.value != data) {
			if (current.next == null) {
				System.out.println("没找到节点");
				return ;
			}
			current = current.next;
		}
		if (current == head) {// 指向下个就表示删除第一个
			deleteHead();
		} else {
			current.pre.next = current.next;
			if(current == tail){		//删除的是尾部
				tail = current.pre;
				current.pre = null;
			}else{
				current.next.pre = current.pre;
			}
		}
	}

    public static void main(String[] args) {

    }
}

class DNode{
	
	int value;		//值

	DNode next;		//指向下一个结点的指针
	DNode pre;		//指向前一个结点的指针

	DNode(int value){
		this.value = value;
		this.next = null;
		this.pre = null;
	}
}
