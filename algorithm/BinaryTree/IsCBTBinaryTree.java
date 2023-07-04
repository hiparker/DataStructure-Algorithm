package algorithm.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一棵二叉树的头节点head
 * 判断当前树是否是一棵完全二叉树
 *
 * @author 周鹏程
 * @date 2023-07-04 2:25 PM
 **/
public class IsCBTBinaryTree extends AbstractBinaryTree{

    public static void main(String[] args) {
        IsCBTBinaryTree isCBTBinaryTree = new IsCBTBinaryTree();

        boolean isCBT = true;
        int maxCount = 100;
        int count = 100_0000;
        for (int i = 0; i < count; i++) {
            Node node = genBinaryTree(5, maxCount);
            if(null == node){
                continue;
            }
            if(isCBTBinaryTree.isCBT1(node) != isCBTBinaryTree.isCBT2(node)){
                isCBT = false;
                break;
            }
        }

        System.out.println("对数器 => " + (isCBT?"一致":"不一致"));

//        Node root = new Node(5);
//
//        Node l1 = new Node(4);
//        root.left = l1;
//
//        Node r1 = new Node(6);
//        root.right = r1;
//
//        Node l11 = new Node(44);
//        l1.left = l11;
//
////        Node l12 = new Node(55);
////        l1.right = l12;
//
//        Node r11 = new Node(66);
//        r1.left = r11;
//
//        Node r12 = new Node(77);
//        r1.right = r12;

//        printBinaryTree(root);
//        System.out.println("是否是一棵完全二叉树");
//        System.out.println(isCBTByBinaryTree.isCBT1(root));
    }


    public boolean isCBT1(Node head){
        Deque<Node> dq = new LinkedList<>();
        dq.add(head);

        boolean leaf = false;
        Node l;
        Node r;
        while (!dq.isEmpty()){
            Node n = dq.poll();
            l = n.left;
            r = n.right;

            if(
                // 如果遇到节点不全，但后续节点又不是叶子节点
                (leaf && (l != null || r != null))
                // 如果左节点为空，又节点不为空 则不是一棵完全二叉树
                || (l == null && r != null)){
                return false;
            }

            if(l != null){
                dq.add(l);
            }
            if(r != null){
                dq.add(r);
            }

            if(l == null || r == null){
                leaf = true;
            }
        }
        return true;
    }


    private static class Info {
        private boolean isCBT;
        private boolean isFull;
        private int height;

        public Info(boolean isCBT, boolean isFull, int height) {
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }
    public boolean isCBT2(Node head){
        Info info = doIsCBT(head);
        return info.isCBT;
    }
    private Info doIsCBT(Node head){
        if(head == null){
            return new Info(true, true, 0);
        }

        Info leftInfo = doIsCBT(head.left);
        Info rightInfo = doIsCBT(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height)+1;

        // 如果 左为满二叉树 右也为满二叉树 同时左右高度还一样
        // 那自身肯定也是 满二叉树
        boolean isFull = leftInfo.isFull && rightInfo.isFull
                                         && leftInfo.height == rightInfo.height;
        // 如果自身是满二叉树 那也就是完全二叉树
        boolean isCBT = isFull;
        if(!isFull){
            // 如果 左为满二叉树，右是完全二叉树 且 高度与左一样  则自身也是完全二叉树
            if(leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height){
                isCBT = true;
            }
            // 如果 左为完全二叉树，右必须为满二叉树 且左高度-1 等于 右高度 则自身也是完全二叉树
            else if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height-1 == rightInfo.height){
                isCBT = true;
            }
        }

        return new Info(isCBT, isFull, height);
    }

}
