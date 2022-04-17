//设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。 
//
// 实现 FreqStack 类: 
//
// 
// FreqStack() 构造一个空的堆栈。 
// void push(int val) 将一个整数 val 压入栈顶。 
// int pop() 删除并返回堆栈中出现频率最高的元素。
// 
// 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。 
// 
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：
//["FreqStack","push","push","push","push","push","push","pop","pop","pop",
//"pop"],
//[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
//输出：[null,null,null,null,null,null,null,5,7,5,4]
//解释：
//FreqStack = new FreqStack();
//freqStack.push (5);//堆栈为 [5]
//freqStack.push (7);//堆栈是 [5,7]
//freqStack.push (5);//堆栈是 [5,7,5]
//freqStack.push (7);//堆栈是 [5,7,5,7]
//freqStack.push (4);//堆栈是 [5,7,5,7,4]
//freqStack.push (5);//堆栈是 [5,7,5,7,4,5]
//freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
//freqStack.pop ();//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
//freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
//freqStack.pop ();//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。 
//
// 
//
// 提示： 
//
// 
// 0 <= val <= 10⁹ 
// push 和 pop 的操作数不大于 2 * 10⁴。 
// 输入保证在调用 pop 之前堆栈中至少有一个元素。 
// 
// Related Topics 栈 设计 哈希表 有序集合 👍 226 👎 0


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class FreqStack {

    /**
     * 如果某几个元素第 n 次出现, 那么它们总会 push 进 freq = n 的 stack 中
     * 优先按频率顺序出栈, 同频率下, 按后进先出 出栈
     *
     * <val, freq> valFreqMap + <freq, stack> freqValsMap
     * 要点: 元素出现第 n 次时, 将这个元素加入 freq = n 的 stack 中, 此时 freq = [1...n] 的 stack 中各有一个该元素
     *
     * maxFreq 记录出现次数最多的元素
     *  1. 每次 push 的时候, valFreqMap 会更新该元素新的 freq, 如果大于 maxFreq, 更新 maxFreq
     *  2. 每次 pop 的时候, freqValsMap 中取出 maxFreq 频率的 stack, 弹出栈顶元素后, 如果栈为空, maxFreq--
     *
     */
    public FreqStack() {
    }

    private final Map<Integer, Integer> valFreqMap = new HashMap<>();
    private final Map<Integer, Stack<Integer>> freqValsMap = new HashMap<>();
    private int maxFreq = 0;

    /**
     * 1. 更新频率为 n
     * 2. 在 freq = n 的 stack 中 push 该元素
     * 3. 更新 maxFreq
     */
    public void push(int val) {
        int freq = valFreqMap.compute(val, (key, oldV) -> oldV == null ? 1 : oldV + 1);
        freqValsMap.computeIfAbsent(freq, key -> new Stack<>()).push(val);
        // 其实 maxFreq 每次最多 +1
        maxFreq = Math.max(freq, maxFreq);
    }

    /**
     * 1. 在 freq = maxFreq 的 stack 中弹出栈顶元素
     * 2. 更新频率为 freq - 1
     * 3. 如果 上面的 stack 为空了, 更新 maxFreq,
     * 由于 [1...freq] stack push 的连续性, 可以保证 maxFreq > 0 时, [1...maxFreq] 表示的所有栈都不为空
     */
    public int pop() {
        if (maxFreq <= 0)
            throw new RuntimeException("FreqStack is empty!");
        Stack<Integer> maxFreqStack = freqValsMap.get(maxFreq);
        int ret = maxFreqStack.pop();

        valFreqMap.put(ret, maxFreq-1);

        if (maxFreqStack.isEmpty())
            maxFreq--;

        return ret;
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push (5);//堆栈为 [5]
        freqStack.push (7);//堆栈是 [5,7]
        freqStack.push (5);//堆栈是 [5,7,5]
        freqStack.push (7);//堆栈是 [5,7,5,7]
        freqStack.push (4);//堆栈是 [5,7,5,7,4]
        freqStack.push (5);//堆栈是 [5,7,5,7,4,5]
        freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
        freqStack.pop ();//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
        freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
        freqStack.pop ();//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
//leetcode submit region end(Prohibit modification and deletion)
