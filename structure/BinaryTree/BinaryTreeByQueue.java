package structure.BinaryTree;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树 - 三叉链表
 * 分层遍历 队列实现
 *
 * @author 周鹏程
 * @date 2022-10-31 7:33 PM
 **/
public class BinaryTreeByQueue {

    public static void main(String[] args) {
        BinaryTreeByQueue bt = new BinaryTreeByQueue();

        //        2
        //   1          5
        //          3        7
        //                        8
        //                            66
        //                        41

        bt.push(2).push(5).push(1).push(7);
        bt.push(8).push(3).push(66).push(41);

        System.out.println("分层遍历");
        // 2 1 5 3 7 8 66 41
        bt.levelOrder(bt.head);


    }


    private LeafNode head;


    /**
     * 分层遍历
     * @param node 根结点
     */
    private void levelOrder(LeafNode node){
        if(null == node){
            return;
        }

        Queue<LeafNode> queue = new LinkedBlockingQueue<>();
        queue.add(node);
        while (!queue.isEmpty()){
            LeafNode poll = queue.poll();
            System.out.println(poll.getData());

            if(null != poll.getlChild()){
                queue.add(poll.getlChild());
            }

            if(null != poll.getrChild()){
                queue.add(poll.getrChild());
            }
        }
    }


    public BinaryTreeByQueue push(int data){
        LeafNode leafNode = new LeafNode();
        leafNode.setData(data);

        if(null == head){
            head = leafNode;
            return this;
        }

        LeafNode currNode = head;
        for(;;){
            if(currNode.getData() > data){
                if(null != currNode.getlChild()){
                    currNode = currNode.getlChild();
                    continue;
                }
                leafNode.setParent(currNode);
                currNode.setlChild(leafNode);
                break;
            }else {
                if(null != currNode.getrChild()){
                    currNode = currNode.getrChild();
                    continue;
                }
                leafNode.setParent(currNode);
                currNode.setrChild(leafNode);
                break;
            }
        }
        return this;
    }



    /**
     * 二叉树叶子节点
     *
     * Integer
     */
    public static class LeafNode{

        private LeafNode parent;

        private LeafNode lChild;

        private LeafNode rChild;

        private Integer data;

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public LeafNode getParent() {
            return parent;
        }

        public void setParent(LeafNode parent) {
            this.parent = parent;
        }

        public LeafNode getlChild() {
            return lChild;
        }

        public void setlChild(LeafNode lChild) {
            this.lChild = lChild;
        }

        public LeafNode getrChild() {
            return rChild;
        }

        public void setrChild(LeafNode rChild) {
            this.rChild = rChild;
        }
    }

}
