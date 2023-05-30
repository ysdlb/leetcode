//你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。 
//
// 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
//输出：[20,24]
//解释： 
//列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
//列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
//列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
// 
//
// 示例 2： 
//
// 
//输入：nums = [[1,2,3],[1,2,3],[1,2,3]]
//输出：[1,1]
// 
//
// 
//
// 提示： 
//
// 
// nums.length == k 
// 1 <= k <= 3500 
// 1 <= nums[i].length <= 50 
// -10⁵ <= nums[i][j] <= 10⁵ 
// nums[i] 按非递减顺序排列 
// 
//
// 
//
// Related Topics 贪心 数组 哈希表 排序 滑动窗口 堆（优先队列） 👍 402 👎 0


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution632 {
    /* 632.最小区间: https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists/
     * 相似题目:
     *  373.查找和最小的 K 对数字: https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/
     * 相似滑动窗口解法:
     *  76.最小覆盖子串: https://leetcode.cn/problems/minimum-window-substring/
     *
     * 优先区间范围更小, 其次区间位置靠左.
     * 数组每行非递减, 有什么用呢?
     *
     * 看了题解:
     * 题目等价于从 n 行数中各取一个数, 使得它们的最大值和最小值的差最小
     *
     * 两种解法
     *  1. n 行合并为一行, 用滑动窗口来做, 类似 76 题
     *  2. 贪心, 利用行有序, 依次按游标从每一行依次取值, 不断去除最小值, 放入改行的下一个值 (隐含窗口收缩扩张)
     *
     * 贪心思路 (类似 373 题)
     * 每次都将当前区间中最小的元素丢弃，换成其原始数组中的下一个元素。
     * 就是说，如果当前我们从每个区间中选取的元素分别是 a1_1,a2_1,a3_1...ak_1
     *   (前面的数字代表来自第几个数组，后面的数字表示该数是该数组的第几个元素),
     * 若此时最小的元素是 ai_1，最大元素是 aj_1 ，那么区间就为 [ai_1, aj_1]，
     * 区间中最小元素是 ai_1,那么我们就将 ai_1 丢弃,将 ai_2 拿出来放进去。
     *
     * 如果不换 ai_1 ,那么区间的起点就一直是 ai_1 ,而且区间长度不可能缩小，区间长度取决于起点和终点，而终点是不可能变小的。
     * 因为我们是从小到大进行元素的选取，我们每次丢弃一个元素，就要选择它在原数组中的下一位，而原数组是升序排列的。故终点不可能减小。
     * 那么，我们只能够通过让起点增大来是区间缩小，即每次丢弃最小的元素，换成它原数组的下一个元素。
     * 然后再计算当前所选区间的长度，如果小于之前的区间长度就更新即可。
     *
     * 结束条件: 任意一行达到该行终点
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        // {列索引, 行索引}
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> nums.get(e[0]).get(e[1])));

        int l = 0, r = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            minHeap.offer(new int[]{i, 0});
            right = Math.max(right, nums.get(i).get(0));
        }

        for (;;) {
            // 窗口收缩
            int[] minE = minHeap.poll();
            int x = minE[0], y = minE[1];
            int left = nums.get(x).get(y);
            if (right - left < r - l) {
                l = left;
                r = right;
            }

            // 任意一行到达末端, 结束
            if (++y >= nums.get(x).size())
                break;

            // 窗口右扩
            right = Math.max(right, nums.get(x).get(y));
            minHeap.offer(new int[]{x, y});
        }
        return new int[]{l, r};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
