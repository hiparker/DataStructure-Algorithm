package algorithm.binary;

/**
 * 二进制 求奇数偶数
 *
 * 一个数组中有一种树 出现了奇数次，其他数都出现了偶数次，怎么找到并打印出来
 *
 * @author 周鹏程
 * @date 2023-05-23 10:35 AM
 **/
public class OddAndEvenNumber {

    public static void main(String[] args) {
        int[] array = {1, 2, 1, 5 ,3 ,5, 2};

        System.out.println(getOdd(array));

    }

    /**
     * 利用 异或特性
     *
     * 1. 多个值异或 顺序不论怎样 结果相同
     * 2. 0 异或 任何数 都等于任何数
     * 3. 任何数异或自身都等于0
     *
     * 如果都是成对出现的 都会相互抵消，而剩下来的便是 奇数次
     *
     */
    private static int getOdd(int[] array){
        int odd = 0;
        for (int i : array) {
            odd ^= i;
        }
        return odd;
    }

}
