package algorithm.BinaryTree;

import java.util.ArrayList;

/**
 * 给定一棵二叉树的头节点head
 * 获取这棵二叉树最大搜索子树的头节点
 *
 * @author 周鹏程
 * @date 2023-07-04 2:25 PM
 **/
public class GetSubBSTHeadByBinaryTree extends AbstractBinaryTree{

    public static void main(String[] args) {
        GetSubBSTHeadByBinaryTree getSubBSTHeadByBinaryTree = new GetSubBSTHeadByBinaryTree();

        boolean isOk = true;
        int maxCount = 100;
        int count = 100_0000;
        for (int i = 0; i < count; i++) {
            Node node = genBinaryTree(5, maxCount);
            if(null == node){
                continue;
            }
            if(getSubBSTHeadByBinaryTree.getMaxSunHead1(node) != getSubBSTHeadByBinaryTree.getMaxSunHead2(node)){
                isOk = false;
                break;
            }
        }

        System.out.println("对数器 => " + (isOk?"一致":"不一致"));


//
//        Node root = new Node(50);
//
//        Node l1 = new Node(40);
//        root.left = l1;
//
//        Node r1 = new Node(60);
//        root.right = r1;
//
//        Node l11 = new Node(30);
//        l1.left = l11;
//
//        Node l12 = new Node(45);
//        l1.right = l12;
//
//        Node r11 = new Node(55);
//        r1.left = r11;
//
//        Node r12 = new Node(70);
//        r1.right = r12;
//
//        printBinaryTree(root);
//        System.out.println("是否是一棵搜索二叉树");
//        System.out.println(getSubBSTHeadByBinaryTree.getMaxSunHead1(root).data);

    }


    public Node getMaxSunHead1(Node head){
        if(null == head){
            return null;
        }
        // 如果自身就是一棵 二叉搜索树 则返回自身
        if(getBSTSize(head) != 0){
            return head;
        }

        Node leftHead = getMaxSunHead1(head.left);
        Node rightHead = getMaxSunHead1(head.right);

        return getBSTSize(leftHead) >= getBSTSize(rightHead)
                ? leftHead
                : rightHead;

    }
    private int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).data <= arr.get(i - 1).data) {
                return 0;
            }
        }
        return arr.size();
    }
    private static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }



    private static class Info {
        private int max;
        private int min;
        private int maxSubBSTSize;
        private Node maxSubBSTHead;

        public Info(int max, int min, int maxSubBSTSize, Node maxSubBSTHead) {
            this.max = max;
            this.min = min;
            this.maxSubBSTSize = maxSubBSTSize;
            this.maxSubBSTHead = maxSubBSTHead;
        }
    }
    public Node getMaxSunHead2(Node head){
        if(head == null){
            return null;
        }
        Info info = doIsBST(head);
        return info.maxSubBSTHead;
    }
    private Info doIsBST(Node head){
        if(head == null){
            return null;
        }

        Info leftInfo = doIsBST(head.left);
        Info rightInfo = doIsBST(head.right);

        int min = head.data;
        int max = head.data;
        Node maxSubBSTHead = null;
        int maxSubBSTSize = 0;

        if(leftInfo != null){
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            maxSubBSTHead = leftInfo.maxSubBSTHead;
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if(rightInfo != null){
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            if(rightInfo.maxSubBSTSize > maxSubBSTSize){
                maxSubBSTHead = rightInfo.maxSubBSTHead;
                maxSubBSTSize = rightInfo.maxSubBSTSize;
            }
        }

        if(
            (leftInfo == null?true:leftInfo.maxSubBSTHead == head.left && leftInfo.max < head.data) &&
            (rightInfo == null?true:rightInfo.maxSubBSTHead == head.right && rightInfo.min > head.data)
            ){
            maxSubBSTHead = head;
            maxSubBSTSize = (leftInfo != null? leftInfo.maxSubBSTSize : 0)
                            + (rightInfo != null? rightInfo.maxSubBSTSize : 0) + 1;
        }

        return new Info(max, min, maxSubBSTSize, maxSubBSTHead);
    }

}
