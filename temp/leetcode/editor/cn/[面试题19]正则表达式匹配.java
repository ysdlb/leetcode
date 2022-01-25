//请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配
//是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3: 
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4: 
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5: 
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。 
// 
//
// 注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
// 
// Related Topics 递归 字符串 动态规划 👍 328 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * @see <a href="https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/">leetcode</a>
 */
class SolutionOffer19 {
    public boolean isMatch(String s, String p) {
        return match(s.toCharArray(), 0, p.toCharArray(), 0);
    }

    /**
     * @param s 文本串
     * @param p 模式串
     */
    private boolean match(char[] s, int s_i, char[] p, int p_i) {
        if (p.length-p_i  == 0)
            return s.length-s_i == 0;

        boolean first_match = s.length-s_i != 0
                && (p[p_i] == s[s_i] || p[p_i] == '.');
        // 考虑 * 的情况 (前字符重复 >= 1 次 || 前字符重复 0 次
        if (p.length - p_i >= 2 && p[p_i + 1] == '*') {
            return (first_match && match(s, s_i + 1, p, p_i))
                    || match(s, s_i, p, p_i + 2);
        } else  { // 非 * 时, 减去已经成功匹配的头部, 继续比较
            // 利用逻辑运算短路计算的特性, 把 first_match 放前面, 为 false 时, 后面不会计算
            return first_match && match(s, s_i+1, p, p_i+1);
        }
    }

    public static void main(String[] args) {
        boolean isM = new SolutionOffer19().isMatch("aab", "c*a*b");
        System.out.println(isM);
    }
}

class SolutionOffer19_LCS {
    /**
     * 求最长连续子序列时
     * f[i][j] = f[i-1][j-1]; if s[i] == p[i][j]
     * f[i][j] = false, if s[i] != p[j]
     *
     *
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        boolean[][] dp = new boolean[s.length()][p.length()];
        for (int i = 1; i < s.length(); i++) {
            for (int j = 1; j < p.length(); j++) {
                // 隐含条件 i < s.length
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    dp[i][j] = (i == 0 && j == 0);
                } else {
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean isM = new SolutionOffer19().isMatch("aab", "c*a*b");
        System.out.println(isM);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
