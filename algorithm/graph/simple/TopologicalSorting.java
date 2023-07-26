package algorithm.graph.simple;

import structure.Graph.Graph;
import structure.Graph.Node;

import java.util.*;

/**
 * 拓扑排序（工程类项目）
 *
 * 假如有一个 工程，需要建造一座大楼
 * 需要
 * 1. 先打地基
 * 2. 插钢筋 、 砌砖
 * 3. 砌砖完毕 才可以抹水泥
 * 3. 等水泥干了以后 再浇筑
 * 4. 最后封顶、拆围栏
 *
 *
 */
public class TopologicalSorting {

    public static void main(String[] args) {
        // 以下是工时
        // 0 地基
        // 1 钢筋
        // 2 砌砖
        // 3 抹水泥
        // 4 浇筑
        // 5 封顶
        // 6 拆围栏
        int[][] graphArray = {
                {0, 20, 20, 0, 0, 0, 0},
                {0, 0, 0, 5, 0, 0, 0},
                {0, 0, 0, 10, 0, 0, 0},
                {0, 0, 0, 0, 10, 0, 0},
                {0, 0, 0, 0, 0, 2, 3},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };

        Graph graph = MatrixConverter.conver(graphArray);
        List<Node> sort = sort(graph);
        for (Node node : sort) {
            System.out.println(node.value);
        }
    }

    public static List<Node> sort(Graph graph){
        if(null == graph || graph.nodes == null || graph.nodes.size() == 0){
            return Collections.emptyList();
        }

        Queue<Node> sourceNodes = new ArrayDeque<>();
        // 第一遍 找到 原始节点
        for (Map.Entry<Integer, Node> entry : graph.nodes.entrySet()) {
            Node node = entry.getValue();
            if(node.in == 0){
                sourceNodes.add(node);
            }
        }

        // 非法图数据
        if(sourceNodes.isEmpty()){
            return Collections.emptyList();
        }

        List<Node> result = new ArrayList<>();
        while (!sourceNodes.isEmpty()){
            Node poll = sourceNodes.poll();
            for (Node next : poll.nexts) {
                next.in--;
                if(next.in == 0){
                    sourceNodes.add(next);
                }
            }
            result.add(poll);
        }
        return result;
    }

}
