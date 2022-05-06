//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 1710 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution200 {

    /**
     * 遍历整个 grid, 每发现一块陆地 (== 1) DFS 标记每个岛屿
     *
     * 另一种思路: 并查集 (相似题目, 990, 323)
     * 其实就是求 == 1 的节点有多少联通分量
     * https://labuladong.gitee.io/algo/2/19/37/
     *
     * 类似题目 130
     * 查看其相似题目
     */
    public int numIslands(char[][] grid) {
        int ret = 0;
        if (grid.length <= 0) return ret;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    ret++;
                    scanLand(grid, i, j);
                }
            }
        }
        return ret;
    }

    /**
     * 扫描整个岛屿
     * 将其标记为 2
     */
    private void scanLand(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length)
            return;
        if (col < 0 || col >= grid[0].length)
            return;

        if (grid[row][col] == '1') {
            grid[row][col] = '2';
            scanLand(grid, row - 1, col);
            scanLand(grid, row + 1, col);
            scanLand(grid, row, col - 1);
            scanLand(grid, row, col + 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
