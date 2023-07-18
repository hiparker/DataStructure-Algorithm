package algorithm.graph.simple.base;

import java.util.ArrayList;

/**
 * Node 节点
 *
 * @author 周鹏程
 * @date 2023-07-15 11:14 AM
 **/
public class Node {

    /** 值 */
    public int value;

    /** 入度 */
    public int in;

    /** 出度 */
    public int out;

    /** 邻居 */
    public ArrayList<Node> nexts;

    /** 边 */
    public ArrayList<Edge> edges;

    public Node(int value, int in, int out, ArrayList<Node> nexts, ArrayList<Edge> edges) {
        this.value = value;
        this.in = in;
        this.out = out;
        this.nexts = nexts;
        this.edges = edges;
    }
}
