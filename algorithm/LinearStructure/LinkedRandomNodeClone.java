package algorithm.LinearStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        LinkedNode headNode = createRandomLinkedList(20);

        // 遍历节点
        printNode(headNode);

    }

    /**
     * 克隆链表
     * hash表实现
     *
     * @return LinkedNode
     */
    public LinkedNode cloneLink1(LinkedNode head){
        return null;
    }


    /**
     * 克隆链表
     * 合并链表实现
     *
     * @return LinkedNode
     */
    public LinkedNode cloneLink2(LinkedNode head){
        return null;
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
