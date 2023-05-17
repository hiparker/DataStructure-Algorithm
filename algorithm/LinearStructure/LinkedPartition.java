package algorithm.LinearStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 链表 分区
 * 给定一个链表
 *
 *  将小于基数的数据 分在小于区
 *  将大于基数的数据 分在大于区
 *  将等于基数的数据 分在等于区
 *
 * @author 周鹏程
 * @date 2023-05-17 1:39 PM
 **/
public class LinkedPartition {

    public static void main(String[] args) {
        LinkedNode headNode = createRandomLinkedList(20);

        // 遍历节点
        printNode(headNode);

        // 分区
        LinkedNode partition = partition(headNode, 5);
        // 再次遍历节点
        printNode(partition);
    }

    public static LinkedNode partition(LinkedNode head, int base){
        LinkedNode sH = null;
        LinkedNode sT = null;
        LinkedNode eH = null;
        LinkedNode eT = null;
        LinkedNode bH = null;
        LinkedNode bT = null;
        while (head != null){
            LinkedNode next = head.getNextNode();
            head.setNextNode(null);

            if(head.getValue() < base){
                if(null == sH){
                    sH = head;
                    sT = head;
                }else {
                    sT.setNextNode(head);
                    sT = head;
                }
            }else if(head.getValue() == base){
                if(null == eH){
                    eH = head;
                    eT = head;
                }else {
                    eT.setNextNode(head);
                    eT = head;
                }
            }else {
                if(null == bH){
                    bH = head;
                    bT = head;
                }else {
                    bT.setNextNode(head);
                    bT = head;
                }
            }
            head = next;
        }

        // 连接
        if(sT != null){
            sT.setNextNode(eH);
            // 谁去连接 大于区的头 谁就变成ET
            eT = eT == null?sT:eT;
        }

        // 连接
        if(eT != null){
            eT.setNextNode(bH);
        }

        return sH != null ? sH : (eH != null ? eH : bH);
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
