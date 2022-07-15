package src.main.java.structure.list.joseph;


import java.util.Arrays;

/**
 * N个人围成一个圈，每个人都按顺序编号，从编号K开始报数，第M个将被杀掉，依次类推，圈逐渐缩小，
 * 最后，除了剩下的唯一一个人，其余人都将被杀掉。
 * 那么，剩下的最后一个人编号是多少呢？
 * 思路：单循环链表。n人数，k编号，m报数个数
 */
public class PersonNodeList {

    private PersonNode head;

    static int[] arr; //用来记录结点移除的顺序
    static int index;

    public static void main(String[] args) {
//        int n=6,k=1,m=5;
        int n=41,k=1,m=3;
        PersonNodeList app = new PersonNodeList();
        app.initList(n);
        PersonNode one= app.getResult(k,m);
        System.out.println(one.number);
        System.out.println(Arrays.toString(arr));
    }

    //每次新添加的指针指向头。
    public void initList(int n){
        arr= new int[n];
        PersonNode cur = null;//指向最新增加的结点；
        for (int i = 1; i <= n; i++) {
            PersonNode newNode = new PersonNode(i);
            if(i == 1){
                head=newNode;
            }else{
                cur.next=newNode;
            }
            newNode.next=head; //指向头，形成环
            cur=newNode;
        }
    }

    public PersonNode getResult(int k,int m){

        PersonNode t = head; //t 代表 startNumber

        //找到指定编号的结点作为开始结点。
        for(;;){
            if(t.number == k){
                break;
            }
            t = t.next;
        }

        //数m声后，‘当前指针’所落到的结点。 i 表示数几声。
        //指针移0次，数1声、指针移1次，移2声、指针移2次，数3声。数m声，指针需移m-1次。(所以i起始为1)
        for (int i = 1; i < m; i++) {
            t = t.next;
        }

        //移除t指向的结点。
         // a)t是仅剩的一个结点 b)t是头结点 c)t是普通结点
        for(;;){
            if(t.next==t){
                arr[index]=t.number;
                return t;
            }
            PersonNode prev = findPrev(t);
            if(t==head){    //如果t是头结点
                head=t.next;//先更新头指针
            }
            PersonNode r = t;//临时记录即将被回收的结点

            prev.next=t.next;
            t = t.next;  //下一个结点作为开始数数的结点
            //再数m声
            for (int i = 1; i < m; i++) {
                t = t.next;
            }

            arr[index]=r.number;
            index++;
            r.next=null;

        }

    }

    public PersonNode findPrev(PersonNode node){
        PersonNode t=head;
        for (;;){
            if(t.next==node){
                return t;
            }else{
                t = t.next;
            }
        }
    }

}
