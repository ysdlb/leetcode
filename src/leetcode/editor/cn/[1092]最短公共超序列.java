//给出两个字符串 str1 和 str2，返回同时以 str1 和 str2 作为子序列的最短字符串。如果答案不止一个，则可以返回满足条件的任意一个答案。 
//
// （如果从字符串 T 中删除一些字符（也可能不删除，并且选出的这些字符可以位于 T 中的 任意位置），可以得到字符串 S，那么 S 就是 T 的子序列） 
//
// 
//
// 示例： 
//
// 输入：str1 = "abac", str2 = "cab"
//输出："cabac"
//解释：
//str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。 
//str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
//最终我们给出的答案是满足上述属性的最短字符串。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= str1.length, str2.length <= 1000 
// str1 和 str2 都由小写英文字母组成。 
// 
//
// Related Topics 字符串 动态规划 👍 194 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

// https://leetcode.cn/problems/shortest-common-supersequence/
class Solution1092 {

    /*
     * 类似题目:
     *   [1142]最长公共子序列
     *
     * 最短公共超序列其实就是最长公共子序列的路径
     *
     * 对于串 s1, s2, 有 dp[i][j] 表示串 s1[1...i] 和 s2[1...j] 的最长公共子序列长度
     *   s1[i] == s2[j],    dp[i][j] = dp[i-1][j-1] + 1
     *   s1[i] != s2[j],    dp[i][j] = max{dp[i-1][j], dp[i][j-1]}
     * 初始条件:
     *   dp[0,0...j] = 0
     *   dp[0...i,0] = 0
     *
     * 根据最长公共子序列的路径反推回去，即最长公共超序列
     * 优先判断 s1[i] == s2[j], 如果是, 直接放入超序列的和中, i--,j--
     * 其次判断 dp[i][j] == dp[i-1][j] 或 dp[i][j-1]; i-- 或 j--
     */
    public String shortestCommonSupersequence(String str1, String str2) {
        int[][] dp = new int[str1.length()+1][str2.length()+1];
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // 根据最长公共子序列的路径反推回去，即最长公共超序列
        // 理论上最短超序列序列的长度 = str1.length + str2.length - maxSubLen
        int i = str1.length(), j = str2.length();
        char[] ret = new char[i+j-dp[i][j]];
        int index = ret.length - 1;
        while (i > 0 && j > 0) {
            if (str1.charAt(i-1) == str2.charAt(j-1)) {
                ret[index--] = str1.charAt(i-1);
                i--;
                j--;
            } else if (dp[i][j] == dp[i-1][j]) {
                ret[index--] = str1.charAt(i-1);
                i--;
            } else {
                ret[index--] = str2.charAt(j-1);
                j--;
            }
        }

        if (i == 0) {
            for (int k = j; k > 0; k--) {
                ret[index--] = str2.charAt(k-1);
            }
        } else {
            for (int k = i; k > 0; k--) {
                ret[index--] = str1.charAt(k-1);
            }
        }
        return new String(ret);
    }

    public static void main(String[] args) {
        Solution1092 solution = new Solution1092();
        String test1 = solution.shortestCommonSupersequence("test1", "teabsc");
        System.out.println(test1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
