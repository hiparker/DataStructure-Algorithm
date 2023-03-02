package algorithm.sort;

/**
 * 快速排序（类似于 有序二叉树的中序遍历）
 *
 * @author 周鹏程
 * @date 2022-03-01 10:39:01
 */
public class QuickSort extends AbstractSortBase{

    public static void main(String[] args) {
        // 生成 随机数组
        int[] randomArray = AbstractSortBase.getRandomArray(10);

        QuickSort shellSort = new QuickSort();

        // 执行排序 对数
        shellSort.check(randomArray);
    }


    /**
     * 快速排序
     *
     * 不稳定排序
     * 时间复杂度 最快O(nlogn) 平均O(nlogn) 最慢O(n2)
     * 辅助空间复杂度 O(nlogn)
     *
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        QSort(array, 0, array.length-1);
        return array;
    }

    private void QSort(int[] array, int low, int high){
        if(low < high){
            int midIndex = getMidIndex(array, low, high);
            // 递归处理前半段
            QSort(array, low, midIndex -1);
            // 递归处理后半段
            QSort(array, midIndex+1, high);
        }
    }

    private int getMidIndex(int[] array, int low, int high){
        int temp = array[low];
        // 必须判断 low < high 内部有修改 low high 会知道下面方法异常进入循环
        while (low < high){
            // 处理 high
            while (low < high && array[high] >= temp){
                high--;
            }
            swap(array, low, high);

            // 处理 low
            while (low < high && array[low] <= temp){
                low++;
            }
            swap(array, high, low);
        }
        // 初始项归为
        array[low] = temp;
        return low;
    }

}
