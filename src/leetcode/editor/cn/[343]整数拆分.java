//给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。 
//
// 返回 你可以获得的最大乘积 。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1。 
//
// 示例 2: 
//
// 
//输入: n = 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。 
//
// 
//
// 提示: 
//
// 
// 2 <= n <= 58 
// 
// Related Topics 数学 动态规划 👍 831 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution343 {
    /**
     * 用来练一下 DP, 不考虑数学方法
     * 经典 DP：53、152
     *
     * f(i) 表示整数 i, 拆分后的最大乘积; 显然 f(i) 不会依赖 f(i+1)
     *
     * 将 i 拆分成任意的 i-x, x; 可能有四种种结果 (i-x)*x, f(i-x)*x, (i-x)*f(x), f(i-x)*f(x)
     * 因为 i-x, x 随 x 变化对称, 所以可 f(i-x)*x , (i-x)*f(x) 只取一个就可以
     */
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int x = 2; x < i; x++) {
                dp[i] = Math.max(dp[i], (i-x)*x);
                dp[i] = Math.max(dp[i], dp[i-x]*x);
                dp[i] = Math.max(dp[i], dp[i-x]*dp[x]);
            }
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
