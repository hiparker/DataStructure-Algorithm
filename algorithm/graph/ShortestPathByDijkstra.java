package algorithm.graph;

import java.util.Random;

/**
 * 最短路径  （Dijkstra算法实现）
 *
 * @author 周鹏程
 * @date 2023-02-13 6:18 PM
 **/
public class ShortestPathByDijkstra {

    /** 默认步长 */
    private static final int DEF_STEP = 0;

    public static void main(String[] args) {

        DiGraph diGraph = DiGraph.createRandomDiGraph();

        System.out.println("图顶点数：" + diGraph.getPointLength());
        System.out.println("图边/弧数：" + diGraph.getSideLength());

        // 遍历原始图
        System.out.println("输出：原始图");
        traverseSourceGraph(diGraph);
        System.out.println();


        // 获取最短路径
        int startIndex = 0;
        int endIndex = diGraph.getPointLength()-1;
        System.out.println("获取最短路径: ");
        Path shortestPath = getShortestPath(diGraph, startIndex, endIndex);
        if(null == shortestPath){
            System.out.print("未找到最短路径");
        }else {
            System.out.print("从"+diGraph.getPointName(startIndex)+"到"+diGraph.getPointName(endIndex)+
                    "  共长"+shortestPath.getLength()+"  最短路径是："+ shortestPath.getPath());
        }
        System.out.println();
    }

    /**
     * 获取最短路径
     * 时间复杂度 O(n2)
     *
     * @param startIndex 起始点
     * @param endIndex 终点
     */
    public static Path getShortestPath(DiGraph diGraph, int startIndex, int endIndex){
        if(null == diGraph){
            return null;
        }

        if(diGraph.getPointLength() == 0){
            return null;
        }

        if(startIndex < 0 || endIndex > diGraph.getPointLength()-1){
            System.out.println("起始与终点不在合法范围内");
        }


        // 原始图
        int[][] graphArray = diGraph.getGraphArray();

        // 路线长度数组
        int[][] pathCountArray = new int[diGraph.getPointLength()][diGraph.getPointLength()];
        // 路线记录
        // 初始化头数据
        String[][] pathArray = new String[diGraph.getPointLength()][diGraph.getPointLength()];
        for (int i = 0; i < diGraph.getPointLength(); i++) {
            if(graphArray[startIndex][i] == DEF_STEP){
                continue;
            }

            // 初始化头步长
            pathCountArray[startIndex][i] = graphArray[startIndex][i];
            // 初始化头路径
            pathArray[startIndex][i] = diGraph.getPointName(startIndex) + "-> "+diGraph.getPointName(i) + "-> ";
        }


        // 每次处理第二行 则最后一行不会进入循环
        for (int i = 0; i < pathCountArray.length - 1; i++) {
            for (int k = 0; k < pathCountArray[i].length; k++) {
                // 上一次本身就没有匹配到的节点 直接退出
                if(pathCountArray[i][k] == DEF_STEP){
                    pathCountArray[i+1][k] = DEF_STEP;
                    continue;
                }

                // 如果原始节点 小于 或者 等于未初始化状态 则继承上一次步长
                if(pathCountArray[i][k] <= pathCountArray[i+1][k] || DEF_STEP == pathCountArray[i+1][k]){
                    pathCountArray[i+1][k] = pathCountArray[i][k];
                    pathArray[i+1][k] = pathArray[i][k];
                }

                // 如果当前路径能通 则继续看 下一路径能通向哪里
                for (int p = 0; p < graphArray[k].length; p++) {
                    // 本身不通的直接跳过
                    if(graphArray[k][p] == 0){
                        continue;
                    }

                    // 下一点步长
                    int nextStep = pathCountArray[i][k] + graphArray[k][p];

                    // 如果当前路线节点 小于 已知通路 则更新为当前这条路径
                    if(nextStep < pathCountArray[i+1][p] || DEF_STEP == pathCountArray[i+1][p]){
                        pathCountArray[i+1][p] = nextStep;
                        // 下一路径
                        pathArray[i+1][p] = pathArray[i][k] + diGraph.getPointName(p) + "-> ";
                    }
                }
            }
        }


        int length = pathCountArray[pathCountArray.length - 1][endIndex];
        if(DEF_STEP == length){
            return null;
        }

        Path path = new Path();
        path.setLength(length);
        path.setPath(pathArray[pathArray.length-1][endIndex]);
        return path;
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
     * 路径和长度
     */
    private static class Path {

        private int length;

        private String path;

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
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
            int randomCount = random.nextInt(10);

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
