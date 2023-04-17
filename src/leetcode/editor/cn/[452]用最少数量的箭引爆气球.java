//有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示
//水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。 
//
// 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足 
//xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。 
//
// 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。 
// 
//
// 示例 1： 
//
// 
//输入：points = [[10,16],[2,8],[1,6],[7,12]]
//输出：2
//解释：气球可以用2支箭来爆破:
//-在x = 6处射出箭，击破气球[2,8]和[1,6]。
//-在x = 11处发射箭，击破气球[10,16]和[7,12]。 
//
// 示例 2： 
//
// 
//输入：points = [[1,2],[3,4],[5,6],[7,8]]
//输出：4
//解释：每个气球需要射出一支箭，总共需要4支箭。 
//
// 示例 3： 
//
// 
//输入：points = [[1,2],[2,3],[3,4],[4,5]]
//输出：2
//解释：气球可以用2支箭来爆破:
//- 在x = 2处发射箭，击破气球[1,2]和[2,3]。
//- 在x = 4处射出箭，击破气球[3,4]和[4,5]。 
//
// 
//
// 
//
// 提示: 
//
// 
// 1 <= points.length <= 10⁵ 
// points[i].length == 2 
// -2³¹ <= xstart < xend <= 2³¹ - 1 
// 
// Related Topics 贪心 数组 排序 👍 571 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution452 {
    /* 用最少数量的箭引爆气球: https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
     * 相似题:
     *   435.无重叠区间: https://leetcode.cn/problems/non-overlapping-intervals/
     * 同排序方案:
     *   56.合并区间: https://leetcode-cn.com/problems/merge-intervals/
     *
     * 思路:
     * 跟合并区间在相交的时候扩张右边界不同
     * 参考 56, 435
     *
     * [1,  6]
     * [1,2]
     *     [3,8]
     *      [7,12]
     *         [10, 16]
     * 1. 如果完全和 [maxL, minR] 不相交, 则新建区间
     * 2. 如果有相交同时收缩左右边界 [maxL, minR]
     *
     * 留存:
     * 求最少有多少个重叠后小区间可串联全部 等价于 求最多有多少个不重叠区间
     * 用 end 排序，可直接求最多有多少个不重叠区间
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, (e1, e2) -> {
            if (e1[0] == e2[0])
                return Integer.compare(e2[1], e1[1]);
            return Integer.compare(e1[0], e2[0]);
        });

        int ret = 1;
        int maxL = points[0][0], minR = points[0][1];
        for (int i = 1; i < points.length; i++) {
            int left = points[i][0];
            int right = points[i][1];
            if (left > minR) {
                ret++;
                maxL = left;
                minR = right;
            } else {
                maxL = Math.max(maxL, left);
                minR = Math.min(minR, right);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[][] arg = new int[][]{{-2147483646,-2147483645},{2147483646,2147483647}};
        Solution452 solution452 = new Solution452();
        solution452.findMinArrowShots(arg);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
