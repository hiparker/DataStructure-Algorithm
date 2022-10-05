package structure.LinearStructure;



/**
 * 循环链表
 * 特点在循环遍历定位对象
 *
 * @author 周鹏程
 * @date 2022-09-30 12:54 PM
 **/
public class MyCycleLinkedList<T> {

    private static final int EMPTY_LENGTH = 0;

    /** 长度 */
    private int length;

    /** 尾节点 */
    private Node<T> tail;

    /**
     * 构造函数
     */
    public MyCycleLinkedList(){
        tail = null;
        length = EMPTY_LENGTH;
    }

    /**
     * 插入数据
     * @param t 数据
     */
    public MyCycleLinkedList<T> insert(T t) {
        linkTail(t);
        return this;
    }

    /**
     * 合并集合
     *
     * @param myCycleLinkedList
     */
    public void mergeList(MyCycleLinkedList<T> myCycleLinkedList){
        if(null == myCycleLinkedList || null == myCycleLinkedList.tail){
            return;
        }

        // 如果自身为空 则直接使用 后者
        if(null == this.tail){
            tail = myCycleLinkedList.tail;
            length = myCycleLinkedList.length();
            return;
        }

        // 自身头节点
        Node<T> head = tail.getNext();
        // 合并对象头节点
        Node<T> mergeHead = myCycleLinkedList.tail.getNext();

        // 设置合并对象尾部 指向自身头
        myCycleLinkedList.tail.setNext(head);
        // 设置自身对象尾部 指向合并对象头部
        tail.setNext(mergeHead);
        // 变更尾节点
        tail = myCycleLinkedList.tail;
        length+=myCycleLinkedList.length();
    }



    /**
     * 获取一个元素
     *
     * @return int
     */
    @SuppressWarnings("unchecked")
    public T getElem(int index){
        int count = 0;
        // 获取头节点（循环链表 尾节点的next 为头节点）
        Node<T> head = null==tail?null:tail.getNext();
        if(null == head){
            return null;
        }

        // 循环的终止条件为 当前节点等于头节点
        Node<T> currNode = null;
        while (currNode != head){
            // 如果当前节点为空 则默认赋值为 头节点
            currNode = null==currNode?head:currNode;

            if(count == index){
                return currNode.getData();
            }

            currNode = currNode.getNext();
            count++;
        }
        return null;
    }


    /**
     * 求前驱元素
     * 返回第一个满足的数据的位序，不存在返回为 -1
     *
     * (n+1)/2
     * 时间复杂度 O(n)
     * @return int
     */
    public int priorElem(T t){
        if(null == t){
            return -1;
        }


        int count = 0;
        // 获取头节点（循环链表 尾节点的next 为头节点）
        Node<T> head = null==tail?null:tail.getNext();
        if(null == head){
            return -1;
        }

        // 循环的终止条件为 当前节点等于头节点
        Node<T> currNode = null;
        while (currNode != head){
            // 如果当前节点为空 则默认赋值为 头节点
            currNode = null==currNode?head:currNode;

            if(t.equals(currNode.getData())){
                // 前方没有元素
                if(count == 0){
                    return -1;
                }
                return count-1;
            }

            currNode = currNode.getNext();
            count++;
        }
        return -1;
    }

    /**
     * 比较元素
     * 返回第一个满足的数据的位序，不存在返回为 -1
     *
     * (n+1)/2
     * 时间复杂度 O(n)
     * @return int
     */
    public int locateElem(T t){
        if(null == t){
            return 0;
        }


        int count = 0;
        // 获取头节点（循环链表 尾节点的next 为头节点）
        Node<T> head = null==tail?null:tail.getNext();
        if(null == head){
            return -1;
        }

        // 循环的终止条件为 当前节点等于头节点
        Node<T> currNode = null;
        while (currNode != head){
            // 如果当前节点为空 则默认赋值为 头节点
            currNode = null==currNode?head:currNode;

            if(t.equals(currNode.getData())){
                return count;
            }

            currNode = currNode.getNext();
            count++;
        }
        return -1;
    }

