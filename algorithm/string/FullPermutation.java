package algorithm.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 字符串全排列 穷举法
 *
 * @author 周鹏程
 * @date 2023-07-06 6:56 PM
 **/
public class FullPermutation {

    public static void main(String[] args) {
        List<String> abc1 = permutation1("abc");
        for (String s : abc1) {
            System.out.println(s);
        }

        System.out.println("----------------------------");

        List<String> abc2 = permutation2("abc");
        for (String s : abc2) {
            System.out.println(s);
        }
    }

    public static List<String> permutation1(String str){
        List<String> array = new ArrayList<>();
        process1(str, new HashSet<>(), "", array);
        return array;
    }

    private static void process1(String str, HashSet<Integer> use, String path, List<String> array) {
        if(use.size() == str.length()){
            array.add(path);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if(!use.contains(i)){
                use.add(i);
                process1(str,use,path + str.charAt(i),array);
                use.remove(i);
            }
        }
    }


    public static List<String> permutation2(String str){
        List<String> array = new ArrayList<>();
        process2(str.toCharArray(), 0, array);
        return array;
    }


    private static void process2(char[] str, int i, List<String> array) {
        if(i == str.length){
            array.add(String.valueOf(str));
        }

        for (int j = i; j < str.length;j++){
            swap(str, i, j);
            process2(str, i+1, array);
            swap(str, i, j);
        }
    }

    private static void swap(char[] str, int i, int j){
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }
}
