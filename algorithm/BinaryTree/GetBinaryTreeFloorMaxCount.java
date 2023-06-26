package algorithm.BinaryTree;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树 - 求二叉树最宽的层有多少个节点
 * 分层遍历 队列实现
 *
 * @author 周鹏程
 * @date 2022-10-31 7:33 PM
 **/
public class GetBinaryTreeFloorMaxCount {

    public static void main(String[] args) {
        GetBinaryTreeFloorMaxCount bt = new GetBinaryTreeFloorMaxCount();

        //        2
        //   1          5
        //          4        7
        //      3        6        8
        //                            66
        //                        41

        bt.push(2).push(5).push(4).push(1).push(7).push(6);
        bt.push(8).push(3).push(66).push(41);

        System.out.println("打印二叉树");
        bt.printBinaryTree(bt.head);

        System.out.println("求二叉树最宽的层有多少个节点");
        int floorMaxCount = bt.getFloorMaxCount(bt.head);
        System.out.println(floorMaxCount);

        System.out.println("分层遍历");
        // 2 1 5 4 7 3 6 8 66 41
        bt.levelOrder(bt.head);
    }


    private LeafNode head;

    /**
     * 打印二叉树
     * @param head
     */
    public void printBinaryTree(LeafNode head){
        doPrintBinaryTree(head, 0, "H", 17);
    }
    private void doPrintBinaryTree(LeafNode head, int height, String to, int len){
        if(null == head){
            return;
        }
        doPrintBinaryTree(head.rChild, height+1, "v", len);

        String tVal = to + head.data + to;
        int left = (len - tVal.length()) >> 1;
        int right = len - left - tVal.length();
        String tValWrapper = getSpace(left) + tVal + getSpace(right);
        System.out.println(getSpace(height*len) + tValWrapper);

        doPrintBinaryTree(head.lChild, height+1, "^", len);
    }
    private String getSpace(int count){
        String space = " ";
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < count; i++) {
            stringBuilder.append(space);
        }
        return stringBuilder.toString();
    }




    /**
     * 分层遍历
     * @param node 根结点
     */
    public void levelOrder(LeafNode node){
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


    /**
     * 求二叉树最宽的层有多少个节点
     * @param node 根结点
     */
    public int getFloorMaxCount(LeafNode node){
        if(null == node){
            return 0;
        }


        Queue<LeafNode> queue = new LinkedBlockingQueue<>();
        queue.add(node);

        int max = 0;
        int currLevelCount = 0;
        LeafNode curEnd = node;
        LeafNode nextEnd = null;
        while (!queue.isEmpty()) {
            LeafNode cur = queue.poll();

            if(null != cur.getlChild()){
                nextEnd = cur.lChild;
                queue.add(nextEnd);
            }
            if(null != cur.getrChild()){
                nextEnd = cur.rChild;
                queue.add(nextEnd);
            }

            currLevelCount++;
            // 怎么确认是最后一个节点
            if(cur == curEnd){
                max = Math.max(max, currLevelCount);
                curEnd = nextEnd;
                currLevelCount = 0;
            }
        }
        return max;
    }


    public GetBinaryTreeFloorMaxCount push(int data){
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


    public LeafNode getHead() {
        return head;
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
