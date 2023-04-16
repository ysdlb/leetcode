//如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 
//个字符。） 
//
// 给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 
//pattern 匹配时， answer[i] 才为 true，否则为 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"],
// pattern = "FB"
//输出：[true,false,true,true,false]
//示例：
//"FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
//"FootBall" 可以这样生成："F" + "oot" + "B" + "all".
//"FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer". 
//
// 示例 2： 
//
// 
//输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"],
// pattern = "FoBa"
//输出：[true,false,true,false,false]
//解释：
//"FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
//"FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
// 
//
// 示例 3： 
//
// 
//输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"],
// pattern = "FoBaT"
//输出：[false,true,false,false,false]
//解释： 
//"FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".
// 
//
// 
//
// 提示： 
//
// 
// 1 <= queries.length <= 100 
// 1 <= queries[i].length <= 100 
// 1 <= pattern.length <= 100 
// 所有字符串都仅由大写和小写英文字母组成。 
// 
//
// Related Topics 字典树 双指针 字符串 字符串匹配 👍 110 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1023 {
    /* 驼峰式子匹配: https://leetcode.cn/problems/camelcase-matching/
     * 相似题目:
     *
     * 对于一个正常的公共子序列问题，时间复杂度为 O(n*m)
     * 但如果问题是 m 是否为 n 的子序列，则时间复杂度为 O(n)，仅遍历 n 即可
     */
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> r = new ArrayList<>();
        for (String query: queries) {
            int i = 0;
            boolean t = true;
            for (char c: query.toCharArray()) {
                if (i < pattern.length() && c == pattern.charAt(i))
                    i++;
                else if ('A' <= c && c <= 'Z') {
                    t = false;
                    break;
                }
            }
            // 未出现不等的大写字母且 m 为 n 的子序列
            r.add(t && i == pattern.length());
        }
        return r;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
