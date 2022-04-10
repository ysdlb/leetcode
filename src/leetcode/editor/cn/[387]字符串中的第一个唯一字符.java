//给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入: s = "leetcode"
//输出: 0
// 
//
// 示例 2: 
//
// 
//输入: s = "loveleetcode"
//输出: 2
// 
//
// 示例 3: 
//
// 
//输入: s = "aabb"
//输出: -1
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length <= 10⁵ 
// s 只包含小写字母 
// 
// Related Topics 队列 哈希表 字符串 计数 👍 535 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution387 {

    /**
     * 扫描两遍
     * 第一遍记录每个字符的出现次数
     * 第二遍遍历 s 这个串, 如果出现只有一次, 返回这个位置的索引
     *
     * 因为只有一次和多次的区别，所以字符不重复时可以存索引, 重复时候存 -1, 第二次遍历找到索引的最小值就行了
     *
     * 扫描一遍, 在 map 的基础上, 额外加一个队列来判断谁是第一个 ( 利用队列先进先出的特性 )
     * 如果该字符目前只出现来一次, 将 (char, i) 放入 map, 然后将 (char, i) 放入 map
     * 如果前面出现过一次, map[char] = -1, 然后通过 map 判断队首字符是否出现过, 如果出现超过一次, 则从队首出队
     * 最终队首元素的 i 就是返回值
     */
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
