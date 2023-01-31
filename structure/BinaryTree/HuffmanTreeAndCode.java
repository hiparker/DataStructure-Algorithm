package structure.BinaryTree;

import java.util.*;

/**
 * 哈夫曼树和编码
 * 重点在数据的压缩(直接压缩成二进制 无损压缩)
 *
 * @author 周鹏程
 * @date 2023-01-30 4:58 PM
 **/
public class HuffmanTreeAndCode {


    /** 默认根节点索引 */
    private static final int DEF_ROOT_INDEX = -1;
    /** 默认头（防止成为单树） */
    private static final String DEF_HEAD = ",;";
    /** 字典  */
    private final Map<Character, String> dict = new HashMap<>();
    /** 哈夫曼树结构 */
    private Map<Character, Integer> huffmanTreeStructure;
    /** 哈夫曼树数组 */
    private List<LeafNode> leafNodes;
    /** 头节点 */
    private LeafNode head;


    /**
     * 创建哈夫曼树
     */
    public synchronized void init(String text){
        if(huffmanTreeStructure != null){
            return;
        }

        // 创建哈夫曼结构基础
        huffmanTreeStructure = createHuffmanTreeStructure(DEF_HEAD + text);

        // 最大长为 2n-1
        leafNodes = new ArrayList<>();

        // 初始化数据
        for (Map.Entry<Character, Integer> entry : huffmanTreeStructure.entrySet()) {
            LeafNode leafNode = new LeafNode();
            leafNode.setParent(DEF_ROOT_INDEX);
            leafNode.setlChild(DEF_ROOT_INDEX);
            leafNode.setrChild(DEF_ROOT_INDEX);
            leafNode.setWeight(entry.getValue());
            leafNode.setDate(entry.getKey());

            leafNodes.add(leafNode);
        }

        // 获取最小的两位根节点
        List<LeafNode> minLeafNodeByTwo = getMinLeafNodeByTwo(leafNodes);
        while (null != minLeafNodeByTwo){
            // 合并节点
            mergeNode(minLeafNodeByTwo);

            if(minLeafNodeByTwo.size() == 1){
                break;
            }

            // 更新最小节点
            minLeafNodeByTwo = getMinLeafNodeByTwo(leafNodes);
        }


        // 处理翻译字典
        for (LeafNode leafNode : leafNodes) {
            // 只处理叶子节点
            if(DEF_ROOT_INDEX != leafNode.getlChild()
                || DEF_ROOT_INDEX != leafNode.getrChild()){
                continue;
            }

            LeafNode currNode = leafNode;
            List<String> highway = new ArrayList<>();
            while (DEF_ROOT_INDEX != currNode.getParent()){
                LeafNode parentNode = leafNodes.get(currNode.getParent());
                if(leafNodes.indexOf(currNode) == parentNode.getlChild()){
                    highway.add("0");
                }else {
                    highway.add("1");
                }
                currNode = parentNode;
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = highway.size() - 1; i >= 0; i--) {
                stringBuilder.append(highway.get(i));
            }
            dict.put(leafNode.getDate(), stringBuilder.toString());
        }
    }

    /**
     * 编码输出
     * @param text 字符串
     */
    public String encodeToString(String text) {
        if(null == text || "".equals(text)){
            return "";
        }

        String tempText = DEF_HEAD + text;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tempText.length(); i++) {
            String temp = dict.get(tempText.charAt(i));
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * 解码输出
     * @param encodeText 字符串
     */
    public String decodeToString(String encodeText) {
        if(null == encodeText || "".equals(encodeText)){
            return "";
        }

        LeafNode currNode = head;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < encodeText.length(); i++) {
            if('0' == encodeText.charAt(i)){
                currNode = leafNodes.get(currNode.getlChild());
            }else {
                currNode = leafNodes.get(currNode.getrChild());
            }

            if(DEF_ROOT_INDEX == currNode.getlChild()
                    && DEF_ROOT_INDEX == currNode.getrChild()){

                sb.append(currNode.getDate());

                // 复原
                currNode = head;
            }
        }

        return sb.toString().replaceFirst(DEF_HEAD, "");
    }


