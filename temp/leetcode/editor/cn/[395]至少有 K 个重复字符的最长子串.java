//给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aaabb", k = 3
//输出：3
//解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
// 
//
// 示例 2： 
//
// 
//输入：s = "ababbc", k = 2
//输出：5
//解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由小写英文字母组成 
// 1 <= k <= 10⁵ 
// 
// Related Topics 哈希表 字符串 分治 滑动窗口 👍 662 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution395 {
    /**
     * 首先是符合要求的子串怎么找
     * 1. 两个不符合要求的子串可能合并成一个符合要求的子串
     * 2. 两个符合要求的子串一定可以合并成一个符合要求的子串
     * 3. 符合要求和不符合要求的两个子串可能合并成符合要求的子串
     *
     * 换一个思路
     * 对任意一个串, 如果有一个字符出现次数少于 k 次, 那么符合要求的串只可能存在在
     * 以该字符为分割点的字串中
     * 由整体到局部
     */
    public int longestSubstring(String s, int k) {
        return longestSubstring(s.toCharArray(), k, 0, s.length()-1);
    }

    public int longestSubstring(char[] s, int k, int l, int r) {
        if (r-l+1 < k) return 0;

        int[] map = new int[26];
        Set<Character> set = new HashSet<>();
        for (int i = l; i <= r; i++) {
            map[s[i] - 'a'] ++;
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0 && map[i] < k)
                set.add((char)('a' + i));
        }
        if (set.isEmpty()) {
            return r - l + 1;
        }

        List<int[]> split = split(s, k, set, l, r);
        int ret = 0;
        for (int[] e: split) {
            ret = Math.max(ret, longestSubstring(s, k, e[0], e[1]));
        }
        return ret;
    }

    private List<int[]> split(char[] s, int k, Set<Character> set, int l, int r) {
        List<int[]> ret = new ArrayList<>();
        while (l <= r) {
            while (l <= r && set.contains(s[l]))
                l++;
            int start = l;

            while (l <= r && !set.contains(s[l]))
                l++;
            int end = l-1;
            if (end - start + 1 >= k)
                ret.add(new int[]{start, end});
        }
        return ret;
    }

    public static void main(String[] args) {
        new Solution395().longestSubstring("aaabb", 3);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
