package algorithm.graph.simple;

import structure.Graph.Edge;
import structure.Graph.Graph;
import structure.Graph.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 手撕堆 实现 最短路径
 * dijkstra
 * @author 周鹏程
 * @date 2023/7/28 14:57
 */
public class MinPathDijkstra {

	public static void main(String[] args) {

//		int[] arr = {5, 7 , 8, 3 ,6 ,4 ,10 ,1 ,9, 2};
//		Heap h = new Heap(arr.length);
//
//		for (int i : arr) {
//			Node node = new Node(i);
//			h.addOrUpdateOrIgnore(node, i);
//		}
//
//		while (!h.isEmpty()){
//			Heap.NodeRecord pop = h.pop();
//			System.out.println(pop.distance);
//		}


		int[][] graphArray = {
				{0, 0, 2, 5},
				{0, 0, 3, 7},
				{7, 5, 0, 8},
				{0, 3, 2, 0}
		};

		Graph graph = MatrixConverter.conver(graphArray);

		Map<Node, Integer> nodeIntegerMap = dijkstra2(graph.nodes.get(0), graph.nodes.size());
		System.out.println(12313);

	}

	public static Map<Node, Integer> dijkstra2(Node head, int size){
		Heap heap = new Heap(size);
		heap.addOrUpdateOrIgnore(head, head.value);
		Map<Node, Integer> resultMap = new HashMap<>();
		while (!heap.isEmpty()){
			Heap.NodeRecord nodeRecord = heap.pop();
			Node node = nodeRecord.node;
			Integer distance = nodeRecord.distance;
			for (Edge edge : node.edges) {
				heap.addOrUpdateOrIgnore(edge.to, distance + edge.weight);
			}
			resultMap.put(nodeRecord.node, nodeRecord.distance);
		}
		return resultMap;
	}

	private static class Heap {

		/** 堆 */
		private Node[] nodes;
		private int size;
		private Map<Node, Integer> distanceMap;
		private Map<Node, Integer> heapIndexMap;

		public Heap(int size) {
			nodes = new Node[size];
			heapIndexMap = new HashMap<>();
			distanceMap = new HashMap<>();
			this.size = 0;
		}

		public boolean isEmpty(){
			return size == 0;
		}

		public void addOrUpdateOrIgnore(Node node, int distance) {
			// 在堆上就更新
			if(isHeap(node)){
				// 更新 取最小
				distanceMap.put(node, Math.min(distanceMap.get(node), distance));
				headInsert(heapIndexMap.get(node));
			}

			// 不在堆上 且 曾经没存在过 则新增
			if(!isEntered(node)){
				nodes[size] = node;
				distanceMap.put(node, distance);
				heapIndexMap.put(node, size);
				headInsert(size++);
			}
		}

		public static class NodeRecord{

			public Node node;
			public  int distance;

			public NodeRecord(Node node, int distance) {
				this.node = node;
				this.distance = distance;
			}
		}

		public NodeRecord pop(){
			NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
			swap(0, size-1);
			distanceMap.remove(nodes[size-1]);
			heapIndexMap.put(nodes[size-1], -1);
			heapify(0, --size);
			return nodeRecord;
		}

		/**
		 * 向上调整
		 * parentIndex = (index - 1) / 2
		 */
		public void headInsert(int index){
			while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index-1)/2])){
				// 交换
				swap(index, (index-1)/2);
				index = (index-1)/2;
			}
		}

		/**
		 * 向下调整
		 *
		 * left = index * 2 + 1;
		 */
		public void heapify(int index, int size){
			int leftIndex = index * 2 + 1;
			while (leftIndex < size){
				// 比较两个孩子 谁小
				int smallIndex = leftIndex+1 < size && distanceMap.get(nodes[leftIndex+1]) < distanceMap.get(nodes[leftIndex])
						? leftIndex+1
						: leftIndex;
				// 与自身比较 谁更小
				smallIndex = distanceMap.get(nodes[smallIndex]) < distanceMap.get(nodes[index])?smallIndex:index;
				if(smallIndex == index){
					break;
				}

				swap(smallIndex, index);
				index = smallIndex;
				leftIndex = index * 2 + 1;
			}
		}

		private void swap(int a, int b) {
			heapIndexMap.put(nodes[a], b);
			heapIndexMap.put(nodes[b], a);

			Node temp = nodes[a];
			nodes[a] = nodes[b];
			nodes[b] = temp;
		}

		/**
		 * 是否 存在过
		 * @param node node
		 * @return boolean
		 */
		private boolean isEntered(Node node){
			return heapIndexMap.containsKey(node);
		}

		/**
		 * 是否在堆上
		 * @param node node
		 * @return boolean
		 */
		private boolean isHeap(Node node){
			return heapIndexMap.containsKey(node) && heapIndexMap.get(node) != -1;
		}

	}

}
