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
     * 不稳定排序
     * 时间复杂度 最快O(n) 平均O(n1.3) 最慢O(n2)
     * 辅助空间复杂度 O(1)
     *
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        // 为了保证 排序间隔递减 最后按照间隔为1重排
        for (int h = array.length/2; h > 0; h /= 2) {
            // 本质上还是 插入排序的优化版本
            // 初始版本 插入排序是挨着牌的跑，交换对应的位置
            for (int i = h; i < array.length; i++) {
                // 如果前面一位大 则不用比较
                if(array[i] > array[i-h]){
                    continue;
                }

                for(int j = i-h; j >= 0 && array[j] > array[j+h]; j--){
                    swap(array, j, j+h);
                }
            }
        }
        return array;
    }
}
