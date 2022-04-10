//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。 
//
// 示例 1: 
//
// 
//输入：s = "abaccdeff"
//输出：'b'
// 
//
// 示例 2: 
//
// 
//输入：s = "" 
//输出：' '
// 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 50000 
// Related Topics 队列 哈希表 字符串 计数 👍 179 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer50 {
    /**
     * 由于是返回第一个只出现一次的字符, 所以得保存字符出现的顺序(当且仅当字符出现 1 次的时候加入队列)
     * 用一个 map 记录字符出现次数
     *
     * 6ms
     */
    public char firstUniqChar(String s) {
        int[] countMap = new int[26];
        List<Integer> queue = new ArrayList<>();

        s.chars().forEach(c -> {
            int i = c - 'a';
            countMap[i]++;
            if (countMap[i] == 1)
                queue.add(c);
        });

        for (int c: queue) {
            int i = c - 'a';
            if (countMap[i] == 1)
                return (char) c;
        }
        return ' ';
    }

    /**
     * 由于是返回第一个只出现一次的字符, 所以得保存字符出现的顺序(当且仅当字符出现 1 次的时候加入队列)
     * 用一个 map 记录字符出现次数
     *
     * 9ms
     */
    public char firstUniqCharV2(String s) {
        int[] countMap = new int[26];

        s.chars().forEach(c -> countMap[c - 'a']++);

        char before = ' ';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == before) continue;

            if (countMap[c - 'a'] == 1)
                return c;
        }
        return ' ';
    }

}
//leetcode submit region end(Prohibit modification and deletion)
