//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
// Related Topics 数组 排序 👍 1363 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 同
 * <a href="https://leetcode-cn.com/problems/remove-covered-intervals/">leetcode-1288</a>
 *
 */
class Solution56 {

    /* 合并区间: https://leetcode-cn.com/problems/merge-intervals/
     * 几乎相同
     *   1288.被删除的覆盖区间: https://leetcode.cn/problems/remove-covered-intervals/
     * 相似题:
     *   986.区间列表的交集: https://leetcode.cn/problems/interval-list-intersections/
     *  最小区间数量:
     *   1024.视频拼接: https://leetcode.cn/problems/video-stitching/
     *
     * 困难，区间带权重
     *   1235.规划兼职工作: https://leetcode.cn/problems/maximum-profit-in-job-scheduling/
     *
     * 目的: 合并所有重叠区间，返回不重复的区间数组
     * 思路:
     * 按左边界升序排, 右边界降序排列
     * 最终效果
     * ----------
     * ------
     * ----
     *  ---
     *   -----------
     *                ------
     *
     * 当前区间与前一个区间相比总有三种情况:
     *   1. 区间被前一个区间完全覆盖: 丢弃当前区间
     *   2. 区间与前一个区间相交: 扩展前一个区间的右边界
     *   3. 区间与前一个区间完全不相交: 将当前区间作为新的区间加入, 作为新的"前一个区间"
     * 时间复杂度 O(n*lgn)
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length < 1)
            throw new RuntimeException("argv error");
        // 左边界正序、右边界倒序排列
        Arrays.sort(intervals, (n1, n2) -> {
            if (n1[0] == n2[0])
                return n2[1] - n1[1];
            return n1[0] - n2[0];
        });

        LinkedList<int[]> ret = new LinkedList<>();
        ret.add(intervals[0]);
        // 最左中最大的区间
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (ret.getLast()[1] >= interval[1]) // 覆盖, 舍弃
                continue;

            // 前提: "前一个区间"的右边界小于当前区间的右边界 (相交/完全不相交)
            if (ret.getLast()[1] >= interval[0]) // 相交, 合并
                ret.getLast()[1] = interval[1];
            else  // 完全不相交, 作为新的"前一个区间" right < interval[0]
                ret.add(interval);
        }
        return ret.toArray(int[][]::new);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
