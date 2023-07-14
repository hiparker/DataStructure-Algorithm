package algorithm.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 用户并集查
 *
 * 假定有三个平台，抖音、快手、B站
 *
 * 抖音 绑定用户的 邮箱
 * 快手 绑定用户的 手机号
 * 微信 绑定用户的 QQ号
 *
 * 现在要做一个新平台，会同时向这三个渠道投放引流广告
 * 从这三个平台注册上来的用户 会携带他们的绑定账号
 *
 * 需求：现在需要把这三个平台账号在新平台合并，求当前平台有多少位真实用户
 * 要求尽可能做到 O(1)
 *
 * @author 周鹏程
 * @date 2023-07-14 5:00 PM
 **/
public class UserUnionFind {


    public static void main(String[] args) {
        User[] users = new User[4];

        // 已知 0-2 为同一个用户 3为独立用户
        users[0] = new User();
        users[0].email = "1@qq.com";
        users[0].phone = "11111111111";
        users[0].qq = "123456789";

        users[1] = new User();
        users[1].email = "2@qq.com";
        users[1].phone = "22222222222";
        users[1].qq = "123456789";

        users[2] = new User();
        users[2].email = "3@qq.com";
        users[2].phone = "22222222222";
        users[2].qq = "4411445";

        users[3] = new User();
        users[3].email = "4@qq.com";
        users[3].phone = "333333333333";
        users[3].qq = "666666666666";

        int userCount = getUserCount(users);
        System.out.println("真实用户数为: " + userCount);
    }


    public static int getUserCount(User[] users){
        if(null == users || users.length == 0){
            return 0;
        }

        UnionFind<User> userUnionFind = new UnionFind<>(users);
        Map<String, User> emailMap = new HashMap<>();
        Map<String, User> phoneMap = new HashMap<>();
        Map<String, User> qqMap = new HashMap<>();

        for (User user : users) {
            // email
            if(emailMap.containsKey(user.email)){
                userUnionFind.union(user, emailMap.get(user.email));
            }else {
                emailMap.put(user.email, user);
            }

            // phone
            if(phoneMap.containsKey(user.phone)){
                userUnionFind.union(user, phoneMap.get(user.phone));
            }else {
                phoneMap.put(user.phone, user);
            }

            // qq
            if(qqMap.containsKey(user.qq)){
                userUnionFind.union(user, qqMap.get(user.qq));
            }else {
                qqMap.put(user.qq, user);
            }
        }

        return userUnionFind.getSize();
    }

    public static class User {

        String email;

        String phone;

        String qq;

    }

    public static class UnionFind<V> {

        private final Map<V, V> parent = new HashMap<>();
        private final Map<V, Integer> size = new HashMap<>();

        public UnionFind(V[] vs){
            if(vs == null || vs.length == 0){
                return;
            }

            for (V v : vs) {
                parent.put(v, v);
                size.put(v, 0);
            }
        }

        private V findParent(V cur){
            Stack<V> path = new Stack<>();
            while (cur != parent.get(cur)){
                path.push(cur);
                cur = parent.get(cur);
            }

            // 优化并集差
            while (!path.isEmpty()){
                parent.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b){
            return findParent(a) == findParent(b);
        }

        public void union(V a, V b){
            V parentA = findParent(a);
            V parentB = findParent(b);
            if(parentA == parentB){
                return;
            }

            Integer aSize = size.get(a);
            Integer bSize = size.get(a);

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
