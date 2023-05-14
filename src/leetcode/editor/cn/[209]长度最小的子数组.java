//给定一个含有 n 个正整数的数组和一个正整数 target 。 
//
// 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长
//度。如果不存在符合条件的子数组，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：target = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
// 
//
// 示例 2： 
//
// 
//输入：target = 4, nums = [1,4,4]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：target = 11, nums = [1,1,1,1,1,1,1,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= target <= 10⁹ 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁵ 
// 
//
// 
//
// 进阶： 
//
// 
// 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。 
// 
//
// Related Topics 数组 二分查找 前缀和 滑动窗口 👍 1704 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution209 {
    /* 209. 长度最小的子数组: https://leetcode.cn/problems/minimum-size-subarray-sum/
     * 滑动窗口相似题目:
     *  2106.摘水果: https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps/     * 字串, 不是子序列
     *
     * 涉及连续子数组的问题，我们通常有两种思路：一是滑动窗口、二是前缀和。
     * 前缀和加二分可以做到 O(n*lgn)
     * 区间和且不包含负数, 优先用滑动窗口
     */
    public int minSubArrayLen(int target, int[] nums) {
        int len = Integer.MAX_VALUE;
        int sum = 0;
        for (int l = 0, r = 0; r < nums.length;) {
            sum += nums[r];
            while (l <= r && sum-nums[l] >= target) {
                sum -= nums[l++];
            }
            if (sum >= target)
                len = Math.min(len, r-l+1);
            r++;
        }
        return sum >= target ? len : 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
