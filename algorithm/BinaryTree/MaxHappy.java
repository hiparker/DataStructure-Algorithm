package algorithm.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 派对最大快乐值
 *
 * class Employee {
 * 	 public int happy; //这名员工可以带来的快乐值
 * 	 List<Employee> subordinates; //这名员工有哪些直接下级
 * }
 *
 * 公司得每个员工都符合Employee类的描述。
 * 整个公司的人员结构可以看做是一棵标准的、没有环的多茶树。
 * 树的头节点是公司唯一的老板。
 * 除老板之外的每个员工都有唯一的直接上级。
 * 叶子节点是没有任何下属的基层员工（subordinates 列表为空），除基层员工外，每个员工都有一个或多个直接下属
 *
 * 这个公司现在要办 party，你可以决定哪些员工来，哪些员工不来，规则：
 * 1. 如果某个员工来了，那么这个员工的所有直接下级都不能来；
 * 2. 排队的整体快乐值是所有到场员工快乐值的累加；
 * 3. 你的目标是让排队的整体快乐值尽量大。
 *
 * 给定一棵多叉树的根节点 boss，请返回排队的最大快乐值。
 *
 * @author 周鹏程
 * @date 2023-07-04 7:46 PM
 **/
public class MaxHappy {

    public static void main(String[] args) {
        MaxHappy h = new MaxHappy();
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (h.getMaxHappy1(boss) != h.getMaxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    private static class Employee {
        //这名员工可以带来的快乐值
 	    int happy;
        //这名员工有哪些直接下级
  	    List<Employee> subordinates;

        public Employee(int happy) {
            this.happy = happy;
            subordinates = new ArrayList<>();
        }
    }

    public int getMaxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    private int process1(Employee cur, boolean up) {
        if (up) { // 如果cur的上级来的话，cur没得选，只能不来
            int ans = 0;
            for (Employee next : cur.subordinates) {
                ans += process1(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.subordinates) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }


    private static class Info{
        int no;
        int yes;

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }
    public int getMaxHappy2(Employee boss){
        if (boss == null) {
            return 0;
        }
        Info allInfo = process2(boss);
        return Math.max(allInfo.no, allInfo.yes);
    }

    private Info process2(Employee boss) {
        if(boss == null){
            return new Info(0, 0);
        }

        int no = 0;
        int yes = boss.happy;
        for (Employee subordinate : boss.subordinates) {
            Info info = process2(subordinate);
            no += Math.max(info.no, info.yes);
            yes += info.no;
        }

        return new Info(no, yes);
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.subordinates.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

}
