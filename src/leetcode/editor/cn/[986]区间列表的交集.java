//给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而 
//secondList[j] = [startj, endj] 。每个区间列表都是成对 不相交 的，并且 已经排序 。 
//
// 返回这 两个区间列表的交集 。 
//
// 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。 
//
// 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。 
//
// 
//
// 示例 1： 
//
// 
//输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,
//24],[25,26]]
//输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
// 
//
// 示例 2： 
//
// 
//输入：firstList = [[1,3],[5,9]], secondList = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：firstList = [], secondList = [[4,8],[10,12]]
//输出：[]
// 
//
// 示例 4： 
//
// 
//输入：firstList = [[1,7]], secondList = [[3,10]]
//输出：[[3,7]]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= firstList.length, secondList.length <= 1000 
// firstList.length + secondList.length >= 1 
// 0 <= starti < endi <= 10⁹ 
// endi < starti+1 
// 0 <= startj < endj <= 10⁹ 
// endj < startj+1 
// 
// Related Topics 数组 双指针 👍 255 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution986 {


    /* 区间列表的交集: https://leetcode.cn/problems/interval-list-intersections/
     * 相似题:
     *   56.合并区间: https://leetcode-cn.com/problems/merge-intervals/
     *   1288.被删除的覆盖区间: https://leetcode.cn/problems/remove-covered-intervals/
     *  最小区间数量:
     *   1024.视频拼接: https://leetcode.cn/problems/video-stitching/
     * 困难，区间带权重
     *   1235.规划兼职工作: https://leetcode.cn/problems/maximum-profit-in-job-scheduling/
     *
     * 题目保证了不相交, 我们就没有必要按左升右降排序了
     * 1. first 的右边和 second 的左边相交, 记录相交区间, first 移动到下一个元素
     * 2. first 的左边和 second 的右边相交, 记录相交区间, second 移动到下一个元素
     *
     * 3.4. 它俩存在覆盖关系, 记录相交区间, 偏左的移动到下一个元素
     * 这四种情况左边界求最大值 maxL, 右边界求最小值 minR, 只要 maxL <= minR, 就是一个有效相交区间
     * 5. 完全不相交, 偏左的移动到下一个元素
     *
     * 我们每次只移动 firstList, secondList 中的一个区间，共需要移动两个列表️之和次
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> ret = new ArrayList<>();
        for (int i = 0, j = 0; i < firstList.length && j < secondList.length;) {
            int[] intervalI = firstList[i], intervalJ = secondList[j];
            int maxL = Math.max(intervalI[0], intervalJ[0]);
            int minR = Math.min(intervalI[1], intervalJ[1]);
            if (maxL <= minR)
                ret.add(new int[]{maxL, minR});

            // 小区间往前走，继续和大区间取交集
            if (intervalI[1] < intervalJ[1])
                i++;
            else
                j++;
        }
        return ret.toArray(new int[ret.size()][]);
    }

    public static void main(String[] args) {
        int[][] argv0 = new int[][]{{0,2},{5,10},{13,23},{24,25}};
        int[][] argv1 = new int[][]{{1,5},{8,12},{15,24},{25,26}};
        new Solution986().intervalIntersection(argv0, argv1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
