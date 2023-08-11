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
		int n = 13;
		long currentTimeMillis = System.currentTimeMillis();
		int[] record = new int[n];
		int i = process1(0, record, n);
		System.out.println("计算数字 => " + i +"  运行时间(ms) => " + (System.currentTimeMillis() - currentTimeMillis) );

		currentTimeMillis = System.currentTimeMillis();
		int j = num2(n);
		System.out.println("计算数字 => " + j +"  运行时间(ms) => " + (System.currentTimeMillis() - currentTimeMillis) );
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


	/**
	 * 优化常数项运算
	 * O(Nn)
	 * @param n
	 * @return
	 */
	public static int num2(int n){
		// 32 皇后 单机算不出来 太大了
		if(n < 1 || n > 32){
			return 0;
		}

		// 如果是 13 皇后问题 最右13个1，其余的都是0
		int limit = n == 32 ? -1 : (1 << n) - 1;
		return process2(limit, 0, 0, 0);
	}

	private static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim){
		// 如果自身限制 完全等于 总limit 则表示 已经放完了皇后 计次+1
		if(colLim == limit){
			return 1;
		}

		// 1. (colLim | leftDiaLim | rightDiaLim)
		//    colLim =      00100
		//    leftDiaLim =  01000
		//    rightDiaLim = 00010
		//    或运算  = 01110
		//
		// 2. ~ 取反运算 10001 有1的位置 表示 能够放入皇后
		//    但就因为取反运算 原先 01110 等于 0000 0000 0000 1110
		//                            变成 1111 1111 1111 0001
		//                            前方出现了很多干扰数字
		// 3. limit & 与运算
		//    limit 假设原先等于     0000 0000 0000 1111
		//    利用与运算特性 遇0得0   0000 0000 0000 1111 & 1111 1111 1111 0001
		//                      = 0000 0000 0000 0001
		//                直接消除了干扰元素 得到想要的结果

		int pos = limit & ( ~ (colLim | leftDiaLim | rightDiaLim));
		int mostRightOne = 0;
		int res = 0;
		while (pos != 0){
			// 每次 取 pos 最右一个 1 的数字继续往下运算
			// 取 当前二进制数字 最右侧1 的公式为
			// X & ((~~X)+1)
			mostRightOne = pos & ((~pos)+1);
			pos ^= mostRightOne;
			res += process2(limit,
						colLim | mostRightOne,
					(leftDiaLim | mostRightOne) << 1,
					// 无符号右移
					(rightDiaLim | mostRightOne) >>> 1
					);
		}
		return res;
	}



}
