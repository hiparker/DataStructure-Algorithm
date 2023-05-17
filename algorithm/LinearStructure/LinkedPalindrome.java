package algorithm.LinearStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 链表 回文判断
 *
 * 1. 压栈倒序判断
 * 2. 找到中点 压一半栈
 * 3. 找到中点 后半部分 反链 判断（结束时 记得重新指回原有顺序）
 *
 * @author 周鹏程
 * @date 2023-05-17 1:39 PM
 **/
public class LinkedPalindrome {

    public static void main(String[] args) {
        LinkedNode headNode1 = new LinkedNode(1);
        LinkedNode headNode2 = new LinkedNode(2);
        LinkedNode headNode3 = new LinkedNode(3);
        LinkedNode headNode4 = new LinkedNode(3);
        LinkedNode headNode5 = new LinkedNode(2);
        LinkedNode headNode6 = new LinkedNode(1);
        headNode1.setNextNode(headNode2);
        headNode2.setNextNode(headNode3);
        headNode3.setNextNode(headNode4);
        headNode4.setNextNode(headNode5);
        headNode5.setNextNode(headNode6);

        LinkedNode headNode = headNode1;

        // 遍历节点
        printNode(headNode);

        // 回文 压栈判断
        boolean palindrome1 = isPalindrome1(headNode);
        System.out.println(palindrome1);

        // 回文 压栈判断
        boolean palindrome2 = isPalindrome2(headNode);
        System.out.println(palindrome2);

        // 回文 压栈判断
        boolean palindrome3 = isPalindrome3(headNode);
        System.out.println(palindrome3);
    }

    public static boolean isPalindrome1(LinkedNode head){
        Stack<LinkedNode> stack = new Stack<>();

        // 压栈
        LinkedNode curr = head;
        while (curr != null){
            stack.add(curr);
            curr = curr.getNextNode();
        }

        while (!stack.isEmpty()){
            LinkedNode pop = stack.pop();
            if(!pop.getValue().equals(head.getValue())){
                return false;
            }
            head = head.getNextNode();
        }
        return true;
    }

    public static boolean isPalindrome2(LinkedNode head){
        LinkedNode mid;
        // 先找到中位点 这样可以 减少一半栈的使用
        if(null == head || null == head.getNextNode() || null == head.getNextNode().getNextNode()){
            mid = head;
        }else {
            LinkedNode slow = head.getNextNode();
            LinkedNode fast = head.getNextNode().getNextNode();
            while (fast.getNextNode() != null && fast.getNextNode().getNextNode() != null){
                slow = slow.getNextNode();
                fast = fast.getNextNode().getNextNode();
            }
            mid = slow;
        }


        Stack<LinkedNode> stack = new Stack<>();
        // 压栈
        LinkedNode curr = mid.getNextNode();
        while (curr != null){
            stack.add(curr);
            curr = curr.getNextNode();
        }

        while (!stack.isEmpty()){
            LinkedNode pop = stack.pop();
            if(!pop.getValue().equals(head.getValue())){
                return false;
            }
            head = head.getNextNode();
        }
        return true;
    }

    public static boolean isPalindrome3(LinkedNode head){
        LinkedNode mid;
        // 先找到中位点
        if(null == head || null == head.getNextNode() || null == head.getNextNode().getNextNode()){
            mid = head;
        }else {
            LinkedNode slow = head.getNextNode();
            LinkedNode fast = head.getNextNode().getNextNode();
            while (fast.getNextNode() != null && fast.getNextNode().getNextNode() != null){
                slow = slow.getNextNode();
                fast = fast.getNextNode().getNextNode();
            }
            mid = slow;
        }

        // 反转链表
        LinkedNode tailBaseNode = reverseLinked(mid.getNextNode());
        LinkedNode tailCurrNode = tailBaseNode;
        LinkedNode headCurrNode = head;

        boolean isFlag = true;
        while (null != tailCurrNode){
            if(!tailCurrNode.getValue().equals(headCurrNode.getValue())){
                isFlag = false;
                break;
            }
            tailCurrNode = tailCurrNode.getNextNode();
            headCurrNode = headCurrNode.getNextNode();
        }

        // 再次翻转链表 恢复
        reverseLinked(tailBaseNode);
        return isFlag;
    }

    private static LinkedNode reverseLinked(LinkedNode baseNode){
        if(null == baseNode || null == baseNode.getNextNode()){
            return baseNode;
        }

        LinkedNode currNode = baseNode.getNextNode();
        LinkedNode prevNode = baseNode;
        LinkedNode nextNode;
        while (null != currNode.getNextNode()){
            nextNode = currNode.getNextNode();

            currNode.setNextNode(prevNode);

            prevNode = currNode;
            currNode = nextNode;
        }
        baseNode.setNextNode(null);
        currNode.setNextNode(prevNode);
        return currNode;
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
