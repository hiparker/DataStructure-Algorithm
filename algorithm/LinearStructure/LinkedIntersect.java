package algorithm.LinearStructure;

import java.util.*;

/**
 * 链表 相交
 *
 * 注：（小学奥数追击问题）快指针走两步 慢指针走一步
 *
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点
 * 如果不相交，返回null
 *
 * 【要求】
 *  如果两个链表长度之和为N，时间复杂度 O(N) 额外空间复杂度O(1)
 *
 * @author 周鹏程
 * @date 2023-05-17 1:39 PM
 **/
public class LinkedIntersect {

    public static void main(String[] args) {
        // 相交点在环前
        LinkedNode headNode1 = createRandomLinkedList(5);
        headNode1.nextNode.nextNode.nextNode.nextNode.nextNode = headNode1.nextNode.nextNode.nextNode;
        LinkedNode headNode2 = createRandomLinkedList(4);
        headNode2.nextNode.nextNode.nextNode.nextNode = headNode1.nextNode.nextNode;
        System.out.println("相交点在环前");
        LinkedNode intersectNode = getIntersectNode(headNode1, headNode2);
        if(null != intersectNode){
            System.out.println(intersectNode.getValue());
        }


        // 相交点在环内
        LinkedNode headNode11 = createRandomLinkedList(6);
        headNode11.nextNode.nextNode.nextNode.nextNode.nextNode.nextNode = headNode11.nextNode.nextNode;
        LinkedNode headNode22 = createRandomLinkedList(3);
        headNode22.nextNode.nextNode.nextNode = headNode11.nextNode.nextNode.nextNode;
        System.out.println("相交点在环内");
        intersectNode = getIntersectNode(headNode11, headNode22);
        if(null != intersectNode){
            System.out.println(intersectNode.getValue());
        }


        // 两条链表 都么环
        LinkedNode headNode111 = createRandomLinkedList(5);
        LinkedNode headNode222 = createRandomLinkedList(3);
        headNode222.nextNode.nextNode.nextNode = headNode111.nextNode.nextNode.nextNode;
        System.out.println("两条链表 都么环");
        printNode(headNode111);
        printNode(headNode222);
        intersectNode = getIntersectNode(headNode111, headNode222);
        if(null != intersectNode){
            System.out.println(intersectNode.getValue());
        }

    }


    public static LinkedNode getIntersectNode(LinkedNode head1, LinkedNode head2){
        LinkedNode head1NoLoop = getLoopNode(head1);
        LinkedNode head2NoLoop = getLoopNode(head2);
        // 非环 链表
        if(null == head1NoLoop && null == head2NoLoop){
            return getNoLoop(head1, head2);
        }

        if(null != head1NoLoop && null != head2NoLoop){
            return getLoop(head1, head2, head1NoLoop, head2NoLoop);
        }
        return null;
    }


    /**
     * 明确 两个链表是 非环链表
     * 则两个链表最后 绝对会变成Y字形
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return LinkedNode
     */
    public static LinkedNode getNoLoop(LinkedNode head1, LinkedNode head2){
        if(null == head1 || null == head2){
            return null;
        }

        // 如果 n > 0, 则链表1长 如果 n < 0 则链表2长
        int n = 0;

        // 遍历链表1
        LinkedNode curr1 = head1;
        while (null != curr1){
            curr1 = curr1.getNextNode();
            n++;
        }

        // 遍历链表2
        LinkedNode curr2 = head2;
        while (null != curr2){
            curr2 = curr2.getNextNode();
            n--;
        }

        // 谁长谁来做 curr1
        curr1 = n > 0 ? head1 : head2;
        curr2 = curr1 == head1 ? head2 : head1;

        // 取n的绝对值 也等于 a b 链表的绝对差值
        // 当长的链表减去绝对差值后 则两个链表长度一致
        int abs = Math.abs(n);
        while (abs != 0){
            curr1 = curr1.getNextNode();
            abs--;
        }

        while(curr1 != curr2){
            curr1 = curr1.getNextNode();
            curr2 = curr2.getNextNode();
        }
        return curr1;
    }

