package algorithm.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一棵二叉树的头节点head
 * 判断当前树是否是一棵满二叉树
 *
 * @author 周鹏程
 * @date 2023-07-04 2:25 PM
 **/
public class IsFullBinaryTree extends AbstractBinaryTree{

    public static void main(String[] args) {
        IsFullBinaryTree isFullBinaryTree = new IsFullBinaryTree();

        boolean isFull = true;
        int maxCount = 100;
        int count = 100_0000;
        for (int i = 0; i < count; i++) {
            Node node = genBinaryTree(5, maxCount);
            if(null == node){
                continue;
            }
            if(isFullBinaryTree.isFull1(node) != isFullBinaryTree.isFull2(node)){
                isFull = false;
                break;
            }
        }

        System.out.println("对数器 => " + (isFull?"一致":"不一致"));



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
//        System.out.println("是否是一棵满二叉树");
//        System.out.println(isFullBinaryTree.isFull1(root));

    }


    public boolean isFull1(Node head){
        Deque<Node> dq = new LinkedList<>();
        dq.add(head);

        int level = 1;

        int currCount = 0;
        int currTotalCount = 1;
        int nextTotalCount = 0;

        Node l;
        Node r;
        while (!dq.isEmpty()){
            currCount++;
            Node curr = dq.poll();
            l = curr.left;
            r = curr.right;

            // 如果左节点为空，又节点不为空 则不是一棵满二叉树
            // 如果左节点不为空，又节点为空 则不是一棵满二叉树
            if((l == null && r != null) || (l != null && r == null)){
                return false;
            }

            if(l != null){
                nextTotalCount++;
                dq.add(l);
            }
            if(r != null){
                nextTotalCount++;
                dq.add(r);
            }

            // 下一层
            if(currCount == currTotalCount){
                level++;
                currTotalCount = nextTotalCount;

                // 如果 在第三层 出现对不齐的情况
                // 2 << (level-2) 大于等于第二层... 当前满节点数量
                if(!dq.isEmpty() && level > 1 && 2 << (level-2) != nextTotalCount){
                    return false;
                }

                currCount = 0;
                nextTotalCount = 0;
            }
        }
        return true;
    }


    private static class Info {
        private boolean isFull;
        private int height;

        public Info(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }
    public boolean isFull2(Node head){
        Info info = doIsFull(head);
        return info.isFull;
    }
    private Info doIsFull(Node head){
        if(head == null){
            return new Info( true, 0);
        }

        Info leftInfo = doIsFull(head.left);
        Info rightInfo = doIsFull(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height)+1;

        // 如果 左为满二叉树 右也为满二叉树 同时左右高度还一样
        // 那自身肯定也是 满二叉树
        boolean isFull = leftInfo.isFull && rightInfo.isFull
                                         && leftInfo.height == rightInfo.height;

        return new Info(isFull, height);
    }

}
