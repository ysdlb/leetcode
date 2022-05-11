//给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。 
//
// 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。 
//
// 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
//输出：3
//解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
//输出：0
//解释：所有 1 都在边界上或可以到达边界。
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 500 
// grid[i][j] 的值为 0 或 1 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 170 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1020 {
    /**
     * 同 130
     */
    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // 标记边界连接的陆地
        for (int row = 0; row < m; row++) {
            signIsland(grid, row, 0);
            signIsland(grid, row, n-1);
        }
        for (int col = 0; col < n; col++) {
            signIsland(grid, 0, col);
            signIsland(grid, m-1, col);
        }

        // 计算飞地的数量
        int ret = 0;
        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1)
                    ret++;
            }
        }
        return ret;
    }

    private void signIsland(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length
                || y < 0 || y >= grid[0].length)
            return;
        // 遇到 0 截止
        if (grid[x][y] != 1)
            return;

        grid[x][y] = 0;
        signIsland(grid, x+1, y);
        signIsland(grid, x-1, y);
        signIsland(grid, x, y+1);
        signIsland(grid, x, y-1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
