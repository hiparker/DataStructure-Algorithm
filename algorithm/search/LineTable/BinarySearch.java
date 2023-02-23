package algorithm.search.LineTable;

/**
 * 二分查找
 *
 * @author 周鹏程
 * @date 2023-02-23 10:23 AM
 **/
public class BinarySearch {

    public static void main(String[] args) {

        int count = 100;
        int[] array = new int[count];
        for (int i = 0, val = 0; i < count;i++) {
            val += (int) (Math.random()*i) + i;
            array[i] = val;
        }

        System.out.println("源数据：");
        for (int j : array) {
            System.out.print(j+" ");
        }
        System.out.println();

        int curr = (int) (Math.random()*(count*2));
        boolean result = binarySearch(array, curr);
        System.out.println("要查找的内容："+curr+"  查找结果：" + result);

        while (!result){
            curr = (int) (Math.random()*(count*2));
            result = binarySearch(array, curr);
            System.out.println("要查找的内容："+curr+"  查找结果：" + result);
        }
    }

    /**
     * 二分查找
     * @param array 数组
     * @param curr 当前值
     * @return boolean
     */
    public static boolean binarySearch(int[] array, int curr){
        int head = 0;
        int tail = array.length;
        int center = (head+tail)/2;

        // 14
        // head = 0      tail = 0
        // center = 0
        //
        // 15
        // 22

        while (tail >= head) {
            if (array[center] == curr) {
                return true;
            } else if (curr > array[center]) {
                head = center + 1;
            } else {
                tail = center - 1;
            }
            // 更新中心位置
            center = (head + tail) / 2;
        }
        return false;
    }




}
