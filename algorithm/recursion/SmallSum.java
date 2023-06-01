package algorithm.recursion;

import java.util.Random;

/**
 * 求小和
 *
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫做组小和
 * 求组小和
 *
 * 例子： [1, 3, 4, 2, 5]
 * 1 左边比 1 小的数:  无
 * 3 左边比 3 小的数:  1
 * 4 左边比 4 小的数:  1, 3
 * 2 左边比 2 小的数:  1
 * 5 左边比 5 小的数:  1, 3, 4, 2
 * 所以数组的小和为 1+1+3+1+1+3+4+2=16
 *
 * @author 周鹏程
 * @date 2023-06-01 12:38 PM
 **/
public class SmallSum {

    public static void main(String[] args) {
        int[] array = {1, 3, 4, 2, 5};
        int processRes = process(array, 0, array.length - 1);
        System.out.println(processRes);

        // 对数器 校验
        check();
    }



    private static int process(int[] array, int low, int high){
        if(low == high){
            return 0;
        }

        int mid = low + ((high - low)>>1);
        return process(array, low, mid) +
                process(array, mid+1, high) +
               merge(array, low, mid, high);
    }

    private static int merge(int[] array, int low, int mid, int high){
        int[] helpArray = new int[high-low+1];
        int res = 0;
        int i = 0;
        int l = low;
        int r = mid+1;
        while (l <= mid || r <= high){
            if(l > mid){
                helpArray[i++] = array[r++];
                continue;
            }

            if(r > high){
                helpArray[i++] = array[l++];
                continue;
            }

            res += array[l] < array[r] ? (high-r+1) * array[l] : 0;
            helpArray[i++] = array[l] < array[r]
                                ? array[l++]
                                : array[r++];
        }

        for (int j = 0; j < helpArray.length; j++) {
            array[low+j] = helpArray[j];
        }
        return res;
    }


    /**
     * 获得随机数
     * @param count
     * @return
     */
    public static int[] getRandomArray(int count){
        Random r = new Random();
        int[] array = new int[count];
        for (int i = 0; i < array.length; i++) {
            array[i]  =r.nextInt(count);
        }
        return array;
    }

    /**
     * 对数器
     * @return boolean
     */
    public static boolean check() {
        Random random = new Random();
        boolean flag = true;
        // 核对1000次
        int count = 1000;
        for (int i = 0; i < count; i++) {
            int[] randomArray = getRandomArray(random.nextInt(count));
            flag = doCheck(randomArray);
            if(!flag){
                break;
            }
        }
        System.out.println("是否正确: " + (flag?"正确":"错误"));
        return flag;
    }

    /**
     * 对数器
     * @return boolean
     */
    private static boolean doCheck(int[] array) {
        // 拷贝第一个 array
        int[] array1 = new int[array.length];
        int[] array2 = new int[array.length];
        System.arraycopy(array, 0, array1, 0, array.length);
        int smallSum = getSmallSum(array1);
        System.arraycopy(array, 0, array2, 0, array.length);
        int process = process(array2, 0, array.length - 1);
        return process == smallSum;
    }

    private static int getSmallSum(int[] array){
        int res = 0;
        for (int i = 0; i < array.length; i++) {
            int count = 0;
            for (int k = i+1; k < array.length; k++) {
                if(array[k] > array[i]){
                    count++;
                }
            }
            res += array[i] * count;
        }
        return res;
    }

}
