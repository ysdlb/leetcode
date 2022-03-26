//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
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
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 7216 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution3 {
    /**
     * 无重复元素的最长字串, 不是子序列
     * 遍历字符串
     * 1. 如果 c 在集合中, 从集合中删除 c 之前包括 c 的所有元素;
     * 2. 将 c 加入集合
     *
     * 期间集合的最大容量就是符合要求的最长字串长度
     */
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        Arrays.fill(map, -1);

        int l = 0, r = 0;
        int ret = 0;
        while (r < s.length()) {
            int i = s.charAt(r);
            // 如果集合中存在
            if (map[i] != -1) {
                int end = map[i];
                while (l <= end)
                    map[s.charAt(l++)]= -1;
            }
            map[i] = r;
            ret = Math.max(ret, r-l+1);
            r++;
        }
        return ret;
    }

    public static void main(String[] args) {
        int len = new Solution3().lengthOfLongestSubstring("abcabcbb");
        System.out.println(len);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
