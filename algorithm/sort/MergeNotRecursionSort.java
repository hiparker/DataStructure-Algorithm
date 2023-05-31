package algorithm.sort;

/**
 * 归并排序 (非递归)
 *
 * @author 周鹏程
 * @date 2023-05-30 5:30 PM
 **/
public class MergeNotRecursionSort extends AbstractSortBase{
    public static void main(String[] args) {
        // 生成 随机数组
        int[] randomArray = AbstractSortBase.getRandomArray(10);
        MergeNotRecursionSort mergeSort = new MergeNotRecursionSort();
        mergeSort.check(randomArray);
    }

    @Override
    public int[] sort(int[] array) {
        // 合并长度 mergeSize*2 等于一次递归处理的总长度
        int mergeSize = 1;
        int n = array.length;
        while (mergeSize < n){
            int low = 0;
            while (low < n){
                // 中间位 等于 低位+mergeSize -1
                int mid = low + mergeSize -1;
                if(mid >= n){
                    break;
                }
                // 高位 等于 中间位+mergeSize （需要与当前数组长度 取最小二选一，有可能最后的数组 无法等分）
                int high = Math.min(mid+mergeSize, n-1);
                // 执行与 递归一样的 合并操作，需要额为提供空间
                merge(array, low, mid, high);
                // low 节点进位 去一下组
                low = high+1;
            }

            // mergeSize > 长度的一半 等同于 ，递归第一次 之后，所以直接退出 不需要再算
            if(mergeSize > (n >> 1)){
                break;
            }

            mergeSize <<= 1;
        }
        return array;
    }

    /**
     * 执行合并
     * @param array 原数组
     * @param low 低位
     * @param mid 中位
     * @param high 高位
     */
    private void merge(int[] array, int low, int mid, int high) {
        int[] tempArray = new int[high-low+1];
        int i = 0;
        int l = low;
        int r = mid+1;
        while (l <= mid || r <= high){
            if(l > mid){
                tempArray[i++] = array[r++];
                continue;
            }
            if(r > high){
                tempArray[i++] = array[l++];
                continue;
            }
            tempArray[i++] =
                    array[l] < array[r]
                    ? array[l++]
                    : array[r++];
        }

        // 覆盖现有数组
        for (int j = 0; j < tempArray.length; j++) {
            array[low+j] = tempArray[j];
        }
    }
}
