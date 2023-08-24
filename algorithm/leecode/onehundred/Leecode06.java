package algorithm.leecode.onehundred;

/**
 * 有一个整数类型的列表nums，找出一个序列乘积最大的连续子序列
 * （该序列包含一个数）
 *
 * 案例
 * data = [1,2,-2,-1,5,-4]
 * 输出20 子序列[-1, 5, -4]
 *
 * @author 周鹏程
 * @date 2023-05-16 10:16 AM
 **/
public class Leecode06 {

    public static void main(String[] args) {
        int[] data = {1, 2, -2, -1, 5,-4};
        System.out.println(maxSubs1(data));
    }


    public static int maxSubs1(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int currMax = arr[i];
            for (int k = i+1; k < arr.length; k++) {
                currMax *= arr[k];
            }
            max = Math.max(max, currMax);
        }
        return max;
    }

}
