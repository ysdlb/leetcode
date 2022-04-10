//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
// Related Topics 数组 动态规划 矩阵 👍 1153 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution64 {
    /**
     * 状态: 当前位于哪个网格
     * 选择: 从右边走过来还是从左边走过来 ( 选择最小的 )
     *
     * 注意启始条件:
     *   a. 右上角的元素总是第一个, 它的右边和左边都是虚无 0
     *   b. 上边沿只能从左边过来, 其上面是正无穷; 左边沿只能从上面过来, 其左边是正无穷
     */
    public int minPathSum(int[][] grid) {
        if (grid.length == 0) return 0;

        int[][] dp = new int[grid.length + 1][grid[0].length + 1];
        // 初始化
        for (int i = 2; i <= grid[0].length; i++)
            dp[0][i] = Integer.MAX_VALUE;
        for (int i = 2; i <= grid.length; i++)
            dp[i][0] = Integer.MAX_VALUE;

        for (int i = 1; i <= grid.length; i++) {
            for (int j = 1; j <= grid[0].length; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i-1][j-1];
            }
        }
        return dp[grid.length][grid[0].length];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
