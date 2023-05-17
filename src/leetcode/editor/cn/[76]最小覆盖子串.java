//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 10⁵ 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 👍 1773 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution76 {
    /**
     * need 数组表示 t 中所有字符以及字符的数量
     * find 数组表示 s 串窗口内找到的字符以及字符数量
     *
     * 窗口扩张: 总是; 每次更新 find 数组
     * 窗口收缩: 如果移除左边界的字符后, 更新后的 find 还满足 need, 那么就移除左边界; 知道上述条件不成立为止
     * 额外操作: 在符合条件的前提下, 更新最小字串长度, 如果有新的最小, 记录下这个字串
     */
    public String minWindow(String s, String t) {
        int[] need = new int[128];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i) - ' ']++;
        }

        int[] find = new int[128];
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        String ret = "";
        while (right < s.length()) {
            // 窗口扩张
            find[s.charAt(right) - ' ']++;

            // 窗口收缩
            if (isTrue(find, need)) {
                int ch;
                while (find[(ch = s.charAt(left) - ' ')] > need[ch]) {
                    find[ch]--;
                    left++;
                }

                // 额外操作
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    ret = s.substring(left, right+1);
                }
            }
            right++;
        }
        return ret;
    }

    private boolean isTrue(int[] find, int[] need) {
        for (int i = 0; i < need.length; i++) {
            if (find[i] < need[i])
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
