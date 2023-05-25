package algorithm.recursion;

import java.util.Random;

/**
 * 递归查询列表最大值
 *
 * @author 周鹏程
 * @date 2023-05-25 3:43 PM
 **/
public class QueryMaxNum {

    public static void main(String[] args) {
        int[] array = createRandomArray(10);
        printArray(array);
        int i = maxNum(array, 0, array.length - 1);
        System.out.println(i);
    }

    private static int maxNum(int[] array, int l, int r){
        if(l == r){
            return array[l];
        }

        int mid = l + ((r-l) >> 1);
        int leftMax = maxNum(array, l, mid);
        int rightMax = maxNum(array, mid+1, r);
        return Math.max(leftMax, rightMax);
    }

    private static void printArray(int[] array){
        System.out.println();
        for (int i : array) {
            System.out.print(i+" ");
        }
        System.out.println();
    }

    private static int[] createRandomArray(int length){
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(10000);
        }
        return array;
    }
}
