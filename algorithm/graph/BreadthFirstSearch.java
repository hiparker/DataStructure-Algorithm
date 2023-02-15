package algorithm.graph;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 图 广搜
 *
 * @author 周鹏程
 * @date 2023-02-15 5:47 PM
 **/
public class BreadthFirstSearch {

    public static void main(String[] args) {

        DiGraph diGraph = DiGraph.createRandomDiGraph();

        System.out.println("图顶点数：" + diGraph.getPointLength());
        System.out.println("图边/弧数：" + diGraph.getSideLength());

        // 遍历原始图
        System.out.println("输出：原始图");
        traverseSourceGraph(diGraph);
        System.out.println();


        // 广度优先遍历原始图
        System.out.println("输出：广搜遍历结果");
        bfsTraverseGraph(diGraph);
        System.out.println();



    }

    /**
     * 广搜遍历原始图
     */
    public static void bfsTraverseGraph(DiGraph diGraph){
        if(null == diGraph){
            return;
        }

        if(diGraph.getPointLength() == 0){
            return;
        }

        // 用于阻断 循环遍历
        int[] flag = new int[diGraph.getPointLength()];
        flag[0] = 1;

        System.out.print(diGraph.getPointName(0) + "  ");

        Queue<int[]> indexQueue = new LinkedBlockingQueue<>();
        indexQueue.add(new int[]{0,0});
        while (!indexQueue.isEmpty()){
            int[] pop = indexQueue.poll();
            int i = pop[0];
            int[] ints = diGraph.getGraphArray()[i];

            for (int k = pop[1]; k < ints.length; k++) {
                if(ints[k] == 1 && flag[k] == 0){
                    System.out.print(diGraph.getPointName(k) + "  ");
                    indexQueue.add(new int[]{k,0});
                    flag[k] = 1;
                }
            }
        }
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

            // 创建随机边
            for (int i = 0; i < randomCount; i++) {
                for (int k = 0; k < randomCount; k++) {
                    if(diGraph.getGraphArray()[k][i] == 1){
                        continue;
                    }

                    int sideFlag =
                            i==k
                            ? 0
                            : random.nextInt(2);
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
