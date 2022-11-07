package structure.BinaryTree;

/**
 * 二叉树 - 三叉链表
 * 递归实现
 *
 * @author 周鹏程
 * @date 2022-10-31 7:33 PM
 **/
public class BinaryTreeByRecursion {

    public static void main(String[] args) {
        BinaryTreeByRecursion bt = new BinaryTreeByRecursion();


        //        2
        //   1          5
        //          3        7
        //                        8
        //                            66
        //                        41

        bt.push(2).push(5).push(1).push(7);
        bt.push(8).push(3).push(66).push(41);

        System.out.println("先序遍历");
        // 2 1 5 3 7 8 66 41
        bt.preOrder(bt.head);

        System.out.println("中序遍历");
        // 1 2 3 5 7 8 41 66
        bt.middleOrder(bt.head);

        System.out.println("后序遍历");
        // 1 3 41 66 8 7 5 2
        bt.postOrder(bt.head);


    }


    private LeafNode head;


    /**
     * 先序遍历
     * @param node 根结点
     */
    private void preOrder(LeafNode node){
        if(null == node){
            return;
        }

        Integer data = node.getData();
        System.out.println(data);

        if(null != node.getlChild()){
            preOrder(node.getlChild());
        }

        if(null != node.getrChild()){
            preOrder(node.getrChild());
        }
    }


    /**
     * 中序遍历
     * @param node 根结点
     */
    private void middleOrder(LeafNode node){
        if(null == node){
            return;
        }


        if(null != node.getlChild()){
            middleOrder(node.getlChild());
        }

        Integer data = node.getData();
        System.out.println(data);

        if(null != node.getrChild()){
            middleOrder(node.getrChild());
        }
    }

    /**
     * 后序遍历
     * @param node 根结点
     */
    private void postOrder(LeafNode node){
        if(null == node){
            return;
        }

        if(null != node.getlChild()){
            postOrder(node.getlChild());
        }

        if(null != node.getrChild()){
            postOrder(node.getrChild());
        }

        Integer data = node.getData();
        System.out.println(data);
    }


    public BinaryTreeByRecursion push(int data){
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
