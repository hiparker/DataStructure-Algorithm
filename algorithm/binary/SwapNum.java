package algorithm.binary;

/**
 * 二进制 交换数字
 *
 * 异或实现
 *
 * @author 周鹏程
 * @date 2023-05-23 10:35 AM
 **/
public class SwapNum {

    public static void main(String[] args) {
        int a = 3, b = 5;
        System.out.println("a="+a+"  b="+b);

        a = a ^ b;
        b = a ^ b; // a ^ b ^ b
        a = a ^ b; // a ^ b ^ a
        System.out.println("a="+a+"  b="+b);
    }
}
