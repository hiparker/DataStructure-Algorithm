package algorithm.DynamicPlanning;

/**
 * 数字字符串 转化 ABCD
 * 从左往右的尝试模型
 *
 * 规定1和A对应、2和B对应、3和C对应
 * 那么一个数字字符串比如 "111" 就可以转化为
 * "AAA"、"KA"、"AK"
 * 给定一个只有数字的字符串组成的字符串Str，返回有多少种转化结果
 *
 * @author 周鹏程
 * @date 2023/8/7 15:02
 */
public class ConvertToLetterString {

	public static void main(String[] args) {
		String str = "111";
		int conver = conver(str);
		System.out.println(conver);
		conver = dpWay(str);
		System.out.println(conver);
	}

	public static int conver(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] chars = str.toCharArray();
		return process(chars, 0);
	}

	private static int process(char[] chars, int i) {
		if (i == chars.length) {
			return 1;
		}

		// 如果 有0 则无对照翻译字符
		if (chars[i] == '0') {
			return 0;
		}

		// i 单转
		int ways = process(chars, i + 1);
		if (i + 1 < chars.length && (chars[i] - '0') * 10 + chars[i + 1] - '0' < 27) {
			ways += process(chars, i + 2);
		}
		return ways;
	}

	private static int dpWay(String str) {
		if(null == str || str.length() == 0){
			return 0;
		}

		char[] chars = str.toCharArray();
		int n = chars.length;
		int[] dp = new int[n+1];

		// dp ?
		dp[n] = 1;
		for (int i = n - 1; i >= 0; i--) {
			// 如果 有0 则无对照翻译字符
			if (chars[i] == '0') {
				dp[i] = 0;
				continue;
			}

			// i 单转
			dp[i] = dp[i + 1];
			if (i + 1 < chars.length && (chars[i] - '0') * 10 + chars[i + 1] - '0' < 27) {
				dp[i] += dp[i + 2];
			}
		}
		return dp[0];
	}
}
