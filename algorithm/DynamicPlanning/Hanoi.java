package algorithm.DynamicPlanning;

/**
 * 汉诺塔
 *
 * @author 周鹏程
 * @date 2023/7/28 11:47
 */
public class Hanoi {

	public static void main(String[] args) {
		int n = 3;
		// 汉诺塔 1
		hanoi1(n);

		System.out.println("----------------------------------");

		// 汉诺塔 2
		hanoi2(n);
	}


	public static void hanoi1(int n) {
		leftToRight(n);
	}

	private static void leftToRight(int n){
		if(n == 1){
			System.out.println("Move 1 from left to right");
			return;
		}

		leftToMid(n - 1);

		System.out.println("Move "+n+" from left to right");

		midToRight(n - 1);
	}

	private static void leftToMid(int n){
		if(n == 1){
			System.out.println("Move 1 from left to mid");
			return;
		}

		leftToRight(n - 1);

		System.out.println("Move "+n+" from left to mid");

		rightToMid(n - 1);
	}

	private static void rightToMid(int n){
		if(n == 1){
			System.out.println("Move 1 from right to mid");
			return;
		}

		rightToLeft(n - 1);

		System.out.println("Move "+n+" from right to mid");

		leftToMid(n - 1);
	}

	private static void rightToLeft(int n){
		if(n == 1){
			System.out.println("Move 1 from right to left");
			return;
		}

		rightToMid(n - 1);

		System.out.println("Move "+n+" from right to left");

		midToLeft(n - 1);
	}

	private static void midToLeft(int n){
		if(n == 1){
			System.out.println("Move 1 from mid to left");
			return;
		}

		midToRight(n - 1);

		System.out.println("Move "+n+" from mid to left");

		rightToLeft(n - 1);
	}

	private static void midToRight(int n){
		if(n == 1){
			System.out.println("Move 1 from mid to right");
			return;
		}

		midToLeft(n - 1);

		System.out.println("Move "+n+" from mid to right");

		leftToRight(n - 1);
	}

	public static void hanoi2(int n) {
		// 左往右倒 中间的可以成为辅助
		func(n, "left", "right", "other");
	}

	private static void func(int n, String from, String to, String other){
		if(n == 1){
			System.out.println("Move 1 from "+ from + " to " + to);
			return;
		}

		func(n-1, from, other, to);
		System.out.println("Move " + n +" from "+ from + " to " + to);
		func(n-1, other, to, from);
	}

}
