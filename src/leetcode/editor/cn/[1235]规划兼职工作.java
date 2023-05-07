//你打算利用空闲时间来做兼职工作赚些零花钱。 
//
// 这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。 
//
// 给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。 
//
// 注意，时间上出现重叠的 2 份工作不能同时进行。 
//
// 如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
//输出：120
//解释：
//我们选出第 1 份和第 4 份工作， 
//时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
// 
//
// 示例 2： 
//
// 
//
// 输入：startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60
//]
//输出：150
//解释：
//我们选择第 1，4，5 份工作。 
//共获得报酬 150 = 20 + 70 + 60。
// 
//
// 示例 3： 
//
// 
//
// 输入：startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
//输出：6
// 
//
// 
//
// 提示： 
//
// 
// 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4 
// 1 <= startTime[i] < endTime[i] <= 10^9 
// 1 <= profit[i] <= 10^4 
// 
//
// Related Topics 数组 二分查找 动态规划 排序 👍 345 👎 0


import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1235 {
    /* 规划兼职工作: https://leetcode.cn/problems/maximum-profit-in-job-scheduling/
     * 重叠区间相似题目:
     *     452.用最少的数量的箭射爆气球: https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
     *     986.区间列表的交集: https://leetcode.cn/problems/interval-list-intersections/
     * 相同题目:
     *     1751.最多可以参加的会议数目 II: https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended-ii/
     *     2008.出租车的最大盈利: https://leetcode.cn/problems/maximum-earnings-from-taxi/
     *
     * 对于覆盖区间/区间怎么合并的问题, 一般是左升右降排序来处理, 当前结束是否大于下一个的开始, 不相交 or 相交/覆盖
     * 对于重叠区间问题，一般是右升排序, 重点看结束时间, 方便二分定位当前起始位置前的区间
     *
     * 思路
     * 按 endTime 升序, 共计 n 个 job, 组成数组 jobs
     * 设 f(i) 为前 i 个工作可得的最大报酬, i 属于 [1, n]
     * 对第 i 个工作, 我们总有两种选择 (jobs 索引从 0 开始)
     *   1. 不参加 jobs[i-1], f(i) = f(i-1)
     *   2. 参加 jobs[i-1], f(i) = f(k) + profit[i-1]
     *     k 为在时间范围 [0, startTime[i]] 之间的任务数量,
     *     因为 jobs 按结束时间有序，所以就是符合 endTime <= startTIme[i] 条件的最后一个元素的索引 + 1
     *     第一个符合 endTime > startTime[i] 条件的索引更合适 (符合 <= 条件不存在的话，任务数量应该为 0, 不好处理)
     * 时间复杂度 O(lgn*n)
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        Arrays.sort(jobs, Comparator.comparing(e -> e[1]));

        int[] dp = new int[n+1];
        // dp[0] = 0;

        for(int i = 1; i <= n; i++) {
            int[] job = jobs[i-1];
            int k = this.searchKMore(jobs, i-1, job[0]);
            dp[i] = Math.max(dp[i-1], dp[k] + job[2]);
        }
        return dp[n];
    }

    /* 符合 endTime > startTime[i] 条件的索引
     * 求做边界，需要向下取整 */
    private int searchKMore(int[][] jobs, int right, int endTime) {
        int i = 0, j = right;
        while (i < j) {
            int mid = (i + j) >> 1;
            int[] job = jobs[mid];
            if (job[1] > endTime) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    /* 符合 endTime <= startTIme[i] 条件的最后一个元素的索引 + 1
     * 这里求相等的右边界, 所以需要向上取整
     * 符合条件的时候自动向右走 */
    private int searchKLess(int[][] jobs, int endTime) {
        int i = 0, j = jobs.length - 1;
        while (i < j) {
            int mid = (i + j + 1) >> 1;
            int[] job = jobs[mid];
            if (job[1] <= endTime) {
                i = mid;
            } else {
                j = mid - 1;
            }
        }
        return jobs[i][1] <= endTime ? i+1 : 0;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
