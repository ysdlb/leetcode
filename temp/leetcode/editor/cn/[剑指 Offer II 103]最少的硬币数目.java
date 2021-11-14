//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：coins = [1], amount = 1
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：coins = [1], amount = 2
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 2³¹ - 1 
// 0 <= amount <= 10⁴ 
// 
//
// 
//
// 注意：本题与主站 322 题相同： https://leetcode-cn.com/problems/coin-change/ 
// Related Topics 广度优先搜索 数组 动态规划 👍 7 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class RecurseOffer103 {
    /**
     * 如果硬币面额为 k1, k2, ... kk
     * 那么定义一个函数 f(n)
     * 代表凑齐 n 元需要的最少硬币数量, 则可以推出:
     *      如果最后一次拿的 k1 元, 那么总数就是 f(n-k1) + 1
     *      如果最后一次拿的 k2 元, 那么总数就是 f(n-k2) + 1
     *      如果最后一次拿的 ki 元, 那么总数就是 f(n-ki) + 1
     *
     * 则 f(n) = min{f(n-ki) + 1}
     *
     * 时间复杂度 2^n
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 0)
            return -1;
        if (amount == 0)
            return 0;

        /*
         * 返回最小的正整数
         * 如果没有, 则返回 -1
         */
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int num = coinChange(coins, amount - coin) + 1;
            if (num > 0 && num < res)
                res = num;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}

class RecurseRememberOffer103 {
    /**
     * 如果硬币面额为 k1, k2, ... kk
     * 那么定义一个函数 f(n)
     * 代表凑齐 n 元需要的最少硬币数量, 则可以推出:
     *      如果最后一次拿的 k1 元, 那么总数就是 f(n-k1) + 1
     *      如果最后一次拿的 k2 元, 那么总数就是 f(n-k2) + 1
     *      如果最后一次拿的 ki 元, 那么总数就是 f(n-ki) + 1
     *
     * 则 f(n) = min{f(n-ki) + 1}
     *
     * 记忆化递归 coins.length * amount
     */
    public int coinChange(int[] coins, int amount) {
        int[] r = new int[amount + 1];
        return coinChange(coins, amount, r);
    }
    private int coinChange(int[] coins, int amount, int[] remember) {
        if (amount < 0)
            return -1;
        if (amount == 0)
            return 0;

        // 记忆化
        if (remember[amount] != 0)
            return remember[amount];

        /*
         * 返回最小的正整数
         * 如果没有, 则返回 -1
         */
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int num = coinChange(coins, amount - coin, remember) + 1;
            if (num > 0 && num < res)
                res = num;
        }

        res = res == Integer.MAX_VALUE ? -1 : res;
        // 记忆化
        remember[amount] = res;
        return res;
    }

    private static class Demo {
        public static void main(String[] args) {
            int[] coins = new int[]{2};
            new RecurseRememberOffer103().coinChange(coins, 11);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class DPOffer103 {
    /**
     * 如果硬币面额为 k1, k2, ... kk
     * 那么定义一个函数 f(n)
     * 代表凑齐 n 元需要的最少硬币数量, 则可以推出:
     * 如果最后一次拿的 k1 元, 那么总数就是 f(n-k1) + 1
     * 如果最后一次拿的 k2 元, 那么总数就是 f(n-k2) + 1
     * 如果最后一次拿的 ki 元, 那么总数就是 f(n-ki) + 1
     * <p>
     * 则 f(n) = min{f(n-ki) + 1}
     * <p>
     * 时间复杂度 2^n
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            /*
             * 返回最小的正整数
             * 如果没有, 则返回 -1
             */
            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                int num = this.get(dp, i - coin) + 1;
                if (num > 0 && num < res)
                    res = num;
            }
            dp[i] = res == Integer.MAX_VALUE ? -1 : res;
        }
        return dp[amount];
    }

    /**
     * 特殊的 dp[index]
     * 当 index 无意义时, 返回 -1
     */
    private int get(int[] dp, int index) {
        if (index < 0)
            return -1;
        else
            return dp[index];
    }

    private static class Demo {
        public static void main(String[] args) {
            int[] coins = new int[]{2};
            new DPOffer103().coinChange(coins, 11);
        }
    }
}

class DPOfferPlus103 {
    /**
     * 如果硬币面额为 k1, k2, ... kk
     * 那么定义一个函数 f(n)
     * 代表凑齐 n 元需要的最少硬币数量, 则可以推出:
     * 如果最后一次拿的 k1 元, 那么总数就是 f(n-k1) + 1
     * 如果最后一次拿的 k2 元, 那么总数就是 f(n-k2) + 1
     * 如果最后一次拿的 ki 元, 那么总数就是 f(n-ki) + 1
     * <p>
     * 则 f(n) = min{f(n-ki) + 1}
     * <p>
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int magicDefinite = Integer.MAX_VALUE - 1;
        Arrays.fill(dp, magicDefinite);

        dp[0] = 0;
        for (int coin: coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == magicDefinite ? -1 : dp[amount];
    }

    private static class Demo {
        public static void main(String[] args) {
            int[] coins = new int[]{2};
            new DPOfferPlus103().coinChange(coins, 11);
        }
    }
}
