//给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以 
//croakOfFrogs 中会混合多个 “croak” 。 
//
// 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。 
//
// 要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会
//发出声音。如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：croakOfFrogs = "croakcroak"
//输出：1 
//解释：一只青蛙 “呱呱” 两次
// 
//
// 示例 2： 
//
// 
//输入：croakOfFrogs = "crcoakroak"
//输出：2 
//解释：最少需要两只青蛙，“呱呱” 声用黑体标注
//第一只青蛙 "crcoakroak"
//第二只青蛙 "crcoakroak"
// 
//
// 示例 3： 
//
// 
//输入：croakOfFrogs = "croakcrook"
//输出：-1
//解释：给出的字符串不是 "croak" 的有效组合。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= croakOfFrogs.length <= 10⁵ 
// 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k' 
// 
//
// Related Topics 字符串 计数 👍 191 👎 0


import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1419 {
    /* 1419.数青蛙: https://leetcode.cn/problems/minimum-number-of-frogs-croaking/
     *
     * 这个题目不是像 1003.检查替换后的词是否有效 那样的模式嵌套
     * 它的要求更为宽松, 只要遍历到 croak 靠后的字母的时候，它比前面的字母少就可以了
     *
     * 当遍历完到 k 的时候，croak 的数量都要去掉 1
     *
     * 如果最后每个字母的数量都为 0，那么 'c' 最多出现的次数就是答案
     * 如果最后有字母的数量不为 0 或中途不满足比前面少的条件, 那么字符串不是有效的组合
     */
    public int minNumberOfFrogs(String croakOfFrogs) {
        int c,r,o,a,k;
        c = r = o = a = k = 0;
        int ans = 0;
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            char ch = croakOfFrogs.charAt(i);
            if (ch == 'c') {
                c++;
                ans = Math.max(ans, c);
            } else if (ch == 'r') {
                if (++r > c) return -1;
            } else if (ch == 'o') {
                if (++o > r) return -1;
            } else if (ch == 'a') {
                if (++a > o) return -1;
            } else if (ch == 'k') {
                if (++k > a) return -1;
                c--;r--;o--;a--;k--;
            }
        }
        if (c == 0 && r == 0 && o == 0 && a == 0 && k == 0)
            return ans;
        else
            return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
