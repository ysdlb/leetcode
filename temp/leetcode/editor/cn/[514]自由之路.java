//电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。 
//
// 给定一个字符串 ring ，表示刻在外环上的编码；给定另一个字符串 key ，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。 
//
// 最初，ring 的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按
//钮，以此逐个拼写完 key 中的所有字符。 
//
// 旋转 ring 拼出 key 字符 key[i] 的阶段中： 
//
// 
// 您可以将 ring 顺时针或逆时针旋转 一个位置 ，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于
//字符 key[i] 。 
// 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段
//）, 直至完成所有拼写。 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//
// 
//输入: ring = "godding", key = "gd"
//输出: 4
//解释:
// 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。 
// 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
// 当然, 我们还需要1步进行拼写。
// 因此最终的输出是 4。
// 
//
// 示例 2: 
//
// 
//输入: ring = "godding", key = "godding"
//输出: 13
// 
//
// 
//
// 提示： 
//
// 
// 1 <= ring.length, key.length <= 100 
// ring 和 key 只包含小写英文字母 
// 保证 字符串 key 一定可以由字符串 ring 旋转拼出 
// 
// Related Topics 深度优先搜索 广度优先搜索 字符串 动态规划 👍 226 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution514 {
    /**
     * 每次按最小距离转到所需字母上
     * 一维数组表示的环中，计算 a，b 两点的距离
     * min (a+Len - b) % Len, (b+Len-a) % Len}
     *
     * 错误的解法 (不是最优解):
     * "caotmcaataijjxi"
     * "oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx"
     * 输出:146 预期: 137
     * 原因, 有可能向左向右距离一样
     */
    public int findRotateSteps(String ring, String key) {
        int[] countMap = new int[26];
        for (int i = 0; i < ring.length(); i++) {
            countMap[ring.charAt(i) - 'a'] ++;
        }
        int[][] map = new int[26][];
        for (int i = ring.length() - 1; i >= 0; i--) {
            int c = ring.charAt(i) - 'a';
            if (map[c] == null) {
                map[c] = new int[countMap[c]];
            }
            map[c][--countMap[c]] = i;
        }

        int site = 0, ringL = ring.length();
        int ret = 0;
        for (int i = 0; i < key.length(); i++) {
            int keyChar = key.charAt(i) - 'a';
            int[] charsSite = map[keyChar];
            int distance = Integer.MAX_VALUE;
            int select = 0;
            for (int j = 0; j < charsSite.length; j++) {
                int charSite = charsSite[j];
                int d;
                if ((d = Math.min((site + ringL - charSite) % ringL, (charSite + ringL - site) % ringL)) < distance) {
                    distance = d;
                    select = j;
                }
            }
            site = charsSite[select];
            ret += distance + 1;
        }
        return ret;
    }

    public static void main(String[] args) {
        int i = new Solution514().findRotateSteps("gdonidg", "di");
        System.out.println(i);
    }
}
class Solution514_DP {
    /**
     * 每次按最小距离转到所需字母上
     * 一维数组表示的环中，计算 a，b 两点的距离
     * min (a+Len - b) % Len, (b+Len-a) % Len}
     *
     * 状态: 圆盘的位置, key 字符的位置
     *
     * 选择: 向左转还是向右转
     * 举例
     * ring = "gdonidg"
     * key = "di"
     * 即使左边的 d 比右边的 d 距离更近, 左边的 d 也不是最优解, 因为右边的 d 旁边就是 i, 总体成本更底, 所以我们需要两边都试一试
     * f(i,j) = min {f(left, j+1) + distanceL, f(right, j+1) + distanceR}
     * 还是说要试全部的相同字母 ?
     * f(i,j) = min for each charToIndex f(charToIndex, j+1) + distanceL + 1  charToIndex 字母 key(i+1) 在 ring 中的所有位置
     */
    public int findRotateSteps(String ring, String key) {
        int[] countMap = new int[26];
        for (int i = 0; i < ring.length(); i++) {
            countMap[ring.charAt(i) - 'a'] ++;
        }
        int[][] map = new int[26][];
        for (int i = ring.length() - 1; i >= 0; i--) {
            int c = ring.charAt(i) - 'a';
            if (map[c] == null) {
                map[c] = new int[countMap[c]];
            }
            map[c][--countMap[c]] = i;
        }

        int[][] dp = new int[ring.length()][key.length()];
        for (int j = key.length() - 1; j >= 0; j--) {
            int[] rings = map[key.charAt(j) - 'a'];
            if (j == key.length() - 1) {
                for (int i: rings) {
                    dp[i][j] = 1;
                }
                continue;
            }

            for (int i: rings) {
                int[] ringsAfter = map[key.charAt(j+1) - 'a'];
                dp[i][j] = Integer.MAX_VALUE;
                for (int iAfter: ringsAfter) {
                    int distance = Math.min((iAfter - i + ring.length()) % ring.length(), (i - iAfter + ring.length()) % ring.length());
                    dp[i][j] = Math.min(dp[iAfter][j+1] + distance + 1, dp[i][j]);
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        int[] rings = map[key.charAt(0) - 'a'];
        for (int i: rings) {
            if (i == 0) {
                ret = Math.min(ret, dp[0][0]);
            } else {
                int distance = Math.min(i, (ring.length() - i));
                ret = Math.min(ret, dp[i][0] + distance);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int i = new Solution514_DP().findRotateSteps("godding", "godding");
        System.out.println(i);
    }
}

class Solution514_DP_Forward {
    /**
     * 每次按最小距离转到所需字母上
     * 一维数组表示的环中，计算 a，b 两点的距离
     * min (a+Len - b) % Len, (b+Len-a) % Len}
     *
     * 状态: 圆盘的位置, key 字符的位置
     *
     * 选择: 向左转还是向右转
     * 举例
     * ring = "gdonidg"
     * key = "di"
     * 即使左边的 d 比右边的 d 距离更近, 左边的 d 也不是最优解, 因为右边的 d 旁边就是 i, 总体成本更底, 所以我们需要两边都试一试
     *
     * key j 位置的字母在转盘 ring 中的 位置数组为 map[j]
     * 设 f(map[j]i, j) 为 ring[0...j] 完成打印所需的最少步骤
     * f(map[j]i, j) = min{f(map[j-1]i, j-1] + distance + 1}, map[j-1]i 属于 key[i-1] 字母在 ring 中的位置数组
     *
     * 设数组 map[key.length - 1] 数组为 lastI
     * 则最终结果为 min{f(lastIi, key.length - 1}, lastIi 属于 lastI 数组中的元素
     */
    public int findRotateSteps(String ring, String key) {
        int[] countMap = new int[26];
        for (int i = 0; i < ring.length(); i++) {
            countMap[ring.charAt(i) - 'a'] ++;
        }
        int[][] map = new int[26][];
        for (int i = ring.length() - 1; i >= 0; i--) {
            int c = ring.charAt(i) - 'a';
            if (map[c] == null) {
                map[c] = new int[countMap[c]];
            }
            map[c][--countMap[c]] = i;
        }

        int[][] dp = new int[ring.length()][key.length()];
        for (int j = 0; j < key.length(); j++) {
            int[] rings = map[key.charAt(j) - 'a'];
            for (int i: rings) {
                int[] ringsBefore = j == 0 ? new int[]{0} : map[key.charAt(j-1) - 'a'];
                dp[i][j] = Integer.MAX_VALUE;
                for (int iBefore: ringsBefore) {
                    int distance = Math.min(Math.abs(iBefore - i), ring.length() - Math.abs(iBefore - i));
                    int iBeforeV = j == 0 ? 0 : dp[iBefore][j-1];
                    dp[i][j] = Math.min(iBeforeV + distance + 1, dp[i][j]);
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        int[] rings = map[key.charAt(key.length() - 1) - 'a'];
        for (int i: rings) {
            ret = Math.min(ret, dp[i][key.length() - 1]);
        }
        return ret;
    }

    public static void main(String[] args) {
        int i = new Solution514_DP_Forward().findRotateSteps("godding", "godding");
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
