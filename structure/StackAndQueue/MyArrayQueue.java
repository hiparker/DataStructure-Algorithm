package structure.StackAndQueue;


import java.util.Arrays;

/**
 * 顺序表 循环队列
 * 实现方式类似于磁盘应用 只是变更指针，数据循环回来后覆盖
 * 未来避免每次因为array都，复制调整整体长度 而造成的性能损耗
 * 该方案为空间换时间
 *
 * 核心是 front 与 rear
 *  front 指向当前准备取出的位置
 *  rear 指向当前准备插入的位置
 *
 * 如果 front == rear 则队列为空
 * 如果 (rear+1)%maxQueueLength == front 则队列已满
 *
 * @author 周鹏程
 * @date 2022-10-08 9:02 PM
 **/
public class MyArrayQueue<T> implements MyQueue<T>{

    private static final int EMPTY_LENGTH = 0;

    /** 最大长度 */
    private final int maxQueueLength;
    /** 头节点 下标 */
    private int front;
    /** 尾节点 下标 */
    private int rear;
    /** 数组 */
    private final Object[] array;

    public MyArrayQueue(int maxQueueLength){
        if(maxQueueLength <= EMPTY_LENGTH){
            throw new RuntimeException("队列长度最小为1");
        }

        // 多出一个空间来解决 假满现象
        // 毕竟当前队列的 rear 指向的是下一个可用位置
        // 当预插入元素 == maxQueueLength 的时候 ，下一个位置便等于0
        // 虽然还有一个空为可以存放数据，但会违反下一个元素的指向，
        // 目前的解决方案是 提前预留出一个空间来解决计算问题
        this.maxQueueLength = maxQueueLength+1;
        this.front = 0;
        this.rear = 0;
        this.array  = new Object[this.maxQueueLength];
    }


    /**
     * 判断队列是否已满
     * @return boolean
     */
    private boolean isFull(){
        return (rear + 1) % maxQueueLength == front;
    }


    /**
     * 压入队列
     * 时间复杂度 O(1)
     *
     * @param t <T>数据
     * @return MyStack<T>
     */
    @Override
    public MyQueue<T> push(T t) {
        if(isFull()){
            throw new RuntimeException("队列溢出 -> 最大允许长度为"+maxQueueLength);
        }

        array[rear] = t;
        rear = (rear+1)%maxQueueLength;
        return this;
    }

    /**
     * 弹栈
     * 时间复杂度 O(1)
     *
     * @return <T>
     */
    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }

        T element = (T) array[front];
        front = (front+1)%maxQueueLength;
        return element;
    }

    /**
     * 查看当前栈顶 元素
     *
     * @return <T>
     */
    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if(isEmpty()){
            return null;
        }
        return (T) array[front];
    }

    /**
     * 获得数据长度
     *
     * @return int
     */
    @Override
    public int length() {
        // 已知 length 不可能大于 max
        // 循环表情况下 要么是正数 要么是循环过去负数+max
        return (rear - front + maxQueueLength)%maxQueueLength;
    }

    /**
     * 判断是否为空
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 清空线性表
     */
    @Override
    public void clear() {
        // 批量GC回收对象
        Arrays.fill(array, true);
        front=0;
        rear=0;
    }


    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyArrayQueue<>(5);
        myQueue.push(1).push(2).push(3).push(4).push(5);

        while (!myQueue.isEmpty()){
            Integer pop = myQueue.pop();
            System.out.println("数据："+pop + "  队列长："+myQueue.length());
        }

    }

}
