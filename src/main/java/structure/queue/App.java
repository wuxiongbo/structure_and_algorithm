package structure.queue;

/**
 * @Author: Battle Bear
 * @Date: 2020/11/7 11:13
 * @Description:
 */
public class App {
    public static void main(String[] args) {
        test2();
    }

    static void test1(){
        ArrayQueue queue = new ArrayQueue(6);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        queue.push(6);
        System.out.println(queue);
        queue.push(7);
        System.out.println(queue);
    }

    static void test2(){
        ArrayQueue queue = new ArrayQueue(6);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        queue.push(6);
        queue.pop();
        queue.pop();
        System.out.println(queue);
        queue.push(7);
        System.out.println(queue);
    }

}
