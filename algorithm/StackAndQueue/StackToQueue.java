package algorithm.StackAndQueue;


import structure.StackAndQueue.MyLinkedStack;
import structure.StackAndQueue.MyQueue;
import structure.StackAndQueue.MyStack;

/**
 * 栈转队列
 *
 * @author 周鹏程
 * @date 2022-10-10 5:27 PM
 **/
public class StackToQueue {

    public static void main(String[] args) {
        MyStackQueue<Integer> stackQueue = new MyStackQueue<>();
        stackQueue.push(1).push(2).push(3).push(4);
        while (!stackQueue.isEmpty()){
            System.out.println(stackQueue.pop());
        }
    }

    /**
     * 核心：三角置换
     * s1 ▲ 转换到  s2 ▼
     * @param <T>
     */
    public static class MyStackQueue<T> implements MyQueue<T> {

        // 双栈
        private final MyStack<T> s1 = new MyLinkedStack<>();
        private final MyStack<T> s2 = new MyLinkedStack<>();

        /**
         * 压入队列
         *
         * @param t <T>数据
         * @return MyQueue<T>
         */
        @Override
        public MyQueue<T> push(T t) {
            // 每次只需要添加到 s1里
            s1.push(t);
            // 如果第一次 s2 为空 则把 s1 倒入 s2
            transferTo();
            return this;
        }

        /**
         * 弹栈
         *
         * @return <T>
         */
        @Override
        public T pop() {
            // 如果上一次 已经把 s2 弹出
            // 则s2为空，需要把s1 再倒入 s2
            transferTo();
            if(s2.isEmpty()){
                return null;
            }
            return s2.pop();
        }

        /**
         * 栈数据转移
         */
        private void transferTo(){
            if(s2.isEmpty()){
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
        }

        /**
         * 查看当前栈顶 元素
         *
         * @return <T>
         */
        @Override
        public T peek() {
            transferTo();
            return s2.peek();
        }

        /**
         * 获得数据长度
         *
         * @return int
         */
        @Override
        public int length() {
            return s1.length() + s2.length();
        }

        /**
         * 判断是否为空
         *
         * @return boolean
         */
        @Override
        public boolean isEmpty() {
            return s1.isEmpty() && s2.isEmpty();
        }

        /**
         * 清空线性表
         */
        @Override
        public void clear() {
            s1.clear();
            s2.clear();
        }
    }


}
