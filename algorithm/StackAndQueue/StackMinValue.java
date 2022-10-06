package algorithm.StackAndQueue;

import java.util.Stack;

/**
 * 求栈的最小值
 *
 * 核心的玩法就是
 *  插入数据时，同时插入到最小栈内
 *      如果当前小于当前最小栈，则插入数据为当前值，否则保持上一次最小值内容
 *  移除数据时，不会维护其他复杂的结构，因为数据时双插，直接全部移除即可（空间换时间）
 *
 *  时间复杂度：O(1)
 *  空间复杂度：O(n)
 *
 * @author 周鹏程
 * @date 2022-09-20 12:35 PM
 **/
public class StackMinValue {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(16);
        myStack.push(5);
        myStack.push(3);
        myStack.push(8);
        myStack.push(2);
        myStack.push(5);

        myStack.printData();
        System.out.println("栈内最小值为：" + myStack.getMinData());

        myStack.poll();
        myStack.poll();
        myStack.printData();
        System.out.println("栈内最小值为：" + myStack.getMinData());
    }


    private static class MyStack {

        private final Stack<Integer> data = new Stack<>();
        private final Stack<Integer> minData = new Stack<>();

        public void push(int value){
            data.push(value);

            // 处理最小数据
            if(minData.empty()){
                minData.push(value);
            }else {
                Integer peekValue = minData.peek();
                // 如果当前栈内 数据大于 新数据 则新数据存入栈
                if(peekValue > value){
                    minData.push(value);
                }else {
                    minData.push(peekValue);
                }
            }
        }

        public int poll(){
            minData.pop();
            return data.pop();
        }

        public int getMinData(){
            return minData.peek();
        }

        public void printData(){
            System.out.println("--------------");
            for (Integer datum : data) {
                System.out.print(datum+"，");
            }
            System.out.println();
            for (Integer datum : minData) {
                System.out.print(datum+"，");
            }
            System.out.println();
            System.out.println("--------------");
        }
    }

}
