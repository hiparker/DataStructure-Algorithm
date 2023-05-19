package algorithm.LinearStructure;

import java.util.*;

/**
 * 链表 Randome随机节点 克隆
 *
 * 一种特殊的单链表类描述如下
 * class Node {
 *     int value;
 *     Node next;
 *     Node rand;
 *     Node(int val){ value = val;}
 * }
 *
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能为空
 * 给定一个由Node节点类型组成的无环 单链表节点的head ，请实现一个函数完成这个链表的复制，并返回新链表的头节点
 *
 * 【要求】
 *  时间复杂度 O(N) 空间复杂度O(1)
 *
 * @author 周鹏程
 * @date 2023-05-17 1:39 PM
 **/
public class LinkedRandomNodeClone {

    public static void main(String[] args) {
        LinkedNode headNode = createRandomLinkedList(5);

        // 遍历节点
        printNode(headNode);

        System.out.println("Hash表处理：");
        LinkedNode linkedNode1 = cloneLink1(headNode);
        // 遍历节点
        printNode(linkedNode1);

        System.out.println("合并处理：");
        LinkedNode linkedNode2 = cloneLink2(headNode);
        // 遍历节点
        printNode(linkedNode2);
    }

    /**
     * 克隆链表
     * hash表实现
     *
     * @return LinkedNode
     */
    public static LinkedNode cloneLink1(LinkedNode head){
        Map<Integer, LinkedNode> hashNewNodeMap = new HashMap<>();
        LinkedNode curr = head;
        while (curr != null){
            hashNewNodeMap.put(curr.hashCode(), new LinkedNode(curr.getValue()));
            curr = curr.getNextNode();
        }

        curr = head;
        while (curr != null){
            if(null != curr.getNextNode()){
                hashNewNodeMap.get(curr.hashCode()).setNextNode(hashNewNodeMap.get(curr.getNextNode().hashCode()));
            }

            if(null != curr.getRandNode()){
                hashNewNodeMap.get(curr.hashCode()).setRandNode(hashNewNodeMap.get(curr.getRandNode().hashCode()));
            }
            curr = curr.getNextNode();
        }
        return hashNewNodeMap.get(head.hashCode());
    }


    /**
     * 克隆链表
     * 合并链表实现
     *
     * @return LinkedNode
     */
    public static LinkedNode cloneLink2(LinkedNode head){
        if(null == head){
            return null;
        }

        // 复制节点 并入主链表
        // a -> b -> c -> d
        // a -> a' -> b -> b' -> c -> c' -> d -> d'
        LinkedNode curr = head;
        while (curr != null){
            LinkedNode nextNode = curr.getNextNode();

            LinkedNode currCopyNode = new LinkedNode(curr.getValue());

            currCopyNode.setNextNode(nextNode);
            curr.setNextNode(currCopyNode);
            curr = nextNode;
        }

        LinkedNode newHead = head.getNextNode();

        // 处理 新节点 rand
        curr = head;
        while (curr != null){
            curr.getNextNode().setRandNode(curr.getRandNode().getNextNode());
            curr = curr.getNextNode().getNextNode();
        }

        // 拆散长链表
        curr = head;
        while (curr != null){
            LinkedNode nextNode = curr.getNextNode().getNextNode();

            // 设置新节点
            if(null != nextNode){
                curr.getNextNode().setNextNode(nextNode.getNextNode());
            }

            curr.setNextNode(nextNode);

            curr = nextNode;
        }

        return newHead;
    }

    /**
     * 遍历链表
     * @param node 头节点
     */
    private static void printNode(LinkedNode node){
        while (node != null){
            String rv = "";
            if(node.getRandNode() != null){
                rv = node.getRandNode().getValue()+"";
            }
            System.out.print(node.getValue()+"----"+rv + " ");
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
        for (int i = 0; i < length; i++) {
            // 设置随机 rand 指针
            int j = random.nextInt(length);
            list.get(i).setRandNode(list.get(j));
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

        private LinkedNode randNode;

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

        public LinkedNode getRandNode() {
            return randNode;
        }

        public void setRandNode(LinkedNode randNode) {
            this.randNode = randNode;
        }

    }
}
