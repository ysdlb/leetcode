//斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是： 
//
// 
//F(0) = 0，F(1) = 1
//F(n) = F(n - 1) + F(n - 2)，其中 n > 1
// 
//
// 给定 n ，请计算 F(n) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：1
//解释：F(2) = F(1) + F(0) = 1 + 0 = 1
// 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出：2
//解释：F(3) = F(2) + F(1) = 1 + 1 = 2
// 
//
// 示例 3： 
//
// 
//输入：n = 4
//输出：3
//解释：F(4) = F(3) + F(2) = 2 + 1 = 3
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 30 
// 
// Related Topics 递归 记忆化搜索 数学 动态规划 👍 415 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <a href="https://leetcode-cn.com/problems/fibonacci-number/">leetcode-509</a>
 */
class Solution509 {
    /**
     * 方法 1: DP， 时间复杂度 O(n)
     * 方法 2: 矩阵快速幂, 时间复杂度 O(lgn)
     * 知识点: 递推公式的进步推理 以及 快速幂算法
     *
     * 递推公式
     * f(n+1)  1*f(n) + 1*f(n-1)
     * f(n)    1*f(n) + 0*f(n-1)
     *
     * f(n+1)  (1,1)  f(n)           (1,1)^n f(n+1-n)
     * f(n)    (1,0)  f(n-1)   等于   (1,0)   f(n-n)
     *
     * 同题目: 剑指 Offer 10-1
     * 类似题目: 50, 372
     */
    public int fib(int n) {
        // 2 * 1的矩阵
        int[][] f = new int[][]{{1},{0}};
        // 2 * 2 的矩阵
        int[][] base = new int[][]{{1,1}, {1,0}};

        // f(n+1) f(n)
        // int[][] fib = multiply(f, pow(base, n));
        // 2*2 与 2*1 相乘
        int[][] fib = multiply(pow(base, n), f);
        return fib[1][0];
    }

    /**
     * 快速幂, 必须是 n 阶段矩阵
     */
    private int[][] pow(int[][] x, int power) {
        if (x.length != x[0].length)
            throw new RuntimeException("非法的 n 阶矩阵");

        // 等价于自然数 1
        int[][] ret = this.nUnitMatrix(x.length);
        while (power > 0) {
            if ((power & 1) == 1)
                ret = this.multiply(x, ret);
            x = this.multiply(x, x);
            power >>= 1;
        }
        return ret;
    }

    /**
     * @return a*b
     */
    private int[][] multiply(int[][] a, int[][] b) {
        int M = a.length, N = b[0].length;
        if (a[0].length != b.length)
            throw new RuntimeException("非法的矩阵相乘");
        int[][] ret = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < b.length; k++) {
                    ret[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return ret;
    }

    /**
     * n 阶 单位矩阵
     */
    private int[][] nUnitMatrix(int n) {
        int[][] ret = new int[n][n];
        for (int i = 0; i < n; i++) {
            ret[i][i] = 1;
        }
        return ret;
    }

    public static void main(String[] args) {
        new Solution509().fib(4);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
