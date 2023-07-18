package structure.Graph;

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

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
