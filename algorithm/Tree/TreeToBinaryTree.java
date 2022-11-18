package algorithm.Tree;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 树 转 二叉树
 * 分层遍历
 *
 *
 * @author 周鹏程
 * @date 2022-11-18 2:55 PM
 **/
public class TreeToBinaryTree {


    private TreeLeafNode treeLeafNodeHead;
    private BinaryTreeLeafNode binaryTreeLeafNodeHead;


    public static void main(String[] args) {
        TreeToBinaryTree tree = createTree();

        // 遍历树
        /*
         *            1
         *     11     12    13
         *         121  122
         *         1211
         * */
        tree.traversalTree();

        // 转换树 为 二叉树
        tree.toBinaryTree();

        // 遍历 二叉树
        /*
         *            1
         *     11
         *          12
         *       121     13
         *   1211   122
         * */
        tree.traversalBinaryTree();

    }

    /**
     * 转换树为二叉树
     */
    public void toBinaryTree() {
        if(null == treeLeafNodeHead){
            return;
        }

        // 初始化 二叉树头
        BinaryTreeLeafNode binaryTreeLeafNode = new BinaryTreeLeafNode();
        binaryTreeLeafNode.setData(treeLeafNodeHead.getData());
        binaryTreeLeafNodeHead = binaryTreeLeafNode;


        Queue<TreeLeafNode> firstChildQueue = new LinkedBlockingQueue<>();
        Queue<BinaryTreeLeafNode> binaryNodes = new LinkedBlockingQueue<>();
        Queue<TreeLeafNode> leafNodes = new LinkedBlockingQueue<>();

        leafNodes.add(treeLeafNodeHead);
        binaryNodes.add(binaryTreeLeafNodeHead);
        firstChildQueue.add(treeLeafNodeHead);

        while (!leafNodes.isEmpty()){
            TreeLeafNode currTree = leafNodes.poll();
            BinaryTreeLeafNode currBinaryTree = binaryNodes.poll();

            if(null == currTree || null == currBinaryTree){
                continue;
            }

            // 长子队列 匹配成功 可以认定为 已经处理完当前这一层
            if(currTree == firstChildQueue.peek()){
                // 弹出 左长子
                firstChildQueue.poll();

                // 处理右孩子
                BinaryTreeLeafNode rChildBinary = currBinaryTree;
                TreeLeafNode rChild = currTree.getNextBro();
                while (null != rChild){
                    BinaryTreeLeafNode temp = new BinaryTreeLeafNode();
                    temp.setData(rChild.getData());
                    rChildBinary.setrChild(temp);

                    // 处理队列
                    binaryNodes.add(temp);
                    leafNodes.add(rChild);

                    rChildBinary = temp;
                    rChild = rChild.getNextBro();
                }
            }


            // 处理左孩子
            if(null != currTree.getFirstChild()){
                BinaryTreeLeafNode temp = new BinaryTreeLeafNode();
                temp.setData(currTree.getFirstChild().getData());
                currBinaryTree.setlChild(temp);

                // 处理队列
                binaryNodes.add(temp);
                leafNodes.add(currTree.getFirstChild());
                firstChildQueue.add(currTree.getFirstChild());
            }

        }
    }


    /**
     * 初始化
     */
    public static TreeToBinaryTree createTree() {
        TreeToBinaryTree treeToBinaryTree = new TreeToBinaryTree();

        TreeLeafNode head = new TreeLeafNode();
        head.setData(1);



        TreeLeafNode a1 = new TreeLeafNode();
        a1.setData(11);
        head.setFirstChild(a1);


        TreeLeafNode a2 = new TreeLeafNode();
        a2.setData(12);
        a1.setNextBro(a2);

        TreeLeafNode a3 = new TreeLeafNode();
        a3.setData(13);
        a2.setNextBro(a3);

        TreeLeafNode a2a = new TreeLeafNode();
        a2a.setData(121);
        a2.setFirstChild(a2a);


        TreeLeafNode a2b = new TreeLeafNode();
        a2b.setData(122);
        a2a.setNextBro(a2b);


        TreeLeafNode a2a1 = new TreeLeafNode();
        a2a1.setData(1211);
        a2a.setFirstChild(a2a1);


        treeToBinaryTree.treeLeafNodeHead = head;
        return treeToBinaryTree;
    }

    /**
     * 分层 遍历 二叉树
     */
    public void traversalBinaryTree() {
        if(null == treeLeafNodeHead){
            return;
        }

        Queue<BinaryTreeLeafNode> queue = new LinkedBlockingQueue<>();
        queue.add(binaryTreeLeafNodeHead);
        while (!queue.isEmpty()){
            BinaryTreeLeafNode poll = queue.poll();
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
     * 分层 遍历 树
     */
    public void traversalTree() {
        if(null == treeLeafNodeHead){
            return;
        }

        Queue<TreeLeafNode> leafNodes = new LinkedBlockingQueue<>();
        leafNodes.add(treeLeafNodeHead);

        while (!leafNodes.isEmpty()){
            TreeLeafNode poll = leafNodes.poll();
            System.out.println(poll.getData());

            // 依次放入 孩子节点的兄弟节点
            TreeLeafNode childBro = poll.getFirstChild();
            while (null != childBro){
                leafNodes.add(childBro);
                childBro = childBro.getNextBro();
            }
        }
    }


    /**
     * 树节点
     *
     * Integer
     */
    public static class TreeLeafNode{

        private Integer data;

        private TreeLeafNode firstChild;

        private TreeLeafNode nextBro;

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public TreeLeafNode getFirstChild() {
            return firstChild;
        }

        public void setFirstChild(TreeLeafNode firstChild) {
            this.firstChild = firstChild;
        }

        public TreeLeafNode getNextBro() {
            return nextBro;
        }

        public void setNextBro(TreeLeafNode nextBro) {
            this.nextBro = nextBro;
        }
    }


    /**
     * 二叉树叶子节点
     *
     * Integer
     */
    public static class BinaryTreeLeafNode{

        private BinaryTreeLeafNode parent;

        private BinaryTreeLeafNode lChild;

        private BinaryTreeLeafNode rChild;

        private Integer data;

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public BinaryTreeLeafNode getParent() {
            return parent;
        }

        public void setParent(BinaryTreeLeafNode parent) {
            this.parent = parent;
        }

        public BinaryTreeLeafNode getlChild() {
            return lChild;
        }

        public void setlChild(BinaryTreeLeafNode lChild) {
            this.lChild = lChild;
        }

        public BinaryTreeLeafNode getrChild() {
            return rChild;
        }

        public void setrChild(BinaryTreeLeafNode rChild) {
            this.rChild = rChild;
        }
    }
}
