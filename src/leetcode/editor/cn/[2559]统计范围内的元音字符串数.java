//给你一个下标从 0 开始的字符串数组 words 以及一个二维整数数组 queries 。 
//
// 每个查询 queries[i] = [li, ri] 会要求我们统计在 words 中下标在 li 到 ri 范围内（包含 这两个值）并且以元音开头和结尾
//的字符串的数目。 
//
// 返回一个整数数组，其中数组的第 i 个元素对应第 i 个查询的答案。 
//
// 注意：元音字母是 'a'、'e'、'i'、'o' 和 'u' 。 
//
// 
//
// 示例 1： 
//
// 
//输入：words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
//输出：[2,3,0]
//解释：以元音开头和结尾的字符串是 "aba"、"ece"、"aa" 和 "e" 。
//查询 [0,2] 结果为 2（字符串 "aba" 和 "ece"）。
//查询 [1,4] 结果为 3（字符串 "ece"、"aa"、"e"）。
//查询 [1,1] 结果为 0 。
//返回结果 [2,3,0] 。
// 
//
// 示例 2： 
//
// 
//输入：words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
//输出：[3,2,1]
//解释：每个字符串都满足这一条件，所以返回 [3,2,1] 。 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 10⁵ 
// 1 <= words[i].length <= 40 
// words[i] 仅由小写英文字母组成 
// sum(words[i].length) <= 3 * 10⁵ 
// 1 <= queries.length <= 10⁵ 
// 0 <= queries[j][0] <= queries[j][1] < words.length 
// 
//
// Related Topics 数组 字符串 前缀和 👍 61 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution2559 {

    /* 2559.统计范围内的元音字符串数: https://leetcode.cn/problems/count-vowel-strings-in-ranges/
     * 相似题:
     *  303.区域和检索 - 数组不可变: https://leetcode.cn/problems/range-sum-query-immutable/
     *  307.区域和检索 - 数组可修改: https://leetcode.cn/problems/range-sum-query-mutable/
     *
     * 对于 words 中的任意元素, 无非两种情况
     *  0. 非元音字符串
     *  1. 是元音字符串
     *
     * 题目其实是统计区间内 1 的个数
     * 需要我们设计一个数据结构, 高效的完成需求
     *
     * 因为只涉及查, 不涉及更新, 所以前缀和数组非常合适, 单次时间复杂度 O(1)
     * 涉及更新只能用树状数组, 查/写均为 O(lgN)
     */
    public int[] vowelStrings(String[] words, int[][] queries) {
        int[] prefix = new int[words.length + 1];
        prefix[0] = 0;
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            prefix[i+1] = isVowel(w) ? prefix[i]+1 : prefix[i];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            ans[i] = prefix[q[1]+1] - prefix[q[0]];
        }
        return ans;
    }

    private static final int[] VOWEL = new int[26];
    static {
        VOWEL['a'-'a'] = 1;
        VOWEL['e'-'a'] = 1;
        VOWEL['i'-'a'] = 1;
        VOWEL['o'-'a'] = 1;
        VOWEL['u'-'a'] = 1;
    }
    private boolean isVowel(String word) {
        return !word.isEmpty()
                && VOWEL[word.charAt(0) - 'a'] == 1
                && VOWEL[word.charAt(word.length()-1) - 'a'] == 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