    /**
     * 先序遍历
     */
    public void preOrder(){
        Stack<LeafNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()){
            LeafNode pop = stack.pop();

            System.out.println(pop.toString());

            if(DEF_ROOT_INDEX != pop.getrChild()){
                stack.push(leafNodes.get(pop.getrChild()));
            }

            if(DEF_ROOT_INDEX != pop.getlChild()){
                stack.push(leafNodes.get(pop.getlChild()));
            }
        }
    }

    /**
     * 合并节点
     * @param minLeafNodeByTwo 最小的两个节点
     */
    private void mergeNode(List<LeafNode> minLeafNodeByTwo){
        if(null == minLeafNodeByTwo || minLeafNodeByTwo.size() == 0){
            return;
        }

        // 如果长度为1 则当前串 只有1个节点
        if(minLeafNodeByTwo.size() == 1){
            head = minLeafNodeByTwo.get(0);
            return;
        }

        int parentIndex = leafNodes.size();
        LeafNode parent = new LeafNode();
        parent.setParent(DEF_ROOT_INDEX);

        int parentWeight = 0;
        for (int i = 0; i < minLeafNodeByTwo.size(); i++) {
            LeafNode leafNode = minLeafNodeByTwo.get(i);
            leafNode.setParent(parentIndex);
            parentWeight += leafNode.getWeight();
            if(i == 0){
                parent.setlChild(leafNodes.indexOf(leafNode));
            }else {
                parent.setrChild(leafNodes.indexOf(leafNode));
            }
        }

        parent.setWeight(parentWeight);
        leafNodes.add(parent);

        head = parent;
    }

    /**
     * 获取两个最小的
     * @param leafNodes 根节点
     * @return List<LeafNode>
     */
    private static List<LeafNode> getMinLeafNodeByTwo(List<LeafNode> leafNodes){
        if(null == leafNodes){
            return null;
        }

        List<LeafNode> rootList = new ArrayList<>();
        for (LeafNode leafNode : leafNodes) {
            if(DEF_ROOT_INDEX != leafNode.getParent()){
                continue;
            }

            rootList.add(leafNode);
        }

        LeafNode[] roots = new LeafNode[rootList.size()];
        rootList.toArray(roots);
        for (int i = 0; i < roots.length - 1; i++) {
            for (int j = 0; j < roots.length - 1 - i; j++) {
                if (roots[j].getWeight() > roots[j + 1].getWeight()) {
                    LeafNode temp = roots[j];
                    roots[j] = roots[j + 1];
                    roots[j + 1] = temp;
                }
            }
        }

        if(roots.length <= 0){
            return null;
        }

        List<LeafNode> resultMinByTwo = new ArrayList<>();
        if(roots.length == 1){
            resultMinByTwo.add(roots[0]);
        }else {
            for (int i = 0; i < roots.length; i++) {
                if(i > 1){
                    break;
                }

                resultMinByTwo.add(roots[i]);
            }
        }
        return resultMinByTwo;
    }


    /**
     * 创建哈夫曼树结构
     * @return Map<Character, Integer>
     */
    private static Map<Character, Integer> createHuffmanTreeStructure(String text){
        Map<Character, Integer> huffmanTreeStructure = new HashMap<>(text.length());
        for (int i = 0; i < text.length(); i++) {
            // 获取权重
            int weight = getWeight(text, text.charAt(i));
            huffmanTreeStructure.put(text.charAt(i), weight);
        }
        return huffmanTreeStructure;
    }

    /**
     * 获取权重
     * @param source 原始值
     * @param curr 当前 字符
     * @return 权重(int)
     */
    private static int getWeight(String source, char curr){
        int count = 0;
        if(null == source || "".equals(source)){
            return count;
        }

        for (int i = 0; i < source.length(); i++) {
            if(source.charAt(i) == curr){
                count++;
            }
        }
        return count;
    }


    /**
     * 叶子节点
     */
    private static class LeafNode {

        /** 父节点 下标 */
        private int parent;

        /** 左孩子 下标 */
        private int lChild;

        /** 右孩子 下标 */
        private int rChild;

        /** 权重 */
        private int weight;

        /** 数据 */
        private Character date;

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public int getlChild() {
            return lChild;
        }

        public void setlChild(int lChild) {
            this.lChild = lChild;
        }

        public int getrChild() {
            return rChild;
        }

        public void setrChild(int rChild) {
            this.rChild = rChild;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Character getDate() {
            return date;
        }

        public void setDate(Character date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "LeafNode{" +
                    "parent=" + parent +
                    ", lChild=" + lChild +
                    ", rChild=" + rChild +
                    ", weight=" + weight +
                    ", date=" + date +
                    '}';
        }
    }



    public static void main(String[] args) {
        String text = "askjjhgfiqhkhjkbxzasdkgjhgsjasjdhdghgasdasdas";

        HuffmanTreeAndCode huffmanTreeAndCode = new HuffmanTreeAndCode();
        // 初始化哈夫曼树
        huffmanTreeAndCode.init(text);

        // 先序遍历哈夫曼树
        //huffmanTreeAndCode.preOrder();

        // 编码输出
        String encodeToString = huffmanTreeAndCode.encodeToString(text);
        System.out.println("哈夫曼编码（增加特殊识别字符）：" + encodeToString);

        // 解码输出
        String decodeToString = huffmanTreeAndCode.decodeToString(encodeToString);
        System.out.println("解码：" + decodeToString);

    }



}
