//如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数
//值排序之后中间两个数的平均值。 
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例 1： 
//
// 输入：
//["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
//[[],[1],[2],[],[3],[]]
//输出：[null,null,null,1.50000,null,2.00000]
// 
//
// 示例 2： 
//
// 输入：
//["MedianFinder","addNum","findMedian","addNum","findMedian"]
//[[],[2],[],[3],[]]
//输出：[null,null,2.00000,null,2.50000] 
//
// 
//
// 限制： 
//
// 
// 最多会对 addNum、findMedian 进行 50000 次调用。 
// 
//
// 注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-
//stream/ 
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 302 👎 0


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder_Offer41 {

    /**
     * 同题参考: 295
     */
    public MedianFinder_Offer41() {
    }

    private final PriorityQueue<Integer> maxStack = new PriorityQueue<>();
    private final PriorityQueue<Integer> minStack = new PriorityQueue<>((n1, n2) -> n2 - n1);

    /**
     * java 优先级队列默认是顶部是最小值，这里写错了，但阴差阳错
     * 两个 stack 数量相等时，值最终放到了 maxStack 里面去
     * 不知道为啥对了，留存在看
     */
    public void addNum(int num) {
        // 加入这个后是偶数
        if (maxStack.size() != minStack.size()) {
            maxStack.add(num);
            minStack.add(maxStack.poll());
        } else { // 加入这个后是奇数
            minStack.add(num);
            maxStack.add(minStack.poll());
        }
    }
    
    public double findMedian() {
        return maxStack.size() != minStack.size() ?
                maxStack.peek() : (maxStack.peek() + minStack.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder_Offer41 offer41 = new MedianFinder_Offer41();
        offer41.addNum(1);
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
