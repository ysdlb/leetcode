//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 5380 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution5 {
    /* 5.最长回文子串: https://leetcode.cn/problems/longest-palindromic-substring/
     * 相似题目:
     *  647.回文子串: https://leetcode.cn/problems/palindromic-substrings/
     *  1177.构建回文串检测: https://leetcode.cn/problems/can-make-palindrome-from-substring/
     * 简单做法, 从中心向两边扩展的做法
     * 以每个可能的中心(单中心或者双中心)扩展出最长的回文串
     * 然后从这些里面再找出最长的
     */
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty())
            throw new IllegalArgumentException("inValid args");

        int max = 0;
        String ret = null;
        for (int i = 0; i < s.length(); i++) {
            // 单中心
            int[] edgeSingle = checkPalindrome(s, i, i);
            if (edgeSingle[1] - edgeSingle[0] - 1 > max) {
                max = edgeSingle[1] - edgeSingle[0] - 1;
                ret = s.substring(edgeSingle[0] + 1, edgeSingle[1]);
            }

            // 双中心
            if (i != s.length() - 1 && s.charAt(i) == s.charAt(i+1)) {
                int[] edgeDouble = checkPalindrome(s, i, i+1);
                if (edgeDouble[1] - edgeDouble[0] - 1 > max) {
                    max = edgeDouble[1] - edgeDouble[0] - 1;
                    ret = s.substring(edgeDouble[0] + 1, edgeDouble[1]);
                }

            }
        }
        return ret;
    }

    private int[] checkPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return new int[]{left, right};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
