package structure.StackAndQueue;



/**
 * 链表 队列
 *
 * @author 周鹏程
 * @date 2022-10-08 9:02 PM
 **/
public class MyLinkedQueue<T> implements MyQueue<T>{

    private static final int EMPTY_LENGTH = 0;

    /** 长度 */
    private int length;

    /** 头节点 */
    private Node<T> head;

    /** 尾节点 */
    private Node<T> tail;


    /**
     * 压入队列
     * 时间复杂度 O(1)
     *
     * @param t <T>数据
     * @return MyStack<T>
     */
    @Override
    public MyQueue<T> push(T t) {
        linkTail(t);
        return this;
    }

    /**
     * 弹栈
     * 时间复杂度 O(1)
     *
     * @return <T>
     */
    @Override
    public T pop() {
        Node<T> tmp  = head;
        Node<T> next = head.getNext();
        // 已经确认为尾节点
        if(null == next){
            head = null;
            tail = null;
        }else {
            head = next;
        }
        length--;
        return tmp.data;
    }

    /**
     * 查看当前栈顶 元素
     *
     * @return <T>
     */
    @Override
    public T peek() {
        if(null == head){
            return null;
        }
        return head.data;
    }

    /**
     * 获得数据长度
     *
     * @return int
     */
    @Override
    public int length() {
        return length;
    }

    /**
     * 判断是否为空
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return null == head;
    }

    /**
     * 清空线性表
     */
    @Override
    public void clear() {
        // 批量GC回收对象
        Node<T> curr = head;
        while (null != curr){
            Node<T> tmp = curr.getNext();
            curr.setNext(null);
            curr = tmp;
        }
        head = null;
        tail = null;
        length = EMPTY_LENGTH;
    }

    private void linkHead(T t){
        Node<T> node = new Node<>();
        node.setData(t);
        node.setNext(head);

        if(null == head){
            tail = node;
        }
        head = node;
        length++;
    }

    private void linkTail(T t){
        if(null == tail){
            linkHead(t);
            return;
        }

        Node<T> node = new Node<>();
        node.setData(t);
        node.setNext(null);

        tail.setNext(node);
        tail = node;
        length++;
    }

    /**
     * 节点
     * @param <T>
     */
    private static class Node<T> {

        private T data;

        private Node<T> next;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

    }


    public static void main(String[] args) {
        MyQueue<Integer> myStack = new MyLinkedQueue<>();
        myStack.push(1).push(2).push(3).push(4);

        while (null != myStack.peek()){
            Integer pop = myStack.pop();
            System.out.println("数据："+pop + "  队列长："+myStack.length());
        }

    }

}
