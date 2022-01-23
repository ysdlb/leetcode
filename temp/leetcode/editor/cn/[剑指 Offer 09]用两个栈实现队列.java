//用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的
//功能。(若队列中没有元素，deleteHead 操作返回 -1 ) 
//
// 
//
// 示例 1： 
//
// 输入：
//["CQueue","appendTail","deleteHead","deleteHead"]
//[[],[3],[],[]]
//输出：[null,null,3,-1]
// 
//
// 示例 2： 
//
// 输入：
//["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
//[[],[],[5],[2],[],[]]
//输出：[null,-1,null,null,5,2]
// 
//
// 提示： 
//
// 
// 1 <= values <= 10000 
// 最多会对 appendTail、deleteHead 进行 10000 次调用 
// 
// Related Topics 栈 设计 队列 👍 414 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 如果做到平均时间复杂度为 O(1)
 * 入栈只入 stack1
 * 出栈只出 stack2
 *   a. 如果 stack2 不为空, 出栈 stack2
 *   b. 如果 stack2 为空, 那么把 stack1 所有元素出栈再填入 stack2 （保证后进后出的特性)
 *
 * 用两个队列实现一个栈, 无法做到平均时间复杂度 O(1)
 *
 * 入队列: 只能入有元素的那个队列
 * 出队列: 把有元素队列里出最后入的那个元素外全部出队再入另一个空队列, 然后把剩下的那个元素出队列
 *
 * 两个队列反复切换
 */
class CQueue {

    private final Stack<Integer> stack1;

    private final Stack<Integer> stack2;

    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void appendTail(int value) {
        stack1.push(value);
    }
    
    public int deleteHead() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        }
        return stack2.isEmpty() ? -1 : stack2.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
//leetcode submit region end(Prohibit modification and deletion)
