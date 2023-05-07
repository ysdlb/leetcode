//给你一个 events 数组，其中 events[i] = [startDayi, endDayi, valuei] ，表示第 i 个会议在 
//startDayi 天开始，第 endDayi 天结束，如果你参加这个会议，你能得到价值 valuei 。同时给你一个整数 k 表示你能参加的最多会议数目。 
//
// 你同一时间只能参加一个会议。如果你选择参加某个会议，那么你必须 完整 地参加完这个会议。会议结束日期是包含在会议内的，也就是说你不能同时参加一个开始日期与
//另一个结束日期相同的两个会议。 
//
// 请你返回能得到的会议价值 最大和 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
//输出：7
//解释：选择绿色的活动会议 0 和 1，得到总价值和为 4 + 3 = 7 。 
//
// 示例 2： 
//
// 
//
// 
//输入：events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
//输出：10
//解释：参加会议 2 ，得到价值和为 10 。
//你没法再参加别的会议了，因为跟会议 2 有重叠。你 不 需要参加满 k 个会议。 
//
// 示例 3： 
//
// 
//
// 
//输入：events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
//输出：9
//解释：尽管会议互不重叠，你只能参加 3 个会议，所以选择价值最大的 3 个会议。 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= events.length 
// 1 <= k * events.length <= 10⁶ 
// 1 <= startDayi <= endDayi <= 10⁹ 
// 1 <= valuei <= 10⁶ 
// 
//
// Related Topics 数组 二分查找 动态规划 排序 👍 77 👎 0


import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1751 {
    /* 最多可以参加的会议数目 II: https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended-ii/
     * 同题目:
     *   1235.规划兼职工作: https://leetcode.cn/problems/maximum-profit-in-job-scheduling/
     *   2008.出租车的最大盈利: https://leetcode.cn/problems/maximum-earnings-from-taxi/
     * 因为限制了最多 k 个，这三个背包问题里最难的就是它
     *
     * 背包:
     * 按结束时间排序, 对每一个会议，选择参加或者不参加
     * 设 f(i) 为前 i 个会议价值的最大和, (profit 索引从 0 开始)
     * f(i) = max{f(i-1), f(x)+profit[i-1]}
     * x 为在 events[i-1] 之前 (小于，不能有重合) 的会议数量, 因为有序，可通过二分查找得到
     *
     * 不同的是，最多限制了 k 个会议可参加
     * f(i,z) = max{f(i-1,z), f(x,z-1)+profit[i-1]}, 1<=z<=k; 1<=i<=len
     * f(i,z) = max{f(i,z), f(i,z-1) 若现实情况前 i 个会议无法完成 z 个会议的情况, 因为总是从 z 小的方向遍历，所以对正确结果没有影响
     */
    public int maxValue(int[][] events, int k) {
        if (events == null || events.length == 0)
            throw new RuntimeException("events can not be null or empty");

        Arrays.sort(events, Comparator.comparing(event -> event[1]));

        int[][] dp = new int[events.length+1][k+1];
        for (int i = 1; i <= events.length; i++) {
            int[] event = events[i-1];
            int x = searchX(events, event[0], i-1);

            for (int z = 1; z <= k; z++) {
                // 不参加 or 参见
                dp[i][z] = Math.max(dp[i-1][z], dp[x][z-1] + event[2]);
            }
        }
        return dp[events.length][k];
    }

    /**
     * @return 在 end 之前的会议数量
     */
    private int searchX(int[][] events, int end, int right) {
        int i = 0, j = right;
        while (i < j) {
            int mid = (i + j) >> 1;
            int[] event = events[mid];

            if (event[1] >= end)
                j = mid;
            else
                i = mid + 1;
        }
        return i;
    }
}
//leetcode submit region end(Prohibit modification and deletion)