//在一个长度 无限 的数轴上，第 i 颗石子的位置为 stones[i]。如果一颗石子的位置最小/最大，那么该石子被称作 端点石子 。 
//
// 每个回合，你可以将一颗端点石子拿起并移动到一个未占用的位置，使得该石子不再是一颗端点石子。 
//
// 值得注意的是，如果石子像 stones = [1,2,5] 这样，你将 无法 移动位于位置 5 的端点石子，因为无论将它移动到任何位置（例如 0 或 3）
//，该石子都仍然会是端点石子。 
//
// 当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。 
//
// 要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves, 
//maximum_moves] 。 
//
// 
//
// 示例 1： 
//
// 
//输入：[7,4,9]
//输出：[1,2]
//解释：
//我们可以移动一次，4 -> 8，游戏结束。
//或者，我们可以移动两次 9 -> 5，4 -> 6，游戏结束。
// 
//
// 示例 2： 
//
// 
//输入：[6,5,4,3,10]
//输出：[2,3]
//解释：
//我们可以移动 3 -> 8，接着是 10 -> 7，游戏结束。
//或者，我们可以移动 3 -> 7, 4 -> 8, 5 -> 9，游戏结束。
//注意，我们无法进行 10 -> 2 这样的移动来结束游戏，因为这是不合要求的移动。
// 
//
// 示例 3： 
//
// 
//输入：[100,101,104,102,103]
//输出：[0,0] 
//
// 
//
// 提示： 
//
// 
// 3 <= stones.length <= 10^4 
// 1 <= stones[i] <= 10^9 
// stones[i] 的值各不相同。 
// 
//
// 
//
// Related Topics 数组 数学 双指针 排序 👍 150 👎 0


import java.lang.reflect.Array;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1040 {

    /* 移动石子直到连续 II: https://leetcode.cn/problems/moving-stones-until-consecutive-ii/
     * 类似题:
     * 1033: 移动石子直到连续: https://leetcode.cn/problems/moving-stones-until-consecutive/
     *
     * 不同于 1033
     *   1. 石子数量很多
     *   2. 存在移动后不再是端点的限制 (对最小/最大移动次数均有影响)
     *
     * 思路:
     *   石子在数轴上天然有序，我们必须对数组排序来满足这个条件
     *   用贪心的思路模拟最小/最大的过程
     *
     * 看了答案之后:
     *   设石子间的空位总数为 k，根据移动的特点，每次移动都会使 k 的值减小，我们的目标是使 k 的值降低到 0
     *   k = a[-1] - a[0] - a.length + 1
     *
     * 每次只能移动边缘的两个石子之一,
     *   若移动最左边的石子, 则必定消灭 a[1] - a[0] - 1 + 1 = a[1] - a[0] 个空位
     *   若移动最右边的石子, 则必定消灭 a[-1] - a[-2] 个空位
     * 若有 n 元素，则最终肯定是一个宽度为 n 的被填满的窗口。
     *
     * 求最大 (从全局角度贪心模拟):
     *   第一次移动根据消灭空位的数量在左右两边选一个较小的，后续可以控制每次移动仅消灭一个空位
     *   即最终值为 k - min{a[1]-a[0], a[-1]-a[-2]} + 1
     *
     * 求最小 (从窗口角度看最终结果):
     *   以最终连续的 n 个石子为目标，会对应一个窗口 [i,j] (a[j] - a[i] + 1 <= n)
     *     如何计算窗口中的石子数量: j-i+1
     *     空位数量: a[j] - a[i] - (j-i+1) + 1 ==> a[j] - a[i] + i - j
     *     当空位数量为 0 时: a[i] - i = a[j] - j
     *
     * 最小例子：
     * [3,4,5,6,10]
     *   x: 3,4,5,6, |
     *   ^: 4,5,6, , |
     *   ^: 6, , , ,10|
     * 需要两次
     *
     * [3,4,5,6,8]
     * 仅需一次
     *
     * 1. 若存在连续的 n-1 个石子，且左边或者右边的空闲为 1，则仅需一次就可完成；
     * 2. 若空闲 >= 2，则最少需要 2 次
     * 3. 其他时候最少需要空位数量次移动
     * 1,3 可合并: 最少 = min{a[j] - a[i] + i - j, 2}
     *
     * 最大例子:
     * 1,10,20 -> 10,11,20 -> 11,12,20 -> ... -> 18,19,20
     * [2,9]
     */
    public int[] numMovesStonesII(int[] stones) {
        if (stones == null || stones.length < 3) return new int[]{0,0};

        int n = stones.length;
        Arrays.sort(stones);

        // k = a[-1] - a[0] - a.length + 1
        // 最终值为 k - min{a[1]-a[0], a[-1]-a[-2]} + 1
        int max = stones[n-1] - stones[0] - n
                - Math.min(stones[n-1]-stones[n-2], stones[1]-stones[0])
                + 2;

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int j = i;
            while (j+1 < n && stones[j+1] - stones[i] + 1 <= n)
                j++;

            // 窗口大小为 n-1, 此时还剩一个元素
            // 必须是连续的 n-1 个元素, 所以 j-i+1 == n-1 不行; a[j] - a[i] == (n - 1) - 1
            // 补充: j-i+1 == n-1 && a[j] - a[i] == (n - 1) - 1
            if (stones[j] - stones[i] == n-2 && j-i+1 == n-1
                    && stones[i] - stones[0] + stones[n-1] - stones[j] >= 2)
                min = Math.min(2, min);
            // 窗口大小不到 n, 加上下一个元素后窗口大小必定超过 n
            // 一共有 n 个空位, n - (j-i+1)
            else
                min = Math.min(n + i - j - 1, min);
        }

        return new int[]{min, max};
    }

    public static void main(String[] args) {
        Solution1040 solution = new Solution1040();
        // int[] arg = new int[]{7,4,9};
        // int[] arg = new int[]{6,5,4,3,8};
        int[] arg = new int[]{6,5,4,3,10};
        int[] ints = solution.numMovesStonesII(arg);
        System.out.println(Arrays.toString(ints));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
