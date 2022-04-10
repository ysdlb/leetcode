//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入：prices = [3,3,5,0,0,3,1,4]
//输出：6
//解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2： 
//
// 
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3： 
//
// 
//输入：prices = [7,6,4,3,1] 
//输出：0 
//解释：在这个情况下, 没有交易完成, 所以最大利润为 0。 
//
// 示例 4： 
//
// 
//输入：prices = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁵ 
// 
// Related Topics 数组 动态规划 👍 886 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 设 dp[i][k][s] 三维数组 代表第 i 天进行了 k 次交易, 持股状态为 0 的时候的最大利润
 * <ol>
 *     <li>
 *         i: [0, prices.length - 1], 代表第几天的状态
 *     </li>
 *     <li>
 *         k: 因为只有买入了才能卖出, 所以买入算一次交易 k++, 卖出不算 （ 第 i 天的最大交易次数 )
 *     </li>
 *     <li>
 *         s: 0 代表当天持有股票, 1 代表未持有股票
 *     </li>
 * </ol>
 *
 * 通过 k, s 可知 d[i][0][0] 代表第 i 天从未进行过任何交易, 所以 dp[i][0][0] = 0;
 *
 * 其它初始状态
 * 第一天只有持股和不持股两种选择, 后续决策只能从第一天开始推理
 * dp[0][0][0] = 0;
 * dp[0][1][1] = -prices[0];
 */
class DP123 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;

        int max_k = 2;
        int[][][] dp = new int[prices.length][max_k + 1][2];

        // 当 i = 0 天未持有股票的时候, 利润总是 0
        // 当 i = 0 天持有股票的时候, 利润总是 -prices[0]
        for (int k = 0; k <= max_k; k++) {
            dp[0][k][1] = -prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            // 当 k == 0, s == 0 的时候, dp[i][k][s] = 0
            for (int k = 1; k <= max_k; k++) {
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][max_k][0];
    }
}


class DFA {
    public int maxProfit(int[] prices) {
        int max_k = 2;
        // 最多买了 k 次后最大利润
        int[] buy = new int[max_k+1];
        // 最多卖了 k 次后最大利润
        int[] sell = new int[max_k+1];

        // base case
        for (int k = 0; k <= max_k; k++) {
            buy[k] = Integer.MIN_VALUE;
            sell[k] = 0;
        }

        // 第 i 次的 buy 或 sell 以来第 i - 1 次的 buy 和 sell
        // 第 i 次的 buy 或 sell 不能对 第 i 次产生影响
        // 上面的动态规划不管是从 max_k, k-- 还是 0, k++ 遍历效果都一样
        // 但这里 i 位置的 buy[k] 要依赖 i-1 位置的 sell[k-1]
        // 先计算 buy[k-1], 会使 i 位置的 buy[k] 依赖 i 位置的 sell[k-1]
        for (int i = 0; i < prices.length; i++) {
            // sell[k] <-- buy[k] <-- sell[k-1]
            // 所以先计算无影响的 sell[k]
            for (int k = max_k; k > 0; k--) {
                sell[k] = Math.max(sell[k], buy[k] + prices[i]);
                buy[k] = Math.max(buy[k], sell[k-1] - prices[i]);
            }
        }
        return sell[max_k];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
