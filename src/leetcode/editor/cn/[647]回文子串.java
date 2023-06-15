//给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。 
//
// 回文字符串 是正着读和倒过来读一样的字符串。 
//
// 子字符串 是字符串中的由连续字符组成的一个序列。 
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
// 
//
// 示例 2： 
//
// 
//输入：s = "aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 817 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution647_ZHONGXINTUOZHAN {
    /* 647.回文子串: https://leetcode.cn/problems/palindromic-substrings/
     * 相似题:
     *  5.最长回文子串: https://leetcode.cn/problems/longest-palindromic-substring/
     *
     * 相较于暴力法, 枚举出所有的字串, O(n^2), 在判断是否为回文串 O(n)
     * 中心拓展枚举所有的中心 O(n), 计算该中心的所有会文串 O(n)
     *
     * 注意会文串有单中心和双中心两种
     */
    public int countSubstrings(String s) {
        int ret = 0;
        // 频繁访问下, char 数组的性能比 string 好
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 单中心
            ret += count(chars, i, i);
            // 双中心
            ret += count(chars, i, i+1);
        }
        return ret;
    }

    private int count(char[] chars, int mid1, int mid2) {
        int ret = 0;
        while (mid1 >= 0 && mid2 < chars.length && chars[mid1] == chars[mid2]) {
            mid1--;
            mid2++;
            ret++;
        }
        return ret;
    }
}

class Solution647_BAOLI {
    /**
     * 暴力法, 求全部字串, 然后判断是不是回文串
     * n^2 * n = O(n^3)
     */
    public int countSubstrings(String s) {
        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isHuiWen(s.substring(i, j+1)))
                    ret++;
            }
        }
        return ret;
    }

    private boolean isHuiWen(String str) {
        if (str == null || str.length() == 0)
            return false;
        int l = 0, r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l++) != str.charAt(r--))
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
