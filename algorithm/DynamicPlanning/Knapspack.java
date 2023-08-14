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
		val = dpWay(weights, values, bag);
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
		if(rest < 0){
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

	/**
	 * 暴力递归改动态规划
	 * @param w w
	 * @param v v
	 * @param bag bag
	 * @return int
	 */
	public static int dpWay(int[] w, int[] v, int bag){
		if(w == null || w.length == 0 ||
				v == null || v.length == 0 || bag == 0){
			return 0;
		}

		int n = w.length;
		int[][] dp = new int[n+1][bag+1];

		// 默认二维表 dp[n+1][0 ~ bag+1] 都是0

		// ..
		for (int index = n - 1; index >= 0; index--) {
			for (int rest = 0; rest <= bag; rest++) {
				// dp[index][rest] = ?
				int p1 = dp[index+1][rest];
				int p2 = -1;
				if(rest - w[index] >= 0){
					p2 = v[index] + dp[index + 1][rest - w[index]];
				}
				dp[index][rest] = Math.max(p1, p2);
			}
		}
		return dp[0][bag];
	}

}
