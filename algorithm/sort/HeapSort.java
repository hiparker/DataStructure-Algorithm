package algorithm.sort;

/**
 * 选择排序 - 堆排序
 *
 * @author 周鹏程
 * @date 2022-03-01 10:39:01
 */
public class HeapSort extends AbstractSortBase{

    public static void main(String[] args) {
        // 生成 随机数组
        int[] randomArray = AbstractSortBase.getRandomArray(10);
        HeapSort heapSort = new HeapSort();

        // 执行排序 对数
        heapSort.check(randomArray);
    }


    /**
     * 选择排序 - 堆排序 - 原地排序
     *
     * 不稳定排序
     * 时间复杂度 O(nlogn)
     * 辅助空间复杂度
     *
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        int length = array.length;

        // 将一个无序的数组组成了一个最大堆，第 1 个元素就是最大值
        // 公式 (length-1)/2 获取最后一个根节点
        for(int i = (length-1)/2; i >=0; i--){
            heapIfy(array, length, i);
        }

        // 这里相当于 消费者 每次把头节点消费掉
        // 然后重排序，最大长度递减 而消费掉的数据 直接替换掉之前的尾部空间
        for (int i = length - 1; i > 0; i--) {
            // 每次把消费的头节点 换到对应的尾部
            swap(array, 0, i);
            // 最大 up，限制为新的数组 然后重排序
            heapIfy(array, i, 0);
        }
        return array;
    }

    /**
     * 向上调整
     *
     * 公式：(i-1)/2 为查找父级
     */
    private void heapInsert(int[] array, int i){
        int parentIndex = (i-1)/2;
        while (array[i] > array[parentIndex]){
            swap(array, i, parentIndex);
            parentIndex = (parentIndex-1)/2;
        }
    }

    /**
     * 向下调整
     *
     * 获取子孩子公式 左孩子 = i*2+1 右孩子 = i*2+2
     */
    private void heapIfy(int[] array, int up, int i){
        int leftChildIndex = 2*i+1;
        while (leftChildIndex < up){
            // 获取较大的一个孩子的下标
            int largest = leftChildIndex+1 < up && array[leftChildIndex+1] > array[leftChildIndex]
                    ? leftChildIndex+1
                    : leftChildIndex;
            // 最大子孩子与自身比较 判断孰大孰小
            if(array[i] > array[largest]){
                break;
            }

            // 交换位置
            swap(array, i, largest);
            i = largest;
            leftChildIndex = 2*i+1;
        }
    }

}
