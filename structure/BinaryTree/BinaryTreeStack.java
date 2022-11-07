package structure.BinaryTree;


import java.util.Stack;

/**
 * 二叉树 - 三叉链表
 * 栈实现
 *
 * @author 周鹏程
 * @date 2022-10-31 7:33 PM
 **/
public class BinaryTreeStack {

    public static void main(String[] args) {
        BinaryTreeStack bt = new BinaryTreeStack();

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

        Stack<LeafNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()){
            LeafNode pop = stack.pop();
            System.out.println(pop.getData());

            // 先放入右节点 这样针对栈特性 先进后出 等下一次循环来了会优先处理左节点
            if(null != pop.getrChild()){
                stack.push(pop.getrChild());
            }

            if(null != pop.getlChild()){
                stack.push(pop.getlChild());
            }
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


        LeafNode p = node;
        Stack<LeafNode> stack = new Stack<>();
        while (null != p || !stack.isEmpty()){
            if(null != p){
                stack.push(p);
                p = p.getlChild();
            }else {
                LeafNode pop = stack.pop();
                System.out.println(pop.getData());
                p = pop.getrChild();
            }
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

        LeafNode prev = null;
        LeafNode p = node;
        Stack<LeafNode> stack = new Stack<>();
        while (null != p || !stack.isEmpty()){
            // 循环到 左子树底
            while (null != p){
                stack.push(p);
                p = p.getlChild();
            }

            LeafNode top = stack.peek();
            // 如果当前节点没有右节点 则默认当前节点 已经为底节点
            // 如果当前节点的右节点 等于上一次出栈节点 则认为当前节点认为已经遍历完毕 直接出栈
            if(null == top.getrChild() || top.getrChild() == prev){
                LeafNode pop = stack.pop();
                System.out.println(pop.getData());
                prev = top;
            }else {
                p = top.getrChild();
            }
        }
    }


    public BinaryTreeStack push(int data){
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
