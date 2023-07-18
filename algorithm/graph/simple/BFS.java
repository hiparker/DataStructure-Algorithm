package algorithm.graph.simple;

import structure.Graph.Graph;
import structure.Graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 广搜
 * @author 周鹏程
 * @date 2023/7/18 12:03
 */
public class BFS {

	public static void main(String[] args) {
		int[][] graphArray = {
				{0, 0, 2, 5},
				{0, 0, 3, 7},
				{7, 5, 0, 8},
				{1, 0, 2, 0}
		};

		Graph graph = MatrixConverter.conver(graphArray);
		bfsPrint(graph.nodes.get(0));
	}


	/**
	 * 广搜遍历
	 * @param start start
	 */
	public static void bfsPrint(Node start){
		HashSet<Node> set = new HashSet<>();
		Queue<Node> queue = new LinkedList<>();
		set.add(start);
		queue.add(start);
		while (!queue.isEmpty()){
			Node poll = queue.poll();
			System.out.println(poll.value);
			for (Node next : poll.nexts) {
				if(!set.contains(next)){
					set.add(next);
					queue.add(next);
				}
			}

		}
	}

}
