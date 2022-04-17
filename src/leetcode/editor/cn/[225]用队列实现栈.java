//请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。 
//
// 实现 MyStack 类： 
//
// 
// void push(int x) 将元素 x 压入栈顶。 
// int pop() 移除并返回栈顶元素。 
// int top() 返回栈顶元素。 
// boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。 
// 
//
// 
//
// 注意： 
//
// 
// 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。 
// 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MyStack", "push", "push", "top", "pop", "empty"]
//[[], [1], [2], [], [], []]
//输出：
//[null, null, null, 2, 2, false]
//
//解释：
//MyStack myStack = new MyStack();
//myStack.push(1);
//myStack.push(2);
//myStack.top(); // 返回 2
//myStack.pop(); // 返回 2
//myStack.empty(); // 返回 False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= x <= 9 
// 最多调用100 次 push、pop、top 和 empty 
// 每次调用 pop 和 top 都保证栈不为空 
// 
//
// 
//
// 进阶：你能否仅用一个队列来实现栈。 
// Related Topics 栈 设计 队列 👍 490 👎 0


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class MyStack {

    /**
     * 用队列实现栈, 每次 pop 出的元素一定是最后一次 push 进的元素, 即队列最里面的哪个元素
     *
     * push 跟 pop & top 总有一组的时间复杂度为 O(n)
     * 我们选 push 为 O(n), 每次从队尾 push 进一个元素之后, 我们总是把之前在
     * 队列里的元素一次队首出队, 然后队尾入队, 这样可以保障从队首到队尾, 总是按栈的顺序排列的
     *
     * 栈 -> 队列：<a href="https://leetcode-cn.com/problems/implement-queue-using-stacks/">leetcode-232</a>
     */
    public MyStack() {

    }

    private final Queue<Integer> queue = new LinkedList<>();
    
    public void push(int x) {
        int count = queue.size();
        queue.offer(x);
        for (int i = 0; i < count; i++)
            queue.offer(queue.poll());
    }
    
    public int pop() {
        if (queue.isEmpty())
            throw new RuntimeException("stack is empty!");
        return queue.poll();
    }
    
    public int top() {
        if (queue.isEmpty())
            throw new RuntimeException("stack is empty!");
        return queue.peek();
    }
    
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)
