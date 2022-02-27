//给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。 
//
// 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/ 
// Related Topics 队列 滑动窗口 单调队列 堆（优先队列） 👍 386 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.Deque;


class SolutionOffer59_2 {
    /**
     * <p>
     *  写的比较早
     * <a href="https://leetcode-cn.com/problems/sliding-window-maximum/">leetcode-239</a>
     * <p>
     * 忘了窗口右缩该删除单调递减队列的哪个元素
     * <a href="https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/">leetcode-offer59-1</a>
     * <p>
     *
     * 因为只有窗口最大的数被挤掉时, 才会对窗口最大值产生影响, 所以优先考虑单调栈
     * 但是栈是先进后出, 窗口是早进早出, 所以用单调队列更合适
     * 维护一个单调递减队列,
     * 窗口右移:
     * 1. 当待加入队列的数比队尾元素大的时候, 删除队尾元素, 直到前面条件不成立为止
     * 将待加入队列数放入队尾
     * 2. 我们不知道真正的队首元素是几 (窗口最左边的元素) (因为单调递减的原因, 真正的队首元素可能早被挤掉了)
     * 但可以肯定的是, 如果窗口最左边元素不等于队首, 说明该左边元素早被挤掉了, 队首元素不需要删除
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[]{};

        MaxWindow window = new MaxWindow();
        int[] ret = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (i >= k) {
                window.remove(nums[i-k]);
            }
            window.add(nums[i]);
            if (i >= k-1) {
                ret[i - (k-1)] = window.max();
            }
        }
        return ret;
    }

    private static class MaxWindow {
        private final Deque<Integer> queue = new ArrayDeque<>();

        public void add(int num) {
            while (!queue.isEmpty() && num > queue.getLast())
                queue.pollLast();
            queue.addLast(num);
        }

        public void remove(int num) {
            if (num == queue.getFirst())
                queue.pollFirst();
        }

        public int max() {
            if (queue.isEmpty())
                throw new RuntimeException();
            return queue.getFirst();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
