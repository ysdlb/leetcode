//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 10⁴ 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 👍 1783 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution32 {
    /**
     * 时间复杂度为 O(n), 空间复杂度为 O(n) 的解法
     * 纯栈解法，留存
     * 空间复杂度 O(1) 的解法稍微参考 22, 921, 1541
     *
     * 有效括号字串一定是可以全部抵消的
     * 设置一个同 s 长度的字符数组, 未抵消的值为 0, 抵消值为 1
     *
     * 最长有效括号字串就是最长连续 0 的个数
     */
    public int longestValidParentheses(String s) {
        char[] signMap = new char[s.length()];
        // 括号匹配的栈中只放 '(', 没有其他元素, 所以我们可以放索引
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else if (!stack.isEmpty()) {
                signMap[stack.pop()] = 1;
                signMap[i] = 1;
            } // stack 为空, 开始下一轮括号匹配了
        }

        int ret = 0;
        for (int i = 0; i < signMap.length; i++) {
            int count = 0;
            while (i < signMap.length && signMap[i] == 1) {
                i++;
                count++;
            }
            ret = Math.max(ret, count);
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
