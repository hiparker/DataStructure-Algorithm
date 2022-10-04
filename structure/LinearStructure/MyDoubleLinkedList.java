package structure.LinearStructure;


import com.sun.tools.javac.util.Assert;

/**
 * 双向链表
 *
 * @author 周鹏程
 * @date 2022-09-30 12:54 PM
 **/
public class MyDoubleLinkedList<T> {

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
    public MyDoubleLinkedList(){
        head = null;
        tail = null;
        length = EMPTY_LENGTH;
    }

    /**
     * 插入数据
     * @param t 数据
     */
    public void insert(T t) {
        linkTail(t);
    }

    /**
     * 插入一个元素
     *
     * @param t 元素
     * @param index 位序
     */
    public void insert(T t, int index){
        Assert.check(index >= 0 && index <= length);

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
                    Node<T> next = x.getNext();
                    Node<T> node = new Node<>();
                    node.setData(t);
                    node.setNext(next);
                    node.setPrior(x);

                    x.setNext(node);
                    next.setPrior(node);
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




        if(EMPTY_LENGTH == index && length == 1){
            this.clear();
        }else if(EMPTY_LENGTH == index && length > 1) {
            head = head.getNext();
            head.setPrior(null);
            length--;
        }else {
            if(length-1 == index){
                tail = tail.getPrior();
                tail.setNext(null);
                length--;
            }else {
                // 少循环一次
                int count = 0;
                for (Node<T> x = head; x != null; x = x.next, count++) {
                    if(count == index - 1){
                        Node<T> next = null!=x.getNext()?x.getNext().getNext():null;
                        x.setNext(next);
                        // 设置前驱节点
                        if(null != next){
                            next.setPrior(x);
                        }

                        length--;
                        return null!=x.getNext()?x.getNext().getData():null;
                    }
                }
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
            curr.setPrior(null);
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
        node.setNext(head);
        node.setPrior(null);

        if(null == head){
            tail = node;
        }else {
            head.setPrior(node);
        }
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
        node.setPrior(tail);
        node.setNext(null);

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

        private final MyDoubleLinkedList<T> mySingleLinkedList;
        private Node<T> nextCurrNode;
        private Node<T> priorCurrNode;


        public MyIterator(MyDoubleLinkedList<T> mySingleLinkedList){
            this.mySingleLinkedList = mySingleLinkedList;
            this.nextCurrNode = mySingleLinkedList.head;
            this.priorCurrNode = mySingleLinkedList.tail;
        }

        public boolean hasePrior(){
            return null != priorCurrNode;
        }

        public boolean haseNext(){
            return null != nextCurrNode;
        }

        @SuppressWarnings("unchecked")
        public T prior(){
            if(null == priorCurrNode){
                return null;
            }

            T data = priorCurrNode.getData();
            priorCurrNode = priorCurrNode.getPrior();
            return data;
        }

        @SuppressWarnings("unchecked")
        public T next(){
            if(null == nextCurrNode){
                return null;
            }

            T data = nextCurrNode.getData();
            nextCurrNode = nextCurrNode.getNext();
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

        private Node<T> prior;

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

        public Node<T> getPrior() {
            return prior;
        }

        public void setPrior(Node<T> prior) {
            this.prior = prior;
        }
    }


    public static void main(String[] args) {

        MyDoubleLinkedList<Integer> singleLinkedList = new MyDoubleLinkedList<>();

        // 增加数据到扩容
        for (int i = 0; i < 53; i++) {
            singleLinkedList.insert(i+1);
        }

        // 删除 最后一个元素
        singleLinkedList.delete(singleLinkedList.length() - 1);

        // 正序 遍历元素
        MyIterator<Integer> iterator = singleLinkedList.getIterator();
        while (iterator.haseNext()){
            System.out.println(iterator.next());
        }

        System.out.println("------------");

        // 倒叙 遍历元素
        MyIterator<Integer> iterator2 = singleLinkedList.getIterator();
        while (iterator2.hasePrior()){
            System.out.println(iterator2.prior());
        }


        // 求前驱后继
        System.out.println("1的前驱 不出意外是没有的（无 = -1） 实际结果 -> " + singleLinkedList.priorElem(1));
        System.out.println("2的前驱位序是 0 实际结果 -> " + singleLinkedList.priorElem(2));

        System.out.println("50的后继位序是50 实际结果 -> " + singleLinkedList.nextElem(50));
        System.out.println("52的后继 不出意外是没有的（无 = -1） 实际结果 -> " + singleLinkedList.nextElem(52));
        System.out.println("求48的位序 实际结果 -> " + singleLinkedList.locateElem(48));

    }

}
