package algorithm.BinaryTree;

/**
 * 给定一棵二叉树的头节点head，判断这棵树是否是平衡二叉树
 * 平衡二叉树特性
 *  1. 树的左右两边的层级数相差不会大于1
 *  2. 非叶子节值大于左边子节点、小于右边子节点
 *
 * @author 周鹏程
 * @date 2023-06-30 5:51 PM
 **/
public class IsBalanceBinaryTree extends AbstractBinaryTree {


    public static void main(String[] args) {
        Node head = new Node(80);

        Node l1 = new Node(50);
        head.left = l1;
        Node l11 = new Node(40);
        l1.left = l11;
        Node l12 = new Node(60);
        l1.right = l12;

        Node r1 = new Node(150);
        head.right = r1;
        Node r11 = new Node(100);
        r1.left = r11;
        Node r12 = new Node(130);
        r1.right = r12;
        Node r111 = new Node(90);
        r11.left = r111;


        printBinaryTree(head);
        boolean b = isBalance(head);
        System.out.println("是否是平衡二叉树：");
        System.out.println(b);
    }

    public static boolean isBalance(Node h){
        if(h == null){
            return true;
        }
        return doIsBalance(h).isBalanceTree;
    }

    private static class Info{

        boolean isBalanceTree;

        int height;

        public Info(boolean isBalanceTree, int height) {
            this.isBalanceTree = isBalanceTree;
            this.height = height;
        }
    }

    private static Info doIsBalance(Node h){
        // 我能和子树要到什么属性
        // 1. 你是不是一棵平衡二叉树
        // 2. 你当前的节点高度是多少
        if(null == h){
            return new Info(true, 0);
        }

        Info leftInfo = doIsBalance(h.left);
        Info rightInfo = doIsBalance(h.right);

        // 当前节点的高度
        int height = Math.max(leftInfo.height, rightInfo.height)+1;
        boolean isBalanceTree = leftInfo.isBalanceTree && rightInfo.isBalanceTree
                && Math.abs(leftInfo.height - rightInfo.height) <= 1;
        // 当前节点是否是平衡二叉树
        return new Info(isBalanceTree, height);
    }

}
