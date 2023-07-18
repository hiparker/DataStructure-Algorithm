package structure.Graph;

/**
 * 边
 *
 * @author 周鹏程
 * @date 2023-07-15 11:16 AM
 **/
public class Edge {

    /** 权重 */
    public int weight;

    /** 来至于 */
    public Node from;

    /** 要去哪儿 */
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
