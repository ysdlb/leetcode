//给你一个大小为 m x n 的二进制矩阵 grid 。 
//
// 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都
//被 0（代表水）包围着。 
//
// 岛屿的面积是岛上值为 1 的单元格的数目。 
//
// 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,
//0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,
//0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//输出：6
//解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[0,0,0,0,0,0,0,0]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 50 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 764 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution695 {
    /**
     * 遍历整个 grid, 每发现一块陆地 (== 1) DFS 标记每个岛屿, 记录岛屿大小
     *
     * 另一种思路: 并查集 (相似题目, 990, 323)
     * 其实就是求 == 1 的节点的最大重量联通分量
     * https://labuladong.gitee.io/algo/2/19/37/
     *
     * 类似题目 200
     */
    public int maxAreaOfIsland(int[][] grid) {
        int maxSize = 0;
        if (grid.length <= 0) return maxSize;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxSize = Math.max(maxSize, scanLand(grid, i, j));
                }
            }
        }
        return maxSize;
    }

    /**
     * 扫描整个岛屿
     * 将其标记为 2, 返回岛屿大小
     */
    private int scanLand(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length)
            return 0;
        if (col < 0 || col >= grid[0].length)
            return 0;
        if (grid[row][col] != 1)
            return 0;

        grid[row][col] = 2;
        return 1 +
                scanLand(grid, row - 1, col) +
                scanLand(grid, row + 1, col) +
                scanLand(grid, row, col - 1) +
                scanLand(grid, row, col + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
