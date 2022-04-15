//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 👍 3177 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution20 {

    /**
     * 基础题, 利用栈的性质抵消匹配成功的括号
     */
    public boolean isValid(String s) {
        char[] stack = new char[s.length()];
        int index = -1;
        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{'); map.put(']', '['); map.put(')', '(');

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsValue(ch)) {
                stack[++index] = ch;
            } else {
                Character chM = map.get(ch);
                if (chM != null && stack[index] == chM)
                    index--;
                else
                    return false;
            }
        }
        // 栈为空, 则满足条件
        return index == -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
