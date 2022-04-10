//0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
// 
//
// 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。 
//
// 
//
// 示例 1： 
//
// 
//输入: n = 5, m = 3
//输出: 3
// 
//
// 示例 2： 
//
// 
//输入: n = 10, m = 17
//输出: 2
// 
//
// 
//
// 限制： 
//
// 
// 1 <= n <= 10^5 
// 1 <= m <= 10^6 
// 
// Related Topics 递归 数学 👍 542 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <a href="https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/">leetcode-offfer-62 约瑟夫环</a>
 * <a href="https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/">leetcode-1823 找出游戏获胜者</a>
 */
class SolutionOffer62 {
    /**
     * 假如第一个数组 F(n) [0,n-1] 杀掉了 (n-1)%m 位置的人
     * 那么下一个数组 F(n-1) 的排列就是 [(n-1)%m + 1, (n-1)%m + 2, ... , (n-1)%m-1] (n-1)%m 已被删掉
     *
     * 显然对 F(n-1) 中的每一个元素, F(n) 中相同元素的下标为 (f(n-1) + (m-1)%n + 1) % n = (f(n-1) + m) % n
     *
     * 设 f(n,m) 为被杀掉 n-1 个人后最终存活人员在当前数组中的索引,
     * 显而易见:
     *   1. f(n) 肯定是我们的原声数组, f(n,m), f(n-1,m), ... ,f(1,m) 都是最终存活的那个人在所属数组中的索引
     *   2. f(1,m) = 0
     * f(1)数组中 唯一的那个元素映射到 f(2) 数组: f(2,m) = (f(1,m) + m) % 2;
     * f(2)数组中 的那个元素映射到 f(3) 数组: f(3,m) = (f(2,m) + m) % 3;
     */
    public int lastRemaining(int n, int m) {
        int f = 0, mod = 1;
        while (mod < n) {
            mod++;
            f = (f+m) % mod;
        }
        return f;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
