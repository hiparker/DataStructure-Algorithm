package algorithm.StackAndQueue;

import structure.StackAndQueue.MyLinkedQueue;
import structure.StackAndQueue.MyQueue;

import java.util.Random;

/**
 * 男女分组 （队列实现）
 * 空间复杂度 O(n)
 * 凑对 时间复杂度 O(1)
 *
 * @author 周鹏程
 * @date 2022-10-10 2:06 PM
 **/
public class GroupOfMenAndWomenByQueue {

    private final MyQueue<String> MAN_QUEUE = new MyLinkedQueue<>();
    private final MyQueue<String> WOMAN_QUEUE = new MyLinkedQueue<>();

    private int count;

    /**
     * 随机入场
     */
    public void randomEntrance(){
        Random random = new Random();
        boolean nextBoolean = random.nextBoolean();
        if(nextBoolean){
            MAN_QUEUE.push(Type.MAN.name() + count++);
        }else {
            WOMAN_QUEUE.push(Type.WOMAN.name() + count++);
        }
    }

    /**
     * 获取男人数量
     */
    public int getManCount(){
        return MAN_QUEUE.length();
    }

    /**
     * 获取女人数量
     */
    public int getWomanCount(){
        return WOMAN_QUEUE.length();
    }

    /**
     * 凑对
     */
    public String getRight(){
        if(MAN_QUEUE.length() > 0
                && WOMAN_QUEUE.length() > 0){
            String pop1 = MAN_QUEUE.pop();
            String pop2 = WOMAN_QUEUE.pop();
            return "凑对成功 [" + pop1+"----"+pop2+"]"
                    +"  剩余男数量: "+getManCount()
                    +"  剩余女数量: "+getWomanCount();
        }
        return "凑对失败"
                +" 剩余男数量: "+getManCount()
                +" 剩余女数量: "+getWomanCount();
    }

    /**
     * 类型
     */
    public enum Type {

        /** 男人 */
        MAN,

        /** 女人 */
        WOMAN,
    }

    public static void main(String[] args) {
        GroupOfMenAndWomenByQueue group = new GroupOfMenAndWomenByQueue();
        for (int i = 0; i < 10; i++) {
            group.randomEntrance();
        }

        System.out.println("数量校对"
                +"  剩余男数量: "+group.getManCount()
                +"  剩余女数量: "+group.getWomanCount());

        for (int i = 0; i < 6; i++) {
            String right = group.getRight();
            System.out.println(right);
        }


    }


}
