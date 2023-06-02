package algorithm.sort;

import java.util.Random;

/**
 * 随机 快速排序（利用荷兰国旗问题解决）
 *
 * @author 周鹏程
 * @date 2022-03-01 10:39:01
 */
public class RandomQuickSort extends AbstractSortBase{

    public static void main(String[] args) {
        // 生成 随机数组
        int[] randomArray = AbstractSortBase.getRandomArray(10);

        RandomQuickSort randomQuickSort = new RandomQuickSort();

        // 执行排序 对数
        randomQuickSort.check(randomArray);
    }


    /**
     * 快速排序
     *
     * 不稳定排序
     * 时间复杂度 O(nlogn)
     * 辅助空间复杂度 O(logn)
     *
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        // 普通快排 利用荷兰国旗问题解决
        // 比较low 原因是 再处理 特定数据 如果 num 重复较多 会做很多无用比较
        //process1(array, 0, array.length-1);

        // 优化快排
        // 去掉了 重复计算 等于值的问题 提高了部分效率，但还不够完美
        // 如果 序列本身就是正序排列 则损耗效率较高
        //process2(array, 0, array.length-1);

        // 最终快排
        // 增加随机数，打破正序排列可能
        process3(array, 0, array.length-1);

        return array;
    }

    public void process1(int[] array, int l, int r){
        if (l >= r){
            return;
        }

        // 这一步 已经把左右两边的 数据区分好 , partition 左侧为 都是小于，右侧为 大于等于
        // 这里 有可能极限情况下 比较一堆 大于等于的数字，导致重复浪费算力
        // 会在 快排2版本 优化这个问题
        int mid = partition1(array, l, r);
        process1(array, l, mid - 1);
        process1(array, mid+1, r);
    }

    public void process2(int[] array, int l, int r){
        if (l >= r){
            return;
        }

        // 这一步 已经把左右两边的 数据区分好 , partition 左侧为 都是小于，右侧为 大于等于
        // 快排2版本 解决 比较一堆 大于等于的数字，导致重复浪费算力 问题
        // 但如果 序列本身就是正序排列 则损耗效率较高 会在快排3中优化
        int[] partitions = partition2(array, l, r);
        process2(array, l, partitions[0] - 1);
        process2(array, partitions[1]+1, r);
    }

    public void process3(int[] array, int l, int r){
        if (l >= r){
            return;
        }

        Random random = new Random();
        // 随机一个数字 与r 互换，打破 全部都是正序的可能
        int randomIndex = l + random.nextInt(r-l+1);
        swap(array, randomIndex, r);

        // 这一步 已经把左右两边的 数据区分好 , partition 左侧为 都是小于，右侧为 大于等于
        // 这里 有可能极限情况下 比较一堆 大于等于的数字，导致重复浪费算力
        // 会在 快排2版本 优化这个问题
        int[] partitions = partition2(array, l, r);
        process3(array, l, partitions[0] - 1);
        process3(array, partitions[1]+1, r);
    }


    public int partition1(int[] array, int l, int r){
        if(l >= r){
            return 0;
        }
        int less = l-1;
        // 锁住r 省去独立开辟空间
        int more = r;
        int i = l;
        while (i < more){
            if(array[i] < array[r]){
                swap(array, ++less, i++);
                continue;
            }else if(array[i] > array[r]){
                swap(array, --more, i);
                continue;
            }
            i++;
        }
        // 经过互换后 more不再是 > num, 而是变成 = num 的最后一个值
        swap(array, more, r);
        // less+1 等于 num第一次出现的位置
        return less+1;
    }

    public int[] partition2(int[] array, int l, int r){
        if(l >= r){
            return new int[]{0, 0};
        }

        int less = l-1;
        // 锁住r 省去独立开辟空间
        int more = r;
        int i = l;
        while (i < more){
            if(array[i] < array[r]){
                swap(array, ++less, i++);
                continue;
            }else if(array[i] > array[r]){
                swap(array, --more, i);
                continue;
            }
            i++;
        }
        // 经过互换后 more不再是 > num, 而是变成 = num 的最后一个值
        swap(array, more, r);
        // less+1 等于 num第一次出现的位置
        return new int[]{less+1, more};
    }

}