    /**
     * 求后继元素
     * 返回第一个满足的数据的位序，不存在返回为 -1
     *
     * (n+1)/2
     * 时间复杂度 O(n)
     * @return int
     */
    public int nextElem(T t){
        if(null == t){
            return -1;
        }

        int count = 0;
        // 获取头节点（循环链表 尾节点的next 为头节点）
        Node<T> head = null==tail?null:tail.getNext();
        if(null == head){
            return -1;
        }

        // 循环的终止条件为 当前节点等于头节点
        Node<T> currNode = null;
        while (currNode != head){
            // 如果当前节点为空 则默认赋值为 头节点
            currNode = null==currNode?head:currNode;

            if(t.equals(currNode.getData())){
                // 后面没有元素
                if(count == length-1){
                    return -1;
                }
                return count+1;
            }

            currNode = currNode.getNext();
            count++;
        }

        return -1;
    }



    /**
     * 获得数据长度
     * @return int
     */
    public int length(){
        return this.length;
    }

    /**
     * 判断是否为空表
     *
     * @return boolean
     */
    public boolean isEmpty(){
        return EMPTY_LENGTH == length;
    }

    /**
     * 清空线性表
     */
    public void clear(){
        // 获取头节点（循环链表 尾节点的next 为头节点）
        Node<T> head = null==tail?null:tail.getNext();
        if(null == head){
            return;
        }

        // 循环的终止条件为 当前节点等于头节点
        Node<T> currNode = null;
        while (currNode != head){
            // 如果当前节点为空 则默认赋值为 头节点
            currNode = null==currNode?head:currNode;
            Node<T> next = currNode.getNext();

            currNode.setData(null);
            currNode.setNext(null);

            currNode = next;
        }

        tail = null;
        length = EMPTY_LENGTH;
    }


    /**
     * 获得迭代器 遍历元素专用
     * @return Iterator
     */
    public MyIterator<T> getIterator(){
        return new MyIterator<T>(this);
    }


    /**
     * 迭代器
     * 不考虑删除问题
     * @param <T>
     */
    public static class MyIterator<T> {

        private Node<T> currNode;
        private final Node<T> head;

        public MyIterator(MyCycleLinkedList<T> myCycleLinkedList){
            this.head = null==myCycleLinkedList.tail?
                    null:myCycleLinkedList.tail.getNext();
        }
        public boolean hasNext(){
            // 获取头节点（循环链表 尾节点的next 为头节点）
            return head != null && currNode != head;
        }

        @SuppressWarnings("unchecked")
        public T next(){
            currNode = null == currNode?head:currNode;
            if(null == currNode){
                return null;
            }

            T data = currNode.getData();
            currNode = currNode.getNext();
            return data;
        }
    }


    private void linkTail(T t){
        Node<T> node = new Node<>();

        // 如果 尾节点为空 则等于自身
        Node<T> head;
        if(null==tail){
            // 如果为空，则自身充当头节点
            head = node;
        }else {
            // 如果不为空
            // 获取头节点
            head = tail.getNext();
            // 尾节点 指向当前节点
            tail.setNext(node);
        }

        // 当前已经为 预尾节点
        node.setData(t);
        // 设置尾节点于头节点关联
        node.setNext(head);

        tail = node;
        length++;
    }




    /**
     * 节点
     * @param <T>
     */
    private static class Node<T> {

        private T data;

        private Node<T> next;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }



    public static void main(String[] args) {

        MyCycleLinkedList<Integer> cycleLinkedList = new MyCycleLinkedList<>();

        // 增加数据到扩容
        for (int i = 0; i < 53; i++) {
            cycleLinkedList.insert(i+1);
        }

        MyCycleLinkedList<Integer> cycleLinkedList2 = new MyCycleLinkedList<>();
        for (int i = 990; i < 1000; i++) {
            cycleLinkedList2.insert(i+1);
        }

        cycleLinkedList.mergeList(cycleLinkedList2);

        // 遍历元素
        MyIterator<Integer> iterator2 = cycleLinkedList.getIterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next());
        }


        int ret = 3;
        // 故意浪费 10 step
        MyIterator<Integer> iterator = cycleLinkedList.getIterator();
        for (int i = 0; i < 10; i++) {
            iterator.next();
        }
        // 循环链表 第二次循环 找到 value = 3
        for(;;){
            Integer next = iterator.next();
            if(next.equals(ret)){
                System.out.println("找到了 -> " + ret);
                break;
            }
        }
    }

}
