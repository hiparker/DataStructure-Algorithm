package algorithm.BinaryTree;

/**
 * 给定一棵二叉树的头节点head，判断最大两点距离
 *
 * @author 周鹏程
 * @date 2023-07-04 7:04 PM
 **/
public class GetMaxDistanceByBinaryTree extends AbstractBinaryTree {

    public static void main(String[] args) {
        GetMaxDistanceByBinaryTree getMaxDistanceByBinaryTree = new GetMaxDistanceByBinaryTree();


        Node root = new Node(5);

        Node l1 = new Node(4);
        root.left = l1;

        Node r1 = new Node(6);
        root.right = r1;

        Node l11 = new Node(44);
        l1.left = l11;

        Node l12 = new Node(55);
        l1.right = l12;

        Node r11 = new Node(66);
        r1.left = r11;

        Node r12 = new Node(77);
        //r1.right = r12;

        printBinaryTree(root);
        System.out.println("二叉树的最大两点距离");
        System.out.println(getMaxDistanceByBinaryTree.maxDistance2(root));

    }


    public int maxDistance2(Node head) {
        return process2(head).maxDistance;
    }

    private static class Info {

        private final int height;
        private final int maxDistance;

        public Info(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }
    private Info process2(Node head){
        if(head == null){
            return new Info(0, 0);
        }
        Info leftInfo = process2(head.left);
        Info rightInfo = process2(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height)+1;

        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height+rightInfo.height+1;
        int maxDistance = Math.max(
                Math.max(p1, p2),
                p3
        );

        return new Info(height, maxDistance);
    }

}
