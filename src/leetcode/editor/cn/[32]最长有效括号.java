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
     * 贪心思路, 空间复杂度 O(1)
     * 该思路参考 22, 921, 1541
     *
     * 对于一组合法的括号, 遍历过程中一定是 left >= right;
     * 贪心的想, 如果它一直是 left >= right,
     * 它现在可能不合法 ()((), 但它未来可能合法, 也可能合法 ((()), 合法长度为 2*right
     *
     * 但如果是 left = right, 它目前一定合法, 合法长度为 2*right
     * 一旦 left < right, 那么它的合法性就中断了, 需要重新计数
     *
     * 主要是 left > right 时, 无法判断是否真的合法, 不计算它的长度可能会漏掉合法的
     * 解决办法就是反向再计算一遍
     */
    public int longestValidParentheses_v3(String s) {
        int left = 0, right = 0;
        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
                if (left == right) {
                    ret = Math.max(ret, 2*right);
                } else if (left < right) {
                    left = 0;
                    right = 0;
                }
            }
        }

        left = 0; right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                left++;
            } else {
                right++;
                if (left == right) {
                    ret = Math.max(ret, 2*right);
                } else if (left < right) {
                    left = 0;
                    right = 0;
                }
            }
        }
        return ret;
    }

    /**
     * 时间复杂度为 O(n), 空间复杂度为 O(n) 的解法
     * 纯栈解法，留存
     * 空间复杂度 O(1) 的解法稍微参考 22, 921, 1541
     *
     * 辅助栈解法
     * 始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
     *
     * 对于遇到的每个 ( ，我们将它的下标放入栈中
     * 对于遇到的每个 ) ，我们先弹出栈顶元素表示匹配了当前右括号：
     *     如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
     *     如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
     *
     * 需要注意的是，如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的
     * 「最后一个没有被匹配的右括号的下标」，为了保持统一，我们在一开始的时候往栈中放入一个值为 -1 的元素。
     */
    public int longestValidParentheses_v2(String s) {
        // 括号匹配的栈中只放 '(', 没有其他元素, 所以我们可以放索引
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int ret = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    // 因为最先放进了一个不可能匹配的 -1, 所以如果 stack 为空, 栈底为最后一个失配的右括号
                    stack.push(i);
                } else {
                    ret = Math.max(ret, i - stack.peek());
                }
            }
        }
        return ret;
    }

    /**
     * 时间复杂度为 O(n), 空间复杂度为 O(n) 的解法
     * 纯栈解法，留存
     * 空间复杂度 O(1) 的解法稍微参考 22, 921, 1541
     *
     * 辅助栈 + 辅助数组的解法
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
