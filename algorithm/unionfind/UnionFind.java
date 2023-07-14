package algorithm.unionfind;

import java.util.*;

/**
 * 并查集
 * 优化后 可以做到 O(1)
 *
 * 小挂大，长队分裂
 *
 * @author 周鹏程
 * @date 2023-07-14 3:26 PM
 **/
public class UnionFind {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 4;
        Integer e = 5;
        List<Integer> list = new ArrayList<>();
        list.add(a);list.add(b);list.add(c);list.add(d);list.add(e);

        UnionFindSet<Integer> unionFindSet = new UnionFindSet<>(list);

        boolean isSameSet;
        isSameSet = unionFindSet.isSameSet(a, b);
        System.out.println("a & b 是否并集 => " + isSameSet);
        unionFindSet.union(a, b);
        isSameSet = unionFindSet.isSameSet(a, b);
        System.out.println("a & b 是否并集 => " + isSameSet);
        unionFindSet.union(c, b);
        unionFindSet.union(e, b);
        isSameSet = unionFindSet.isSameSet(a, e);
        System.out.println("a & e 是否并集 => " + isSameSet);
        isSameSet = unionFindSet.isSameSet(a, d);
        System.out.println("a & d 是否并集 => " + isSameSet);
    }


    public static class UnionFindSet<V> {
        private final Map<V, V> parent = new HashMap<>();
        private final Map<V, Integer> size = new HashMap<>();

        public UnionFindSet(List<V> list){
            if(null == list || list.size() == 0){
                return;
            }

            for (V v : list) {
                parent.put(v, v);
                size.put(v, 1);
            }
        }

        private V findParent(V cur){
            Stack<V> stack = new Stack<>();
            while (cur != parent.get(cur)){
                stack.push(cur);
                cur = parent.get(cur);
            }

            // 优化后 可以做到 O(1)
            // 算法优化 从长链路 转换成 锻炼路
            while (!stack.isEmpty()){
                parent.put(stack.pop(), cur);
            }
            return cur;
        }

        /**
         * 判断两个 对象是否一个并查集
         * @param a a
         * @param b b
         * @return boolean
         */
        public boolean isSameSet(V a, V b) {
            return findParent(a) == findParent(b);
        }

        /**
         * 合并两个对象
         * @param a a
         * @param b b
         */
        public void union(V a, V b){
            // 两个父类一致的话 本身就在一个并查集里 不需要再处理
            V parentA = findParent(a);
            V parentB = findParent(b);
            if(parentA == parentB){
                return;
            }

            Integer aSize = size.get(parentA);
            Integer bSize = size.get(parentB);

            V bigParentV = aSize > bSize ? parentA : parentB;
            V smallParentV = bigParentV == parentB ? parentA : parentB;

            parent.put(smallParentV, bigParentV);
            size.put(bigParentV, aSize+bSize);
            size.remove(smallParentV);
        }


        public int getSize(){
            return size.size();
        }
    }

}
