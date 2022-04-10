//给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 2186 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <a href="https://leetcode-cn.com/problems/edit-distance/">leetcode-72</a>
 */
class Solution72 {
    /*
     * 对两个字符串 s1 和 s2,
     *  在 s1 替换等价于在 s2 替换;
     *  在 s1 删除等价于在 s2 插入;
     *  在 s1 插入等价与在 s2 删除
     * 所以 s1 变 s2 还是 s2 变 s1 在操作次数上是等价的, 这里只讨论把 s1 变 s2
     *
     * 回到 LCS 问题上, 一般对字符串对操作用两个游标来表示 (i,j)
     * 设 f(i,j) 为 s1[0...i] 变为 s2[0...j] 所需要的最少操作次数
     *
     * 那么假如 s1[i] == s2[j]
     * 无需任何操作 ==>
     *      f(i,j) = f(i-1,j-1) when i >= 1 && j >= 1
     *      f(i,j) = i+1 or j+1 who in (i,j) = 0     ;base case
     *
     * 假如 s1[i] != s2[j]
     * 我们有三种可选方案, 在 s1 删除; 在 s1 插入; 在 s1 替换, 我们选代价开销最小的
     *      f(i,j) = min {
     *          f(i-1,j) + 1,   // s1[0...i-1] 经过 f(i-1,j) 步已经变得和 s2[0...j] 相同的情况下, s1[i] 是多余的, 删掉它就好了
     *          f(i,j-1) + 1,   // s1[0...i] 和 s2[0...j-1] 是相同的情况下, 最后少了一个字符和 s2[j] 匹配, 插入一个
     *          f(i-1,j-1) + 1  // s1[0...i-1] 和 s2[0...j-1] 相同的情况下, 我们把 s1[i] 替换成 s2[j]
     *      }
     *
     * base case:
     * f(-1,j) 表示空串 s1 变成 s2[0...j] 需要的步骤, 显然需要插入 [0...j] 共 j+1 个 s2 的字母到 s1
     * f(i,-1) 表示 s1[0...i] 变成空串 s2 需要的步骤, 显然需要删除 [0...i] 共 i+1 个 s1 的字母
     *
     * 举个例子 f(-1,-1) = 0, f(-1,0) = 1, f(0,-1) = 1
     * 假如 s1[0] == s2[0]
     *  f(0,0) = f(-1,-1) = 0
     * 假如 s1[0] != s2[0]
     *  f(0,0) = min{
     *      f(-1,0) + 1,    // 在 s1[0] 前面的位置插入 s2[0] 字符 (base case f(-1,j) 的定义), 然后删除 s1[0] (递推公式的定义)
     *      f(0,-1) + 1,    // 删除 s1[0] 字符, 变得和空串 s2[0...-1] 一样(base case f(i,-1) 的定义), 然后插入和 s2[0] 一样的字符 s1[0] (递推公式的定义)
     *      f(-1,-1) + 1    // 空串 s1[.] 和空串 s2[.] (base case 的定义） 然后替换 s1[0] 和  s2[0] 一样
     *  } = 0+1 = 1;
     *
     * 形势和 LCS 差不多, 因为 LCS 求最大, f(-1,-1) 肯定是较小的, 可以不用与计算最大值
     * 这里要计算最小值, 所以要比较三个值的最小.
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        // base case
        for (int i = 0; i <= word1.length(); i++)
            dp[i][0] = i;
        for (int j = 0; j <= word2.length(); j++)
            dp[0][j] = j;

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) // i-1 == -1
                    dp[i][j] = dp[i-1][j-1];
                else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
