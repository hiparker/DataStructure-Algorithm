package algorithm.DynamicPlanning;

/**
 * 斐波那契数列 递归+动态规划 解题
 *
 * @author 周鹏程
 * @date 2023/8/14 19:34
 */
public class Fib {

	public static void main(String[] args) {
		int n = 15;
		int val = fib(n);
		System.out.println(val);
		val = dpWay(n);
		System.out.println(val);
	}

	private static int fib(int n) {
		if(n == 1 || n == 2){
			return 1;
		}
		return fib(n-1) + fib(n-2);
	}

	private static int dpWay(int n) {
		if(n == 1 || n == 2){
			return 1;
		}

		int[] dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		return dp[n];
	}


}
