package structure.BinaryTree;

/**
 * 二叉树 - 三叉链表
 *
 *
 *
 * @author 周鹏程
 * @date 2022-10-31 7:33 PM
 **/
public class BinaryTree {

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();


        bt.push(2).push(5).push(1).push(7);
        bt.push(8).push(3).push(66).push(41);

        System.out.println("先序遍历");
        bt.preOrder();

        System.out.println("中序遍历");
        bt.middleOrder();

        System.out.println("后序遍历");
        bt.postOrder();


    }


    private LeafNode head;


    public BinaryTree(){

    }

    public void preOrder(){
        preOrderInner(head);
    }



    private void preOrderInner(LeafNode node){
        if(null == node){
            return;
        }

        Integer data = node.getData();
        System.out.println(data);

        if(null != node.getlChild()){
            preOrderInner(node.getlChild());
        }

        if(null != node.getrChild()){
            preOrderInner(node.getrChild());
        }
    }


    public void middleOrder(){
        middleOrderInner(head);
    }

    private void middleOrderInner(LeafNode node){
        if(null == node){
            return;
        }


        if(null != node.getlChild()){
            preOrderInner(node.getlChild());
        }

        Integer data = node.getData();
        System.out.println(data);

        if(null != node.getrChild()){
            preOrderInner(node.getrChild());
        }
    }


    public void postOrder(){
        postOrderInner(head);
    }

    private void postOrderInner(LeafNode node){
        if(null == node){
            return;
        }

        if(null != node.getlChild()){
            preOrderInner(node.getlChild());
        }

        if(null != node.getrChild()){
            preOrderInner(node.getrChild());
        }

        Integer data = node.getData();
        System.out.println(data);
    }


    public BinaryTree push(int data){
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
