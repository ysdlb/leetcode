//给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 2, prices = [2,4,1]
//输出：2
//解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。 
//
// 示例 2： 
//
// 
//输入：k = 2, prices = [3,2,6,5,0,3]
//输出：7
//解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 
//。 
//
// 
//
// 提示： 
//
// 
// 0 <= k <= 100 
// 0 <= prices.length <= 1000 
// 0 <= prices[i] <= 1000 
// 
// Related Topics 数组 动态规划 👍 587 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution188 {
    public int maxProfit(int k, int[] prices) {
        // 最多买了 k 次后最大利润
        int[] buy = new int[k+1];
        // 最多卖了 k 次后最大利润
        int[] sell = new int[k+1];

        // base case
        for (int _k = 0; _k <= k; _k++) {
            buy[_k] = Integer.MIN_VALUE;
            sell[_k] = 0;
        }

        // dp

        // 第 i 次的 buy 或 sell 以来第 i - 1 次的 buy 和 sell
        // 第 i 次的 buy 或 sell 不能对 第 i 次产生影响
        // 上面的动态规划不管是从 max_k, k-- 还是 0, k++ 遍历效果都一样
        // 但这里 i 位置的 buy[k] 要依赖 i-1 位置的 sell[k-1]
        // 先计算 buy[k-1], 会使 i 位置的 buy[k] 依赖 i 位置的 sell[k-1]
        for (int i = 0; i < prices.length; i++) {
            for(int _k = k; _k > 0; _k--) {
                sell[_k] = Math.max(sell[_k], buy[_k] + prices[i]);
                buy[_k] = Math.max(buy[_k], sell[_k - 1] - prices[i]);
            }
        }
        return sell[k];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
