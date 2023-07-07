package algorithm.string;

/**
 * 种花问题
 *
 * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 *
 * 给你一个整数数组flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数n ，能否在不打破种植规则的情况下种入n朵花？能则返回 true ，不能则返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/can-place-flowers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 周鹏程
 * @date 2023-07-07 1:23 PM
 **/
public class PlantFlowers {

    public static void main(String[] args) {
        int[] flowerbed = {0,0,1,0,0};
        int n = 1;
        boolean b = canPlaceFlowers(flowerbed, n);
        System.out.println(b);
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        for(int i = 0; i < flowerbed.length; i++){
            if(flowerbed[i] == 1){
                continue;
            }

            // 贪心 能种则种
            boolean flag = (flowerbed.length == 1 && flowerbed[i] == 0) ||
                    (flowerbed.length > 1 && i == 0 && flowerbed[i + 1] == 0) ||
                    (flowerbed.length > 1 && i == flowerbed.length - 1 && flowerbed[i - 1] == 0) ||
                    (flowerbed.length > 1 && i > 0 && i < flowerbed.length - 1 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0);
            if(flag){
                flowerbed[i] = 1;
                --n;
            }
        }
        return n <= 0;
    }

}
