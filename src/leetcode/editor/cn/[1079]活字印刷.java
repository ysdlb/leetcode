//你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。 
//
// 注意：本题中，每个活字字模只能使用一次。 
//
// 
//
// 示例 1： 
//
// 
//输入："AAB"
//输出：8
//解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
// 
//
// 示例 2： 
//
// 
//输入："AAABBC"
//输出：188
// 
//
// 示例 3： 
//
// 
//输入："V"
//输出：1 
//
// 
//
// 提示： 
//
// 
// 1 <= tiles.length <= 7 
// tiles 由大写英文字母组成 
// 
//
// Related Topics 哈希表 字符串 回溯 计数 👍 184 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1079 {
    /* 1079.活字印刷: https://leetcode.cn/problems/letter-tile-possibilities/
     * 相似题目:
     *  47.全排列 II: https://leetcode.cn/problems/permutations-ii/
     * 约束不同的相似题目:
     *  22.括号生成: https://leetcode.cn/problems/generate-parentheses/
     *
     * 典型的回溯问题:
     * 典型全排列的约束就是已经使用过的不可再次使用, 就是在位置 first 枚举所有可选元素
     * 一个技巧就是将选中的元素交换到当前位置, 这样 s[0,first-1] 都是已经选过的元素，s[first,n) 都是可选元素
     * 在 first 的基础上进入位置 first+1 重复上次操作
     *
     * 若可选的元素集合有重复, 只要保证在任意 i 位置枚举可选元素的时候, 过滤掉同值元素即可
     * 证明:
     *   1. s[0] 不重复
     *   2. 同一个 s[i], s[i+1] 一定不重复
     *   3. 所有枚举出的序列 s[0,i] 一定不重复
     */
    public int numTilePossibilities(String tiles) {
        return numTilePossibilities(tiles.toCharArray(), 0);
    }

    private int numTilePossibilities(char[] chars, int first) {
        if (first == chars.length-1) return 1;

        int[] set = new int[26];
        int ans = 0;
        for (int i = first; i < chars.length; i++) {
            char ch = chars[i];
            if (set[ch-'A'] > 0) continue;

            set[ch-'A']++;
            this.swap(chars, i, first);
            ans += 1+numTilePossibilities(chars, first+1);
            this.swap(chars, i, first);
        }
        return ans;
    }

    private void swap(char[] chars, int x, int y) {
        if (x == y) return;

        char t = chars[x];
        chars[x] = chars[y];
        chars[y] = t;
    }

    public static void main(String[] args) {
        Solution1079 so = new Solution1079();
        {
            int i = so.numTilePossibilities("AAABBC");
            System.out.println(i);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
