//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。 
//
// 换句话说，s1 的排列之一是 s2 的 子串 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
// 
//
// 示例 2： 
//
// 
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 10⁴ 
// s1 和 s2 仅包含小写字母 
// 
// Related Topics 哈希表 双指针 字符串 滑动窗口 👍 629 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 同 438
 */
class Solution567 {

    /**
     * 滑动窗口
     * 窗口扩张: 总是; 更新 find
     * 窗口收缩: 如果新入的右边界不在 need 中, 直接收缩至右边界 + 1
     *          如果在 need 中, 恰好组成异位词, 记录, 然后收缩 1 个 (abab, ab) 不能直接收缩至右边界 + 1
     *          如果某个词多了, 收缩, 直到这个词不多为止
     *          如果不够组成异位词, 不收缩;
     */
        public boolean checkInclusion(String p, String s) {
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
                return true;
            } else if (find[chR] > need[chR]) { // 如果某个词多了, 收缩, 直到这个词不多为止
                while (find[chR] > need[chR]) {
                    find[s.charAt(left) - 'a']--;
                    left++;
                }
            }
            // 不够组成异位词, 不收缩
            right++;
        }
        return false;
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