    /**
     * 明确 两个链表是 有环链表
     * 则最多出现 3种情况
     * 1. a b 链表 相交位置在环前
     * 2. a b 链表 相交位置都是环首
     * 3. a b 链表 相交位置在环中
     *
     * 小tips
     *   要么出现 非环相交
     *   要么出现 有环相交（都是有环链表）
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return LinkedNode
     */
    public static LinkedNode getLoop(LinkedNode head1, LinkedNode head2,
                                     LinkedNode loopNode1, LinkedNode loopNode2) {
        LinkedNode curr1 = null;
        LinkedNode curr2 = null;

        // 如果 环首相等 则相交位置 必然 相交位置 在当前位置 或者 在当前位置前
        if(loopNode1 == loopNode2){
            curr1 = head1;
            curr2 = head2;

            int n = 0;
            while (curr1 != loopNode1){
                curr1 = curr1.getNextNode();
                n++;
            }

            while (curr2 != loopNode2){
                curr2 = curr2.getNextNode();
                n--;
            }

            // 谁长 谁是curr1
            curr1 = n > 0 ? head1 : head2;
            curr2 = curr1 == head1 ? head2 : head1;

            // 差值
            int abs = Math.abs(n);
            while (abs != 0){
                curr1 = curr1.getNextNode();
                abs--;
            }


            while (curr1 != curr2){
                curr1 = curr1.getNextNode();
                curr2 = curr2.getNextNode();
            }

            return curr1;
        }else {
            // 如果 在环上 循环 head1，如果找到head2 则就是相交节点
            curr1 = loopNode1.getNextNode();
            while (curr1 != loopNode1){
                if(curr1 == loopNode2.getNextNode()){
                    return loopNode1;
                }
                curr1 = curr1.getNextNode();
            }
            return null;
        }
    }

    /**
     * 克隆链表
     * hash表实现
     *
     * @return LinkedNode
     */
    public static LinkedNode getLoopNode(LinkedNode head){
        if(null == head || null == head.getNextNode() || null == head.getNextNode().getNextNode()){
            return null;
        }

        // 慢走1 快走2
        LinkedNode slow = head.getNextNode();
        LinkedNode fast = head.getNextNode().getNextNode();
        while (slow != fast){
            // 如果 fast 先行走完全程 最后为空 则不再是回环结构
            if(null == fast.getNextNode() || null == fast.getNextNode().getNextNode()){
                return null;
            }
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
        }

        // 第一次相遇 说明是回环

        // 将快回置为起点 慢走1 快走1 ，再一次相交 便是回环的起点
        fast = head;
        while (slow != fast){
            slow = slow.getNextNode();
            fast = fast.getNextNode();
        }

        return fast;
    }



    /**
     * 遍历链表
     * @param node 头节点
     */
    private static void printNode(LinkedNode node){
        while (node != null){
            System.out.print(node.getValue() + " ");
            node = node.getNextNode();
        }
        System.out.println();
    }

    /**
     * 创建链表
     * @param length 长度
     * @return LinkedNode 头节点
     */
    private static LinkedNode createRandomLinkedList(int length){
        Random random = new Random();
        List<LinkedNode> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(new LinkedNode(random.nextInt(10)));
        }
        for (int i = list.size() - 1; i > 0; i--) {
            list.get(i-1).setNextNode(list.get(i));
        }
        return list.get(0);
    }


    /**
     * 创建链表
     * @param length 长度
     * @return LinkedNode 头节点
     */
    private static LinkedNode createLinkedList(int length){
        List<LinkedNode> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(new LinkedNode(i+1));
        }
        for (int i = list.size() - 1; i > 0; i--) {
            list.get(i-1).setNextNode(list.get(i));
        }
        return list.get(0);
    }

    /**
     * 链表Node
     */
    private static class LinkedNode {

        private final Integer value;

        private LinkedNode nextNode;

        public LinkedNode(Integer value){
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public LinkedNode getNextNode() {
            return nextNode;
        }

        public void setNextNode(LinkedNode nextNode) {
            this.nextNode = nextNode;
        }

    }
}
