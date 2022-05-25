//你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9
//' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。 
//
// 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。 
//
// 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。 
//
// 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。 
//
// 
//
// 示例 1: 
//
// 
//输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
//输出：6
//解释：
//可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
//注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
//因为当拨动到 "0102" 时这个锁就会被锁定。
// 
//
// 示例 2: 
//
// 
//输入: deadends = ["8888"], target = "0009"
//输出：1
//解释：把最后一位反向旋转一次即可 "0000" -> "0009"。
// 
//
// 示例 3: 
//
// 
//输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], 
//target = "8888"
//输出：-1
//解释：无法旋转到目标数字且不被锁定。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= deadends.length <= 500 
// deadends[i].length == 4 
// target.length == 4 
// target 不在 deadends 之中 
// target 和 deadends[i] 仅由若干位数字组成 
// 
// Related Topics 广度优先搜索 数组 哈希表 字符串 👍 474 👎 0


import java.util.*;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution752 {
    /**
     * 一个转盘锁的一定可以转到任意一个可能的值, 主要是能否避开 dead 数字的问题
     * 这类似一个迷宫
     * 对, 它是一个四维度空间的迷宫, 迷宫能不能走通比较简单, 只要标记好走过的节点, 不走回头路就好
     * 这里的难点如果能走通需要求最少的步数
     * 这个迷宫很像 1631
     * 其实是求最短路, 但是由于每步的 cost 都一样, 所以 BFS 层级就是它的总开销
     *
     * 接发类似题：773
     * 相似题目: 433: 最小基因变化
     */
    public int openLock(String[] deadends, String target) {
        Set<String> deadSet = Arrays.stream(deadends).collect(Collectors.toSet());

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add("0000");
        queue.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            // 层序遍历
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (deadSet.contains(cur)) continue;
                if (target.equals(cur)) return step;

                // 处理所有相邻点
                for (int k = 0; k < 4; k++) {
                    String next1 = changeSite(cur, k, true);
                    if (!visited.contains(next1)) {
                        queue.offer(next1);
                        visited.add(next1);
                    }

                    String next2 = changeSite(cur, k, false);
                    if (!visited.contains(next2)) {
                        queue.offer(next2);
                        visited.add(next2);
                    }

                }
            }
            step++;
        }
        return -1;
    }

    private String changeSite(String value, int i, boolean isAdd) {
        int changed = isAdd ? 1 : -1;

        char[] val = value.toCharArray();
        if (val[i] == '9' && changed == 1) {
            val[i] = '0';
            return new String(val);
        }

        if (val[i] == '0' && changed == -1) {
            val[i] = '9';
            return new String(val);
        }

        val[i] += changed;
        return new String(val);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
