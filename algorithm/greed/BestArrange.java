package algorithm.greed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法 安排会议
 *
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲
 * 提供每个项目的开始时间和结束时间，安排，返回最多的宣讲场次
 *
 * @author 周鹏程
 * @date 2023-07-12 6:12 PM
 **/
public class BestArrange {

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange1(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    // 暴力！所有情况都尝试！
    public static int bestArrange1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    private static int process(Program[] programs, int done, int timeLine) {
        if(programs.length == 0){
            return done;
        }

        int max = done;
        for (int i = 0; i < programs.length; i++) {
            // 表示 可以安排会议
            if(programs[i].start >= timeLine){
                Program[] newPrograms = copyButExcept(programs, i);
                max = Math.max(process(newPrograms, done+1, programs[i].end), max);
            }
        }
        return max;
    }

    private static Program[] copyButExcept(Program[] programs, int i){
        Program[] newPrograms = new Program[programs.length-1];
        for (int j = 0, k = 0; j < programs.length; j++) {
            if(j != i){
                newPrograms[k++] = programs[j];
            }
        }
        return newPrograms;
    }


    // 贪心
    public static int bestArrange2(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }

        int timeline = 0;
        int done = 0;
        Arrays.sort(programs, Comparator.comparingInt(o -> o.end));
        for (Program program : programs) {
            if (timeline <= program.start) {
                timeline = program.end;
                done++;
            }
        }
        return done;
    }

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }
}
