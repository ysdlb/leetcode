//一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 示例 1： 
//
// 输入：n = 2
//输出：2
// 
//
// 示例 2： 
//
// 输入：n = 7
//输出：21
// 
//
// 示例 3： 
//
// 输入：n = 0
//输出：1 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
//
// 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/ 
//
// 
// Related Topics 记忆化搜索 数学 动态规划 👍 238 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <a href="https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/">leetcode-offer-10</a>
 */
class SolutionOffer10_2 {
    private static final int MOD = 1000000007;

    /**
     * f(0) = 1, f(1) = 1, f(2) = 2
     *
     * n > 2 时候, 青蛙只有两个位置达到第 n 级台阶, f(n-1) + f(n-2)
     *
     * f(n+1) = f(n) + f(n-1)
     * f(n) = f(n)
     *
     * [[f(n+1)], [f(n)]] = [[1,1],[1,0]]^1 * [[f(n)], [f(n-1)]]
     *
     * [[f(n+1)], [f(n)]] = [[1,1],[1,0]]^n * [[f(1)], [f(0)]]
     */
    public int numWays(int n) {
        int[][] matrix = new int[][]{{1,1},{1,0}};
        int[][] ret = new int[][]{{1}, {1}};
        ret = matrixMultiply(matrixPow(matrix, n), ret);
        return ret[1][0];
    }

    /**
     * 计算 n 阶矩阵的幂
     */
    private static int[][] matrixPow(int[][] a, int power) {
        // 矩阵的 0 次幂全是 E
        // if (power == 0) return a;

        if (a.length != a[0].length)
            throw new RuntimeException("this matrix cannot get power");

        int[][] ret = nMatrix(a.length);
        while (power > 0) {
            if ((power & 1) == 1) {
                ret = matrixMultiply(ret, a);
            }
            a = matrixMultiply(a, a);
            power >>= 1;
        }
        return ret;
    }

    /**
     * @return n 阶单位矩阵 (E)
     */
    private static int[][] nMatrix(int n) {
        int[][] ret = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) ret[i][j] = 1;
                else ret[i][j] = 0;
            }
        }
        return ret;
    }

    /**
     *  计算两个 n 阶矩阵相乘的结果
     */
    private static int[][] matrixMultiply(int[][] a, int[][] b) {
        int M = a.length, J = b.length, N = b[0].length;
        if (a[0].length != J) throw new RuntimeException("非法的矩阵相乘");

        int[][] res = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                long sum = 0;
                for (int k = 0; k < J; k++) {
                    sum += (long) a[i][k] * b[k][j];
                }
                res[i][j] = (int) (sum % MOD);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
