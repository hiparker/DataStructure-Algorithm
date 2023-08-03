package algorithm.DynamicPlanning;

/**
 * 打印字符串的 全部子字符串
 *
 * @author 周鹏程
 * @date 2023/8/3 19:44
 */
public class PrintStringAllSubString {

	public static void main(String[] args) {
		String str = "abcd";
		printAllSubString(str);
	}

	public static void printAllSubString(String str){
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(chars[i]);
			System.out.println(sb);
			for (int k = i+1; k < chars.length; k++) {
				sb.append(chars[k]);
				System.out.println(sb);
			}
		}
	}

}
