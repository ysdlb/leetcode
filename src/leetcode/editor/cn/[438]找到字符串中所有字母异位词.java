//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 836 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 同 567
 */
class Solution438 {
    /**
     * 滑动窗口
     * 窗口扩张: 总是; 更新 find
     * 窗口收缩: 如果新入的右边界不在 need 中, 直接收缩至右边界 + 1
     *          如果在 need 中, 恰好组成异位词, 记录, 然后收缩 1 个 (abab, ab) 不能直接收缩至右边界 + 1
     *          如果某个词多了, 收缩, 直到这个词不多为止
     *          如果不够组成异位词, 不收缩;
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ret = new ArrayList<>();

        int[] need = new int[26];
        for (int i = 0; i < p.length(); i++)
            need[p.charAt(i) - 'a']++;

        int[] find = new int[26];
        int left = 0, right = 0;
        while (right < s.length()) {
            int chR = s.charAt(right) - 'a';
            find[chR] ++;
            if (need[chR] == 0) {  // 如果新入的右边界不在 need 中, 直接收缩至右边界 + 1
                left = right + 1;
                Arrays.fill(find, 0);
            } else if (checkAllFind(find, need)) {  // 如果在 need 中, 恰好组成异位词, 记录, 然后收缩
                ret.add(left);
                // 只是单纯破坏掉这个异位状态
                find[s.charAt(left) - 'a']--;
                left++;
            } else if (find[chR] > need[chR]) { // 如果某个词多了, 收缩, 直到这个词不多为止
                while (find[chR] > need[chR]) {
                    find[s.charAt(left) - 'a']--;
                    left++;
                }
            }
            // 不够组成异位词, 不收缩
            right++;
        }

        return ret;
    }

    private boolean checkAllFind(int[] find, int[] need) {
        for (int i = 0; i < need.length; i++) {
            if (find[i] != need[i])
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
