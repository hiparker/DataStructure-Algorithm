package algorithm.LinearStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * 链表快慢指针
 *
 * @author 周鹏程
 * @date 2023-05-17 1:39 PM
 **/
public class LinkedFastSlowPoint {

    public static void main(String[] args) {
        LinkedNode headNode = createLinkedList(6);

        // 遍历节点
        printNode(headNode);

        // 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
        LinkedNode linkedNode1 = midOrUpMidNode(headNode);
        System.out.println(linkedNode1.getValue());


        // 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
        LinkedNode linkedNode2 = midOrDownMidNode(headNode);
        System.out.println(linkedNode2.getValue());

        // 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
        LinkedNode linkedNode3 = midOrUpMidPreNode(headNode);
        System.out.println(linkedNode3.getValue());

        //
        LinkedNode linkedNode4 = midOrDownMidPreNode(headNode);
        System.out.println(linkedNode4.getValue());
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * @param headNode 头节点
     */
    public static LinkedNode midOrUpMidNode(LinkedNode headNode){
        if(null == headNode || null == headNode.getNextNode() || null == headNode.getNextNode().getNextNode()){
            return headNode;
        }

        // 慢指针 初始1
        LinkedNode slow = headNode.getNextNode();
        // 慢指针 初始2
        LinkedNode fast = headNode.getNextNode().getNextNode();
        while (null != fast.getNextNode() && null != fast.getNextNode().getNextNode()){
            // 慢指针1步 快指针2步
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * @param headNode 头节点
     */
    public static LinkedNode midOrDownMidNode(LinkedNode headNode){
        if(null == headNode || null == headNode.getNextNode() || null == headNode.getNextNode().getNextNode()){
            return headNode;
        }

        // 慢指针 初始1
        LinkedNode slow = headNode.getNextNode();
        // 慢指针 初始1
        LinkedNode fast = headNode.getNextNode();
        while (null != fast.getNextNode() && null != fast.getNextNode().getNextNode()){
            // 慢指针1步 快指针2步
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * @param headNode 头节点
     */
    public static LinkedNode midOrUpMidPreNode(LinkedNode headNode){
        if(null == headNode || null == headNode.getNextNode()){
            return null;
        }

        if(null == headNode.getNextNode().getNextNode()){
            return headNode;
        }

        // 慢指针 初始0
        LinkedNode slow = headNode;
        // 慢指针 初始2
        LinkedNode fast = headNode.getNextNode().getNextNode();
        while (null != fast.getNextNode() && null != fast.getNextNode().getNextNode()){
            // 慢指针1步 快指针2步
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     * @param headNode 头节点
     */
    public static LinkedNode midOrDownMidPreNode(LinkedNode headNode){
        if(null == headNode || null == headNode.getNextNode()){
            return null;
        }

        if(null == headNode.getNextNode().getNextNode()){
            return headNode;
        }

        // 慢指针 初始0
        LinkedNode slow = headNode;
        // 快指针 初始1
        LinkedNode fast = headNode.getNextNode();
        while (null != fast.getNextNode() && null != fast.getNextNode().getNextNode()){
            // 慢指针1步 快指针2步
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
        }
        return slow;
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
