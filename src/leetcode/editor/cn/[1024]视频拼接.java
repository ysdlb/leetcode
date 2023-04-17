//你将会获得一系列视频片段，这些片段来自于一项持续时长为 time 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。 
//
// 使用数组 clips 描述所有的视频片段，其中 clips[i] = [starti, endi] 表示：某个视频片段开始于 starti 并于 
//endi 结束。 
//
// 甚至可以对这些片段自由地再剪辑： 
//
// 
// 例如，片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。 
// 
//
// 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, time]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1
// 。 
//
// 
//
// 示例 1： 
//
// 
//输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
//输出：3
//解释：
//选中 [0,2], [8,10], [1,9] 这三个片段。
//然后，按下面的方案重制比赛片段：
//将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
//现在手上的片段为 [0,2] + [2,8] + [8,10]，而这些覆盖了整场比赛 [0, 10]。
// 
//
// 示例 2： 
//
// 
//输入：clips = [[0,1],[1,2]], time = 5
//输出：-1
//解释：
//无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
// 
//
// 示例 3： 
//
// 
//输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],
//[2,6],[3,4],[4,5],[5,7],[6,9]], time = 9
//输出：3
//解释： 
//选取片段 [0,4], [4,7] 和 [6,9] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= clips.length <= 100 
// 0 <= starti <= endi <= 100 
// 1 <= time <= 100 
// 
// Related Topics 贪心 数组 动态规划 👍 264 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1024 {
    /* 视频拼接: https://leetcode.cn/problems/video-stitching/
     * 相似题:
     *   56.合并区间: https://leetcode-cn.com/problems/merge-intervals/
     *   1288.被删除的覆盖区间: https://leetcode.cn/problems/remove-covered-intervals/
     *   986.区间列表的交集: https://leetcode.cn/problems/interval-list-intersections/
     * 困难，区间带权重
     *   1235.规划兼职工作: https://leetcode.cn/problems/maximum-profit-in-job-scheduling/
     *
     * 合并所需最少区间
     * 关注指定边界的下面所有边界的最右边界
     *
     * 区间按照起点升序，终点降序
     * 那么从前到后的区间, 只有覆盖, 相交, 完全不相交三种情况
     *
     * 贪心思路:
     * 在保证没有完全不想交的情况
     *   尽可能利用相交区间（所有相交区间里取右边界最右的)
     * 时间复杂度为 O(n*lgN)
     *
     * 留存两种思路
     *   1. dp[time]   O(time*n)
     *   2. 贪心预处理   O(time+n)
     * 由于 time 固定且范围不大，可以不用排序，
     * 用差不多类似基数排序的思想，预处理下 clips 同左边界下的最右边界
     * [0,0] 找到最右边界 n1
     * ==> (0,n1] 找到最右边界 n2
     * ==> (n1,n2] 找到最右边界 n3
     * 依次类推
     */
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (n1, n2) -> {
            if (n1[0] == n2[0])
                return n2[1] - n1[1];
            return n1[0] - n2[0];
        });

        // 确保 left 从 0 开始
        if (clips[0][0] != 0)
            return -1;

        int ret = 0;
        int right = 0, nextRight = 0;
        for (int i = 0; i < clips.length;) {
            // 一旦出现完全不相交, 返回 -1
            if (right < clips[i][0])
                break;
            // 找到 right 下面的最右边界
            while (i < clips.length && right >= clips[i][0]) { // 相交 可以和覆盖一起处理
                nextRight = Math.max(nextRight, clips[i][1]);
                i++;
            }
            ret++;
            right = nextRight;
            if (right >= time)
                return ret;
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[][] argv = new int[][]{{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
        int[][] argv = new int[][]{{0,1},{1,2},{8,10},{1,9},{1,5},{5,9}};
        new Solution1024().videoStitching(argv, 10);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
