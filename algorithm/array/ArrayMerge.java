package algorithm.array;

/**
 * 数组合并 最优算法
 *
 * @author 周鹏程
 * @date 2022-10-26 8:48 PM
 **/
public class ArrayMerge {

    public static void main(String[] args) {

        // 时间复杂度 O(m+n)
        int[] a = {2,5,7,8,17};
        int[] b = {1,3,6,9,13,18};
        int[] c = new int[a.length+b.length];

        int count = 0;
        int i = 0;
        int j = 0;
        while (count < (a.length+b.length)){
            if(i > a.length -1){
                c[count++] = b[j++];
                continue;
            }

            if(j > b.length -1){
                c[count++] = a[i++];
                continue;
            }

            c[count++] = a[i] < b[j]?a[i++]:b[j++];
        }


        // 校验
        for (int i1 : c) {
            System.out.println(i1);
        }

    }

}
