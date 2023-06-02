package algorithm.NetherlandsFlag;

import java.util.Arrays;
import java.util.Random;

/**
 * 荷兰国旗问题 抽象类
 *
 * @author 周鹏程
 * @date 2023-06-02 10:54 AM
 **/
public abstract class AbstractNetherlandsFlag {

    /**
     * 排序
     */
    public abstract int[] sort(int[] array, int num);

    /**
     * 对数器检查 (只检查最初版本)
     * @return boolean
     */
    public boolean checkSimple(){
        Random r = new Random();
        boolean res = true;
        int count = 1000;
        for (int k = 0; k < count; k++) {
            int length = r.nextInt(10000);
            int numIndex = r.nextInt(length);
            int[] randomArray = getRandomArray(length);
            int num = randomArray[numIndex];

            // 拷贝第一个 array 并排序
            int[] array1 = new int[randomArray.length];
            int[] array2 = new int[randomArray.length];
            int[] array3 = new int[randomArray.length];

            System.arraycopy(randomArray, 0, array1, 0, randomArray.length);
            sort(array1, num);
            System.arraycopy(randomArray, 0, array2, 0, randomArray.length);
            System.arraycopy(array1, 0, array3, 0, array1.length);
            Arrays.sort(array2);
            Arrays.sort(array3);

            // 校验是否相等
            for (int j = 0; j < array2.length; j++) {
                if(array2[j] != array3[j]){
                    res = false;
                    break;
                }
            }
            if(res){
                // -1 小于   0 等于    1大于
                int flag = -1;
                for (int i = 0; i < array1.length; i++) {
                    if(i == 0){
                        flag = Integer.compare(array1[i], num);
                        continue;
                    }

                    // 判定失败 如果判定 已经出现 = num的情况，就不允许再出现 < 的情况
                    if(flag >= 0 && array1[i] < num){
                        res = false;
                        break;
                    }

                    // 如果后续出现 >= 的情况
                    if(array1[i] >= num){
                        flag = Integer.compare(array1[i], num);
                    }
                }
            }
        }


        System.out.println("是否正确: " + (res?"正确":"错误"));
        return res;
    }


    /**
     * 对数器检查
     * @return boolean
     */
    public boolean check(){
        Random r = new Random();
        boolean res = true;
        int count = 1000;
        for (int k = 0; k < count; k++) {
            int length = r.nextInt(10000);
            if(length <= 0){
                length = 10000;
            }
            int numIndex = r.nextInt(length);
            int[] randomArray = getRandomArray(length);
            int num = randomArray[numIndex];

            // 拷贝第一个 array 并排序
            int[] array1 = new int[randomArray.length];
            int[] array2 = new int[randomArray.length];
            int[] array3 = new int[randomArray.length];

            System.arraycopy(randomArray, 0, array1, 0, randomArray.length);
            sort(array1, num);
            System.arraycopy(randomArray, 0, array2, 0, randomArray.length);
            System.arraycopy(array1, 0, array3, 0, array1.length);
            Arrays.sort(array2);
            Arrays.sort(array3);

            // 校验是否相等
            for (int j = 0; j < array2.length; j++) {
                if (array2[j] != array3[j]) {
                    res = false;
                    break;
                }
            }
            if (res) {
                // -1 小于   0 等于    1大于
                int flag = -1;
                for (int i = 0; i < array1.length; i++) {
                    if (i == 0) {
                        flag = Integer.compare(array1[i], num);
                        continue;
                    }

                    // 判定失败 如果判定 已经出现 = num的情况，就不允许再出现 < 的情况
                    if (flag == 0 && array1[i] < num) {
                        res = false;
                        break;
                    }
                    // 判定失败 如果判定 已经出现 > num的情况，就不允许再出现 <= 的情况
                    else if (flag == 1 && array1[i] <= num) {
                        res = false;
                        break;
                    }

                    // 如果后续出现 >= 的情况
                    if (array1[i] >= num) {
                        flag = Integer.compare(array1[i], num);
                    }
                }
            }
        }
        System.out.println("是否正确: " + (res?"正确":"错误"));
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
     * 交换
     * @param arr 原数组
     * @param i i
     * @param j j
     */
    public void swap(int[] arr, int i, int j){
        if(i == j){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
