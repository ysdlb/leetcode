//给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。一个 岛屿 是由 四个方向 （水平或者竖
//直）上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。 
//
// 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那
//么我们称 grid2 中的这个岛屿为 子岛屿 。 
//
// 请你返回 grid2 中 子岛屿 的 数目 。 
//
// 
//
// 示例 1： 
//
// 输入：grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], 
//grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
//输出：3
//解释：如上图所示，左边为 grid1 ，右边为 grid2 。
//grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
// 
//
// 示例 2： 
//
// 输入：grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], 
//grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
//输出：2 
//解释：如上图所示，左边为 grid1 ，右边为 grid2 。
//grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
// 
//
// 
//
// 提示： 
//
// 
// m == grid1.length == grid2.length 
// n == grid1[i].length == grid2[i].length 
// 1 <= m, n <= 500 
// grid1[i][j] 和 grid2[i][j] 都要么是 0 要么是 1 。 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 39 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1905 {
    /**
     * 同 200 岛屿数量
     * 正常标记 grid2 中的岛屿数量, 只不过 如果 grid1 同位置为水域的话, 这个岛屿无效
     * 要注意, grid2 整个岛屿要标记完
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int ret = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[0].length; j++) {
                if (grid2[i][j] == 1 && dfsSign(grid1, grid2, i, j))
                    ret++;
            }
        }
        return ret;
    }

    private boolean dfsSign(int[][] grid1, int[][] grid2, int x, int y) {
        if (x < 0 || y < 0
                || x >= grid2.length || y >= grid2[0].length
                || grid2[x][y] != 1)
            return true;

        boolean ret = grid1[x][y] == 1;
        grid2[x][y] = 0;
        if (!dfsSign(grid1, grid2, x - 1, y))
            ret = false;
        if (!dfsSign(grid1, grid2, x + 1, y))
            ret = false;
        if (!dfsSign(grid1, grid2, x, y - 1))
            ret = false;
        if (!dfsSign(grid1, grid2, x, y + 1))
            ret = false;

        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
