package algorithm.StackAndQueue;

import structure.StackAndQueue.MyLinkedStack;
import structure.StackAndQueue.MyStack;

import java.util.HashMap;
import java.util.Map;

/**
 * 判断正确括号表达式（栈实现）
 *
 * @author 周鹏程
 * @date 2022-10-10 10:39 AM
 **/
public final class ParenthesisExpressionByStack {

    private final static Map<Character, Character> DICT_MAP = new HashMap<>();
    static {
        DICT_MAP.put('(', ')');
        DICT_MAP.put('[', ']');
        DICT_MAP.put('{', '}');
    }

    public static boolean isCorrect(String expression){
        if(null == expression || "".equals(expression)){
            return false;
        }

        MyStack<Character> myStack = new MyLinkedStack<>();
        for (int i = 0; i < expression.length(); i++) {
            char charAt = expression.charAt(i);

            // 左括号 压栈
            if(DICT_MAP.containsKey(charAt)){
                myStack.push(charAt);
                continue;
            }

            // 右括号 弹栈
            try {
                Character popChar = myStack.pop();
                Character popCharValue = DICT_MAP.get(popChar);
                if(null == popCharValue ||
                        !popCharValue.equals(charAt)){
                    return false;
                }
            }catch (Exception e){
                // 越界 等异常
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String expression = "({}[])";
        boolean correct = isCorrect(expression);
        System.out.println(correct);
    }

}
