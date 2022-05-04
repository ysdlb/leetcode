//你的任务是计算 aᵇ 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。 
//
// 
//
// 示例 1： 
//
// 
//输入：a = 2, b = [3]
//输出：8
// 
//
// 示例 2： 
//
// 
//输入：a = 2, b = [1,0]
//输出：1024
// 
//
// 示例 3： 
//
// 
//输入：a = 1, b = [4,3,3,8,5,2]
//输出：1
// 
//
// 示例 4： 
//
// 
//输入：a = 2147483647, b = [2,0,0]
//输出：1198
// 
//
// 
//
// 提示： 
//
// 
// 1 <= a <= 2³¹ - 1 
// 1 <= b.length <= 2000 
// 0 <= b[i] <= 9 
// b 不含前导 0 
// 
// Related Topics 数学 分治 👍 269 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution372 {
    /**
     * 模运算推论: (a * b) % k = (a % k)(b % k) % k
     *
     * a^1564
     * = a^4 * (a^156)^10
     *
     * 分治做法, 递归分解
     * 思路正确，但是 double 也会溢出，所以需要自己写快速幂算法
     *
     * 类似题目: 50, 509
     */
    private static final int BASE = 1337;
    public int superPow(int a, int[] b) {
        if (b.length < 1)
            throw new RuntimeException("illegal param int[] b");
        return superPow(a%BASE, b, b.length - 1);
    }

    private int superPow(int a, int[] b, int i) {
        int x = pow(a, b[i]) % BASE;
        if (i == 0)
            return x;

        int y = pow(superPow(a, b, i-1), 10) % BASE;
        return (x * y) % BASE;
    }

    private int pow(int a, int b) {
        int ret = 1;
        int multiply = a;
        while (b != 0) {
            if ((b & 1) == 1) {
                ret = (multiply * ret) % BASE;
            }
            multiply = (multiply * multiply) % BASE;
            b >>= 1;
        }
        return ret;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
