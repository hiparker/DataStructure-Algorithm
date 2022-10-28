package algorithm.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 病毒DNA 匹配
 * KMP 实现
 *
 * 匹配 DNA 环状 匹配
 * baa
 * bbaabbbaa
 *
 * @author 周鹏程
 * @date 2022-10-28 7:12 PM
 **/
public class VirusDnaMatch {

    public static void main(String[] args) {
        String virus = "baa";
        String dna1 = "bbaabbbaa";
        boolean b = virusMatch(virus, dna1);
        System.out.println(b);
    }

    public static boolean virusMatch(String virus, String dna){

        int virusLen = virus.length();
        // 复制两份
        String virusCir = virus + virus;

        List<String> source = new ArrayList<>();
        for (int i = 0; i < dna.length(); i++) {
            source.add(String.valueOf(dna.charAt(i)));
        }
        List<Integer> nextArray = buildNextArray(source);

        for (int i = 0; i < virusLen; i++) {
            List<String> virusArray = new ArrayList<>();
            for (int j = i; j < i+3; j++) {
                virusArray.add(String.valueOf(virusCir.charAt(j)));
            }

            boolean match = match(nextArray, source, virusArray);
            if(match){
                return true;
            }
        }

        return false;
    }

    private static boolean match(List<Integer> nextArray, List<String> str, List<String> patt){
        if(null == str || str.size() == 0){
            return false;
        }

        if(null == patt || patt.size() == 0){
            return false;
        }

        if(null == nextArray || nextArray.size() == 0){
            return false;
        }

        int i = 0;
        int j = 0;
        while (i < str.size()){
            if(str.get(i).equals(patt.get(j))){
                i++;
                j++;
            }else if (j > 0){
                j = nextArray.get(j-1);
            }else {
                i++;
            }

            if(j == patt.size()){
                return true;
            }
        }
        return false;
    }

    private static List<Integer> buildNextArray(List<String> str){
        List<Integer> nextArray = new ArrayList<>();
        nextArray.add(0);

        if(null == str || str.size() == 0){
            return nextArray;
        }

        int commonLen = 0;
        int i = 1;
        while (i < str.size()){
            if(str.get(commonLen).equals(str.get(i))){
                nextArray.add(++commonLen);
                i++;
                continue;
            }

            if(commonLen > 0){
                nextArray.add(commonLen);
                i++;
                continue;
            }

            commonLen = nextArray.get(commonLen-1);
        }
        return nextArray;
    }


}
