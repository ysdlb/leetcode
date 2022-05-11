//二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。 
//
// 请返回 封闭岛屿 的数目。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,
//0,1],[1,1,1,1,1,1,1,0]]
//输出：2
//解释：
//灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。 
//
// 示例 2： 
//
// 
//
// 
//输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：grid = [[1,1,1,1,1,1,1],
//             [1,0,0,0,0,0,1],
//             [1,0,1,1,1,0,1],
//             [1,0,1,0,1,0,1],
//             [1,0,1,1,1,0,1],
//             [1,0,0,0,0,0,1],
//             [1,1,1,1,1,1,1]]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length, grid[0].length <= 100 
// 0 <= grid[i][j] <=1 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 135 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1254 {
    /**
     * 先处理边界
     * 同 130, 1020
     */
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // 只有不跟边界连接的 0 才是封闭的 0
        for (int row = 0; row < m; row++) {
            dfs(grid, row, 0);
            dfs(grid, row, n-1);
        }
        for (int col = 0; col < n; col++) {
            dfs(grid, 0, col);
            dfs(grid, m-1, col);
        }

        int ret = 0;
        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if (grid[i][j] == 0) {
                    ret++;
                    dfs(grid, i, j);
                }
            }
        }
        return ret;
    }

    private void dfs(int[][] grid, int x, int y) {
        if (x < 0 || y < 0
                || x >= grid.length || y >= grid[0].length
                || grid[x][y] != 0)
            return;

        grid[x][y] = 2;
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
