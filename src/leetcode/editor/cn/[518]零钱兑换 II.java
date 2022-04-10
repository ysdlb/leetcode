//给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。 
//
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。 
//
// 假设每一种面额的硬币有无限个。 
//
// 题目数据保证结果符合 32 位带符号整数。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：amount = 5, coins = [1, 2, 5]
//输出：4
//解释：有四种方式可以凑成总金额：
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
// 
//
// 示例 2： 
//
// 
//输入：amount = 3, coins = [2]
//输出：0
//解释：只用面额 2 的硬币不能凑成总金额 3 。
// 
//
// 示例 3： 
//
// 
//输入：amount = 10, coins = [10] 
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 300 
// 1 <= coins[i] <= 5000 
// coins 中的所有值 互不相同 
// 0 <= amount <= 5000 
// 
// Related Topics 数组 动态规划 👍 746 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * <a href="https://leetcode-cn.com/problems/coin-change/">leetcode-322</a>
 * <a href="https://leetcode-cn.com/problems/coin-change-2/">leetcode-518</a>
 */
class Solution518 {
    /**
     * 状态: 当前已经凑够的总金额 n
     * 设 f(n) 为凑够 总金额 n 所有方式的数量
     * 选择: 是否可以从 f(n-coin) 所表示的方式 加一个 coin 面额的硬币 凑过来
     * f(n) = f(n-coin) + 1
     *
     * 问题 凑够 n-coin1 面额 + coin1 的方式里可能和
     * 凑够 n-coin2 面额 + coin2 的方式里重复
     * 比如 n = 9, coin1 = 1, coin2 = 2
     * f(8) + 1 存在 3个2，2个1, 1 个 1 的情况
     * f(7) + 2 存在 2 个 2, 3 个 1, 一个 2 的情况
     * 由于无法消除重复影响, 这样的方法不可行
     *
     * 设 f(i,n) 为用前 i 枚硬币凑够 金额n 所有方式的数量
     * 状态: 第i枚硬币, 总金额 n
     * 选择: 是否要将 第i枚 硬币装进背包
     * 可以从 f(i, n-coin[i-1]) 所表示的方式 加一个 coin 面额的硬币 凑过来 (装)
     * 也可以从 f(i-1, n) 一步到位凑过来 (不装)
     *
     *  f(i,n) = f(i,n-coin[i-1]) + f(i-1,n)
     *
     *  base case
     *  f(0,N) = 0;     0 枚硬币啥都凑不出来
     *  f(I,0) = 1;     >0 枚硬币总有一个方法可以凑出金额 0
     */
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount+1];
        // f(0,N) = 0;     0 枚硬币啥都凑不出来
        for (int k = 0; k <= amount; k++)
            dp[0][k] = 0;
        // f(I,0) = 1;     >0 枚硬币总有一个方法可以凑出金额 0
        for (int k = 1; k <= coins.length; k++)
            dp[k][0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            for (int n = 1; n <= amount; n++) {
                dp[i][n] = dp[i-1][n];
                int n0 = n - coins[i-1];
                if (n0 >= 0)
                    dp[i][n] += dp[i][n0];
            }
        }
        return dp[coins.length][amount];
    }

    public static void main(String[] args) {
        int[] argv1 = new int[]{1,2,5};
        new Solution518().change(5, argv1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
