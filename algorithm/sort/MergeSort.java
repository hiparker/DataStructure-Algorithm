package algorithm.sort;

/**
 * 归并排序
 *
 * @author 周鹏程
 * @date 2023-05-30 5:30 PM
 **/
public class MergeSort extends AbstractSortBase{
    public static void main(String[] args) {
        // 生成 随机数组
        int[] randomArray = AbstractSortBase.getRandomArray(10);
        MergeSort mergeSort = new MergeSort();
        mergeSort.check(randomArray);
    }

    @Override
    public int[] sort(int[] array) {
        // 归并排序
        process(array, 0, array.length-1);
        return array;
    }

    /**
     * 递归处理
     * @param array 原数组
     * @param low 低位
     * @param high 高位
     */
    private void process(int[] array, int low, int high){
        if(low == high){
            return;
        }
        int mid = low + ((high-low)>>1);
        process(array, low, mid);
        process(array, mid+1, high);
        merge(array, low, mid, high);
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
        for(int j = low, k = 0; j <= high; j++, k++){
            array[j] = tempArray[k];
        }
    }
}
