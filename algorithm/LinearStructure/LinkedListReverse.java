package algorithm.LinearStructure;


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

        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node3 = new LinkedNode(3);
        LinkedNode node4 = new LinkedNode(4);
        node1.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(node4);

        LinkedNode baseNode = node1;

        System.out.println("原始顺序：");
        // 遍历结果
        LinkedNode currNode = baseNode;
        while (null != currNode){
            System.out.print(currNode.getValue() + " ");
            currNode = currNode.getNextNode();
        }

        System.out.println();
        System.out.println("反转顺序：");
        // 递归反转
        //baseNode = reverseRecursion(baseNode);
        // 循环反转
        baseNode = reverseWhile(baseNode);
        // 遍历结果
        currNode = baseNode;
        while (null != currNode){
            System.out.print(currNode.getValue() + " ");
            currNode = currNode.getNextNode();
        }

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
