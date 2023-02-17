package structure.Tree;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 树 （孩子兄弟二叉树）
 *
 * 二叉树 模式 类似于 横竖都通马路
 *
 * 优点：
 *  如果想要获取当前节点的所有 孩子节点
 *      只需要 getFirstChild，然后再FirstChild节点上 getNextBro 即可
 *
 *  如果想要获得当前节点的兄弟节点
 *      只需要 getNextBro 即可
 *
 * @author 周鹏程
 * @date 2022-11-15 5:30 PM
 **/
public class ChildBroTree implements Tree{

    private ILeafNode head;

    public static void main(String[] args) {
        /*
        *            1
        *     11     12    13
        *         121  122
        *         1211
        * */


        ChildBroTree arrayTree = new ChildBroTree();

        LeafNode head = new LeafNode();
        head.setData("1");



        LeafNode a1 = new LeafNode();
        a1.setData("11");
        head.setFirstChild(a1);


        LeafNode a2 = new LeafNode();
        a2.setData("12");
        a1.setNextBro(a2);

        LeafNode a3 = new LeafNode();
        a3.setData("13");
        a2.setNextBro(a3);

        LeafNode a2a = new LeafNode();
        a2a.setData("121");
        a2.setFirstChild(a2a);


        LeafNode a2b = new LeafNode();
        a2b.setData("122");
        a2a.setNextBro(a2b);


        LeafNode a2a1 = new LeafNode();
        a2a1.setData("1211");
        a2a.setFirstChild(a2a1);


        arrayTree.init(head);
        // 遍历树
        arrayTree.traversal();
    }

    @Override
    public void init(ILeafNode iLeafNode) {
        this.head = iLeafNode;
    }

    @Override
    public void traversal() {
        if(null == head){
            return;
        }

        Queue<LeafNode> leafNodes = new LinkedBlockingQueue<>();
        leafNodes.add((LeafNode) head);

        while (!leafNodes.isEmpty()){
            LeafNode poll = leafNodes.poll();
            System.out.println(poll.getData());

            // 依次放入 孩子节点的兄弟节点
            LeafNode childBro = poll.getFirstChild();
            while (null != childBro){
                leafNodes.add(childBro);
                childBro = childBro.getNextBro();
            }
        }
    }

    /**
     * 节点
     *
     * Integer
     */
    public static class LeafNode implements ILeafNode{

        private String data;

        private LeafNode firstChild;

        private LeafNode nextBro;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public LeafNode getFirstChild() {
            return firstChild;
        }

        public void setFirstChild(LeafNode firstChild) {
            this.firstChild = firstChild;
        }

        public LeafNode getNextBro() {
            return nextBro;
        }

        public void setNextBro(LeafNode nextBro) {
            this.nextBro = nextBro;
        }
    }

}
