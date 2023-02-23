package algorithm.search.LineTable;


/**
 * 线性表 哨兵查找 （非线程安全）
 *
 * @author 周鹏程
 * @date 2023-02-22 6:53 PM
 **/
public class SentinelSearch {

    public static void main(String[] args) {
        int length = 1000;
        int[] array = new int[length+1];
        for (int i = 1; i < length;) {
            array[i] = ++i;
        }

        // 乱序处理 洗牌
        for (int i = length - 1; i > 0; i--) {
            int index = (int) (Math.random()*i);
            if(0 == index){
                continue;
            }

            int temp = array[index];
            array[index] =  array[i];
            array[i] = temp;
        }

        int curr = (int) (Math.random()*(length*2));
        // 查找
        boolean b = sentinelSearch(array, curr);
        System.out.println("要查找的内容："+curr+"  查找结果：" + b);
    }

    /**
     * 哨兵查找 时间复杂度 O(n)
     *
     * @param array 原始数组
     * @param curr 当前要查找的数据
     * @return 结果
     */
    private static boolean sentinelSearch(int[] array, int curr){
        array[0] = curr;
        int i = array.length - 1;
        for(;array[i] != curr; i--);
        // 复原
        array[0] = 0;
        return i != 0
                ? true
                : false;
    }



}
