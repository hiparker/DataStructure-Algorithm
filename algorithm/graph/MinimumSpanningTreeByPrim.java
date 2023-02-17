package algorithm.graph;

import structure.Tree.ArrayTree;
import structure.Tree.ChildBroTree;
import structure.Tree.Tree;

import java.util.*;

/**
 * 图 最小生成树 prim算法
 *           6              5
 *     |----------【V1】----------|
 *     |    5       |        5   |
 *   【V2】-----     |  1   ----【V4】
 *     |      |     |      |     |
 *   3 |      |     |      |     |  2
 *     | |----|---【V3】---|----| |
 *     | | 6               4   | |
 *   【V5】---------------------【V6】
 *                  6
 *
 *  最短路径为   V1
 *             V3
 *          V2    V6
 *        v5        V4
 *
 * 对于一个带权连通无向图G=(V,E)，生成树不同，每棵树的权（树中所有边上的权值和）也不同，设R为G的所有生成树的集合，若T为R中权值和最小的生成树，则T称为G的最小生成树（Minimum-Spanning-Tree，MST）
 * 注：
 * 1、最小生成树可能有多个，但边的权值之和总是唯一且最小的
 * 2、最小生成树的边数=定点数-1，砍掉一条则不连通，增加一条则会出现回路
 * 3、若一个连通图本身就是一棵树，则其最小生成树就是它本身
 * 4、只有连通图才有生成树，非连通图只有生成森林
 *
 * Prim算法
 * 从某一个顶点（所以存在多个最小生成树）开始构建生成树，每次将代价最小的新顶点纳入生成树，直到所有顶点都纳入为止
 *
 *
 * @author 周鹏程
 * @date 2023-02-13 6:18 PM
 **/
public class MinimumSpanningTreeByPrim {

    public static void main(String[] args) {

        DiGraph diGraph = DiGraph.createRandomDiGraph();

        System.out.println("图顶点数：" + diGraph.getPointLength());
        System.out.println("图边/弧数：" + diGraph.getSideLength());

        // 遍历原始图
        System.out.println("输出：原始图");
        traverseSourceGraph(diGraph);
        System.out.println();


        // 创建最小生成树
        System.out.println("创建最小生成树");
        Tree minimumSpanningTree = createMinimumSpanningTree(diGraph);
        if(null != minimumSpanningTree){
            minimumSpanningTree.traversal();
        }
        System.out.println();
    }

    /**
     * 创建最小生成树
     */
    public static Tree createMinimumSpanningTree(DiGraph diGraph){
        if(null == diGraph){
            return null;
        }

        if(diGraph.getPointLength() == 0){
            return null;
        }


        // 最小生成树集合
        List<int[]> minimumSpanningTreeList = new ArrayList<>();

        for (int j = 0; j < diGraph.getPointLength(); j++) {
            // 最小生成树集合
            minimumSpanningTreeList.clear();
            // 初始化索引
            int[] initIndex = new int[]{j,j,0};
            // 剩余点集合
            int residuePointLength = diGraph.getPointLength();
            // 剩余点状态
            int[] residuePointStatus = new int[diGraph.getPointLength()];
            for (int i = 0; i < diGraph.getPointLength(); i++) {
                residuePointStatus[i] = 0;
            }


            while (residuePointLength > 0){
                if(minimumSpanningTreeList.isEmpty()){
                    minimumSpanningTreeList.add(initIndex);
                    residuePointStatus[j] = 1;
                    residuePointLength--;
                    continue;
                }

                int[][] graphArray = diGraph.getGraphArray();
                // 第一位是行 第二位是列 第三位是值
                int[] minWeight = null;
                // 循环获取当前节点内 已知点得 最短边
                for (int i = 0; i < minimumSpanningTreeList.size(); i++) {
                    int[] pointArray = minimumSpanningTreeList.get(i);
                    int[] ints = graphArray[pointArray[1]];
                    for (int k = 0; k < ints.length; k++) {
                        if(ints[k] == 0){
                            continue;
                        }

                        // 判断最小边
                        boolean isMin = residuePointStatus[k] == 0 &&
                                (null == minWeight || ints[k] < minWeight[2]);
                        if(isMin){
                            if(null == minWeight){
                                minWeight = new int[3];
                            }

                            int parentIndex = i != 0
                                    ? minimumSpanningTreeList.get(i)[1]
                                    : i;
                            minWeight[0] = parentIndex;
                            minWeight[1] = k;
                            minWeight[2] = ints[k];
                        }
                    }
                }
                if(null != minWeight){
                    minimumSpanningTreeList.add(minWeight);
                    residuePointStatus[minWeight[1]] = 1;
                }
                residuePointLength--;
            }


            // 找到正确的最小生成树
            if(diGraph.getPointLength() == minimumSpanningTreeList.size()){
                break;
            }
        }

        // 找到正确的最小生成树
        if(diGraph.getPointLength() != minimumSpanningTreeList.size()){
            System.out.println("没找到最小生成树");
            return null;
        }

        return createTree(diGraph, minimumSpanningTreeList);
    }

