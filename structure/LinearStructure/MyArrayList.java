package structure.LinearStructure;


import com.sun.tools.javac.util.Assert;

import java.util.Arrays;

/**
 * 线性顺序表
 *
 * @author 周鹏程
 * @date 2022-09-24 3:47 PM
 **/
public class MyArrayList<T> {

    private static final int EMPTY_LENGTH = 0;
    private static final int DEFAULT_SIZE = 2 << 3;
    private int length;
    private int currCapacity;
    private Object[] array;

    /**
     * 初始化 构造函数
     *
     */
    public MyArrayList(){
        length = 0;
        currCapacity = DEFAULT_SIZE;
        array = new Object[currCapacity];
    }

    /**
     * 插入一个元素
     *
     * @param t 元素
     */
    public void insert(T t){
        // 校验是否需要自动扩容 阔多大
        ensureCapacityInternal();

        array[length++] = t;
    }

    /**
     * 插入一个元素
     *
     * @param t 元素
     * @param index 位序
     */
    public void insert(T t, int index){
        // 校验是否需要自动扩容 阔多大
        ensureCapacityInternal();

        Assert.check(index >= 0 && index <= length);

        // 最后一个 直接调用尾节点插入
        if(length == index){
            insert(t);
            return;
        }

        int copyLength = length++ - index;
        System.arraycopy(array, index, array, index+1,
                copyLength);

        array[index] = t;
    }


    /**
     * 删除一个元素
     * @param index 位序
     */
    public T delete(int index){
        Object obj = array[index];

        // 假设有 3
        // 要删除第二位 则时 1
        // 总长 length = 3 3-1-1 = 1个位置

        // 需要复制的长度 = 总长度
        int copyLength = length - index - 1;
        if (copyLength > 0){
            System.arraycopy(array, index+1, array, index,
                    copyLength);
        }

        // GC 回收
        array[--length] = null;
        return (T) obj;
    }


    /**
     * 获取一个元素
     *
     * @return int
     */
    @SuppressWarnings("unchecked")
    public T getElem(int index){
        return (T) array[index];
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

        for (int i = 0; i < array.length; i++) {
            // 简化处理 直接比较物理地址
            // 默认包装类 如 Integer 可以直接比较
            if(array[i].equals(t)){
                return i;
            }
        }
        return -1;
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

        for (int i = 0; i < array.length; i++) {
            // 简化处理 直接比较物理地址
            // 默认包装类 如 Integer 可以直接比较
            if(array[i].equals(t)){
                // 前方没有元素
                if(i == 0){
                    return -1;
                }
                return i-1;
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

        for (int i = 0; i < array.length; i++) {
            // 简化处理 直接比较物理地址
            // 默认包装类 如 Integer 可以直接比较
            if(array[i].equals(t)){
                // 后方没有元素
                if(i == length-1){
                    return -1;
                }
                return i+1;
            }
        }
        return -1;
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
     * 返回表的大小
     *
     * @return boolean
     */
    public int length(){
        return length;
    }

    /**
     * 清空线性表
     */
    public void clear(){
        length = EMPTY_LENGTH;

        // 直接初始化
        //modCount = DEFAULT_SIZE;
        //array = new T[DEFAULT_SIZE];

        // 考虑到 性能问题 空间换时间 保留原有槽位 减少扩容次数
        // 批量GC回收对象
        Arrays.fill(array, true);
    }

    /**
     * 销毁
     */
    public void destroy(){
        array = null;
        length = EMPTY_LENGTH;
        currCapacity = DEFAULT_SIZE;
    }

    /**
     * 获得迭代器 遍历元素专用
     * @return Iterator
     */
    public MyIterator<T> getIterator(){
        return new MyIterator<T>(this);
    }

    /**
     * 扩容
     * 自定义检测阈值 75%，超过后 自动扩容 1.5倍
     *
     */
    private void ensureCapacityInternal(){
        // 旧的容量
        int oldCapacity = currCapacity;

        // 扩大阈值 75% 一半 + 一半的一半
        int jumpThreshold = (oldCapacity >> 1) + (oldCapacity >> 2);

        // 新容量(1.5倍)
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        // 当前容量超过 75%时自动扩容 1.5倍
        if(length+1 < jumpThreshold){
            return;
        }

        array = Arrays.copyOf(array, newCapacity);
        currCapacity = newCapacity;
    }



    /**
     * 迭代器
     * 不考虑删除问题
     * @param <T>
     */
    public static class MyIterator<T> {

        private final MyArrayList<T> myArrayList;
        private int currCount;

        public MyIterator(MyArrayList<T> myArrayList){
            this.myArrayList = myArrayList;
        }

        public boolean hasNext(){
            return currCount <= myArrayList.length() - 1;
        }

        @SuppressWarnings("unchecked")
        public T next(){
            return (T) myArrayList.getElem(currCount++);
        }

    }


    public static void main(String[] args) {

        MyArrayList<Integer> arrayList = new MyArrayList<>();

        // 增加数据到扩容
        for (int i = 0; i < 53; i++) {
            arrayList.insert(i+1);
        }

        // 删除 最后一个元素
        arrayList.delete(arrayList.length() - 1);

        // 遍历元素
        MyIterator<Integer> iterator = arrayList.getIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        // 求前驱后继
        System.out.println("1的前驱 不出意外是没有的（无 = -1） 实际结果 -> " + arrayList.priorElem(1));
        System.out.println("2的前驱位序是 0 实际结果 -> " + arrayList.priorElem(2));

        System.out.println("50的后继位序是50 实际结果 -> " + arrayList.nextElem(50));
        System.out.println("52的后继 不出意外是没有的（无 = -1） 实际结果 -> " + arrayList.nextElem(52));
        System.out.println("求48的位序 实际结果 -> " + arrayList.locateElem(48));

    }

}



