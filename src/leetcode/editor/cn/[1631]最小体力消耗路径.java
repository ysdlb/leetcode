//你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row,
// col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你
//每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。 
//
// 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。 
//
// 请你返回从左上角走到右下角的最小 体力消耗值 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
//输出：2
//解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
//这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
//输出：1
//解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
// 
//
// 示例 3： 
//
// 
//输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
//输出：0
//解释：上图所示路径不需要消耗任何体力。
// 
//
// 
//
// 提示： 
//
// 
// rows == heights.length 
// columns == heights[i].length 
// 1 <= rows, columns <= 100 
// 1 <= heights[i][j] <= 10⁶ 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 二分查找 矩阵 堆（优先队列） 👍 287 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1631 {
    private static final int[][] DIRECTS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    /**
     * 相似题目: 743, 1514
     */
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        final int INF = Integer.MAX_VALUE / 2;

        int[][] distTo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                distTo[i][j] = INF;
            }
        }

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));
        // Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});
        distTo[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int fromX = point[0], fromY = point[1];
            // 尽管有优先级队列, for 循环里不一定是最小值(队列还有其它的没遍历), 这里才保证是最小值
            if (fromX == m - 1 && fromY == n - 1)
                return point[2];

            if (point[2] > distTo[fromX][fromY])
                continue;

            for (int[] p: adj(heights, fromX, fromY)) {
                int toX = p[0], toY = p[1];
                int effort = Math.abs(heights[fromX][fromY] - heights[toX][toY]);
                int distance = Math.max(distTo[fromX][fromY], effort);
                if (distance < distTo[toX][toY]) {
                    distTo[toX][toY] = distance;
                    queue.offer(new int[]{toX, toY, distance});
                }
            }
        }

        // 这个点一定可达
        return distTo[m-1][n-1];
    }

    private List<int[]> adj(int[][] heights, int x, int y) {
        List<int[]> ret = new ArrayList<>();
        int m = heights.length, n = heights[0].length;
        for (int[] direct: DIRECTS) {
            int i = x + direct[0];
            int j = y + direct[1];
            if (i >= 0 && i < m && j >= 0 && j < n)
                ret.add(new int[]{i, j});
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
