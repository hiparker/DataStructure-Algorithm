package algorithm.BinaryTree;

/**
 * 二叉树 - 三叉链表
 * 获取二叉树的深度
 *
 * @author 周鹏程
 * @date 2022-10-31 7:33 PM
 **/
public class GetDepthByBinaryTree {

    public static void main(String[] args) {
        GetDepthByBinaryTree bt = new GetDepthByBinaryTree();


        //        2
        //   1          5
        //          3        7
        //                        8
        //                            66
        //                        41

        bt.push(2).push(5).push(1).push(7);
        bt.push(8).push(3).push(66).push(41);

        int depth = bt.getDepth();
        System.out.println("输出二叉树深度 -> "+depth);

    }


    private LeafNode head;


    public int getDepth(){
        if(null == head){
            return 0;
        }

        return getDepthInner(head);
    }
    private int getDepthInner(LeafNode node){
        int l = 0, r = 0;
        if(null != node.getlChild()){
            l = getDepthInner(node.getlChild());
        }
        if(null != node.getrChild()){
            r = getDepthInner(node.getrChild());
        }

        if(l > r){
            return l +1;
        }
        return r + 1;
    }



    public GetDepthByBinaryTree push(int data){
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
