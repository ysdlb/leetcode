//有 N 堆石头排成一排，第 i 堆中有 stones[i] 块石头。 
//
// 每次移动（move）需要将连续的 K 堆石头合并为一堆，而这个移动的成本为这 K 堆石头的总数。 
//
// 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 输入：stones = [3,2,4,1], K = 2
//输出：20
//解释：
//从 [3, 2, 4, 1] 开始。
//合并 [3, 2]，成本为 5，剩下 [5, 4, 1]。
//合并 [4, 1]，成本为 5，剩下 [5, 5]。
//合并 [5, 5]，成本为 10，剩下 [10]。
//总成本 20，这是可能的最小值。
// 
//
// 示例 2： 
//
// 输入：stones = [3,2,4,1], K = 3
//输出：-1
//解释：任何合并操作后，都会剩下 2 堆，我们无法再进行合并。所以这项任务是不可能完成的。.
// 
//
// 示例 3： 
//
// 输入：stones = [3,5,1,2,6], K = 3
//输出：25
//解释：
//从 [3, 5, 1, 2, 6] 开始。
//合并 [5, 1, 2]，成本为 8，剩下 [3, 8, 6]。
//合并 [3, 8, 6]，成本为 17，剩下 [17]。
//总成本 25，这是可能的最小值。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= stones.length <= 30 
// 2 <= K <= 30 
// 1 <= stones[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 176 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1000 {
    /* 合并石头的最低成本: https://leetcode.cn/problems/minimum-cost-to-merge-stones/
     * 相似题:
     *   312. 戳气球: https://leetcode.cn/problems/burst-balloons/
     *
     * 每次合并总会减少 k-1 堆石头, 最后一次减少 k 堆石头
     * (k-1) * (n-1) + k = total ==> (k-1)*n = total - 1
     *
     * 每次都是将连续的 k 堆合并为 1 堆
     *
     * 首先简化问题: 假设 k = 2
     * 则可以设置 f(i,j) 为 合并 [i,j] 区间内的两堆石头为 1 堆的最低成本
     * 我们设 i <= m < j，总是可以将 [i,j] 分为两堆
     *   1. [i,m] 合并成的一堆
     *   2. [m+1,j] 合并成的一堆
     * 遍历 m，求:
     *   f(i,j) = min{f(i,m)+f(m+1,j), ...} + sum(i,j)   i <= m < j
     *
     * 假设 f(i,j,x) 为合并 [i,j] 区间内石头为 x 堆的最低成本，我们要将 [i,j] 合并成一堆，则需要之前 [i,j] 内已经合并成了 k 堆
     *   f(i,j,1) = f(i,j,k) + sum(i,j)
     * 若 k == 2，
     *   min{f(i,m,1)+f(m+1,j,1), ...} == f(i,j,2)
     *
     *
     * 若 k >= 2; 且 x >= 2; 遍历 m 求:
     *   f(i,j,x) = min{f(i,m,1)+f(m+1,j,x-1), ...},  2 <= x <= k, i <= m < j; m += k-1
     * 结合 f(i,j,1) = f(i,j,k) + sum(i,j), 我们可以推出上面 k == 2 时特殊情况的公式
     *
     * 若 k != 2，[i,j] 不一定可以满足每次合并 k 个的要求，在这个要求的基础上，[i,j] 也不一定可以满足合并为 x 堆的要求
     * 对于这些不可能的 f(i,j,x) 值，我们设置为正无穷
     *
     * 同时遍历的过程中，可以酌情将 m 每一跳的间隙设置为 k-1，来忽略不可能到达的位置
     * 因为在每次合并 k 堆的基础上，若 [i,m] 可以合并为 1 堆，[m+1,j] 必然可以合并为 k-1 堆
     *
     * [i,j] 循环中，f(i,j,x) (2<=x) 因为没有 1 堆的限定条件，所以不可以这么跳
     *
     * 初始状态推断
     * 如果 j-i+1 == k, 则 f(i,j,k) == 0 ==> f(i,i,1) == 0
     * 即 f(i,i+1,2) == 0
     *
     * 从左到右, 从下到上遍历
     */
    public int mergeStones(int[] stones, int k) {
        // (k-1) * (n-1) + k = total ==> (k-1)*n = total - 1
        if ((stones.length - 1) % (k-1) != 0) return -1;

        int[] prefix = new int[stones.length+1];
        for (int i = 0; i < stones.length; i++) {
            prefix[i+1] = prefix[i] + stones[i];
        }

        int[][][] dp = new int[stones.length][stones.length][k+1];
        int INF = 99999;
        for (int i = 0; i < stones.length; i++) {
            for (int j = i; j < stones.length; j++) {
                for (int x = 1; x <= k; x++) {
                    dp[i][j][x] = INF;
                }
            }
            dp[i][i][1] = 0;
        }

        for (int i = stones.length - 1; i >= 0; i--) {
            // dp[3][4][2] == 0, 所以 j 不能从 i+k-1 开始
            for (int j = i+1; j < stones.length; j++) {
                for (int m = i; m < j; m += k-1) {
                    for (int x = 2; x <= k; x++) {
                        dp[i][j][x] = Math.min(dp[i][j][x], dp[i][m][1] + dp[m+1][j][x-1]);
                    }
                }
                // 这里如果 (j-i) % (k-1) != 0, dp[i][j][k] 会无穷大
                dp[i][j][1] = dp[i][j][k] + prefix[j+1] - prefix[i];
            }
        }
        return dp[0][stones.length-1][1];
    }

    public static void main(String[] args) {
        int[] arg = new int[]{3,5,1,2,6};
        Solution1000 solution1000 = new Solution1000();
        solution1000.mergeStones(arg, 3);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