    /**
     * 创建树
     *
     * @param diGraph 图
     * @param minimumSpanningTreeList 最小生成树集合
     * @return Tree
     */
    private static Tree createTree(DiGraph diGraph, List<int[]> minimumSpanningTreeList){
        // 创建树
        Tree tree = new ArrayTree();
        // 树头
        // 第一位是行 第二位是列 第三位是值
        // 第一位是父节点索引 第二位是自身索引 第三位忽略

        List<ArrayTree.LeafNode> leafNodeList = new ArrayList<>();
        for (int[] ints : minimumSpanningTreeList) {
            ArrayTree.LeafNode leafNode = new ArrayTree.LeafNode();
            if(ints[0] == ints[1]){
                leafNode.setData(diGraph.getPointName(ints[1]));
                leafNodeList.add(leafNode);
                continue;
            }

            // 子节点加入父节点
            String parentPointName = diGraph.getPointName(ints[0]);
            leafNode.setData(diGraph.getPointName(ints[1]));
            for (ArrayTree.LeafNode node : leafNodeList) {
                if(node.getData().equals(parentPointName)){
                    node.addChild(leafNode);
                    break;
                }
            }
            leafNodeList.add(leafNode);
        }

        tree.init(leafNodeList.get(0));

        return tree;
    }

    /**
     * 遍历原始图
     */
    public static void traverseSourceGraph(DiGraph diGraph){
        if(null == diGraph){
            return;
        }

        int[][] graphArray = diGraph.getGraphArray();
        System.out.print("    ");
        for (int i = 0; i < graphArray.length; i++) {
            System.out.print(diGraph.getPointName(i)+"  ");
        }
        System.out.println();

        for (int i = 0; i < graphArray.length; i++) {
            int[] ints = graphArray[i];

            System.out.print(diGraph.getPointName(i)+"   ");
            for (int anInt : ints) {
                System.out.print(anInt + "   ");
            }
            System.out.println();
        }

    }


    /**
     * 有向图
     */
    private static class DiGraph {

        // 顶点数组
        private String[] pointArray;

        // 图数组
        private int[][] graphArray;



        /**
         * 创建图
         */
        public static DiGraph createRandomDiGraph(){
            DiGraph diGraph = new DiGraph();

            Random random = new Random();
            int randomCount = random.nextInt(6);

            diGraph.pointArray = new String[randomCount];
            diGraph.graphArray = new int[randomCount][randomCount];

            for (int i = 0; i < randomCount; i++) {
                diGraph.getPointArray()[i] = "D" + i;
            }

            // 乱序排列
            for (int i = diGraph.getPointLength() - 1; i >= 0; i--) {
                int index = (int) (Math.random()*i);
                String temp = diGraph.getPointArray()[index];
                diGraph.getPointArray()[index] =  diGraph.getPointArray()[i];
                diGraph.getPointArray()[i] = temp;
            }

            // 指定边
            for (int i = 0; i < randomCount; i++) {
                for (int k = 0; k < randomCount; k++) {
                    if(diGraph.getGraphArray()[k][i] >= 1){
                        continue;
                    }

                    int sideFlag =
                            i==k
                            ? 0
                            : random.nextInt(5);
                    diGraph.getGraphArray()[i][k] = sideFlag;
                }
            }

            return diGraph;
        }

        /**
         * 获取顶Name
         * @return int
         */
        public String getPointName(int index){
            return pointArray[index];
        }

        /**
         * 获取顶点个数
         * @return int
         */
        public int getPointLength(){
            return pointArray.length;
        }

        /**
         * 获取边的个数
         * @return int
         */
        public int getSideLength(){
            if (null == graphArray){
                return 0;
            }

            int totalSideCount = 0;
            for (int[] sideArray : graphArray) {
                int countTemp = 0;
                for (int i : sideArray) {
                    countTemp += i;
                }
                totalSideCount += countTemp;
            }
            return totalSideCount;
        }


        /**
         * 获取顶点数组
         * @return int[]
         */
        public String[] getPointArray() {
            return pointArray;
        }

        /**
         * 获取顶点与弧数组
         * @return int[]
         */
        public int[][] getGraphArray() {
            return graphArray;
        }


    }

}
