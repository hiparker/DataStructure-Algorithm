package algorithm.sort;

/**
 * 简单选择排序
 *
 * @author 周鹏程
 * @date 2022-03-01 10:39:01
 */
public class SelectSort extends AbstractSortBase{

    public static void main(String[] args) {
        // 生成 随机数组
        int[] randomArray = AbstractSortBase.getRandomArray(10);

        SelectSort selectSort = new SelectSort();

        // 执行排序 对数
        selectSort.check(randomArray);
    }


    /**
     * 简单选择排序
     *
     * 不稳定排序
     * 时间复杂度 最快O(n2) 平均O(n2) 最慢O(n2)
     * 辅助空间复杂度 O(1)
     *
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if(array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            // 交换位置
            swap(array, i, minIndex);
        }
        return array;
    }
}
