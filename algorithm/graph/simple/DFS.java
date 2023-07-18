package algorithm.graph.simple;

import algorithm.graph.simple.base.Graph;
import algorithm.graph.simple.base.Node;

import java.util.HashSet;
import java.util.Stack;

/**
 * 深搜
 * @author 周鹏程
 * @date 2023/7/18 12:03
 */
public class DFS {

	public static void main(String[] args) {
		int[][] graphArray = {
				{0, 0, 2, 5},
				{0, 0, 3, 7},
				{7, 5, 0, 8},
				{1, 0, 2, 0}
		};

		Graph graph = MatrixConverter.conver(graphArray);
		dfsPrint(graph.nodes.get(0));
	}


	/**
	 * 深搜遍历
	 * @param start start
	 */
	public static void dfsPrint(Node start){
		HashSet<Node> set = new HashSet<>();
		Stack<Node> stack = new Stack<>();
		set.add(start);
		stack.add(start);

		System.out.println(start.value);
		while (!stack.isEmpty()){
			Node pop = stack.pop();
			for (Node next : pop.nexts) {
				if(!set.contains(next)){
					stack.add(pop);
					stack.add(next);
					set.add(next);
					System.out.println(next.value);
					// 只处理一个
					break;
				}
			}

		}
	}

}
