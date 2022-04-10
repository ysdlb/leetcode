//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。 
//
// 整数除法仅保留整数部分。 
//
// 你可以假设给定的表达式总是有效的。所有中间结果将在 [-2³¹, 2³¹ - 1] 的范围内。 
//
// 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "3+2*2"
//输出：7
// 
//
// 示例 2： 
//
// 
//输入：s = " 3/2 "
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：s = " 3+5 / 2 "
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 10⁵ 
// s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开 
// s 表示一个 有效表达式 
// 表达式中的所有整数都是非负整数，且在范围 [0, 2³¹ - 1] 内 
// 题目数据保证答案是一个 32-bit 整数 
// 
// Related Topics 栈 数学 字符串 👍 539 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution227 {
    /**
     * 224 minus 版
     * 将所有的运算都转换为 '+' 法
     */
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        char sign = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isDigital(ch))
                // 小心溢出
                num = num*10 + (ch - '0');

            // 数字转换结束后对数字做处理
            // 条件是碰到后面的运算符或者处理到最后一个数字(也是最后一个字符)
            // 处理时判断的是上一个运算符
            // 记录新的符号
            if (isSign(ch) || i == s.length() - 1) {
                switch (sign) {
                    case '+' -> stack.push(num);
                    case '-' -> stack.push(-num);
                    case '*' -> stack.push(num * stack.pop());
                    case '/' -> stack.push(stack.pop() / num);
                    default -> throw new IllegalStateException("Unexpected value: " + sign);
                }
                sign = ch;
                num = 0;
            }
        }
        return stack.stream().mapToInt(Integer::intValue).sum();
    }

    private boolean isSign(char ch) {
        return ch == '+'
                || ch == '-'
                || ch == '*'
                || ch == '/';
    }
    private boolean isDigital(char ch) {
        return '0' <= ch && ch <= '9';
    }
}
//leetcode submit region end(Prohibit modification and deletion)
