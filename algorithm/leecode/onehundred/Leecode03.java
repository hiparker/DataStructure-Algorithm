package algorithm.leecode.onehundred;

import java.util.*;

/**
 * 49. 最长连续序列（中等）
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 *
 * @author 周鹏程
 * @date 2023-05-16 10:16 AM
 **/
public class Leecode03 {

    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        int maxLong = longestConsecutive(nums);
        System.out.println(maxLong);
    }

    public static int longestConsecutive(int[] nums) {
        // 去重减少处理量
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxLong = 0;
        for (int num : set) {
            // 排除掉 非根节点
            if(set.contains(num-1)){
                continue;
            }

            int currNum = num;
            int currMaxLong = 1;
            while (set.contains(++currNum)){
                currMaxLong++;
            }

            maxLong = Math.max(maxLong, currMaxLong);
        }
        return maxLong;
    }

}
