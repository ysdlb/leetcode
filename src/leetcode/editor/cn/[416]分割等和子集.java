//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 1186 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <a href="https://leetcode-cn.com/problems/partition-equal-subset-sum/">leetcode-416</a>
 * 494, 698
 */
class Solution416 {
    /**
     * 存在一个 sum/2 容量的背包, 有 N 个物品, 是否存在一种装法, 使背包恰好装满
     * 状态: 第 N 个物品, 背包容量 s
     * 选择: 第 N 个物品装不装
     *
     * 因为对于 N 个物品, 总存在一种装法, 使背包容量为 0
     * 所以 f(N,0) = true
     *
     * 对 0 种物品, 不存在任何装法, 是背包容量为 sum
     * 所以 f(0,s) = false
     * f(0,0) = true
     *
     * f(n,s) =
     *      f(n-1,s);       // 不装
     *   || f(n-1,s-nums[n-1])    // 装, 0-1 背包问题, n 只有一个, 所以不能是 f(n, s-nums[n-1])
     *
     * 结果 f(N,sum/2)
     *
     * 参考 518 零钱兑换，不同的是，它是一个 0-1 背包问题
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num: nums)
            sum += num;
        // 奇数不可能平分
        if (sum % 2 == 1)
            return false;

        int S = sum / 2, N = nums.length;
        boolean[][] dp = new boolean[N+1][S+1];
//        for (int k = 1; k <= S; k++)
//            dp[0][k] = false;
        for (int k = 0; k <= N; k++)
            dp[k][0] = true;

        for (int n = 1; n <= N; n++) {
            for (int s = 1; s <= S; s++) {
                dp[n][s] = dp[n-1][s]; // 不装
                int s0 = s - nums[n-1];
                if (s0 >= 0)
                    dp[n][s] |= dp[n-1][s0];
            }
        }
        return dp[N][S];
    }

    /**
     * 二维压缩到一维度
     */
    public boolean canPartition_space(int[] nums) {
        int sum = 0;
        for (int num: nums)
            sum += num;
        // 奇数不可能平分
        if (sum % 2 == 1)
            return false;

        int S = sum / 2, N = nums.length;
        boolean[] dp = new boolean[S+1];
        // base case
        dp[0] = true;

        for (int n = 1; n <= N; n++) {
//            for (int s = 1; s <= S; s++) {
//                dp[s] = dp[s]; // 不装
//                int s0 = s - nums[n-1];
//                if (s0 >= 0)
//                    dp[s] |= dp[s0];
//            }
            for (int s = S; s >= 0; s--) {
                int s0 = s - nums[n - 1];
                if (s0 >= 0)
                    dp[s] |= dp[s0];
            }
        }
        return dp[S];
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1,5,11,5};
        int[] nums = new int[]{1,2,4,7};
        new Solution416().canPartition(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
