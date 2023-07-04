package algorithm.BinaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一棵二叉树的头节点head
 * 判断当前树是否是一棵搜索二叉树
 *
 * @author 周鹏程
 * @date 2023-07-04 2:25 PM
 **/
public class IsBSTBinaryTree extends AbstractBinaryTree{

    public static void main(String[] args) {
        IsBSTBinaryTree isBSTBinaryTree = new IsBSTBinaryTree();

        boolean isFull = true;
        int maxCount = 100;
        int count = 100_0000;
        for (int i = 0; i < count; i++) {
            Node node = genBinaryTree(5, maxCount);
            if(null == node){
                continue;
            }
            if(isBSTBinaryTree.isBST1(node) != isBSTBinaryTree.isBST2(node)){
                isFull = false;
                break;
            }
        }

        System.out.println("对数器 => " + (isFull?"一致":"不一致"));


//
//        Node root = new Node(5);
//
//        Node l1 = new Node(4);
//        root.left = l1;
//
//        Node r1 = new Node(6);
//        root.right = r1;
//
//        Node l11 = new Node(44);
//        //l1.left = l11;
//
//        Node l12 = new Node(55);
//        //l1.right = l12;
//
//        Node r11 = new Node(66);
//        //r1.left = r11;
//
//        Node r12 = new Node(77);
//        //r1.right = r12;
//
//        printBinaryTree(root);
//        System.out.println("是否是一棵搜索二叉树");
//        System.out.println(isBSTBinaryTree.isBST1(root));

    }

    public boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).data <= arr.get(i - 1).data) {
                return false;
            }
        }
        return true;
    }
    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }



    private static class Info {
        private boolean isBST;
        private int max;
        private int min;

        public Info(boolean isBST, int ma, int mi) {
            this.isBST = isBST;
            this.max = ma;
            this.min = mi;
        }
    }
    public boolean isBST2(Node head){
        Info info = doIsBST(head);
        return info.isBST;
    }
    private Info doIsBST(Node head){
        if(head == null){
            return null;
        }

        Info leftInfo = doIsBST(head.left);
        Info rightInfo = doIsBST(head.right);

        int min = head.data;
        int max = head.data;

        if(leftInfo != null){
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if(rightInfo != null){
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }

        boolean isBST = true;
        if(leftInfo != null && (!leftInfo.isBST || leftInfo.max >= head.data)){
            isBST = false;
        }
        if(rightInfo != null && (!rightInfo.isBST || rightInfo.min <= head.data)){
            isBST = false;
        }

        return new Info(isBST, max, min);
    }

}
