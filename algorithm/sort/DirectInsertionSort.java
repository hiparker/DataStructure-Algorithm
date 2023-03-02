package algorithm.sort;

/**
 * 直接插入排序
 *
 * @author 周鹏程
 * @date 2022-03-01 10:39:01
 */
public class DirectInsertionSort extends AbstractSortBase{

    public static void main(String[] args) {
        // 生成 随机数组
        int[] randomArray = AbstractSortBase.getRandomArray(10);

        DirectInsertionSort insertionSort = new DirectInsertionSort();

        // 执行排序 对数
        insertionSort.check(randomArray);
    }

    /**
     * 快速排序
     *
     * 稳定排序
     * 时间复杂度 最快O(n) 平均O(n2) 最慢O(n2)
     * 辅助空间复杂度 O(1)
     *
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        // 从第二位开始 向前以此匹配
        for (int i = 1; i < array.length; i++) {
            // 如果前面一位大 则不用比较
            if(array[i] > array[i-1]){
                continue;
            }

            // 如果不比前面大 从当前位置开始
            // 需要依次向前比较 直到找到比自己小的为止
            for(int j = i-1; j >= 0 && array[j+1] < array[j] ; j--){
                    swap(array, j, j+1);
            }
        }
        return array;
    }
}
