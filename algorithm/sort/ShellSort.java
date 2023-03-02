package algorithm.sort;

/**
 * 希尔排序 （优化版本的插入排序）
 *
 * @author 周鹏程
 * @date 2022-03-01 10:39:01
 */
public class ShellSort extends AbstractSortBase{

    public static void main(String[] args) {
        // 生成 随机数组
        int[] randomArray = AbstractSortBase.getRandomArray(10);

        ShellSort shellSort = new ShellSort();

        // 执行排序 对数
        shellSort.check(randomArray);
    }


    /**
     * 希尔插入排序 优于直接插入排序
     * 主要是分了多段进行排序，最后采用直接插入排序扫尾
     *
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        for (int h = array.length/2; h > 0; h /= 2) {
            for (int i = h; i < array.length; i++) {
                for(int j = i-h; j >= 0 && array[j] > array[j+h]; j--){
                    swap(array, j, j+h);
                }
            }
        }
        return array;
    }
}
