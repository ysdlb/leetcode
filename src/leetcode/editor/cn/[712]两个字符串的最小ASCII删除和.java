//给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。 
//
// 
//
// 示例 1: 
//
// 
//输入: s1 = "sea", s2 = "eat"
//输出: 231
//解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
//在 "eat" 中删除 "t" 并将 116 加入总和。
//结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
// 
//
// 示例 2: 
//
// 
//输入: s1 = "delete", s2 = "leet"
//输出: 403
//解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
//将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
//结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
//如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
// 
//
// 
//
// 提示: 
//
// 
// 0 <= s1.length, s2.length <= 1000 
// s1 和 s2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 257 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <a href="https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings/">leetcode-712></a>
 */
class Solution712 {
    /*
     * 思路一
     * 其它 LCS 问题 1143, 583
     * LCS 问题小变种
     * 回顾下 LCS 的解法
     * 设 f(i,j) 为 text1[0...i] 与 text2[0...j] 的最长公共子序列
     * 那么对于 text1[i] == text2[j], i,j 都可以添加进公共子序列里
     * f(i,j) = f(i-1,j-1) + 1
     *
     * 对于 text1[i] != text2[j], i,j 至少有一个不可能添加进公共子序列里
     * f(i,j) = max{
     *      f(i-1,j)   i 不添加
     *      f(i,j-1)   j 不添加
     *      f(i-1,j-1) 都不添加
     * }
     * 因为 f(i,j) >= f(i-1,j-1) 所以它可以忽略
     * base
     * 从 0 开始, 遍历从第一个字母开始
     * 一对字符串的 LCS 可能有多条("abd", "adb"), 我们只需要找到符合条件的那一条
     *
     *
     * 思路二
     * 直接设 f(i,j) 为 text1[0...i] 与 text2[0...j] 相等所需要删除的 ASCII 码最小和
     * 那么对于 text1[i] == text2[j], i,j 都可以添加进公共子序列里
     * f(i,j) = f(i-1,j-1)
     * 对于 text1[i] != text2[j], i,j 至少有一个不可能添加进公共子序列里, 即至少有一个肯定会被删除
     * f(i,j) = min {
     *      f(i-1,j) + text1[i]
     *      f(i,j-1) + text2[j]
     *      f(i-1,j-1) + text2[j] + text1[i]
     * }
     * f(i-1,j-1) + text2[j] + text1[i] >= f(i,j) 肯定成立(从左上到右下都在加)
     * 所以可以忽略
     * base case
     * f(i,0) = sum text1[i]
     * f(0,j) = sum text2[j]
     *
     * 简单概括
     * 1.s1[i-1] == s2[j-1]，新增的两个字符相等的情况下，没有必要删除之前的结果，因此dp[i][j] = dp[i-1][j-1]
     * 2.s1[i-1] != s2[j-1]，取三者的最小值
     *  （1）保留s2串，删除s1串的字符，dp[i][j] = dp[i-1][j] + s1.charAt(i-1)
     *  （2）保留s1串，删除s2串的字符，dp[i][j] = dp[i][j-1] + s1.charAt(j-1)
     *  （3）删除s1、s2串的字符，dp[i][j] = dp[i-1][j-1] + s1.charAt(i-1) + s2.charAt(j-1)
     */
    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        // base case
        for (int i = 1; i <= s1.length(); i++)
            dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
        for (int j = 1; j <= s2.length(); j++)
            dp[0][j] = dp[0][j-1] + s2.charAt(j-1);

        for (int i = 1; i <= s1.length(); i++) {
            char ch1 = s1.charAt(i-1);
            for (int j = 1; j <= s2.length(); j++) {
                char ch2 = s2.charAt(j-1);
                if (ch1 == ch2)
                    dp[i][j] = dp[i-1][j-1];
                else {
                    dp[i][j] = Math.min(
                            dp[i-1][j] + ch1,
                            dp[i][j-1] + ch2
                    );
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
