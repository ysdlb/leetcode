//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 1356 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution96 {
    /**
     * 类似题 95
     * 返回 n 个不同的数可以构成多少种二叉树
     */
    public int numTrees(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;

        int res = 0;
        // 因为 [1, n] 有序, 那么随意选一个做根节点,
        for (int i = 0; i < n; i++)
            res += numTrees(i) * numTrees(n - 1 - i);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class DP96 {
    /**
     * 动态规划
     * dp[i] = dp[0]*dp[i-1] + ... + dp[i-1]*dp[0]
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int res = 0;
            for (int j = 0; j < i; j ++) {
                res += dp[j] * dp[i-j-1];
            }
            dp[i] = res;
        }
        return dp[n];
    }
}
