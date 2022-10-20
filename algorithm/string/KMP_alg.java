package algorithm.string;

import structure.LinearStructure.MyArrayList;

/**
 * KMP 匹配算法（取当前位置子串的 共同前后缀）
 *  1. 需要先计算出 next array， nextArray 起到决定 每次匹配跳过多少位的作用 也是这个算法能够变快的根本原因
 *  2. 正常依次匹配字符串，如果没有匹配到 就取当前子串-1未知的 跳过位置 进行匹配
 *  3. 但凡是能够在当前位置匹配不到，当前子串位置-1 的 跳过个数绝对是没问题的
 *
 * @author 周鹏程
 * @date 2022-10-19 8:41 PM
 **/
public class KMP_alg {

    public static void main(String[] args) {
        String str = "abdaaababababcdda";
        String patt = "ababc";

        int i = kmpSearch(str, patt);

        // 校验
        String substring = str.substring(i, i+patt.length());
        System.out.println("主串："+str);
        System.out.println("子串："+patt);
        System.out.println("匹配到的字符串："+substring + "   匹配状态："+(patt.equals(substring)));
    }

    /**
     * kmp 查找
     * @param str 主串
     * @param patt 子串
     * @return 匹配位置
     */
    public static int kmpSearch(String str, String patt){
        // 非法判断
        if(null == str || null == patt){
            return -1;
        }

        // 成功next数组
        MyArrayList<Integer> nextList = buildNext(patt.toCharArray());

        int i=0,j=0;
        while (i < str.length()){
            // 当前字符匹配成功 继续往后匹配
            if(str.charAt(i) == patt.charAt(j) ){
                i++;
                j++;
            }
            // 没有匹配成功 获取子串 next数组所跳过的位置
            else if(j > 0){
                j = nextList.getElem(j-1);
            }
            // patt[0] 就没有匹配成功 主串直接继续向后移动
            else {
                i++;
            }

            // 匹配成功
            if(j == patt.length()){
                // 返回匹配成功的位置
                return i-j;
            }
        }

        return -1;
    }

    /**
     * 获取 next 数组
     * @param modelArray 子串数组
     * @return MyArrayList
     */
    private static MyArrayList<Integer> buildNext(char[] modelArray){
        // next value == 跳过不再匹配的个数 == 模组右移 ****
        MyArrayList<Integer> nextList = new MyArrayList<>();
        // 初始化元素为 一个0
        nextList.insert(0);

        // 非法判断
        if(null == modelArray || modelArray.length == 0){
            return nextList;
        }

        // 当前共同的前后缀长度
        int prefixLen = 0;
        for (int i = 1; i < modelArray.length;) {
            // 如果 前后相等 则 next+1
            if(modelArray[prefixLen] == modelArray[i]){
                nextList.insert(++prefixLen);
                i++;
                continue;
            }

            // 如果前后不相等 且前缀 == 0
            if(prefixLen == 0){
                // 下一位 next = 0
                nextList.insert(prefixLen);
                i++;
                continue;
            }

            // 如果前后不相等 且前缀 != 0 重制前缀长度
            prefixLen = nextList.getElem(prefixLen - 1);
        }

        return nextList;
    }

}
