package structure.Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树/单词查找树
 *
 * 运用场景：单词压缩匹配、ES的FST倒排索引等等
 *
 * @author 周鹏程
 * @date 2023-06-08 12:01 PM
 **/
public class TrieTree {

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("abc");
        t.insert("abd");
        t.insert("cad");
        t.insert("abda");
        t.insert("abdc");
        t.insert("abca");


        System.out.println(t.search("abc"));
        System.out.println(t.searchPrefix("abd"));
        t.delete("abd");
        System.out.println(t.search("abc"));
        System.out.println(t.searchPrefix("abd"));
    }





    private static class Trie {

        private final Node root;

        public Trie(){
            root = new Node();
        }

        /**
         * 新增前缀
         * @param word 单词
         */
        public void insert(String word){
            // 为空不处理
            if(null == word || "".equals(word)){
                return;
            }

            char[] words = word.toCharArray();
            Node curr = root;
            curr.pass++;

            for (char w : words) {
                int index = w;
                // 计算 ascii码
                if(!curr.nextMap.containsKey(index)){
                    Node node = new Node();
                    curr.nextMap.put(index, node);
                    curr = node;
                }else {
                    curr = curr.nextMap.get(index);
                }
                curr.pass++;
            }
            curr.end++;
        }

        public void delete(String word){
            // 为空不处理
            if(null == word || "".equals(word)){
                return;
            }

            // 没查询到 不处理
            if(search(word) <= 0){
                return;
            }

            char[] words = word.toCharArray();
            Node curr = root;
            curr.pass--;
            for (char w : words) {
                int index = w;
                if(--curr.nextMap.get(index).pass <= 0){
                    curr.nextMap.remove(index);
                    return;
                }
                curr = curr.nextMap.get(index);
            }
            curr.end--;
        }

        /**
         * 获得命中单词个数
         * @param word 单词
         * @return int
         */
        public int search(String word){
            if(null == word || "".equals(word)){
                return 0;
            }

            char[] words = word.toCharArray();
            Node curr = root;
            for (char w : words) {
                int index = w;
                if(!curr.nextMap.containsKey(index)){
                    return 0;
                }
                curr = curr.nextMap.get(index);
            }
            return curr.end;
        }

        /**
         * 获得命中单词前缀个数
         * @param word 单词
         * @return int
         */
        public int searchPrefix(String word){
            if(null == word || "".equals(word)){
                return 0;
            }

            char[] words = word.toCharArray();
            Node curr = root;
            for (char w : words) {
                int index = w;
                if(!curr.nextMap.containsKey(index)){
                    return 0;
                }
                curr = curr.nextMap.get(index);
            }
            return curr.pass;
        }

    }

    /**
     * Node 节点
     */
    private static class Node {

        /** 通过次数 */
        private int pass;

        /** 结尾次数 */
        private int end;

        /** next 节点集合 */
        private Map<Integer, Node> nextMap;

        public Node(){
            this.pass = 0;
            this.end = 0;
            nextMap = new HashMap<>();
        }
    }

}
