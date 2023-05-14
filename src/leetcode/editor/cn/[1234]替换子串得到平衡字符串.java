//有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。 
//
// 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。 
//
// 
//
// 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。 
//
// 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。 
//
// 请返回待替换子串的最小可能长度。 
//
// 如果原字符串自身就是一个平衡字符串，则返回 0。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "QWER"
//输出：0
//解释：s 已经是平衡的了。 
//
// 示例 2： 
//
// 
//输入：s = "QQWE"
//输出：1
//解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
// 
//
// 示例 3： 
//
// 
//输入：s = "QQQW"
//输出：2
//解释：我们可以把前面的 "QQ" 替换成 "ER"。 
// 
//
// 示例 4： 
//
// 
//输入：s = "QQQQ"
//输出：3
//解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10^5 
// s.length 是 4 的倍数 
// s 中只含有 'Q', 'W', 'E', 'R' 四种字符 
// 
//
// Related Topics 字符串 滑动窗口 👍 239 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1234 {

    /* 无重复字符的最长子串: https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     * 滑动窗口相似题目:
     *  2106.摘水果: https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps/     * 字串, 不是子序列
     *
     * s 的长度为 4 的倍数，根据【平衡字符串】的约束，字母 QWER 在 s 中出现的次数都应该为 len/4 个
     * 有的字母出现次数少了，就必定有出现多了的，我们的目标就是将出现多的字母换成少了的字母
     *
     * 题目变成求一个最小的窗口，至少包含多余数量的对应字母
     */
    public int balancedString(String s) {
        int n = s.length() / 4;
        int[] need = new int[26];
        int[] count = new int[26];
        Arrays.fill(need, -n);

        char[] chars = s.toCharArray();
        for (char ch: chars) {
            need[ch - 'A']++;
        }

        int ans = chars.length;
        for (int i = 0, j = 0; j < chars.length;) {
            count[chars[j]-'A']++;
            while (i <= j && isEnough(count, need)) {
                ans = Math.min(ans, j-i+1);
                count[chars[i]-'A']--;
                i++;
            }
            // i>j的时候，也有可能满足
            if (isEnough(count, need))
                ans = Math.min(ans, j-i+1);
            j++;
        }
        return ans;
    }

    private boolean isEnough(int[] count, int[] need) {
        return count['Q'-'A'] >= need['Q'-'A']
                && count['W'-'A'] >= need['W'-'A']
                && count['E'-'A'] >= need['E'-'A']
                && count['R'-'A'] >= need['R'-'A'];
    }

    public static void main(String[] args) {
        Solution1234 so = new Solution1234();
        int i = so.balancedString("QQWE");
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
