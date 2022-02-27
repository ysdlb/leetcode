//请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。 
//
// 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// s.length <= 40000 
// 
//
// 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-
//repeating-characters/ 
// Related Topics 哈希表 字符串 滑动窗口 👍 369 👎 0


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer48 {
    /**
     * 记住右边界 和 窗口大小, 外加一个 map<element, occur_size>, 用距离计算
     * 我们总能确定窗口的右边沿, 在计算窗口的大小, 这两个条件就能唯一确定一个窗口
     *
     * 不必把注意力限制在不重复字符的窗口内, 只需要记录所有出现过的字符, 如果当前字符在
     * 之前出现过, 我们可以得到一个距离 d, 如果 d 大于当前窗口大小, 则窗口大小加 1;
     * 否则, 窗口大小变为 d
     */
    public int lengthOfLongestSubstring(String s) {
        int[] index = new int[26];
        Arrays.fill(index, -1);

        int cur_win_size = 0, max_win_size = 0;
        for (int i = 0; i < s.length(); i++) {
            int element = s.charAt(i) - 'a';
            int distance = i - index[element];
            if (index[element] < 0 || (distance > cur_win_size)) {
                cur_win_size++;
                max_win_size = Math.max(cur_win_size, max_win_size);
            } else {
                cur_win_size = distance;
            }
            index[element] = i;
        }
        return max_win_size;
    }
}

class SolutionOffer48_SET {
    /**
     * 记住左边界, 右边界, 外加一个集合, 如果出现重复, 从左往右删除窗口内的元素
     * 用左右边界更新最大窗口值
     */
    public int lengthOfLongestSubstring(String s) {
        int max_win_size = 0;
        Set<Character> set = new HashSet<>();
        for(int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            while(set.contains(c)) {
                set.remove(s.charAt(l++));
            }
            set.add(c);
            max_win_size = Math.max(max_win_size, r - l + 1);
        }

        return max_win_size;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
