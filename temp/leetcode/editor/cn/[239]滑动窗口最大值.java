//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,-1], k = 1
//输出：[1,-1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [9,11], k = 2
//输出：[11]
// 
//
// 示例 5： 
//
// 
//输入：nums = [4,-2], k = 2
//输出：[4] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1301 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution239 {

    /**
     * 窗口右移一位, 则会移除一个左边元素, 新增一个右边元素
     * 若移除的不是当时窗口的最大值, 则对当时窗口毫无影响, 因为当时最大值不变
     * 若新增对不是当前窗口对最大值, 则也对当前窗口无影响, 因为当前最大值不变
     *
     * 因此我们只保留窗口内从左到右单调不增对按序队列
     * 1. 移除第i个元素时, 如果 nums[i] 为队列最大值之一（队列最左边元素，有重复) 则删掉左边一个元素
     * 2. 新增第i + k 个元素时候, 从右往左把比它小都元素都挤掉 (拍它前面都元素先与它被移除, 且对窗口内最大值无影响)
     *
     * 我们肯定会遍历一遍数组, 并且每个元素最多进出队列一次, 所以时间复杂度是 O(n)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Que queue = new Que(nums.length);
        int[] res = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            // poll 和 offer 的顺序无影响
            if (i >= k)
                queue.poll(nums[i-k]);
            queue.offer(nums[i]);

            // 队列第一次满(窗口第一次准备完成的时候) 开始记录
            if (i >= k - 1)
                res[i-(k-1)] = queue.max();
        }
        return res;
    }

    private static class Que {
        private final Deque<Integer> deque;

        private Que(int size) {
            this.deque = new ArrayDeque<>(size);
        }

        // 移除第i个元素时, 如果 nums[i] 为队列最大值之一（队列最左边元素，有重复) 则删掉左边一个元素
        public void poll(int e) {
            if (deque.getFirst() == e)
                deque.pollFirst();
        }

        // 新增第i + k 个元素时候, 从右往左把比它小都元素都挤掉 (拍它前面都元素先与它被移除, 且对窗口内最大值无影响)
        public void offer(int e) {
            while (!deque.isEmpty() && deque.getLast() < e)
                deque.pollLast();
            deque.offerLast(e);
        }

        public int max() {
            // 先 poll 再 offer, 可以很容易保证队列不为空
            // if (deque.isEmpty())
            //     throw new RuntimeException();
            return deque.getFirst();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
