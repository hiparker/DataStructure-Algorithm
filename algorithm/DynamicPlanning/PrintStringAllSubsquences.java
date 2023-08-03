package algorithm.DynamicPlanning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 1. 打印字符串的 全部子序列
 * 2. 打印字符串的 全部子序列, 要求不重复
 * @author 周鹏程
 * @date 2023/8/3 19:44
 */
public class PrintStringAllSubsquences {

	public static void main(String[] args) {
		String str = "abcd";
		List<String> ans = subs1(str);
		for (String an : ans) {
			System.out.println(an);
		}

		System.out.println("----------------------");

		str = "aaa";
		HashSet<String> ans2 = subs2(str);
		for (String an : ans2) {
			System.out.println(an);
		}
	}

	public static List<String> subs1(String str){
		char[] strChars = str.toCharArray();
		List<String> ans = new ArrayList<>();
		process1(strChars, 0, ans, "");
		return ans;
	}

	public static HashSet<String> subs2(String str){
		char[] strChars = str.toCharArray();
		HashSet<String> ans = new HashSet<>();
		process2(strChars, 0, ans, "");
		return ans;
	}

	private static void process1(char[] strChars, int index, List<String> ans, String path){
		if(index == strChars.length){
			ans.add(path);
			return;
		}

		String no = path;
		process1(strChars, index+1, ans, no);

		String yes = path + strChars[index];
		process1(strChars, index+1, ans, yes);
	}


	private static void process2(char[] strChars, int index, HashSet<String> ans, String path){
		if(index == strChars.length){
			ans.add(path);
			return;
		}

		String no = path;
		process2(strChars, index+1, ans, no);

		String yes = path + strChars[index];
		process2(strChars, index+1, ans, yes);
	}

}
