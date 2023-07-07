package algorithm.string;

/**
 * 交替合并字符串
 *
 * 给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
 *
 * 返回 合并后的字符串 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-strings-alternately
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 周鹏程
 * @date 2023-07-07 11:44 AM
 **/
public class AlternateMergeString {

    public static void main(String[] args) {
        AlternateMergeString alternateMergeString = new AlternateMergeString();
        String s = alternateMergeString.mergeAlternately("ab", "pqrs");
        System.out.println(s);
    }

    public String mergeAlternately(String word1, String word2) {
        int i = 0, j = 0;
        // 先找到最长的 word
        int m = word1.length(), n = word2.length();
        char[] chars = new char[m + n];
        int index = 0;
        while (i < m || j < n){
            if(i < m){
                chars[index++] = word1.charAt(i++);
            }
            if(j < n){
                chars[index++] = word2.charAt(j++);
            }
        }
        return String.valueOf(chars);
    }

}
