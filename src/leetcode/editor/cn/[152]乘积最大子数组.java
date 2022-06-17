//给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。 
//
// 测试用例的答案是一个 32-位 整数。 
//
// 子数组 是数组的连续子序列。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 
//输入: nums = [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -10 <= nums[i] <= 10 
// nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数 
// 
// Related Topics 数组 动态规划 👍 1688 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution152 {
    /**
     * 类似题目:
     * Offer 42
     * 343
     *
     * 乘法特点, 在不碰到 0 的情况下不断相乘会往偏离 0 轴的方向移动
     * max[n] 表示以 索引 n 结尾的连续子序列的最大乘积
     * min[n] 表示以 索引 n 结尾的连续子序列的最小乘积
     *
     * max[n] = MAX{max[n-1]*nums[n], nums[n], min[n-1]*nums[n])
     * min[n] = MIN{max[n-1]*nums[n], nums[n], min[n-1]*nums[n])
     *
     * 根据 nums[n] 是否大于0, 可简化求 max, min 的逻辑
     *
     * return max 数组中的最大值
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("Invalid param!");

        int ret = Integer.MIN_VALUE;
        int max = 1, min = 1;
        for (int n: nums) {
            int _max = max, _min = min;
            if (n >= 0) {
                max = Math.max(n, _max*n);
                min = Math.min(n, _min*n);
            } else {
                max = Math.max(n, _min*n);
                min = Math.min(n, _max*n);
            }
            ret = Math.max(ret, max);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arg = new int[]{-4,-3,-2};
        Solution152 solution = new Solution152();
        solution.maxProduct(arg);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
