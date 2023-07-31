package algorithm.recursion;

import java.util.Stack;

/**
 * 递归 翻转栈
 *
 * @author 周鹏程
 * @date 2023/7/31 18:28
 */
public class FlipStack {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);

		reverse(stack);

		while (!stack.isEmpty()){
			Integer pop = stack.pop();
			System.out.println(pop);
		}
	}

	public static void reverse(Stack<Integer> stack){
		if (stack.isEmpty()){
			return;
		}

		int flip = flip(stack);
		reverse(stack);
		stack.push(flip);
	}

	/**
	 * 去掉底 并返回
	 * @param stack stack
	 * @return int
	 */
	private static int flip(Stack<Integer> stack){
		Integer pop = stack.pop();
		if(stack.isEmpty()){
			return pop;
		}

		int flipPop = flip(stack);
		stack.push(pop);
		return flipPop;
	}



}
