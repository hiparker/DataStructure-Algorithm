package algorithm.binary;

/**
 * 二进制 求1存在的次数
 * 主要运用场景为 redis bitmap
 *  假设将 单用户签到记录 存放在一个2进制表中 一共30位 代表30天签到打开 0为未打卡 1为已打卡
 * 这个算法就可以 快速算出 当前这个用户 本月完成多少次打卡
 *
 * @author 周鹏程
 * @date 2023-05-23 10:35 AM
 **/
public class SumOneCount {

    public static void main(String[] args) {
        System.out.println(getCount(1));
        System.out.println(getCount(2));
        System.out.println(getCount(3));
        System.out.println(getCount(52));
    }

    /**
     * 利用 异或特性
     *
     * 1. 二进制忽略进位相加 每次都加自己的尾数1 最终这个数 就会为0
     * 2. 获取 二进制最后尾部1 公式  n&((~n)+1)
     */
    private static int getCount(int n){
        int count = 0;
        while (n != 0){
            n = n ^ (n&((~n)+1));
            count++;
        }
        return count;
    }

}
