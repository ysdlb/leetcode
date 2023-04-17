//给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。 
//
// 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。 
//
// 在完成所有删除操作后，请你返回列表中剩余区间的数目。 
//
// 
//
// 示例： 
//
// 
//输入：intervals = [[1,4],[3,6],[2,8]]
//输出：2
//解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 1000 
// 0 <= intervals[i][0] < intervals[i][1] <= 10^5 
// 对于所有的 i != j：intervals[i] != intervals[j] 
// 
// Related Topics 数组 排序 👍 64 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)

class Solution1288 {
    /* 按左边界降序排序
     * 从左到右遍历, 然后遍历当前元素后面对所有元素, 后面元素到左边界肯定不大于前面元素左边界, 如果后面元素的右边界还不小于前面元素的右边界,
     * 那么后面元素覆盖前面元素
     * 继续从左到右遍历下一个元素
     *
     * 时间复杂度 O(n^2)
     *
     * 这是个错误的解法 样例不足, 同左边界，右边界更大的前后顺序未说明, 区间可以合并, 这里当作不能合并做的
     */
    public int removeCoveredIntervalsWrong(int[][] intervals) {
        int ret = intervals.length;
        // 从大到小排序
        Arrays.sort(intervals, (n1, n2) -> n2[0] - n1[0]);
        for (int i = 0; i < intervals.length - 1; i++) {
            int[] a = intervals[i];
            for (int j = i+1; j < intervals.length; j++) {
                int[] b = intervals[j];
                // b[0] <= a[0] 天然成立
                if (b[1] >= a[1]) {
                    ret--;
                    break;
                }
            }
        }
        return ret;
    }

    /* 被删除的覆盖区间: https://leetcode.cn/problems/remove-covered-intervals/
     * 几乎相同:
     *   56.合并区间: https://leetcode-cn.com/problems/merge-intervals/
     * 相似题:
     *   986.区间列表的交集: https://leetcode.cn/problems/interval-list-intersections/
     *  最小区间数量:
     *   1024.视频拼接: https://leetcode.cn/problems/video-stitching/
     * 困难，区间带权重
     *   1235.规划兼职工作: https://leetcode.cn/problems/maximum-profit-in-job-scheduling/
     *
     * 仅删除被覆盖的区间
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
     *   1. 区间被前一个区间完全覆盖: 丢弃当前区间, 原区间总数 -1
     *   2. 区间与前一个区间相交: 扩展前一个区间的右边界
     *   3. 区间与前一个区间完全不相交: 将当前区间作为新的区间加入, 作为新的"前一个区间"
     * 因为排序的原因，前一个区间的左边界一定不大于当前区间的左边界
     *   所以只要"前一个区间"的右边界 >= 当前区间的右边界，那么一定是覆盖关系
     *   否则，就一定不是覆盖关系
     */
    public int removeCoveredIntervals(int[][] intervals) {
        int ret = intervals.length;
        // 从大到小排序
        Arrays.sort(intervals, (n1, n2) -> {
            if (n1[0] == n2[0])
                return n2[1] - n1[1];
            return n1[0] - n2[0];
        });

        // 因为排序的原因，前一个区间的左边界一定不大于当前区间的左边界
        //   即 left <= a[0] 总是成立
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] a = intervals[i];
            if (right >= a[1]) // 覆盖
                ret--;
            // right < a[1] && right >= a[0] 相交
            // right < a[1] && right < a[0] 完全不相交
            else
                // left = a[0];
                right = a[1];
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] arg = new int[][]{{34335,39239},{15875,91969},{29673,66453},{53548,69161},{40618,93111}};
        new Solution1288().removeCoveredIntervals(arg);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
