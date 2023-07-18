package algorithm.graph.simple;

import algorithm.graph.simple.base.Edge;
import algorithm.graph.simple.base.Graph;
import algorithm.graph.simple.base.Node;

/**
 * 矩阵转化器 -> 图
 * @author 周鹏程
 * @date 2023/7/18 11:13
 */
public final class MatrixConverter {

	private MatrixConverter(){}

	public static Graph conver(int[][] graphArray){
		Graph graph = new Graph();
		if(null == graphArray){
			return graph;
		}

		for (int i = 0; i < graphArray.length; i++) {
			for (int j = 0; j < graphArray[i].length; j++) {
				int from = i;
				int to = j;
				int weight = graphArray[from][to];

				// 如果 from to 节点不存在 则先创建节点
				if(!graph.nodes.containsKey(from)){
					graph.nodes.put(from, new Node(from));
				}
				if(!graph.nodes.containsKey(to)){
					graph.nodes.put(to, new Node(to));
				}

				Node fromNode = graph.nodes.get(from);
				Node toNode = graph.nodes.get(to);

				// to节点 可能为空
				if(toNode != null && weight > 0){
					Edge edge = new Edge(weight, fromNode, toNode);

					fromNode.out++;
					fromNode.nexts.add(toNode);
					fromNode.edges.add(edge);
					toNode.in++;
					graph.edges.add(edge);
				}
			}
		}
		return graph;
	}

	public static void main(String[] args) {
		int[][] graphArray = {
				{0, 0, 2, 5},
				{0, 0, 3, 7},
				{7, 5, 0, 8},
				{1, 0, 2, 0}
		};

		Graph graph = MatrixConverter.conver(graphArray);
		System.out.println(graph);

	}

}
