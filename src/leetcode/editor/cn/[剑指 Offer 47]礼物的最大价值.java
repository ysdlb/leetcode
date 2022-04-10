//在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直
//到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？ 
//
// 
//
// 示例 1: 
//
// 输入: 
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 12
//解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物 
//
// 
//
// 提示： 
//
// 
// 0 < grid.length <= 200 
// 0 < grid[0].length <= 200 
// 
// Related Topics 数组 动态规划 矩阵 👍 245 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer47 {
    /**
     * dp[i][j] 为到 grid[i][j] 位置礼物的最大价值
     *
     * dp[i[j] = max(dp[i-1][j], dp[i][j-1]) + grid[i][j]
     *
     * 这里 dp[i][j] 只以来第 i-1 行和第 i 行前一个, 所以可以用一维数组替代二维数组,
     * 依赖的两个元素正好落到 dp[j-1] 和 dp [j] 这个俩位置上
     * 每遍历一行刷新一遍该一维数组
     */
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = 0;
                if (i > 0) { // 上一个元素
                    tmp = dp[j];
                }
                if (j > 0) { // 前一个元素
                    tmp = Math.max(tmp, dp[j-1]);
                }
                dp[j] = tmp + grid[i][j];
            }
        }
        return dp[n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
