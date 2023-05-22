package algorithm.sort;

/**
 * 二分插入排序
 *
 * @author 周鹏程
 * @date 2022-03-01 10:39:01
 */
public class BinaryInsertionSort extends AbstractSortBase{

    public static void main(String[] args) {
        // 生成 随机数组
        int[] randomArray = AbstractSortBase.getRandomArray(10);

        BinaryInsertionSort insertionSort = new BinaryInsertionSort();

        // 执行排序 对数
        insertionSort.check(randomArray);
    }


    /**
     * 二分查找插入排序 优于直接插入排序
     * 主要是在判断比较上优于直接插入，实际移动数组效率一致
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


            // 计算二分位置
            // [1 , 2 , 3 , 5 , 7 , 8 , 12 , 15 , 22 , 9 , ]
            // high = 8   low = 0   mid = (0 + 8) /2 = 4
            //     mid = 4   7 < 9  low = mid +1 = 4 + 1 = 5
            // high = 8   low = 5   mid = (5 + 8)  /2  = 6
            //     mid = 6   12 > 9 high = mid - 1 = 6 - 1 = 5
            // high = 5   low = 5   mid = (5 + 5) /2 = 5
            //     mid = 5   8 < 9 low = mid + 1 = 5 + 1 = 6
            // high = 6   low = 6   mid = (6 + 6) /2 = 6
            //     mid = 6   12 > 9 high = mid - 1 = 5
            // high < low break; low = 5, high = 4

            // 依次移动位置
            // 12 , 15 , 22 , 9
            // 12   15   9    22
            // 12   9    15   22
            // 9    12   15   22

            // 二分查找
            int low = 0, high = i - 1, mid;
            while (high >= low){
                // L 10亿 R8亿 可能会溢出
                // mid = (low + high) / 2;
                // mid = low + (high - low) / 2;
                mid = low + ((high - low) >> 1);
                if(array[mid] < array[i]){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }

            // 依次交换位置
            for(int j = i-1; j >= low ;j--){
                swap(array, j+1, j);
            }
        }
        return array;
    }
}
