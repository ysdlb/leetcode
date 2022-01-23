//写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下： 
//
// 
//F(0) = 0,   F(1) = 1
//F(N) = F(N - 1) + F(N - 2), 其中 N > 1. 
//
// 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
// Related Topics 记忆化搜索 数学 动态规划 👍 287 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer10_1 {
    private static final int MOD = 1000000007;
    /**
     * 斐波那契数列矩阵快速幂的递推公式
     * f(n)*1 + f(n-1)*1 = f(n+1)
     * f(n)*1 + f(n-1)*0 = f(n)
     *
     * [[f(n+1)], [f(n)]] = [[1,1],[1,0]]^n * [[f(1)], [f(0)]]
     * ret[1][0]
     */
    public int fib(int n) {
        int[][] matrix = new int[][]{{1, 1}, {1, 0}};
        int[][] matrixN = matrixPow(matrix, n);
        int[][] ret = matrixMultiply(matrixN, new int[][]{{1}, {0}});
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

    public static void main(String[] args) {
        int[][] a = new int[][]{{1,1}, {1,0}};
        int[][] b = new int[][]{{1}, {0}};

        a = matrixPow(a, 0);
        System.out.println(Arrays.deepToString(a));
        System.out.println(Arrays.deepToString(matrixMultiply(a, b)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
