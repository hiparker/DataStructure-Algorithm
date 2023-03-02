package algorithm.sort;

/**
 * 冒泡排序
 *
 * @author 周鹏程
 * @date 2022-03-01 10:39:01
 */
public class BubbleSort extends AbstractSortBase{

    public static void main(String[] args) {
        // 生成 随机数组
        int[] randomArray = AbstractSortBase.getRandomArray(10);

        BubbleSort sort = new BubbleSort();

        // 执行排序 对数
        sort.check(randomArray);
    }


    /**
     * 冒泡排序
     *
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i -1; j++) {
                if(array[j] > array[j+1]){
                    swap(array, j, j+1);
                }
            }
        }
        return array;
    }
}
