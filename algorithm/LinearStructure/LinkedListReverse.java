package algorithm.LinearStructure;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 反转链表
 *
 * 常用地反转方式
 * 递归反转、循环反转
 *
 * @author 周鹏程
 * @date 2022-09-20 11:48 AM
 **/
public class LinkedListReverse {

    public static void main(String[] args) {
        LinkedNode baseNode = createLinkedList(4);
        DoubleLinkedNode baseDoubleNode = createDoubleLinkedList(4);

        System.out.println("原始顺序：");
        // 遍历结果
        printNode(baseNode);

        System.out.println();
        System.out.println("反转顺序：");
        // 递归反转
        //baseNode = reverseRecursion(baseNode);
        // 循环反转
        LinkedNode reverse1 = reverseWhile(baseNode);
        // 遍历结果
        printNode(reverse1);
        reverseWhile(reverse1);

        System.out.println("-----------------");
        System.out.println("简化版本反转：");
        LinkedNode reverse2 = reverseWhileSimple(baseNode);
        // 遍历结果
        printNode(reverse2);

        System.out.println();
        System.out.println("双向链表原始顺序：");
        // 遍历结果
        printNode(baseDoubleNode);

        System.out.println();
        System.out.println("双向链表反转顺序：");
        DoubleLinkedNode doubleLinkedNode1 = doubleReverseWhileSimple(baseDoubleNode);
        // 遍历结果
        printNode(doubleLinkedNode1);

    }

    /**
     * 循环反转
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @return LinkedNode
     */
    public static LinkedNode reverseWhile(LinkedNode baseNode){
        // 自身 或者 下一个为空 返回自身（说明 为空 或者 当前列表有且只有一个）
        if(null == baseNode || null == baseNode.getNextNode()){
            return baseNode;
        }

        // 跳过第一个，最后一个
        // 从第二个开始循环 忽略第一个
        LinkedNode currNode = baseNode.getNextNode();
        LinkedNode preNode = baseNode;
        LinkedNode nextNode;
        while (null != currNode.getNextNode()){
            nextNode = currNode.getNextNode();
            // 交换位置 设置下一个节点为 记录好的上一个节点
            currNode.setNextNode(preNode);

            preNode = currNode;
            currNode = nextNode;
        }

        // 设置第一个
        baseNode.setNextNode(null);
        // 设置最后一个
        currNode.setNextNode(preNode);

        return currNode;
    }

    /**
     * 递归反转
     * 注：当数据量过大时 压栈会溢出
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @return LinkedNode
     */
    public static LinkedNode reverseRecursion(LinkedNode baseNode){
        // 自身 或者 下一个为空 返回自身（说明 为空 或者 当前列表有且只有一个）
        // 同是有这个判断 在递归中 最后一个节点不会进入
        if(null == baseNode || null == baseNode.getNextNode()){
            return baseNode;
        }

        // 展开
        LinkedNode resultNode = reverseRecursion(baseNode.getNextNode());

        // 假设当前节点为 3，该操作就是将 4 节点的next指向3
        baseNode.getNextNode().setNextNode(baseNode);
        // 将设当前节点为 1，该操作就是将 1 节点指向空，虽然其他节点也都会指向空，但终究会被上游递归调整指向
        baseNode.setNextNode(null);

        return resultNode;
    }


    /**
     * 循环反转（简化版本）
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @return LinkedNode
     */
    public static LinkedNode reverseWhileSimple(LinkedNode head){
        LinkedNode prev = null;
        LinkedNode next = null;
        while (null != head){
            next = head.nextNode;

            head.nextNode = prev;

            prev = head;
            head = next;
        }
        return prev;
    }


    /**
     * 循环反转 (双向)（简化版本）
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @return LinkedNode
     */
    public static DoubleLinkedNode doubleReverseWhileSimple(DoubleLinkedNode head){
        DoubleLinkedNode prev = null;
        DoubleLinkedNode next = null;
        while (null != head){
            next = head.next;

            head.next = prev;
            head.prev = next;

            prev = head;
            head = next;
        }
        return prev;
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
     * 遍历链表
     * @param node 头节点
     */
    private static void printNode(DoubleLinkedNode node){
        DoubleLinkedNode a = node;
        DoubleLinkedNode b = node;
        while (a != null){
            System.out.print(a.value + " ");
            if(null == a.next){
                b = a;
            }
            a = a.next;
        }
        System.out.println();
        while (b != null){
            System.out.print(b.value + " ");
            b = b.prev;
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
     * 创建链表
     * @param length 长度
     * @return LinkedNode 头节点
     */
    private static DoubleLinkedNode createDoubleLinkedList(int length){
        List<DoubleLinkedNode> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(new DoubleLinkedNode(i+1));
        }
        for (int i = list.size() - 1; i > 0; i--) {
            list.get(i-1).next = list.get(i);
            list.get(i).prev = list.get(i-1);
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

    /**
     * 双向链表Node
     */
    private static class DoubleLinkedNode {

        private final Integer value;

        private DoubleLinkedNode prev;

        private DoubleLinkedNode next;

        public DoubleLinkedNode(Integer value){
            this.value = value;
        }

    }

}
