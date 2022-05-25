//基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。 
//
// 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。 
//
// 
// 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。 
// 
//
// 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。 
//
// 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成
//此基因变化，返回 -1 。 
//
// 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。 
//
// 
//
// 示例 1： 
//
// 
//输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA",
//"AAACGGTA"]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC",
//"AACCCCCC"]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// start.length == 8 
// end.length == 8 
// 0 <= bank.length <= 10 
// bank[i].length == 8 
// start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成 
// 
// Related Topics 广度优先搜索 哈希表 字符串 👍 210 👎 0


import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution433 {
    /**
     * start = "AAAAACCC", end = "AACCCCCC"
     * 变化的步骤不一定是 start 和 end 的差异数量, 因为一定要命中 bank 库才是一次有效的基因变化
     * 这里类似一个多维空间的迷宫, 能否在满足条件的情况下从 start -> end
     * 解决方法有 DFS 和 BFS 两种
     * 又因为要最少次数, 所以只能用 BFS
     *
     * 相似题 752: 打开转盘锁
     * 后续可以参考 752 优化下代码结构
     */
    public int minMutation(String start, String end, String[] bank) {
        char[] change = new char[]{'A', 'C', 'G', 'T'};
        Set<String> bankSet = new HashSet<>();
        // 记录走过的路
        for (String b: bank)
            bankSet.add(b);

        int count = 0;
        Set<String> record = new HashSet<>();
        Deque<String> deque = new LinkedList<>();
        deque.offer(start);
        record.add(start);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                String state = deque.poll();
                if (state.equals(end))
                    return count;

                char[] chars = state.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    for (char c: change) {
                        if (c == chars[j])
                            continue;

                        // 这里恢复原样要尽快, 跨度太大很容易忘记, 变数也大
                        char copy = chars[j];
                        chars[j] = c;
                        String s = new String(chars);
                        chars[j] = copy;

                        if (record.contains(s)|| !bankSet.contains(s))
                            continue;
                        deque.offer(s);
                        record.add(s);
                    }
                }
            }
            count++;
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution433 solution433 = new Solution433();
        String start = "AACCGGTT";
        String end = "AACCGGTA";
        String[] bank = new String[]{"AACCGGTA"};
        solution433.minMutation(start, end, bank);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
