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
        List<String> abc = permutation("abc");
        for (String s : abc) {
            System.out.println(s);
        }
    }

    public static List<String> permutation(String str){
        List<String> array = new ArrayList<>();
        process(str, new HashSet<>(), "", array);
        return array;
    }

    private static void process(String str, HashSet<Integer> use, String path, List<String> array) {
        if(use.size() == str.length()){
            array.add(path);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if(!use.contains(i)){
                use.add(i);
                process(str,use,path + str.charAt(i),array);
                use.remove(i);
            }
        }
    }

}
