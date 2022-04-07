//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。 
//
// 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而
//"aec"不是）。 
//
// 进阶： 
//
// 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代
//码？ 
//
// 致谢： 
//
// 特别感谢 @pbrother 添加此问题并且创建所有测试用例。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abc", t = "ahbgdc"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "axc", t = "ahbgdc"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 100 
// 0 <= t.length <= 10^4 
// 两个字符串都只由小写字符组成。 
// 
// Related Topics 双指针 字符串 动态规划 👍 625 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution392 {
    /**
     * 假设
     * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，
     * 你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     *
     * 如何用空间换时间
     * 我们有大量的时间用于在 t 中找到下一个匹配字符。
     * 用 f[j][a...z] 表示 [a...z] 在 t 中 位置 j 之后出现的位置, 预处理下
     *
     * 解法留存
     */
    public boolean isSubsequence_v2(String s, String t) {
        return false;
    }

    /**
     * 迭代 s 和 t, 只有 s[i] == t[j] 时候, 才可以 i++
     * 如果 i 到达 s 的终点了, 则证明 s 是 t 的子序列; 否则不是
     *
     * 这里用到了贪心的思想
     */
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j))
                i++;
            j++;
        }
        return i == s.length();
    }

}
//leetcode submit region end(Prohibit modification and deletion)
