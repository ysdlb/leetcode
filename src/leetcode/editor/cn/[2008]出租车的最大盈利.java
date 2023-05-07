//你驾驶出租车行驶在一条有 n 个地点的路上。这 n 个地点从近到远编号为 1 到 n ，你想要从 1 开到 n ，通过接乘客订单盈利。你只能沿着编号递增的方
//向前进，不能改变方向。 
//
// 乘客信息用一个下标从 0 开始的二维数组 rides 表示，其中 rides[i] = [starti, endi, tipi] 表示第 i 位乘客需要从
//地点 starti 前往 endi ，愿意支付 tipi 元的小费。 
//
// 每一位 你选择接单的乘客 i ，你可以 盈利 endi - starti + tipi 元。你同时 最多 只能接一个订单。 
//
// 给你 n 和 rides ，请你返回在最优接单方案下，你能盈利 最多 多少元。 
//
// 注意：你可以在一个地点放下一位乘客，并在同一个地点接上另一位乘客。 
//
// 
//
// 示例 1： 
//
// 输入：n = 5, rides = [[2,5,4],[1,5,1]]
//输出：7
//解释：我们可以接乘客 0 的订单，获得 5 - 2 + 4 = 7 元。
// 
//
// 示例 2： 
//
// 输入：n = 20, rides = [[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]
//
//输出：20
//解释：我们可以接以下乘客的订单：
//- 将乘客 1 从地点 3 送往地点 10 ，获得 10 - 3 + 2 = 9 元。
//- 将乘客 2 从地点 10 送往地点 12 ，获得 12 - 10 + 3 = 5 元。
//- 将乘客 5 从地点 13 送往地点 18 ，获得 18 - 13 + 1 = 6 元。
//我们总共获得 9 + 5 + 6 = 20 元。 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10⁵ 
// 1 <= rides.length <= 3 * 10⁴ 
// rides[i].length == 3 
// 1 <= starti < endi <= n 
// 1 <= tipi <= 10⁵ 
// 
//
// Related Topics 数组 二分查找 动态规划 排序 👍 61 👎 0


import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2008 {
    /* 出租车的最大盈利: https://leetcode.cn/problems/maximum-earnings-from-taxi/
     *
     * 同题目: 1235.规划兼职工作: https://leetcode.cn/problems/maximum-profit-in-job-scheduling/
     *
     * 1. 直接考虑每段行程的利润 ride[1]
     * 2. 然后按结束位置升序排列
     * 设前 i 段行程的利润为 f(i)
     * 则对第 i 段行程，总有两种选择，拉或者不拉
     *   f(i) = max{f(i-1), f(k)+profit[i-1])
     * k 为第 i 段前的行程数量
     *
     * 时间复杂度 O(n*lgn)
     */
    public long maxTaxiEarnings(int n, int[][] rides) {
        if (rides == null || rides.length == 0)
            throw new RuntimeException("param illegal");

        Arrays.sort(rides, Comparator.comparing(ride -> ride[1]));
        long[] dp = new long[rides.length + 1];
        // dp[0] = 0;
        for (int i = 1; i <= rides.length; i++) {
            int[] ride = rides[i-1];
            int profit = ride[2] + (ride[1]-ride[0]);

            int k = this.searchK(rides, ride[0], i-1);
            dp[i] = Math.max(dp[i-1], dp[k]+profit);
        }
        return dp[rides.length];
    }

    /**
     * @return 在 end 的行程数量
     * 等价于第一个 > end 行程的索引 (省去 <= end 不存在的麻烦)
     */
    private int searchK(int[][] rides, int end, int right) {
        int i = 0, j = right;
        while (i < j) {
            int mid = (i + j) >> 1;
            int[] ride = rides[mid];
            if (ride[1] > end) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Solution2008 solution = new Solution2008();
        int[][] arr = new int[][]{{2,3,4},{4,5,1}};
        solution.maxTaxiEarnings(5, arr);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
