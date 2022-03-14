//给定一个整数 n ，返回 n! 结果中尾随零的数量。 
//
// 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：0
//解释：3! = 6 ，不含尾随 0
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出：1
//解释：5! = 120 ，有一个尾随 0
// 
//
// 示例 3： 
//
// 
//输入：n = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 10⁴ 
// 
//
// 
//
// 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？ 
// Related Topics 数学 👍 556 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution172 {
    /**
     * 一个数的末尾有 n 个 0, 那么这个数一定可以分解出 n 组 (2,5) 质数对
     * 时间复杂度 O(n*lgn)
     */
    public int trailingZeroes(int n) {
        int twoCount = 0, fiveCount = 0;
        for (int i = 2; i <= n; i++) {
            int num = i;
            while (num > 0 && num % 2 == 0) {
                num = num / 2;
                twoCount++;
            }
            while (num > 0 && num % 5 == 0) {
                num = num / 5;
                fiveCount++;
            }
        }
        return Math.min(twoCount, fiveCount);
    }
    /**
     * 一个数的末尾有 n 个 0, 那么这个数一定可以分解出 n 组 (2,5) 质数对
     * 在阶乘中, 每出现 x 个质因子 5, 必然出现多于 x 个对质因子 2, 所以我们只需要统计 5 的个数就够了
     *
     * 5, 10, 15, 20 都可以提供一个质因子, 而 25, 可以提供 2 个质因子, 30, 35, 40... 又只可以提供一个质因子, 125 可以提供 3 个质因子
     * 可提供不少于 1 个质因子整数都数量 + 可提供不少于 2 个质因子整数的数量 + ... + 可提供不少于 n 个质因子整数的数量
     * = [1...n] 序列中质因子 5 的总数量
     *
     * 时间复杂度 O(lgn)
     */
    public int trailingZeroes_v2(int n) {
       int fiveCount = 0;
       long base = 5;
       while (n >= base) {
           fiveCount += n/base;
           base *= 5;
       }
       return fiveCount;
    }

    public static void main(String[] args) {
        new Solution172().trailingZeroes_v2(Integer.MAX_VALUE);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
