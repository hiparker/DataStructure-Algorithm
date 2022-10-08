package structure.StackAndQueue;

/**
 * 队列
 *
 * @author 周鹏程
 * @date 2022-10-08 9:02 PM
 **/
public interface MyQueue<T> {

    /**
     * 压入队列
     *
     * @param t <T>数据
     * @return MyQueue<T>
     */
    MyQueue<T> push(T t);

    /**
     * 弹栈
     *
     * @return <T>
     */
    T pop();

    /**
     * 查看当前栈顶 元素
     *
     * @return <T>
     */
    T peek();


    /**
     * 获得数据长度
     * @return int
     */
    int length();

    /**
     * 判断是否为空
     *
     * @return boolean
     */
    boolean isEmpty();

    /**
     * 清空线性表
     */
    void clear();

}
