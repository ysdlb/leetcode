//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 👍 2439 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution22 {
    /**
     * 全排列问题, 参考 46, 47 题
     * 只有左括号的数量比右括号的数量多的时候, 才能添加右括号, 否则只能添加左括号
     * 括号匹配规则参考: <a href="https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid/">leetcode-921</a>
     * 参考: 921, 1514，32（空间O1做法）
     */
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        String path = "";
        backTrack(ret, path, 0, 0, n);
        return ret;
    }

    /**
     * @param ret ret
     * @param path path
     * @param numL 左括号的数量
     * @param numR 右括号的数量
     * @param n 括号的对数
     */
    private void backTrack(List<String> ret, String path, int numL, int numR, int n) {
        if (numL == n && numR == n)
            ret.add(path);

        // 这里不需要  add 再 remove 利用了 java String 对象不可变的性质
        if (numL < n)
            backTrack(ret, path + "(", numL + 1, numR, n);
        if (numL > numR)
            backTrack(ret, path + ")", numL, numR + 1, n);
    }

    public static void main(String[] args) {
        List<String> strings = new Solution22().generateParenthesis(3);
        System.out.println(strings);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
