package algorithm.NetherlandsFlag;


/**
 * 经典排序（荷兰国旗问题）
 *
 * 要求把数组内 比 num 小的数 放左边 ， 等于 num 的放中间， 比num大的数放右边
 *
 * @author 周鹏程
 * @date 2023-06-02 10:13 AM
 **/
public class ClassicsSort extends AbstractNetherlandsFlag {

    public static void main(String[] args) {
        ClassicsSort simpleSort = new ClassicsSort();
        simpleSort.check();
    }

    @Override
    public int[] sort(int[] array, int num){
        int less = -1;
        int more = array.length;
        int i = 0;
        while (i < more){
            if (array[i] < num){
                swap(array, ++less, i);
                i++;
                continue;
            }else if(array[i] > num){
                swap(array, --more, i);
                continue;
            }
            i++;
        }
        return array;
    }

}
