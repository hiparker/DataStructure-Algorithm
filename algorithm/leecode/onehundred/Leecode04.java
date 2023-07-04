package algorithm.leecode.onehundred;

/**
 * 283. 移动零（简单）
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: [0]
 *
 *
 * @author 周鹏程
 * @date 2023-05-16 10:16 AM
 **/
public class Leecode04 {

    public static void main(String[] args) {
        int[] nums = {45192,0,0,-52359,0,-75991,0,-15155,27382,59818,0,-30645,-17025,81209,887,64648};
        long l = System.currentTimeMillis();
        moveZeroes(nums);
        System.out.println("耗时：" + (System.currentTimeMillis()-l));
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int less = -1;
        int more = nums.length-1;
        int i = 0;
        while (less < more){
            if(nums[i] == 0){
                int endIndex = more--;
                int tmp = nums[i];
                nums[i] = nums[endIndex];
                nums[endIndex] = tmp;
                continue;
            }
            i++;
            less++;
        }
    }

//    public static void moveZeroes(int[] nums) {
//        if (nums == null || nums.length <= 1) {
//            return;
//        }
//
//        int index = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if(nums[i] != 0){
//                nums[index++] = nums[i];
//            }
//        }
//
//        for (int i = index; i < nums.length; i++) {
//            nums[index++] = 0;
//        }
//    }

//    public static void moveZeroes(int[] nums) {
//        if (nums == null || nums.length <= 1) {
//            return;
//        }
//        int r = 0, l = 0;
//        while (r < nums.length) {
//            if(nums[r] != 0){
//                if(r != l){
//                    int t = nums[r];
//                    nums[r] = nums[l];
//                    nums[l] = t;
//                }
//                l++;
//            }
//            r++;
//        }
//    }

}
