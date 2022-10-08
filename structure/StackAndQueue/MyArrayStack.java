package structure.StackAndQueue;

import structure.LinearStructure.MyArrayList;


/**
 * 数组栈
 *
 * @author 周鹏程
 * @date 2022-10-08 9:02 PM
 **/
public class MyArrayStack<T> implements MyStack<T>{

    private static final int EMPTY_LENGTH = 0;

    private final MyArrayList<Object> arrayList;

    /**
     * 初始化 构造函数
     *
     */
    public MyArrayStack(){
        arrayList = new MyArrayList<>();
    }


    /**
     * 压栈
     * 压栈效率较低 每次有扩容的损耗
     *
     * @param t <T>数据
     * @return MyStack<T>
     */
    @Override
    public MyStack<T> push(T t) {
        arrayList.insert(t);
        return this;
    }

    /**
     * 弹栈
     * 时间复杂度 O(1)
     *
     * @return <T>
     */
    @SuppressWarnings("unchecked")
    @Override
    public T pop() {
        return (T) arrayList.delete(arrayList.length()-1);
    }

    /**
     * 查看当前栈顶 元素
     *
     * @return <T>
     */
    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        if(EMPTY_LENGTH == arrayList.length()){
            return null;
        }
        return (T) arrayList.getElem(arrayList.length()-1);
    }

    /**
     * 获得数据长度
     *
     * @return int
     */
    @Override
    public int length() {
        return arrayList.length();
    }

    /**
     * 判断是否为空
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    /**
     * 清空线性表
     */
    @Override
    public void clear() {
        arrayList.clear();
    }

    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyArrayStack<>();
        myStack.push(1).push(2).push(3).push(4);

        while (null != myStack.peek()){
            Integer pop = myStack.pop();
            System.out.println("数据："+pop + "  栈长："+myStack.length());
        }

    }

}
