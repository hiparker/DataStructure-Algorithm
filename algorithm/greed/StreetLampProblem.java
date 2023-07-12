package algorithm.greed;

import java.util.HashSet;

/**
 * 路灯问题
 *
 * 假设 X为墙 .为路灯 问最少需要多少个路灯才能照亮所有的墙
 * 一个灯可以照亮左右两边的墙
 * 例如：XXXX.XXXX.XX.X.XXXX.  需要5个路灯
 *
 * @author 周鹏程
 * @date 2023-07-07 6:11 PM
 **/
public class StreetLampProblem {

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            int ans3 = minLight3(test);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }

    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }
    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') { // 当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没结束
            // i X .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    private static int minLight2(String str) {
        int curr = 0;
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == '.'){
                flag = true;
                ++curr;
                if(i == str.length()-1){
                    // 最后一个字符
                    count += calculate(curr);
                    curr = 0;
                }
            }else {
                if(flag){
                    // 遇到墙 该计算了
                    count += calculate(curr);
                    curr = 0;
                }
                flag = false;
            }
        }
        return count;
    }

    /**
     * 真正的贪心
     * @param str
     * @return
     */
    private static int minLight3(String str) {
        char[] chars = str.toCharArray();
        int i = 0;
        int light = 0;
        while (i < chars.length){
            if(chars[i] == 'X'){
                i++;
            }else {
                light++;
                if(i+1 == chars.length){
                    return light;
                }else {
                    if (chars[i+1] == 'X'){
                        // .XX
                        // .X.
                        // curr = .
                        // next = X || .
                        // 如果下一位是X 则直接跳2位 略过X 由X的后一位做判断
                        i += 2;
                    }else {
                        // ..X.
                        // ...X
                        // ....X
                        // curr = .
                        // next = . || X || .
                        // 如果下一位不是X 则直接跳3位 略过X和下一位 由X的后两位做判断
                        i += 3;
                    }
                }
            }
        }
        return light;
    }

    private static int calculate(int totalCount){
        int base = 3;
        if(totalCount <= base){
            return  1;
        }
        return totalCount % base == 0?totalCount/base:totalCount/base+1;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

}
