package algorithm.base;

/**
 * @author Parker
 * @date 2022-09-19 3:48 PM
 **/
public class Log2N {

    public static void main(String[] args) {

        // 求时间复杂度
        int n = 2048;
        int i = 1;
        while (i <= n){
            i = i << 1;
        }

        // 假设循环了 x 次
        // 因为 2^x <= n
        // x = log2n
        // 时间复杂度 =  O(log2n) 抹去对于影响不大的2 得到结果为 O(logn)

    }

}
