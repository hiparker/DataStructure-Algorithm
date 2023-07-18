package structure.LinearStructure;


/**
 * 单向链表
 *
 * @author 周鹏程
 * @date 2022-09-30 12:54 PM
 **/
public class MySingleLinkedList<T> {

    private static final int EMPTY_LENGTH = 0;

    /** 长度 */
    private int length;

    /** 头节点 */
    private Node<T> head;

    /** 尾节点 */
    private Node<T> tail;

    /**
     * 构造函数
     */
    public MySingleLinkedList(){
        head = null;
        tail = null;
        length = EMPTY_LENGTH;
    }

    /**
     * 插入数据
     * @param t 数据
     */
    public MySingleLinkedList<T> insert(T t) {
        linkTail(t);
        return this;
    }

    /**
     * 插入一个元素
     *
     * @param t 元素
     * @param index 位序
     */
    public void insert(T t, int index){
        // 确认为尾节点
        if(length == index){
            linkTail(t);
        }
        // 确认为头节点
        else if(EMPTY_LENGTH == index){
            linkHead(t);
        }else {
            // 少循环一次
            int count = 0;
            for (Node<T> x = head; x != null; x = x.next, count++) {
                if(count == index - 1){
                    Node<T> node = new Node<>();
                    node.setData(t);
                    node.setNext(x.getNext());
                    x.setNext(node);
                    length++;
                    break;
                }
            }
        }
    }

    /**
     * 删除数据
     * @param index 数据
     */
    public T delete(int index) {
        if(EMPTY_LENGTH == length
                || index < 0
                || index > length - 1){
            return null;
        }

//        // 少循环一次
//        // 0 1 2 3
//        // 1 3-1 = 2
//        Node<T> node = head;
//        // 循环到 index -1 位置
//        for (int i = 1; i < index - 1; i++) {
//            node = node.getNext();
//        }
//
//        Node<T> oldNode = node.getNext();
//        Node<T> nextNode = null!=oldNode?oldNode.getNext():null;
//        // 移除中间节点
//        node.setNext(nextNode);
//        length--;
//
//        return null!=oldNode?oldNode.getData():null;

        // 少循环一次
        int count = 0;
        for (Node<T> x = head; x != null; x = x.next, count++) {
            if(count == index - 1){
                Node<T> next = null!=x.getNext()?x.getNext().getNext():null;
                x.setNext(next);
                length--;
                return null!=x.getNext()?x.getNext().getData():null;
            }
        }
        return null;
    }


    /**
     * 获取一个元素
     *
     * @return int
     */
    @SuppressWarnings("unchecked")
    public T getElem(int index){
        int count = 0;
        for (Node<T> x = head; x != null; x = x.next, count++) {
            if(count == index){
                return x.getData();
            }
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
        for (Node<T> x = head; x != null; x = x.next, count++) {
            if(null == x.getData()){
                continue;
            }
            if(x.getData().equals(t)){
                // 前方没有元素
                if(count == 0){
                    return -1;
                }
                return count-1;
            }
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
        for (Node<T> x = head; x != null; x = x.next, count++) {
            if(null == x.getData()){
                continue;
            }
            if(x.getData().equals(t)){
                return count;
            }
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
        for (Node<T> x = head; x != null; x = x.next, count++) {
            if(null == x.getData()){
                continue;
            }
            if(x.getData().equals(t)){
                // 后面没有元素
                if(count == length-1){
                    return -1;
                }
                return count+1;
            }
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
        // 批量GC回收对象
        Node<T> curr = head;
        while (null != curr){
            Node<T> tmp = curr.getNext();
            curr.setNext(null);
            curr = tmp;
        }
        head = null;
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

    private void linkHead(T t){
        Node<T> node = new Node<>();
        node.setData(t);
        if(null == head){
            tail = node;
        }
        node.setNext(head);
        head = node;
        length++;
    }

    private void linkTail(T t){
        if(null == tail){
            linkHead(t);
            return;
        }

        Node<T> node = new Node<>();
        node.setData(t);
        tail.setNext(node);
        tail = node;
        length++;
    }


    /**
     * 迭代器
     * 不考虑删除问题
     * @param <T>
     */
    public static class MyIterator<T> {

        private final MySingleLinkedList<T> mySingleLinkedList;
        private Node<T> currNode;

        public MyIterator(MySingleLinkedList<T> mySingleLinkedList){
            this.mySingleLinkedList = mySingleLinkedList;
            this.currNode = mySingleLinkedList.head;
        }

        public boolean hasNext(){
            return null != currNode;
        }

        @SuppressWarnings("unchecked")
        public T next(){
            if(null == currNode){
                return null;
            }

            T data = currNode.getData();
            currNode = currNode.getNext();
            return data;
        }
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

        MySingleLinkedList<Integer> singleLinkedList = new MySingleLinkedList<>();

        // 增加数据到扩容
        for (int i = 0; i < 53; i++) {
            singleLinkedList.insert(i+1);
        }

        // 删除 最后一个元素
        singleLinkedList.delete(singleLinkedList.length() - 1);

        // 遍历元素
        MyIterator<Integer> iterator = singleLinkedList.getIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        // 求前驱后继
        System.out.println("1的前驱 不出意外是没有的（无 = -1） 实际结果 -> " + singleLinkedList.priorElem(1));
        System.out.println("2的前驱位序是 0 实际结果 -> " + singleLinkedList.priorElem(2));

        System.out.println("50的后继位序是50 实际结果 -> " + singleLinkedList.nextElem(50));
        System.out.println("52的后继 不出意外是没有的（无 = -1） 实际结果 -> " + singleLinkedList.nextElem(52));
        System.out.println("求48的位序 实际结果 -> " + singleLinkedList.locateElem(48));
    }

}
