package algorithm.string;

/**
 * BF 匹配算法
 * 暴力(Brute Force)算法
 *
 * @author 周鹏程
 * @date 2022-10-18 10:58 AM
 **/
public final class BF_alg {

    public static boolean match(char[] source, char[] curr){
        if(null == source || null == curr){
            return false;
        }

        int i = 0, j = 0;
        while (i < source.length){
            if(source[i] != curr[j]){
                // 该算法可以 回溯 source 到上一次判断位置+1
                i = i-j+1;
                // curr 数组 回退到初始位置
                j = 0;
                continue;
            }

            // 当j 匹配到 最后一个位置 还正确的情况下 则认为匹配成功 退出循环
            if(j == curr.length-1){
                return true;
            }

            // 当前还没匹配完 继续往下走
            i++;
            j++;
        }

        return false;
    }

    public static void main(String[] args) {
        char[] a = {'a','c','b','c','a','c','c'};
        char[] b = {'a','c','c'};
        System.out.println(match(a, b));
    }

}
