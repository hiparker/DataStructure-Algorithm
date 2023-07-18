package algorithm.graph.simple;

import algorithm.graph.simple.base.Edge;
import algorithm.graph.simple.base.Graph;
import algorithm.graph.simple.base.Node;

/**
 * n*3 矩阵转化器 -> 图
 * @author 周鹏程
 * @date 2023/7/18 11:13
 */
public final class Matrix3Converter {

	private Matrix3Converter(){}

	/**
	 * n*3矩阵
	 * [weight, from节点上面的值，to节点上面的值]
	 *
	 * @param graphArray
	 * @return
	 */
	public static Graph conver(int[][] graphArray){
		Graph graph = new Graph();
		if(null == graphArray){
			return graph;
		}

		for (int[] matrixArray : graphArray) {
			int weight = matrixArray[0];
			int from = matrixArray[1];
			int to = matrixArray[2];


			// 如果 from to 节点不存在 则先创建节点
			if (!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			if (!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}

			Node fromNode = graph.nodes.get(from);
			Node toNode = graph.nodes.get(to);

			// to节点 可能为空
			if (toNode != null && weight > 0) {
				Edge edge = new Edge(weight, fromNode, toNode);

				fromNode.out++;
				fromNode.nexts.add(toNode);
				fromNode.edges.add(edge);
				toNode.in++;
				graph.edges.add(edge);
			}
		}
		return graph;
	}

	public static void main(String[] args) {
		int[][] graphArray = {
				{2, 0, 2},
				{5, 0, 3},
				{3, 1, 2},
				{7, 1, 3},
				{7, 2, 0},
				{5, 2, 1},
				{8, 2, 3},
				{1, 3, 0},
				{2, 3, 2}
		};

		Graph graph = Matrix3Converter.conver(graphArray);
		System.out.println(graph);
	}

}
