package algorithm.BinaryTree;

/**
 * 二叉树 抽象类
 *
 * @author 周鹏程
 * @date 2023-07-04 2:58 PM
 **/
public class AbstractBinaryTree {

    /**
     * 生产二叉树
     * @param maxLevel 最大层
     * @param maxValue 最大值
     * @return Node
     */
    protected static Node genBinaryTree(int maxLevel, int maxValue){
        return doGen(1, maxLevel, maxValue);
    }
    private static Node doGen(int level, int maxLevel, int maxValue){
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }

        Node head = new Node((int) (Math.random() * maxValue));

        // left
        Node leftNode = doGen(level + 1, maxLevel, maxValue);
        // right
        Node rightNode = doGen(level + 1, maxLevel, maxValue);

        head.left = leftNode;
        head.right = rightNode;
        return head;
    }


    /**
     * 打印二叉树
     * @param head 头节点
     */
    protected static void printBinaryTree(Node head){
        doPrintBinaryTree(head, 0, "H", 17);
    }
    private static void doPrintBinaryTree(Node head, int height, String to, int len){
        if(null == head){
            return;
        }
        doPrintBinaryTree(head.left, height+1, "v", len);

        String tVal = to + head.data + to;
        int left = (len - tVal.length()) >> 1;
        int right = len - left - tVal.length();
        String tValWrapper = getSpace(left) + tVal + getSpace(right);
        System.out.println(getSpace(height*len) + tValWrapper);

        doPrintBinaryTree(head.right, height+1, "^", len);
    }
    private static String getSpace(int count){
        String space = " ";
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < count; i++) {
            stringBuilder.append(space);
        }
        return stringBuilder.toString();
    }

    protected static class Node {

        protected Node left;

        protected Node right;

        protected int data;

        public Node(int data) {
            this.data = data;
        }
    }

}
