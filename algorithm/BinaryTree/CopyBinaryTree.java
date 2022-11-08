package algorithm.BinaryTree;

import java.util.Stack;

/**
 * 拷贝二叉树
 *
 * @author 周鹏程
 * @date 2022-11-08 3:14 PM
 **/
public class CopyBinaryTree {

    public static void main(String[] args) {
        CopyBinaryTree bt = new CopyBinaryTree();

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
        preOrder(bt.getHead());

        System.out.println("拷贝二叉树 - 先序遍历");
        CopyBinaryTree copyBinaryTree = copy(bt.getHead());
        preOrder(copyBinaryTree.getHead());
    }

    private LeafNode head;


    /**
     * 先序遍历
     * @param node 根结点
     */
    public static CopyBinaryTree copy(LeafNode node){
        CopyBinaryTree copyBt = new CopyBinaryTree();
        if(null == node){
            return copyBt;
        }

        if(null == copyBt.head){
            copyBt.head = new LeafNode();
        }

        LeafNode curr = copyBt.head;

        curr.setData(node.getData());

        LeafNode prev = node;

        Stack<LeafNode> stack = new Stack<>();
        stack.push(prev);

        while (!stack.isEmpty()){
            LeafNode pop = stack.pop();
            // 先放入右节点 这样针对栈特性 先进后出 等下一次循环来了会优先处理左节点
            if(null != pop.getrChild()){
                stack.push(pop.getrChild());
            }

            if(null != pop.getlChild()){
                stack.push(pop.getlChild());
            }

            // 跳过根节点
            if(pop == node){
                continue;
            }

            LeafNode tmp = new LeafNode();
            tmp.setData(pop.getData());

            // 如果当前是 左节点
            if(pop == prev.getlChild()){
                curr.setlChild(tmp);
            }
            // 否则是 右节点
            else {
                curr.setrChild(tmp);
            }

            prev = pop;
            curr = tmp;
        }
        return copyBt;
    }


    /**
     * 先序遍历
     * @param node 根结点
     */
    public static void preOrder(LeafNode node){
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




    public CopyBinaryTree push(int data){
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

