//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 2, n = 3, k = 1
//输出：3
// 
//
// 示例 2： 
//
// 输入：m = 3, n = 1, k = 0
//输出：1
// 
//
// 提示： 
//
// 
// 1 <= n,m <= 100 
// 0 <= k <= 20 
// 
// Related Topics 深度优先搜索 广度优先搜索 动态规划 👍 424 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer13 {
    /**
     * 隐含条件: 已经去过但不再去
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return recurseBack(m, n , 0, 0, visited, k);
    }

    private int recurseBack(int m, int n, int i, int j, boolean[][] visited, int k) {
        if (i < 0 || i >= m || j < 0 || j >= n
                || visited[i][j]
                || calcSumOfLocation(i, j) > k)
            return 0;

        visited[i][j] = true;
        return 1
                + recurseBack(m, n, i-1, j, visited, k)
                + recurseBack(m, n, i+1, j, visited, k)
                + recurseBack(m, n, i, j-1, visited, k)
                + recurseBack(m, n, i, j+1, visited, k);
    }

    private int calcSumOfLocation(int i, int j) {
        int ret = 0;
        ret += i%10;
        while ((i = i/10) != 0) {
            ret += i%10;
        }

        ret += j%10;
        while ((j = j/10) != 0) {
            ret += j%10;
        }

        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
