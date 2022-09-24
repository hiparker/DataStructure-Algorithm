package structure.LinearStructure;


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
     */
    public void insert(T t){
        // 校验是否需要自动扩容 阔多大
        ensureCapacityInternal();

        array[length++] = t;
    }

    /**
     * 删除一个元素
     * @param index 位序
     */
    public void delete(int index){

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
     * 遍历元素
     *
     */
    public void traverse(){
    }


    /**
     * 比较元素
     * 返回第一个满足的数据的位序，不存在返回为 -1
     *
     * @return T
     */
    public int locateElem(int e){
//        for (int j : array) {
//            if (j == e) {
//                return j;
//            }
//        }
        return -1;
    }

    /**
     * 求前驱元素
     * 返回第一个满足的数据的位序，不存在返回为 -1
     *
     * @return T
     */
    public int priorElem(int i){
        return -1;
    }

    /**
     * 求后继元素
     * 返回第一个满足的数据的位序，不存在返回为 -1
     *
     * @return T
     */
    public int nextElem(int i){
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

    public static void main(String[] args) {

        MyArrayList<Integer> arrayList = new MyArrayList<>();

        // 增加数据到扩容
        for (int i = 0; i < 53; i++) {
            arrayList.insert(i+1);
        }

        // 删除 最后一个元素
        arrayList.delete(arrayList.length() - 1);

        System.out.println(arrayList.getElem(47));


    }

}
