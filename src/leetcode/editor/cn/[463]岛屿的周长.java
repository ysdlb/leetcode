//给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。 
//
// 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。 
//
// 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿
//的周长。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
//输出：16
//解释：它的周长是上面图片中的 16 个黄色的边 
//
// 示例 2： 
//
// 
//输入：grid = [[1]]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：grid = [[1,0]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// row == grid.length 
// col == grid[i].length 
// 1 <= row, col <= 100 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 👍 547 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution463 {
    /**
     * 求边界的数量
     * 直接遍历整个数组，碰到0或者边界计数
     * 类似题目: 200, 695
     */
    public int islandPerimeter(int[][] grid) {
        if (grid.length == 0) return 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return sanLand(grid, i, j);
                }
            }
        }
        return 0;
    }

    /**
     * 超出 grid, 以及进去水域 （==0) 视为边界
     * @return 边界的数量
     */
    public int sanLand(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length)
            return 1;
        if (col < 0 || col >= grid[0].length)
            return 1;

        int ret = 0;
        if (grid[row][col] == 0) {
            ret = 1;
        } else if (grid[row][col] == 1) {
            grid[row][col] = 2;
            ret = sanLand(grid, row-1, col) +
                    sanLand(grid, row+1, col) +
                    sanLand(grid, row, col-1) +
                    sanLand(grid, row, col+1);
        }
        return ret;
    }
}

class Solution463_ERROR {
    /**
     * 4*格子数量 - 2*相邻数量
     * badcase: [[1,1],[1,1]]
     * 输出:10, 预期: 8
     */
    public int islandPerimeter(int[][] grid) {
        if (grid.length == 0) return 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int[] s = sanLand(grid, i, j);
                    return s[0] * 4 - s[1] * 2;
                }
            }
        }
        return 0;
    }

    /**
     * @return [格子数量, 相邻数量]
     */
    public int[] sanLand(int[][] grid, int row, int col) {
        int[] ret = new int[]{0,0};
        if (row < 0 || row >= grid.length)
            return ret;
        if (col < 0 || col >= grid[0].length)
            return ret;

        if (grid[row][col] == 1) {
            grid[row][col] = 2;
            int[] s1 = sanLand(grid, row-1, col);
            ret[0] += s1[0]; ret[1] += s1[1];
            // 记一个相邻边
            if (s1[0] > 0) ret[1]++;

            s1 = sanLand(grid, row+1, col);
            ret[0] += s1[0]; ret[1] += s1[1];
            if (s1[0] > 0) ret[1]++;

            s1 = sanLand(grid, row, col-1);
            ret[0] += s1[0]; ret[1] += s1[1];
            if (s1[0] > 0) ret[1]++;

            s1 = sanLand(grid, row, col+1);
            ret[0] += s1[0]; ret[1] += s1[1];
            if (s1[0] > 0) ret[1]++;

            ret[0]++;
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
