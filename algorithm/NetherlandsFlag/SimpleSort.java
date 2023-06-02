package algorithm.NetherlandsFlag;


/**
 * 简单排序（不强制要求左右两边顺序）
 *
 * 要求把 比 num 小的数 放左边 ，比num大的数放右边
 *
 * @author 周鹏程
 * @date 2023-06-02 10:13 AM
 **/
public class SimpleSort extends AbstractNetherlandsFlag {

    public static void main(String[] args) {
        SimpleSort simpleSort = new SimpleSort();
        simpleSort.checkSimple();
    }

    @Override
    public int[] sort(int[] array, int num){
        int less = -1;
        for (int i = 0; i < array.length; i++) {
            if(array[i] < num){
                swap(array, ++less, i);
            }
        }
        return array;
    }

}
