//给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//输出：6
//解释：[1,1,1,0,0,1,1,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 6。 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
//输出：10
//解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 10。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// nums[i] 不是 0 就是 1 
// 0 <= k <= nums.length 
// 
//
// Related Topics 数组 二分查找 前缀和 滑动窗口 👍 546 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1004 {

    /* 1004.最大连续1的个数 III: https://leetcode.cn/problems/max-consecutive-ones-iii/
     * 滑动窗口相似题目:
     *  2106.摘水果: https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps/     * 字串, 不是子序列
     *
     * 区间和不涉及负数，优先用滑动窗口解决
     *
     * 在保证窗口 0 的数量不找过 k 个的情况下，求最大的窗口大小
     */
    public int longestOnes(int[] nums, int k) {
        int width = 0;
        int count = 0;
        for (int l = 0, r = 0; r < nums.length;) {
            if (nums[r] == 0) count++;

            while (l <= r && count > k) {
                if (nums[l] == 0)
                    count--;
                l++;
            }
            width = Math.max(width, r-l+1);
            r++;
        }
        return width;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
