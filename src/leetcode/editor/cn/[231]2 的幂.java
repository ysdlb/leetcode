//给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。 
//
// 如果存在一个整数 x 使得 n == 2ˣ ，则认为 n 是 2 的幂次方。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 1
//输出：true
//解释：2⁰ = 1
// 
//
// 示例 2： 
//
// 
//输入：n = 16
//输出：true
//解释：2⁴ = 16
// 
//
// 示例 3： 
//
// 
//输入：n = 3
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：n = 4
//输出：true
// 
//
// 示例 5： 
//
// 
//输入：n = 5
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// -2³¹ <= n <= 2³¹ - 1 
// 
//
// 
//
// 进阶：你能够不使用循环/递归解决此问题吗？ 
// Related Topics 位运算 递归 数学 👍 468 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <a href="https://leetcode-cn.com/problems/power-of-two/">leetcode-231</a>
 */
class Solution231 {
    /**
     * 不使用循环/递归判断 n 是否为 2 的幂
     * 参考 jdk8 hashmap 如何将初始容量向上取整到最近的 2 的幂上
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return true;
        return n == powerOfTwo(n);
    }

    /**
     * 其实只要判断正数二进制是否只有一位是 1 就行
     *
     * n & (n-1) 可以直接将最低位 1 移除
     */
    public boolean isPowerOfTwo_v2(int n) {
        return n > 0 && (n & (n-1)) == 0;
    }

    /**
     * 其实只要判断正数二进制是否只有一位是 1 就行
     *
     * n & (-n) 可以获取二进制表示的最低位的 1
     */
    public boolean isPowerOfTwo_v3(int n) {
        return n > 0 && (n & -n) == n;
    }

    private int powerOfTwo(int n) {
        n = n-1;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        return n+1;
    }

    public static void main(String[] args) {
        Solution231 o = new Solution231();
        System.out.println(o.powerOfTwo(-6));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
