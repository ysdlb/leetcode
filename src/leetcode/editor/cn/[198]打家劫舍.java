//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 数组 动态规划 👍 1926 👎 0


import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution198 {
    /* 198.打家劫舍: https://leetcode.cn/problems/house-robber/
     * 相似题:
     *  2560.打家劫舍 IV: https://leetcode.cn/problems/house-robber-iv/
     *
     * 小偷的状态有两个
     *   1. 目前偷到了第几家房子
     *   2. 上一家偷了还是没有偷 (这一家偷还是不偷更合适)
     *
     * 然后它的选择
     *   偷到的金额最高
     *
     * f(n,0) 表示偷到第 n 家且未偷第 n 家的情况下可以偷到的最高金额
     * f(n,1) 表示偷到第 n 家且偷了第 n 家的情况下可以偷到的最高金额
     *
     * f(0,0) = f(0,1) = 0;
     * f(n,0) = Math.max{f(n-1, 0), f(n-1, 1)}
     * f(n,1) = f(n-1, 0) + nums(n)
     */
    public int rob(int[] nums) {
        int[][] dp = new int[nums.length + 1][2];
        for (int i = 1; i <= nums.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i-1]; // nums 从 0 开始
        }
        return Math.max(dp[nums.length][0], dp[nums.length][1]);
    }

    /**
     * 空间优化
     */
    public int rob_plus(int[] nums) {
        int steal = 0, notSteal = 0;
        for (int num : nums) {
            int notStealCopy = notSteal;
            notSteal = Math.max(steal, notStealCopy);
            steal = notStealCopy + num;
        }
        return Math.max(steal, notSteal);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
