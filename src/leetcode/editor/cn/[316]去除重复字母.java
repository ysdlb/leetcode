//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。 
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-
//distinct-characters 相同 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bcabc"
//输出："abc"
// 
//
// 示例 2： 
//
// 
//输入：s = "cbacdcbc"
//输出："acdb" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 
// Related Topics 栈 贪心 字符串 单调栈 👍 628 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution316 {
    /**
     * 1. 无重复, 出现且仅出现一次
     * 2. 保持原有相对顺序
     * 3. 字典序最小
     *
     * <p> 前两条可以用一个长度为 26 的数组正序遍历完成
     * <p> 保证字典序(用于字符串比大小）最小, 需要把值小的字母尽可能往前放,
     *
     * <p> 以下是一个有问题的描述:
     * 先统计每个字母出现的次数, 对遍历的每个字母 A, 如果在其前面出现过的字母 B 在它们后面还有, 那么把 B 干掉
     * 如果 xyzaxy, 字典序最下的 xyza, 而不是 zaxy
     *
     * <p> 我们只有干掉最近的, 才能进一步干掉更前面的
     * 此时我对单调栈的理解还不足 (2021-12-04)
     */
    public String removeDuplicateLetters(String s) {
        boolean[] used = new boolean[26];
        int[] count = new int[26];

        // 栈, indexStack == -1 时 栈为空
        int[] stack = new int[s.length()];
        int indexStack = -1;

        for (char c: s.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : s.toCharArray()) {
            count[c - 'a']--;
            // 如果当前序列中（当前栈) 中已经有了, 该字母必然在前面且没被干掉
            if (used[c - 'a']) continue;

            // 栈不为空, 栈顶元素比当前元素大, 栈顶元素后面还有, 干掉栈顶元素
            while (indexStack >= 0 && c - 'a' < stack[indexStack] && count[stack[indexStack]] > 0) {
                used[stack[indexStack]] = false;
                indexStack--;
            }
            stack[++indexStack] = c - 'a';
            used[c - 'a'] = true;
        }

        char[] res = new char[indexStack + 1];
        for (int i = 0; i <= indexStack; i++) {
            res[i] = (char)(stack[i] + 'a');
        }
        return new String(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
