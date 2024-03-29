package structure.Tree;

/**
 * 树的抽象接口
 *
 * @author 周鹏程
 * @date 2022-11-15 5:32 PM
 **/
public interface Tree {

    /**
     * 遍历树
     *
     * @param head 头节点
     */
    void init(ILeafNode head);

    /**
     * 遍历树
     */
    void traversal();

    interface ILeafNode{

    }

}
