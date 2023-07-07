package algorithm.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 拥有最多糖果的孩子
 *
 * 给你一个数组candies和一个整数extraCandies，其中candies[i]代表第 i 个孩子拥有的糖果数目。
 *
 * 对每一个孩子，检查是否存在一种方案，将额外的extraCandies个糖果分配给孩子们之后，此孩子有 最多的糖果。注意，允许有多个孩子同时拥有 最多的糖果数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kids-with-the-greatest-number-of-candies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 周鹏程
 * @date 2023-07-07 12:44 PM
 **/
public class MaxCandies {

    public static void main(String[] args) {
        int[] candies = new int[]{2,3,5,1,3};
        int extraCandies = 3;
        List<Boolean> booleans = kidsWithCandies(candies, extraCandies);
        System.out.println(booleans);
    }

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int candy : candies) {
            max = Math.max(candy, max);
        }

        ArrayList<Boolean> result = new ArrayList<>();
        for (int candy : candies) {
            result.add(candy + extraCandies >= max);
        }
        return result;
    }
}
