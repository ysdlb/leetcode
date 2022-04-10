//请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都
//是O(1)。 
//
// 若队列为空，pop_front 和 max_value 需要返回 -1 
//
// 示例 1： 
//
// 输入: 
//["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
//[[],[1],[2],[],[],[]]
//输出: [null,null,null,2,1,2]
// 
//
// 示例 2： 
//
// 输入: 
//["MaxQueue","pop_front","max_value"]
//[[],[],[]]
//输出: [null,-1,-1]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= push_back,pop_front,max_value的总操作数 <= 10000 
// 1 <= value <= 10^5 
// 
// Related Topics 设计 队列 单调队列 👍 319 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * <a href="https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/">leetcode-offer-59-2</a>
 * 单调递减队列
 */
class MaxQueue {
    private final Deque<Integer> maxDeque = new ArrayDeque<>();
    private final Deque<Integer> deque = new ArrayDeque<>();

    public MaxQueue() {
    }
    
    public int max_value() {
        if (maxDeque.isEmpty())
            return -1;
        return maxDeque.getFirst();
    }
    
    public void push_back(int value) {
        while (!maxDeque.isEmpty() && value > maxDeque.getLast()) {
            maxDeque.pollLast();
        }
        maxDeque.offerLast(value);
        deque.offerLast(value);
    }

    /**
     * 滑动窗口中, 我们可以确定要要 pop_front 的元素,
     * 通过该元素与 queue 队首的元素比较来确定该元素是否已经在 push_back 的时候挤掉了
     *
     * 这里没有上述条件, 必须记录 push_back 干掉了之前几个元素 (在不要求返回干掉的元素的情况下)
     * 要求返回元素时, 需要新起一个队列正常返回
     */
    public int pop_front() {
        if (deque.isEmpty())
            return -1;

        int ret = deque.pollFirst();
        if (ret == maxDeque.getFirst())
            maxDeque.pollFirst();
        return ret;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
//leetcode submit region end(Prohibit modification and deletion)
