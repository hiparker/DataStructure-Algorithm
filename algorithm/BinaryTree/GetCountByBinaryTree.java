package algorithm.BinaryTree;

/**
 * 二叉树 - 三叉链表
 * 获取二叉树的节点数
 *
 * @author 周鹏程
 * @date 2022-10-31 7:33 PM
 **/
public class GetCountByBinaryTree {

    public static void main(String[] args) {
        GetCountByBinaryTree bt = new GetCountByBinaryTree();


        //        2
        //   1          5
        //          3        7
        //                        8
        //                            66
        //                        41

        bt.push(2).push(5).push(1).push(7);
        bt.push(8).push(3).push(66).push(41);

        int depth = bt.getCount();
        System.out.println("输出二叉树节点数 -> "+depth);

    }


    private LeafNode head;


    public int getCount(){
        if(null == head){
            return 0;
        }

        return getCountInner(head);
    }
    private int getCountInner(LeafNode node){
        if(null == node){
            return 0;
        }

        return getCountInner(node.getlChild()) +
                getCountInner(node.getrChild()) + 1 ;
    }



    public GetCountByBinaryTree push(int data){
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
