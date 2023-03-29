//给你一个整数 n，请返回长度为 n 、仅由元音 (a, e, i, o, u) 组成且按 字典序排列 的字符串数量。 
//
// 字符串 s 按 字典序排列 需要满足：对于所有有效的 i，s[i] 在字母表中的位置总是与 s[i+1] 相同或在 s[i+1] 之前。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 1
//输出：5
//解释：仅由元音组成的 5 个字典序字符串为 ["a","e","i","o","u"]
// 
//
// 示例 2： 
//
// 
//输入：n = 2
//输出：15
//解释：仅由元音组成的 15 个字典序字符串为
//["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"]
//注意，"ea" 不是符合题意的字符串，因为 'e' 在字母表中的位置比 'a' 靠后
// 
//
// 示例 3： 
//
// 
//输入：n = 33
//输出：66045
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 50 
// 
//
// Related Topics 数学 动态规划 组合数学 👍 120 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1641 {
    /*
     * 核心思想是拆分子问题
     *   ["a","e","i","o","u"]
     *   ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"]
     * 我们每一次都在前面追加一个字母, 这个追加的字母包含之前所有以大于等于该字母的所有字母为前缀的组合
     * f(i,j) 表示元素数量为 j 的集合, 组成长度为 i, 符合字典序列的所有组合数量
     *
     *   1,1,1,1,1
     *   1,2,3,4,5
     *   1,3,6,10,15
     *
     * f(i,j) 表示 j 个独立不同元素的情况下, i 长度的组合数量
     * f(1,5) = f(0,1) + f(0,2) + f(0,3) + f(0,4) + f(0,5)
     * f(2,5) = f(1,1) + f(1,2) + f(1,3) + f(1,4) + f(1,4)
     *
     * f(i,j) = f(i-1,j) + f(i,j-1);
     *
     * 二维数组可优化至一维
     */
    public int countVowelStrings(int n) {
        int NUM = 5;
        int[][] dp = new int[n+1][NUM+1];
        for (int i = 1; i <= NUM; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= NUM; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[n][NUM];
    }

    public static void main(String[] args) {
        Solution1641 solution = new Solution1641();
        System.out.println(solution.countVowelStrings(1));
        System.out.println(solution.countVowelStrings(2));
        System.out.println(solution.countVowelStrings(3));
        System.out.println(solution.countVowelStrings(33));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
