//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。 
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。 
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 
//
// 示例 2： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁴ 
// 
// Related Topics 数组 动态规划 👍 1871 👎 0


/**
 * 动态规划
 */
class DP121 {
    /**
     * 假如计划在第 i 天卖出股票
     * 那么最大利润的差值一定是在[0, i-1] 之间选最低点买入
     * 所以遍历数组, 依次求每个卖出时机的的最大差值, 再从中取最大值。
     *
     * 如果 prices[i] < minP, 则改天卖利润一定为负值
     * 所以没必要求差值, 只更新 [0, i] 之间的最小值 minP 即可
     * 该值用于下次在 i+1 的位置求利润 (即差值)
     */
    public int maxProfit(int[] prices) {
        // 在求 profit （差值) 的时候, prices[0, i-1] 之间的最小值
        int minP = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] <= minP)
                minP = prices[i];
            else
                profit = Math.max(prices[i] - minP, profit);
        }
        return profit;
    }
}

/**
 * 分治算法
 */
class DivideAndConquer121 {
    /**
     * 用于表示 prices[i, j] 空间内
     * 最小的值 min
     * 最大的值 max
     * 最大的利润 profit ( 不一定等于 max - min )
     */
    private static class Status {
        int min;
        int max;
        int profit;
        public Status(int min, int max, int profit) {
            this.min = min;
            this.max = max;
            this.profit = profit;
        }
    }

    /**
     * 方法描述: 返回 prices[l, h] 空间内的 Status 信息
     */
    private Status maxProfit(int[] prices, int l, int h) {
        if (l == h)
            return new Status(prices[l], prices[l], 0);

        int mid = (l + h) >> 1;
        Status left = maxProfit(prices, l, mid);
        Status right = maxProfit(prices, mid + 1, h);

        int min = Math.min(left.min, right.min);
        int max = Math.max(left.max, right.max);
        int profit = Math.max(right.max - left.min, Math.max(left.profit, right.profit));
        return new Status(min, max, profit);
    }

    public int maxProfit(int[] prices) {
        return maxProfit(prices, 0, prices.length - 1).profit;
    }
}
