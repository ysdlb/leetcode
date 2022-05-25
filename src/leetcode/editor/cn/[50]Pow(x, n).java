//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xⁿ ）。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100
// 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
// 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// -104 <= xⁿ <= 104 
// 
// Related Topics 递归 数学 👍 945 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution50 {
    /**
     * 快速幂, 类似题目: 372, 509
     *
     * 相同题目：Offer16
     * 类似题：面试题 08.05
     */
    public double myPow(double x, int n) {
        if (n < 0) {
            x = 1/x;
            return pow(x, -(n + 1)) * x;
        } else {
            return pow(x, n);
        }
    }

    /**
     * n > 0
     */
    private double pow(double x, int n) {
        double res = 1.0;
        while (n > 0) {
            if ((n & 1) == 1) {
                res *= x;
            }
            n /= 2;
            x *= x;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
