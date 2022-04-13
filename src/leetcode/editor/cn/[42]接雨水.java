//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= height[i] <= 10⁵ 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 3336 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution42 {
    /**
     * 双指针描述一个窗口, 如果当前右边界小于左边界, 那么右边界扩张; 否则 (>=) 处理这个窗口;
     *      左边界就是水深, 水深与窗口内柱子高度的差之和就是当前窗口的容量
     * 处理完窗口后, 窗口收缩, 左边界移动到右边界位置
     *
     * 特殊情况, 右边界移动到数组尾部的时候, 可能不满足右边界大于等于左边界, 此时窗口水深就是左右边界的较小值
     *
     * 上述描述不对, 无法找全所有的池子, 池子一定是先下降, 后上升; 更复杂的是, 池子的底部还可能是有高有低的
     * 我们很可能把底部的池子误算为独立的池子
     *
     * 上面的路子走窄了, 这道题应该是对每一个柱子, 它可以盛的水为 min{leftMax, rightMax} - height
     * 然后重点是求每个柱子的 leftMax 和 rightMax, 求完所有的柱子的时间复杂度为 O(n^2)
     *
     * 我们可以采用预计算的方式, 从左到右不断更新最大值, 记录 maxLeft, 同理从右往左不断更新最大值, 记录 maxRight
     * 这样求所有柱子的时间复杂度为 O(n)
     *
     * 高难版 407
     */
    public int trap(int[] height) {
        int[] maxRight = new int[height.length];
        int max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            if (max < height[i]) {
                max = height[i];
            }
            maxRight[i] = max;
        }

        int maxL = 0;
        int ret = 0;
        for (int i = 0; i < height.length; i++) {
            if (maxL < height[i]) {
                maxL = height[i];
            }

            ret += Math.min(maxL, maxRight[i]) - height[i];
        }

        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
