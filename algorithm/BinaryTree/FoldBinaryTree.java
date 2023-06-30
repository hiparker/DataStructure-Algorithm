package algorithm.BinaryTree;


/**
 * 二叉树 解
 *
 * 请把一张纸条竖着放在桌子上,然后从纸条的下方向上方对折1次,压出折痕后展开.
 * 此时折痕是凹下去的,即折痕凸起的方向指向纸条的背面.
 * 如果从纸条的下边向上连续对折2次,压出折痕后展开,此时有三条折痕,从上到下依次是下折痕、下折痕和上折痕
 *
 * 给定一个输入参数N, 代表纸条都从下边向上对折N次. 请从上到下打印所有折痕的方向
 * 例如:  N=1时, 打印 down, N=2时, 打印 down down up
 */
public class FoldBinaryTree {

    public static void main(String[] args) {
        fold(3);
    }


    public static void fold(int n){
        doFold(1, n, true);
    }

    private static void doFold(int curr, int n, boolean isConcave){
        if(curr > n){
            return;
        }

        doFold(curr+1, n, true);

        String p = isConcave?"凹":"凸";
        System.out.println(p);

        doFold(curr+1, n, false);
    }


}
