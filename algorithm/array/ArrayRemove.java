package algorithm.array;

/**
 * 删除数组指定val 要求 空间 O(1)
 * @author 周鹏程
 * @date 2023/8/22 11:57
 */
public class ArrayRemove {

	public static void main(String[] args) {

		int[] array = {7, 5, 2, 6, 2, 2, 3, 8, 7};
		System.out.println("输出原始串: ");
		for (int i : array) {
			System.out.print(i+" ");
		}
		System.out.println();

		// 删除操作
		int len = removeNode(array, 2);
		System.out.println("输出删除后串: " + len);
		for (int i : array) {
			System.out.print(i+" ");
		}
		System.out.println();
	}

	/**
	 * 双指针方案实现
	 * 如果 arr[right] != val
	 *  默认是当前赋值 arr[left] = arr[right]
	 * 但如果 arr[right] == val
	 *  则意味着命中了 要删除的属性 right 指针继续向前走 left指针原地不动
	 *  等下一次 left = right 则 跳过了要删除的val
 	 *
	 * @param arr 原数组
	 * @param val 删除值
	 * @return 长度
	 */
	private static int removeNode(int[] arr, int val) {
		int left = 0;
		for (int right = 0; right < arr.length; right++){
			if(arr[right] != val){
				arr[left++] = arr[right];
			}
		}

		// 处理尾部数据
		for (int i = left; i < arr.length; i++) {
			arr[i] = 0;
		}
		return left;
	}

}
