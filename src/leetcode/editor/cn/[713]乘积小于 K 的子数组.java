//给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,5,2,6], k = 100
//输出：8
//解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
//需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3], k = 0
//输出：0 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// 1 <= nums[i] <= 1000 
// 0 <= k <= 10⁶ 
// 
//
// Related Topics 数组 滑动窗口 👍 686 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution713 {
    /* 713. 乘积小于 K 的子数组: https://leetcode.cn/problems/subarray-product-less-than-k/
     * 滑动窗口相似题目:
     *  209. 长度最小的子数组: https://leetcode.cn/problems/minimum-size-subarray-sum/
     *  2106.摘水果: https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps/     * 字串, 不是子序列
     *
     * 滑动窗口,
     * 每次窗口右移动一位, 调整左边界符合 < k 的条件
     * 新增符合题目要求的子数组(连续)数目为 len
     * len = r-l+1    l > r 时, len = 0
     *
     * [10,5,2,6]  k=100
     * [10] 1
     * [5] 1
     * [5,2] 2
     * [5,2,6] 3
     *
     * 同 209, 也可用前缀和+二分来做，只是时间复杂度略高，N*lgN
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int product = 1;
        int ans = 0;
        for (int l = 0, r = 0; r < nums.length;) {
            product *= nums[r];
            while (l <= r && product >= k) {
                product /= nums[l];
                l++;
            }
            ans += r-l+1;
            r++;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
