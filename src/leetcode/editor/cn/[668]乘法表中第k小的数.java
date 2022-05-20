//几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？ 
//
// 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。 
//
// 例 1： 
//
// 
//输入: m = 3, n = 3, k = 5
//输出: 3
//解释: 
//乘法表:
//1	2	3
//2	4	6
//3	6	9
//
//第5小的数字是 3 (1, 2, 2, 3, 3).
// 
//
// 例 2： 
//
// 
//输入: m = 2, n = 3, k = 6
//输出: 6
//解释: 
//乘法表:
//1	2	3
//2	4	6
//
//第6小的数字是 6 (1, 2, 2, 3, 4, 6).
// 
//
// 注意： 
//
// 
// m 和 n 的范围在 [1, 30000] 之间。 
// k 的范围在 [1, m * n] 之间。 
// 
// Related Topics 二分查找 👍 191 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution668 {
    /**
     * 稍微转化下和 378 是一个题
     * 将乘法表扩展为一张真正的表, 这张二维表行列有序,
     * 那么最小值为 min = table[0][0], 最大值 max = table[m-1][n-1]
     * 对于任意的 x 属于 [min, max], f(x) 为小于等于 x 的值的数量
     *
     * 显然, f(x) 单调递增, 满足 f(x) >= k 的所有 x 中, 最小的一个就是我们要的结果
     *
     * 时间复杂度：lg(m*n) * (m+n)
     * 同 378 很像
     * 数据类型类似 240（完全不同的解法，稍微利用了共同的性质）
     */
    public int findKthNumber(int m, int n, int k) {
        if (m < 1 || n < 1)
            throw new IllegalArgumentException("invalid param!");

        int min = 1, max = m*n;
        while (min < max) {
            // 天然向下取整
            int mid = (min + max) / 2;
            int count = countLessThanAndEqual(m, n, mid);

            if (count >= k) {
                max = mid;
            } else {
                // 一旦 mid 满足了 count >= k, min 就不会再变了
                // 而 mid 这个值一定恰好再乘法表中
                min = mid + 1;
            }
        }
        return min;
    }

    /**
     * @param m 表行
     * @param n 表列
     * @param x x
     * @return 对于任意的 x 属于 [min, max], 计算小于等于 x 的值的数量
     */
    private int countLessThanAndEqual(int m, int n, int x) {
        int count = 0;
        for (int i = 1, j = n; i <= m; i++) {
            // j 左移
            while (j >= 1 && i*j > x)
                j--;
            count += j;
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
