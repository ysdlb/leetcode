//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
// 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 3 * 10 ^ 4 
// 0 <= prices[i] <= 10 ^ 4 
// 
// Related Topics 贪心算法 数组 
// 👍 1176 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * 动态规划
 * 股票买入，profit = -price; 股票卖出，profit = price
 * 某一天只有只有股票和卖出股票两种状态。dpy vs dpw 分别代表当天持有股票和卖出股票后的最优利润
 * 那么求 dpy[i], 如果第i-1天持有，则利润为 dpy[i-1]; 如果第i-1天未持有，则第i天需要买入，利润为dpw[i-1] - prices[i];
 * 同理，求 dpw[i]
 */
class DP122 {
    public int maxProfit(int[] prices) {
        int length =prices.length;
        int[] dpw = new int[length], dpy = new int[length];
        dpw[0] = 0; dpy[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dpw[i] = Math.max(dpw[i-1], dpy[i-1] + prices[i]);
            dpy[i] = Math.max(dpy[i-1], dpw[i-1] - prices[i]);
        }
        return dpw[length-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class G122 {
    /**
     * 既然开了上帝视角, 且买卖次数不受限制
     * 那么
     * 只要上涨前夕就应该买入 ( 连续下跌
     * 只要下跌前夕就应该卖出
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length;) {
            while (i < prices.length && prices[i] <= prices[i-1])
                i++;
            int min = prices[i-1];
            while (i < prices.length && prices[i] >= prices[i-1])
                i++;
            int max = prices[i-1];

            profit += max - min;
        }
        return profit;
    }
}

class Simplest122 {
    /**
     * 最简单的解法
     * 只要是趋势向上的相邻节点 (prices[i] > prices[i-1]
     * 都应该计入我的利润
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        }
        return profit;
    }
}
