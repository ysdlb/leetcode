//给定一个二进制字符串 s 和一个正整数 n，如果对于 [1, n] 范围内的每个整数，其二进制表示都是 s 的 子字符串 ，就返回 true，否则返回 
//false 。 
//
// 子字符串 是字符串中连续的字符序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "0110", n = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "0110", n = 4
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s[i] 不是 '0' 就是 '1' 
// 1 <= n <= 10⁹ 
// 
//
// Related Topics 字符串 👍 90 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1016 {
    /* 子串能表示从 1 到 N 数字的二进制串: https://leetcode.cn/problems/binary-string-with-substrings-representing-1-to-n/
     *
     * 求 [1,n] 二进制表达字符串的最短公共超序列 minCommon
     * 若 minCommon 为 s 的子序列，则 <s, n> 符合题目条件
     * 如何求最长公共超序列: 等价于最短公共子序列的决策路径
     *
     * 这里是子串，不是子序列
     * 所以直接 indexOf 或者 contains 就好
     *
     * 留存，直接暴力枚举, 也可以过
     */
    public boolean queryString(String s, int n) {
        for (int i = n; i > 0; i--) {
            if (!s.contains(Integer.toBinaryString(i)))
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
