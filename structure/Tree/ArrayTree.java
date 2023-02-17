package structure.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 树 （孩子节点由数组实现）
 *
 * 数组表示的数结构
 *
 * @author 周鹏程
 * @date 2022-11-15 5:30 PM
 **/
public class ArrayTree implements Tree{

    private ILeafNode head;

    public static void main(String[] args) {
        /*
        *            1
        *     11     12    13
        *         121  122
        *         1211
        * */


        ArrayTree arrayTree = new ArrayTree();

        LeafNode head = new LeafNode();
        head.setData("1");

        LeafNode a1 = new LeafNode();
        a1.setData("11");
        head.addChild(a1);


        LeafNode a2 = new LeafNode();
        a2.setData("12");
        head.addChild(a2);

        LeafNode a3 = new LeafNode();
        a3.setData("13");
        head.addChild(a3);

        LeafNode a2a = new LeafNode();
        a2a.setData("121");
        a2.addChild(a2a);


        LeafNode a2b = new LeafNode();
        a2b.setData("122");
        a2.addChild(a2b);


        LeafNode a2a1 = new LeafNode();
        a2a1.setData("1211");
        a2a.addChild(a2a1);


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

            // 依次放入 孩子节点
            if(null != poll.getChildrenList()
                && poll.getChildrenList().size() > 0){
                leafNodes.addAll(poll.getChildrenList());
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

        private List<LeafNode> childrenList;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public List<LeafNode> getChildrenList() {
            return childrenList;
        }

        public void addChild(LeafNode leafNode) {
            if(null == this.childrenList){
                this.childrenList = new ArrayList<>();
            }
            this.childrenList.add(leafNode);
        }
    }

}
