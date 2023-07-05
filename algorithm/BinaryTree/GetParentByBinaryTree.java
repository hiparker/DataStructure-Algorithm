package algorithm.BinaryTree;

import java.util.*;

/**
 * 给定一棵二叉树的头节点head，和另外两个节点a和b
 * 返回a和b的最低公共祖先 (附加条件 a b 必在这棵树上)
 *
 * @author 周鹏程
 * @date 2023-07-04 2:25 PM
 **/
public class GetParentByBinaryTree extends AbstractBinaryTree{

    public static void main(String[] args) {
        GetParentByBinaryTree getParentByBinaryTree = new GetParentByBinaryTree();
        boolean isOk = true;
        int maxCount = 100;
        int count = 100_0000;
        for (int i = 0; i < count; i++) {
            Node node = genBinaryTree(5, maxCount);
            if(null == node){
                continue;
            }

            Node a = pickRandomOne(node);
            Node b = pickRandomOne(node);
            if(getParentByBinaryTree.getLowestParentNode1(node, a, b) != getParentByBinaryTree.getLowestParentNode2(node, a, b)){
                isOk = false;
                break;
            }
        }

        System.out.println("对数器 => " + (isOk?"一致":"不一致"));

//        Node root = new Node(5);
//
////        Node l1 = new Node(4);
////        root.left = l1;
//
//        Node r1 = new Node(6);
//        root.right = r1;
////
////        Node l11 = new Node(44);
////        l1.left = l11;
////
////        Node l12 = new Node(55);
////        l1.right = l12;
////
////        Node r11 = new Node(66);
////        r1.left = r11;
////
////        Node r12 = new Node(77);
////        r1.right = r12;
//
//        //l1 r12
//
//        printBinaryTree(root);
//        System.out.println("返回a和b的最低公共祖先");
//        Node parent = getParentByBinaryTree.getLowestParentNode2(root, root, r1);
//        System.out.println(parent.data);
    }

    public Node getLowestParentNode1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }
    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }


    private static class Info {

        private boolean isFindA;
        private boolean isFindB;
        private Node firstHead;

        public Info(boolean isFindA, boolean isFindB, Node firstHead) {
            this.isFindA = isFindA;
            this.isFindB = isFindB;
            this.firstHead = firstHead;
        }
    }

    private Node getLowestParentNode2(Node root, Node a, Node b) {
        if(root == null){
            return null;
        }

        Info info = process2(root, a, b);
        return info.firstHead;
    }

    private Info process2(Node root, Node a, Node b) {
        if(root == null){
            return new Info(false, false, null);
        }
        Info leftInfo = process2(root.left, a, b);
        Info rightInfo = process2(root.right, a, b);

        boolean isFindA = root == a || leftInfo.isFindA || rightInfo.isFindA;
        boolean isFindB = root == b || leftInfo.isFindB || rightInfo.isFindB;

        Node firstHead = null;
        if(leftInfo.firstHead != null){
            firstHead = leftInfo.firstHead;
        }else if(rightInfo.firstHead != null){
            firstHead = rightInfo.firstHead;
        }else {
            if(isFindA && isFindB){
                firstHead = root;
            }
        }

        return new Info(isFindA, isFindB, firstHead);
    }


    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }
    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }


}
