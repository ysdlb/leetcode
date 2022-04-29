//给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重
//叠 。 
//
// 
//
// 示例 1: 
//
// 
//输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
//输出: 1
//解释: 移除 [1,3] 后，剩下的区间没有重叠。
// 
//
// 示例 2: 
//
// 
//输入: intervals = [ [1,2], [1,2], [1,2] ]
//输出: 2
//解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
// 
//
// 示例 3: 
//
// 
//输入: intervals = [ [1,2], [2,3] ]
//输出: 0
//解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= intervals.length <= 10⁵ 
// intervals[i].length == 2 
// -5 * 10⁴ <= starti < endi <= 5 * 10⁴ 
// 
// Related Topics 贪心 数组 动态规划 排序 👍 674 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution435 {
    /**
     * 同 452 求重叠后区间, 区别是紧挨着不算相交
     * 然后总区间减去重叠后区间就是目标值
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (e1, e2) -> {
            if (e1[0] == e2[0])
                return Integer.compare(e2[1], e1[1]);
            return Integer.compare(e1[0], e2[0]);
        });

        int ret = 1;
        int maxL = intervals[0][0], minR = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            if (left >= minR) {
                ret++;
                maxL = left;
                minR = right;
            } else {
                maxL = Math.max(maxL, left);
                minR = Math.min(minR, right);
            }
        }
        return intervals.length - ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
