//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab", p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// 1 <= p.length <= 30 
// s 只包含从 a-z 的小写字母。 
// p 只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 递归 字符串 动态规划 👍 2790 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution10 {
    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0);
    }

    /**
     * * 可以匹配 0 个, 或者匹配 n 个
     *
     * 对于 p 未到头, 但 s 到头的情况
     * 对于 s = "aa", p = ".*"
     * i = 2， j = 0 时, 是可以走 isMatch(s, 2, p, 2) 返回 true, 应该过
     *
     * 但对于 s = "aa", p = ".*c"
     * i = 2, j = 2 的时候, 是不可以做 s.charAt(i) 调用的, 会溢出, 不应该过
     *
     * 所以 p 未到头, s 到头 的情况不能算 base case, 应该走正常到代码逻辑
     * 对不应该过的情况, 在 i == s.len 的情况下返回 false 就好
     */
    private boolean isMatch(String s, int i, String p, int j) {
        if (j == p.length()) // 如果 p 到头了, 但 i 还没到头的话, 匹配失败
            return i == s.length();

        boolean firstMatch = i != s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if (j < p.length() - 1 && p.charAt(j+1) == '*') {
            return isMatch(s, i, p, j+2) // 匹配 0 个
            || (firstMatch && isMatch(s, i+1, p, j));
        } else {
            return firstMatch
                    && isMatch(s, i+1, p, j+1);
        }
    }

    public static void main(String[] args) {
        new Solution10().isMatch("aa", ".*c");
    }
}

class Solution10_DP {
    /**
     * 正推 DP
     * 设 f(i,j) 表示 s[0...i] 是否匹配模式串 p[0...j]
     * p 串匹配 n 个, 就相当于用 p 的一个模式, 抵消 s 串的几个字符
     *
     * 如果 p[j] == '*'
     * f(i,j) = f(i, j-2);
     * 匹配 0 个, s串一个未抵消, 可以认为 p串的 .* 不存在, 只判断 s[0...i] 匹配 p[0...j-2]
     *
     * 或者 f(i,j) = f(i-1,j) && (s[i] == p[j-1] || p[j-1] == '.')
     * 匹配 n 个, 在匹配 n-1 个基础上{f(i-1,j)} s[i] == p[j-1]
     *
     * .* 匹配 0 个，匹配 1 个，并不能递推到匹配 n 个
     * 匹配 0 个计算出来后, 匹配一个应当是在 匹配0个 的基础上多匹配一个, 即 j 不回退,
     * f(i-1,j) 比 f(i,j) 少匹配了一个 s[i], 我们只需要判断 s[i] == p[j-1] 是否成立就可以了
     *
     * 如果 p[j] != '*'
     * f(i,j) = (s[i] == p[j] || p[j] == '.') && f(i-1, j-1)
     *
     * base case
     * f(-1,-1) == true
     * 对于 s = "", p = ".*"
     * f(-1, 1) = f(-1, -1) = true    .* 抵消 0 个
     */
    public boolean isMatch(String s, String p) {
        // 代码从 1 开始
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        // base case
        dp[0][0] = true;
        for (int j = 1; j <= p.length(); j++)
            if (p.charAt(j-1) == '*')
                dp[0][j] = dp[0][j-2];

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j-1) == '*')
                    dp[i][j] = dp[i][j-2] // 匹配 0 个
//                            || ((dp[i-1][j-2]) && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j-2) == '.')); // 用两个匹配 1 or n 个
                            || ((dp[i-1][j]) && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j-2) == '.')); // 匹配 n 个, 在匹配 n-1 个的基础上
                else
                    dp[i][j] = dp[i-1][j-1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j-1) == '.') ;
            }
        }

        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        Solution10_DP dp = new Solution10_DP();
        System.out.println(dp.isMatch("aa", "a*"));
        System.out.println(dp.isMatch("aaa", "ab*a"));
        System.out.println(dp.isMatch("aab", "c*a*b"));
        System.out.println(dp.isMatch("aaa", "ab*a*c*a"));
        System.out.println(dp.isMatch("aa", "ab*a*c*"));
        System.out.println(dp.isMatch("a", "ab*a*c*"));
    }
}

//leetcode submit region end(Prohibit modification and deletion)
