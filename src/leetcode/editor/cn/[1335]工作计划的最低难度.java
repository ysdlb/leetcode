//你需要制定一份 d 天的工作计划表。工作之间存在依赖，要想执行第 i 项工作，你必须完成全部 j 项工作（ 0 <= j < i）。 
//
// 你每天 至少 需要完成一项任务。工作计划的总难度是这 d 天每一天的难度之和，而一天的工作难度是当天应该完成工作的最大难度。 
//
// 给你一个整数数组 jobDifficulty 和一个整数 d，分别代表工作难度和需要计划的天数。第 i 项工作的难度是 jobDifficulty[i]。
// 
//
// 返回整个工作计划的 最小难度 。如果无法制定工作计划，则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：jobDifficulty = [6,5,4,3,2,1], d = 2
//输出：7
//解释：第一天，您可以完成前 5 项工作，总难度 = 6.
//第二天，您可以完成最后一项工作，总难度 = 1.
//计划表的难度 = 6 + 1 = 7 
// 
//
// 示例 2： 
//
// 输入：jobDifficulty = [9,9,9], d = 4
//输出：-1
//解释：就算你每天完成一项工作，仍然有一天是空闲的，你无法制定一份能够满足既定工作时间的计划表。
// 
//
// 示例 3： 
//
// 输入：jobDifficulty = [1,1,1], d = 3
//输出：3
//解释：工作计划为每天一项工作，总难度为 3 。
// 
//
// 示例 4： 
//
// 输入：jobDifficulty = [7,1,7,1,7,1], d = 3
//输出：15
// 
//
// 示例 5： 
//
// 输入：jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
//输出：843
// 
//
// 
//
// 提示： 
//
// 
// 1 <= jobDifficulty.length <= 300 
// 0 <= jobDifficulty[i] <= 1000 
// 1 <= d <= 10 
// 
//
// Related Topics 数组 动态规划 👍 118 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1335 {

    /* 1335.工作计划的最低难度: https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule/
     * 相似题目: dp?
     *
     * 题目要求 d 天恰好完成 n 份任务, 不能完不成, 每天也不能闲着
     *
     * 设 f(i,j) 为 j 天完成 i 份任务的最低难度; 1<=j<=d; j<=i<=n(每天至少要完成 1 项任务)
     * min[a,b] 表示从第 a+1 份任务到 b+1 份任务的最小值
     *  f(i,j) = min{f(j-1,j-1)+min[j-1,i-1], f(j,j-1)+min[j,i-1], f(i-1,j-1)+min[i-1,i-1]}
     *   ==> f(i,j) = min{f(k,j-1)+max[k,i-1]} j-1<=k<=i-1
     *
     * 对于 f(i,j) 的递推公式，j 层只依赖 j-1 层，所以二维可以改成一维
     * 指定 j 的每个 f(i) 需要 O(n), 这一层的 f(i) 需要 O(n*n)
     *
     * 每次找最小值的时间复杂度 O(n)
     * 总时间复杂度为 O(n*n*n * d)
     * 我们从后往前推进，区间最小值可以均摊到 O(1) 时间复杂度, 总时间复杂度为 O(n*n*d)
     *
     * 留存，单调栈的空间复杂度可以做到更低, O(n*d)
     */
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        int[] f = new int[n+1];

        // f(0,j) j>0 不可达，应当返回 -1
        Arrays.fill(f, Integer.MAX_VALUE/2);
        f[0] = 0;

        for (int j = 1; j <= d; j++) {
            // 因为大的 i 依赖 小的 i，所以先计算大的
            // 确保每一轮都遍历完全部元素
            for (int i = n; i >= 1; i--) {
                // 此时是新的一层 j, f(i,j) 不可达，预先设置为最大值
                // 而且最小值取值里也不包含 f[i] 本身
                f[i] = Integer.MAX_VALUE/2;
                // i < j 不可达
                if (i < j) continue;

                int max = Integer.MIN_VALUE;
                // 第 j-1 天，至少要完成 j-1 个任务
                // 反推，均摊找最小值的复杂度
                for (int k = i-1; k >= j-1; k--) {
                    max = Math.max(max, jobDifficulty[k]);
                    f[i] = Math.min(f[i], f[k]+max);
                }
            }
        }

        return f[n] >= Integer.MAX_VALUE/2 ? -1 : f[n];
    }

    public static void main(String[] args) {
        Solution1335 so = new Solution1335();
        {
            int[] ars = new int[]{6, 5, 4, 3, 2, 1};
            int i = so.minDifficulty(ars, 2);
            System.out.println(i);
        }
        {
            int[] ars = new int[]{9,9,9};
            int i = so.minDifficulty(ars, 4);
            System.out.println(i);
        }
        {
            int[] ars = new int[]{7,1,7,1,7,1};
            int i = so.minDifficulty(ars, 3);
            System.out.println(i);
        }
        {
            int[] ars = new int[]{11,111,22,222,33,333,44,444};
            int i = so.minDifficulty(ars, 6);
            System.out.println(i);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
