package algorithm.StackAndQueue;

import structure.StackAndQueue.MyLinkedStack;
import structure.StackAndQueue.MyStack;

/**
 * 十进制转换
 * 栈实现
 * @author 周鹏程
 * @date 2022-10-08 10:27 PM
 **/
public final class DecimalismToOtherByStack {

    /**
     * 转换 只处理正数
     * @param source 源数据
     * @param system 进制
     * @return String
     */
    public static String transition(int source, SystemEnums system){
        int systemEnumsVal = system.getValue();

        MyStack<Integer> myStack = new MyLinkedStack<>();
        int quotient = source;
        while (quotient > 0){
            myStack.push(quotient%systemEnumsVal);
            quotient = quotient/systemEnumsVal;
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (null != myStack.peek()){
            Integer pop = myStack.pop();
            stringBuilder.append(pop);
        }

        return stringBuilder.toString();
    }

    /**
     * 进制
     */
    public enum SystemEnums {

        /** 十进制 */
        TEN(10),

        /** 八进制 */
        EIGHT(8),

        /** 二进制 */
        BIN(2);

        private final int value;

        SystemEnums(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        String transition = transition(64, SystemEnums.BIN);
        System.out.println(transition);
    }
}
