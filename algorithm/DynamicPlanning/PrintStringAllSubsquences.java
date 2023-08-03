package algorithm.DynamicPlanning;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印字符串的 全部子序列
 *
 * @author 周鹏程
 * @date 2023/8/3 19:44
 */
public class PrintStringAllSubsquences {

	public static void main(String[] args) {
		String str = "abcd";
		printAllSubsquences(str);
	}

	public static void printAllSubsquences(String str){
		char[] strChars = str.toCharArray();
		List<String> ans = new ArrayList<>();
		process(strChars, 0, ans, "");

		for (String an : ans) {
			System.out.println(an);
		}
	}

	private static void process(char[] strChars, int index, List<String> ans, String path){
		if(index == strChars.length){
			ans.add(path);
			return;
		}

		String no = path;
		process(strChars, index+1, ans, no);

		String yes = path + strChars[index];
		process(strChars, index+1, ans, yes);
	}

}
