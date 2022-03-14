//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。 
//
// 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "1 + 1"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：s = " 2-1 + 2 "
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：s = "(1+(4+5+2)-3)+(6+8)"
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 10⁵ 
// s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 
// s 表示一个有效的表达式 
// '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效) 
// '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的) 
// 输入中不存在两个连续的操作符 
// 每个数字和运行的计算将适合于一个有符号的 32位 整数 
// 
// Related Topics 栈 递归 数学 字符串 👍 715 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution224 {
    /*
     * 同 227
     * 采用栈来保存运算符后的值, 对于 +- 运算, 将其压入栈顶, 对于 乘除 运算, 将其与栈顶元素计算后替换栈顶元素的结果
     * 对于括号, 括号具有天然的递归性质, 一个括号内视为一个单独的表达式, 其结果视为单独的数字
     * 解释: 每次碰到左括号, 开始一个新表达式的计算, 括号的结尾同运算符与表达式结尾一样, 作为上一个数字结束的标志
     * 括号结尾的时候, 完成一个表达式的替换
     */
    public int calculate(String s) {
        Deque<Character> chars = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++)
            chars.offerLast(s.charAt(i));

        return calculate(chars);

    }

    private int calculate(Deque<Character> chars) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0; // 数字计算
        char sign = '+';
        while (!chars.isEmpty()) {
            char ch = chars.pollFirst();
            if (isDigital(ch)) {
                num = num * 10 + (ch - '0');
            } else if (ch == '(') { // 遇到左括号 递归计算, 且这对括号内容视为一个数字
                num = calculate(chars);
            }

            if (isSign(ch) || ch == ')' || chars.isEmpty()) {
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
            if (ch == ')') // 作为一个数字返回
                break;
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

    public static void main(String[] args) {
//        int r = new Solution224().calculate("(1+(4+5+2)-3)+(6+8)");
//        int r = new Solution224().calculate("1 + 1");
        int r = new Solution224().calculate("-2147483648");
        System.out.println(r);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
