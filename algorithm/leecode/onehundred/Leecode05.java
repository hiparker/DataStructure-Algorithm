package algorithm.leecode.onehundred;

/**
 * 11. 盛最多水的容器（中等）
 *
 * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
 *
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
 *
 *
 * @author 周鹏程
 * @date 2023-05-16 10:16 AM
 **/
public class Leecode05 {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int maxArea = maxArea(height);
        System.out.println(maxArea);
    }

    /**
     * 求两条线 之间的 面积（木桶原理 取决于最小的板）
     * @param height height
     * @return 最大盛水量
     */
    public static int maxArea(int[] height) {
        int l = 0, r = height.length-1, max = 0;
        while (l < r){
            max = height[l] < height[r]
                    ? Math.max(max, (r-l) * height[l++])
                    : Math.max(max, (r-l) * height[r--]);
        }
        return max;
    }

}
