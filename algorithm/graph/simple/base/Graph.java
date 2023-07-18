package algorithm.graph.simple.base;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 *
 * @author 周鹏程
 * @date 2023-07-15 11:12 AM
 **/
public class Graph {

    /** 所有节点 */
    public HashMap<Integer, Node> nodes;
    /** 所有边 */
    public HashSet<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
