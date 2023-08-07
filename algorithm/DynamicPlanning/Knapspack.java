package algorithm.DynamicPlanning;

/**
 * 背包问题 (从左到右的尝试模型)
 *
 * 给定两个长度为N的数组weights和values
 * weight[i] 和 values[i] 分别代表i号物品的重量和价值
 * 给你一个正数的bag。表示一个载重bag的袋子
 * 你装的物品不能超过这个重量
 * 返回你能装下最多价值的是多少
 *
 *
 * @author 周鹏程
 * @date 2023/8/7 10:26
 */
public class Knapspack {

	public static void main(String[] args) {

		int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
		int[] values = { 5, 6, 3, 19, 12, 4, 2 };
		int bag = 15;

		int val = maxValue(weights, values, bag);
		System.out.println(val);
	}

	public static int maxValue(int[] w, int[] v, int bag){
		if(w == null || w.length == 0 ||
			v == null || v.length == 0 || bag == 0){
			return 0;
		}

		// 尝试函数
		return process(w, v, 0, bag);
	}

	private static int process(int[] w, int[] v, int i, int rest) {
		if(rest <= 0){
			return Integer.MIN_VALUE;
		}

		if(i == w.length){
			return 0;
		}

		int p1 = process(w, v, i+1, rest);
		int p2 = process(w, v, i+1, rest - w[i]);
		if(p2 != Integer.MIN_VALUE){
			p2 = v[i] + p2;
		}
		return Math.max(p1, p2);
	}

}
