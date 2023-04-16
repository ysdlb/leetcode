//你会得到一个字符串 text 。你应该把它分成 k 个子字符串 (subtext1, subtext2，…， subtextk) ，要求满足: 
//
// 
// subtexti 是 非空 字符串 
// 所有子字符串的连接等于 text ( 即subtext1 + subtext2 + ... + subtextk == text ) 
// 对于所有 i 的有效值( 即 1 <= i <= k ) ，subtexti == subtextk - i + 1 均成立 
// 
//
// 返回k可能最大值。 
//
// 
//
// 示例 1： 
//
// 
//输入：text = "ghiabcdefhelloadamhelloabcdefghi"
//输出：7
//解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
// 
//
// 示例 2： 
//
// 
//输入：text = "merchant"
//输出：1
//解释：我们可以把字符串拆分成 "(merchant)"。
// 
//
// 示例 3： 
//
// 
//输入：text = "antaprezatepzapreanta"
//输出：11
//解释：我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text.length <= 1000 
// text 仅由小写英文字符组成 
// 
//
// Related Topics 贪心 双指针 字符串 动态规划 哈希函数 滚动哈希 👍 100 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1147 {

    /* 段式回文: https://leetcode.cn/problems/longest-chunked-palindrome-decomposition/
     * 相似题:
     *   647. 回文子串: https://leetcode.cn/problems/palindromic-substrings/
     *   5. 最长回文子串: https://leetcode.cn/problems/longest-palindromic-substring/
     *   ( 以上两个题目的特点都是从中间向两边迭代比较 )
     *
     * 那种分段的非严格对称
     *  此时便不可以从中间到两边双指针迭代
     * a[0,0] == a[n-1,n-1]
     * a[1,2] == a[n-1-2,n-1-1]
     * ==>
     *   a[i...j] == a[n-1-j,n-1-i]
     *
     * 从两边向中间暴力求解:
     * 依次遍历所有符合以下模式的集合:
     *   a[i...j] == a[n-1-j,n-1-i]  (j < n-1-j)
     *
     * 初始 i = j = 0
     * 若不符合上述模式:
     *   j++
     * 若匹配成功进入下一次循环:
     *   i = j+1; j=i;
     *
     * 初始子字符串数量为 r =1,
     * 每成功匹配一对: r += 2
     * 若最后一个匹配的 a[i...j] == a[n-1-j,n-1-i]; 符合条件 j+1 == n-1-j, 则 r--
     *
     * 暴力求解时间复杂度 O(n^2)
     */
    public int longestDecomposition(String text) {
        if (text == null || text.length() == 0) return 0;

        int n = text.length();
        int r = 1;
        for (int i = 0, j = 0; j < n-1-j;) {
            String a = text.substring(i, j+1);
            String b = text.substring(n-1-j, n-i);
            if (a.hashCode() != b.hashCode() || !a.equals(b))
                j++;
            else {
                r += 2;
                if (j+1 == n-1-j) r--;
                i = j+1;
                j = i;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Solution1147 solution = new Solution1147();
        //int r = solution.longestDecomposition("merchant");
        //int r = solution.longestDecomposition("antaprezatepzapreanta");
        //int r = solution.longestDecomposition("ghiabcdefhelloadamhelloabcdefghi");
        int r = solution.longestDecomposition("aa");
        System.out.println(r);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
