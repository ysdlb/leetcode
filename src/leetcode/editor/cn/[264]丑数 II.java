//给你一个整数 n ，请你找出并返回第 n 个 丑数 。 
//
// 丑数 就是只包含质因数 2、3 和/或 5 的正整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 10
//输出：12
//解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
//解释：1 通常被视为丑数。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 1690 
// 
//
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 👍 1074 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution254 {
    /* 264.丑数 II: https://leetcode.cn/problems/ugly-number-ii/
     * 相似题目:
     *  373.查找和最小的 K 对数字: https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/
     * 相似描述:
     *  263.丑数: https://leetcode.cn/problems/ugly-number/
     *
     * 本质上是只由 2,3,5 质因数组成的 3 个有序数组
     *  在原有丑数数组的基础上, 每个元素分别乘以 2,3,5 得到的 3 个数组
     *  比原来 max 更大的最小数就是下一个丑数
     *
     * 3 路归并, 依次取最小值
     */

    public int nthUglyNumber(int n) {
        int[] ans = new int[n];
        ans[0] = 1;

        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < n; i++) {
            int ugly2 = ans[i2]*2, ugly3 = ans[i3]*3, ugly5 = ans[i5]*5;

            int ugly = Math.min(ugly2, Math.min(ugly3, ugly5));
            if (ugly2 == ugly) i2++;
            if (ugly3 == ugly) i3++;
            if (ugly5 == ugly) i5++;

            ans[i] = ugly;
        }
        return ans[n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
