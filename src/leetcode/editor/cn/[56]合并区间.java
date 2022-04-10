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
import java.util.List;

/**
 * 同
 * <a href="https://leetcode-cn.com/problems/remove-covered-intervals/">leetcode-1288</a>
 *
 * <p>
 * <a href="https://leetcode-cn.com/problems/merge-intervals/">leetcode-56</a>
 */
class Solution56 {

    /**
     * 按左边界升序排, 右边界降序排列
     * 最终效果
     * ----------
     * ------
     * ----
     *  ---
     *   -----------
     *                ------
     *
     * 当区间被虚拟区间完全覆盖时候, 被覆盖区间数量加一
     * 当区间与虚拟区间相交时, 扩展虚拟区间的右边界
     * 当区间与虚拟区间完全不想交时, 右移虚拟区间左边界, 扩展右边界
     * 时间复杂度 O(n*lgn)
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length < 1)
            throw new RuntimeException("argv error");
        // 同 1288 题
        Arrays.sort(intervals, (n1, n2) -> {
            if (n1[0] == n2[0])
                return n2[1] - n1[1];
            return n1[0] - n2[0];
        });

        int[][] ret = new int[intervals.length][];
        int index = -1;
        // 最左中最大的区间
        ret[++index] = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (ret[index][1] >= interval[1]) // 覆盖, 舍弃
                continue;
            else if (ret[index][1] >= interval[0]) // 相交, 合并
                ret[index][1] = interval[1];
            else  // 完全不想交, right < interval[0]
                ret[++index] = interval;
        }
        return Arrays.copyOf(ret, index+1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
