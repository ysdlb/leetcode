//给你一个字符串 s ，请你判断它是否 有效 。
// 字符串 s 有效 需要满足：假设开始有一个空字符串 t = "" ，你可以执行 任意次 下述操作将 t 转换为 s ： 
//
// 
// 将字符串 "abc" 插入到 t 中的任意位置。形式上，t 变为 tleft + "abc" + tright，其中 t == tleft + 
//tright 。注意，tleft 和 tright 可能为 空 。 
// 
//
// 如果字符串 s 有效，则返回 true；否则，返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aabcbc"
//输出：true
//解释：
//"" -> "abc" -> "aabcbc"
//因此，"aabcbc" 有效。 
//
// 示例 2： 
//
// 
//输入：s = "abcabcababcc"
//输出：true
//解释：
//"" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
//因此，"abcabcababcc" 有效。 
//
// 示例 3： 
//
// 
//输入：s = "abccba"
//输出：false
//解释：执行操作无法得到 "abccba" 。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2 * 10⁴ 
// s 由字母 'a'、'b' 和 'c' 组成 
// 
// Related Topics 栈 字符串 👍 64 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1003 {
    /* 1003.检查替换后的词是否有效: https://leetcode.cn/problems/check-if-word-is-valid-after-substitutions/
     *
     * 这个题可以反复遍历数组, 不断清除连续的 abc 来做, 时间复杂度为 O(n)
     *
     * 这个串的特点就是 pattern 连续或者嵌套生成一个更大的 pattern
     * 对任意一个 pattern, 它的首字母肯定是 a, 然后可能嵌套若干个 pattern, 下一个字母是 b, 再嵌套若干个 pattern, 下一个字母是 c;
     *
     * 用栈更优雅，Java 可用 StringBuilder 来代替
     * 当前用方法递归模拟了一个栈的效果
     *
     * 留存: 1. 是否有效括号的栈实现 2. 是否有效括号的计数实现
     * 类似题目：20，32；22；921，1541
     */
    public boolean isValid(String s) {
        return valid(s, 0) == s.length();
    }
    private int valid(String s, int i) {
        while (i < s.length() && s.charAt(i) == 'a') {
            // 至少需要 3 个字符
            if (i + 2 >= s.length())
                return -1;

            i++;
            char ch = s.charAt(i);
            if (ch != 'b') {
                i = valid(s, i);
                if (i == -1 || i >= s.length() || s.charAt(i) != 'b')
                    return -1;
            }

            i++;
            ch = s.charAt(i);
            if (ch != 'c') {
                i = valid(s, i);
                if (i == -1 || i >= s.length() || s.charAt(i) != 'c')
                    return -1;
            }
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        String arg = "abcabcababcc";
        Solution1003 solution1003 = new Solution1003();
        solution1003.isValid(arg);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
