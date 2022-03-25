//给你一个非负整数 x ，计算并返回 x 的 算术平方根 。 
//
// 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。 
//
// 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 4
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：x = 8
//输出：2
//解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= x <= 2³¹ - 1 
// 
// Related Topics 数学 二分查找 👍 938 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution69 {
    /**
     * 二分法
     *
     * 牛顿迭代法实现, 其收敛速度比二分法更快
     * x 的平分根, x/n == n 成立
     * 对任意的 n, 总有 x/n * n == x
     * 对 i = (x/n + n)/2, i 肯定比 n 更靠近 x 的平分根
     */
    public int mySqrt(int x) {
        return (int) sqrt(x);
    }

    private double sqrt(int x) {
        if (x == 0) return 0;
        double n = 1.0;
        double res = (x/n + n) / 2;
        while (res != n) {
            n = res;
            res = (x/n + n) / 2;
        }
        return res;
    }

    public static void main(String[] args) {
        double sqrt = new Solution69().sqrt(900000);
        System.out.println(sqrt);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
