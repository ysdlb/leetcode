//如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。 
//
// 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。 
//
// 
//
// 示例 1： 
//
// 输入：text = "ababa"
//输出：3
// 
//
// 示例 2： 
//
// 输入：text = "aaabaaa"
//输出：6
// 
//
// 示例 3： 
//
// 输入：text = "aaabbaaa"
//输出：4
// 
//
// 示例 4： 
//
// 输入：text = "aaaaa"
//输出：5
// 
//
// 示例 5： 
//
// 输入：text = "abcdef"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text.length <= 20000 
// text 仅由小写英文字母组成。 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 176 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1156 {
    /* 1156.单字符重复子串的最大长度: https://leetcode.cn/problems/swap-for-longest-repeated-character-substring/
     * 相似题目:
     *  3.无重复字符的最长子串: https://leetcode.cn/problems/longest-substring-without-repeating-characters/     *
     *
     * 滑动窗口内保证不重复字符不超过 1 个, 很容易做出来, 但是我们用把 26 个字母都按这个思路走一遍流程吗
     *
     * 滑动窗口值统计连续字符, 额外一个循环向外扩展求间隔一个非重复字符向外扩展的最大长度
     * 时间复杂度高于 O(n) 了, 但常数很低
     */
    public int maxRepOpt1(String text) {
        char[] chars = text.toCharArray();
        int[] count = new int[26];
        for (char ch: chars)
            count[ch - 'a']++;

        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            int j = i+1;
            while (j < chars.length && chars[j] == chars[i])
                j++;
            // 此时 j 为非重复字符

            int k = j+1;
            while (k < chars.length && chars[k] == chars[i])
                k++;
            // j 与 k 都是右边的开区间
            int len = k-i-1;
            len += count[chars[i]-'a'] > len ? 1 : 0;
            ans = Math.max(ans, len);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
