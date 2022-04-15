//只有满足下面几点之一，括号字符串才是有效的： 
//
// 
// 它是一个空字符串，或者 
// 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者 
// 它可以被写作 (A)，其中 A 是有效字符串。 
// 
//
// 给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。 
//
// 
// 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。 
// 
//
// 返回 为使结果字符串 s 有效而必须添加的最少括号数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "())"
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：s = "((("
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 只包含 '(' 和 ')' 字符。 
// 
// Related Topics 栈 贪心 字符串 👍 130 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution921 {
    /**
     * 用栈将匹配的括号对一一消除, 剩下多少个单独的括号, 就需要多少配对的括号来补充
     * 这需要 O(n) 的空间复杂度, 我们要在时间复杂度不变的情况下, 将空间复杂度降低为 O(1)
     *
     * 括号匹配左括号可以无限添加, 后面总是可以出现右括号与其配对, 这部分需要的右括号数目记为 need
     * 但右括号一旦失配, 再也没有机会配置了; 这部分失配的记录为 has
     *
     * 括号匹配规则参考: <a href="https://leetcode-cn.com/problems/generate-parentheses/">leetcode-22</a>
     */
    public int minAddToMakeValid_v2(String s) {
        int need = 0;
        int has = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                need++;
            } else { // ch == ')'
                if (need > 0)
                    need--;
                else
                    has++;
            }
        }
        return need + has;
    }

    /**
     * 用栈将匹配的括号对一一消除, 剩下多少个单独的括号, 就需要多少配对的括号来补充
     */
    public int minAddToMakeValid(String s) {
        char[] stack = new char[s.length()];
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
//            if (ch == '(') {
//                stack[++index] = ch;
//            } else {
//                // ch == ')' 的情况
//                if (index >= 0 && stack[index] == '(')
//                    index--;
//                else
//                    stack[++index] = ch;
//            }
            // 对上Vj面 if else 的整理
            if (index >= 0 && ch == ')' && stack[index] == '(') {
                index--;
            } else {
                stack[++index] = ch;
            }
        }
        return index + 1;
    }

    public static void main(String[] args) {
        Solution921 solution921 = new Solution921();
        String arg = "()))((";
        solution921.minAddToMakeValid_v2(arg);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
