package algorithm.StackAndQueue;

import structure.StackAndQueue.MyLinkedQueue;
import structure.StackAndQueue.MyQueue;
import structure.StackAndQueue.MyStack;

/**
 * 队列转栈
 *
 * @author 周鹏程
 * @date 2022-10-10 5:27 PM
 **/
public class QueueToStack {

    public static void main(String[] args) {
        MyQueueStack<Integer> queueStack = new MyQueueStack<>();
        queueStack.push(1).push(2).push(3).push(4);
        while (null != queueStack.peek()){
            System.out.println(queueStack.pop());
        }
    }

    public static class MyQueueStack<T> implements MyStack<T> {

        // 双栈
        private MyQueue<T> q1 = new MyLinkedQueue<>();
        private MyQueue<T> q2 = new MyLinkedQueue<>();

        /**
         * 压栈
         *
         * @param t <T>数据
         * @return MyStack<T>
         */
        @Override
        public MyStack<T> push(T t) {
            q1.push(t);
            return this;
        }

        /**
         * 弹栈
         *
         * @return <T>
         */
        @Override
        public T pop() {
            // 保留队列 1的 最后一个
            for (int i = 0; i < q1.length()-1; i++) {
                q2.push(q1.pop());
            }

            // 转换
            // 将 q1 q2 互换位置
            transferTo();

            // 实际q2 的 pop 是 q1 剩下的最后一个
            return q2.pop();
        }

        /**
         * 队列数据转移
         * 交换位置
         *
         * q1 = 剩余数据
         * q2 = q1 的最后一个节点数据
         */
        private void transferTo(){
            if(!q2.isEmpty()){
                return;
            }
            MyQueue<T> tmp = q1;
            q1 = q2;
            q2 = tmp;
        }

        /**
         * 查看当前栈顶 元素
         *
         * @return <T>
         */
        @Override
        public T peek() {
            transferTo();
            return q2.peek();
        }

        /**
         * 获得数据长度
         *
         * @return int
         */
        @Override
        public int length() {
            return q1.length() + q2.length();
        }

        /**
         * 判断是否为空
         *
         * @return boolean
         */
        @Override
        public boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        /**
         * 清空线性表
         */
        @Override
        public void clear() {
            q1.clear();
            q2.clear();
        }
    }

}
