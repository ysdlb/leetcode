//给你一个字符串列表 words 和一个目标字符串 target 。words 中所有字符串都 长度相同 。 
//
// 你的目标是使用给定的 words 字符串列表按照下述规则构造 target ： 
//
// 
// 从左到右依次构造 target 的每一个字符。 
// 为了得到 target 第 i 个字符（下标从 0 开始），当 target[i] = words[j][k] 时，你可以使用 words 列表中第 j 
//个字符串的第 k 个字符。 
// 一旦你使用了 words 中第 j 个字符串的第 k 个字符，你不能再使用 words 字符串列表中任意单词的第 x 个字符（x <= k）。也就是说，所
//有单词下标小于等于 k 的字符都不能再被使用。 
// 请你重复此过程直到得到目标字符串 target 。 
// 
//
// 请注意， 在构造目标字符串的过程中，你可以按照上述规定使用 words 列表中 同一个字符串 的 多个字符 。 
//
// 请你返回使用 words 构造 target 的方案数。由于答案可能会很大，请对 10⁹ + 7 取余 后返回。 
//
// （译者注：此题目求的是有多少个不同的 k 序列，详情请见示例。） 
//
// 
//
// 示例 1： 
//
// 
//输入：words = ["acca","bbbb","caca"], target = "aba"
//输出：6
//解释：总共有 6 种方法构造目标串。
//"aba" -> 下标为 0 ("acca")，下标为 1 ("bbbb")，下标为 3 ("caca")
//"aba" -> 下标为 0 ("acca")，下标为 2 ("bbbb")，下标为 3 ("caca")
//"aba" -> 下标为 0 ("acca")，下标为 1 ("bbbb")，下标为 3 ("acca")
//"aba" -> 下标为 0 ("acca")，下标为 2 ("bbbb")，下标为 3 ("acca")
//"aba" -> 下标为 1 ("caca")，下标为 2 ("bbbb")，下标为 3 ("acca")
//"aba" -> 下标为 1 ("caca")，下标为 2 ("bbbb")，下标为 3 ("caca")
// 
//
// 示例 2： 
//
// 
//输入：words = ["abba","baab"], target = "bab"
//输出：4
//解释：总共有 4 种不同形成 target 的方法。
//"bab" -> 下标为 0 ("baab")，下标为 1 ("baab")，下标为 2 ("abba")
//"bab" -> 下标为 0 ("baab")，下标为 1 ("baab")，下标为 3 ("baab")
//"bab" -> 下标为 0 ("baab")，下标为 2 ("baab")，下标为 3 ("baab")
//"bab" -> 下标为 1 ("abba")，下标为 2 ("baab")，下标为 3 ("baab")
// 
//
// 示例 3： 
//
// 
//输入：words = ["abcd"], target = "abcd"
//输出：1
// 
//
// 示例 4： 
//
// 
//输入：words = ["abab","baba","abba","baab"], target = "abba"
//输出：16
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 1000 
// 1 <= words[i].length <= 1000 
// words 中所有单词长度相同。 
// 1 <= target.length <= 1000 
// words[i] 和 target 都仅包含小写英文字母。 
// 
//
// Related Topics 数组 字符串 动态规划 👍 27 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1639 {
    /* 通过给定词典构造目标字符串的方案数: https://leetcode.cn/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/
     * 相似题目:
     *   1353.最多可以参加的会议数目: https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/
     *
     * 等长的 words 列表等价于在长度为 i 的序列中，在某索引位置有 words.length 个字符可选
     * 然后用它的子序列构建目标字符串 target 有多少种构建方案, target 的索引为 j
     *
     * f(i,j) 为序列长度为 i，构建长度为 j 的 target 的方案数量
     *   1<=i<=word[0].length, 1<=j<=target.length
     * map[i-1][target[j-1]] 第 i 位置, target 第 j 个字符出现的次数
     *
     * 每一个 i 我们都可以选择使用或者放弃
     *  1. 使用
     *   f(i,j) = f(i-1,j-1)*map[i-1][target[j-1])
     *  2. 放弃
     *   f(i,j) = f(i-1,j)
     *  1<=i<=word[0].length, 1<=j<=target.length
     *  两者取和
     *
     * 因为 i 总是依赖 i-1; 所以 dp 的 i 维度可以省略
     * 最终值为 f(word[0].len, target.len)
     *
     * 时间复杂度 O(NM), 空间复杂度 O(M)
     */
    public int numWays(String[] words, String target) {
        if (words == null || words.length == 0) return 0;

        int E = 1000000007;
        int n = words[0].length();
        int m = target.length();
        int[][] map = new int[n][26];
        for (String word : words) {
            for (int i = 0; i < n; i++) {
                map[i][word.charAt(i) - 'a']++;
            }
        }

        // f(i,j) = f(i-1,j-1)*map[i-1][target[j-1])
        // f(i,j) = f(i-1,j)
        // 因为 i 总是依赖 i-1; 所以 dp 的 i 维度可以省略
        // j 会依赖上一轮 i-1 的更小的值，所以 j 要从大到下遍历
        long[] dp = new long[m+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                dp[j] = (dp[j] + dp[j - 1]*map[i - 1][target.charAt(j - 1) - 'a']) % E;
            }
        }
        return (int)dp[m];
    }

    public static void main(String[] args) {
        Solution1639 solution = new Solution1639();
        String[] arg = new String[]{"abba", "baab"};
        int i = solution.numWays(arg, "bab");
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
