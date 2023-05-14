//给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改
// 数组以供接下来的操作使用。 
//
// 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,4,2,3], x = 5
//输出：2
//解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [5,6,7,8,9], x = 4
//输出：-1
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,2,20,1,1,3], x = 10
//输出：5
//解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁴ 
// 1 <= x <= 10⁹ 
// 
//
// Related Topics 数组 哈希表 二分查找 前缀和 滑动窗口 👍 282 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1658 {
    /* 1658.将 x 减到 0 的最小操作数: https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
     * 滑动窗口相似题目:
     *  2106.摘水果: https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps/     * 字串, 不是子序列
     *
     * 动态规划涉及的状态太多，时间复杂度会很高
     * 不管怎么选，中间剩下的总是连续子序列, 我们求值等于 sum-x 的最长连续子序列
     */
    public int minOperations(int[] nums, int x) {
        int target = -x;
        for (int n: nums) target += n;

        if (target < 0)
            return -1;
        if (target == 0)
            return nums.length;

        int sum = 0;
        int maxLen = 0;
        for (int l = 0, r = 0; r < nums.length;) {
            sum += nums[r];
            while (l <= r && sum > target) {
                sum -= nums[l];
                l++;
            }
            if (sum == target) {
                maxLen = Math.max(maxLen, r-l+1);
            }
            r++;
        }
        return maxLen == 0 ? -1 : nums.length - maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
