package algorithm.array;

/**
 * 合并k个升序列表
 * 注：血与泪的教训 当时怎么没做出来了呢？
 *
 * @author 周鹏程
 * @date 2023/7/26 17:01
 */
public class ArrayMerge3 {
	public static void main(String[] args) {

		int[][] lists = {{1,4,5},{1,3,4},{2,6}};

		int len = 0;
		int[] indexArray = new int[lists.length];
		for (int i = 0; i < lists.length; i++) {
			len += lists[i].length;
			indexArray[i] = 0;
		}

		int[] arr = new int[len];
		int index = 0;
		while (index < len){
			int minIndex = -1;
			int minMum = 0;
			for (int i = 0; i < indexArray.length; i++) {
				// 非法判断
				if(indexArray[i] >= lists[i].length){
					continue;
				}

				if(minIndex == -1){
					minIndex = i;
					minMum = lists[i][indexArray[i]];
					continue;
				}

				int tmp = lists[i][indexArray[i]];
				if(tmp < minMum){
					minIndex = i;
					minMum = tmp;
				}
			}

			arr[index++] = lists[minIndex][indexArray[minIndex]];
			indexArray[minIndex]++;
		}

		for (int i : arr) {
			System.out.println(i);
		}
	}

}
