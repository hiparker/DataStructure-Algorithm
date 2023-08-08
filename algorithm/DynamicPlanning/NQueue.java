package algorithm.DynamicPlanning;

/**
 * N 皇后
 *
 * N 皇后问题是指在N*N的棋盘上 要摆N个皇后
 * 要求任何两个皇后不同行、不同列，也不在同一条斜线上
 *
 * 给定一个整n，返回N皇后的摆法有多少种
 *
 * n=1, 返回1
 * n=2或3 2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 *
 * @author 周鹏程
 * @date 2023/8/8 17:16
 */
public class NQueue {

	public static void main(String[] args) {
		int n = 8;
		int[] record = new int[n];
		int i = process1(0, record, n);
		System.out.println(i);
	}

	public static int process1(int i, int[] record, int n){
		if(i == n){
			return 1;
		}

		int res = 0;
		for (int j = 0; j < n; j++) { // 当前行在i行，尝试i行所有的列 -> j
			// 当前i行的皇后，放在j列，会不会和之前(0..i-1)的皇后，不共行、不共列、不共斜线
			if(isValid(record, i, j)){
			// 如果是 认为有效 继续往下尝试
				record[i] = j;
				res += process1(i+1, record, n);
			}
		}
		return res;
	}

	private static boolean isValid(int[] record, int i, int j){
		for (int k = 0; k < i; k++) {
			// 本身就默认的1行放一个皇后 所有不会共行
			// 判断一 是否共列
			// 判断二 是否共斜线
			if(record[k] == j || Math.abs(record[k]-j) == Math.abs(i - k)){
				return false;
			}
		}
		return true;
	}

}
