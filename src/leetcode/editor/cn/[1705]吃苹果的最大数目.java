//有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，这些苹果将会在 days[i] 天后（也就
//是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] =
//= 0 表示。 
//
// 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。 
//
// 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。 
//
// 
//
// 示例 1： 
//
// 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
//输出：7
//解释：你可以吃掉 7 个苹果：
//- 第一天，你吃掉第一天长出来的苹果。
//- 第二天，你吃掉一个第二天长出来的苹果。
//- 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
//- 第四天到第七天，你吃的都是第四天长出来的苹果。
// 
//
// 示例 2： 
//
// 输入：apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
//输出：5
//解释：你可以吃掉 5 个苹果：
//- 第一天到第三天，你吃的都是第一天长出来的苹果。
//- 第四天和第五天不吃苹果。
//- 第六天和第七天，你吃的都是第六天长出来的苹果。
// 
//
// 
//
// 提示： 
//
// 
// apples.length == n 
// days.length == n 
// 1 <= n <= 2 * 10⁴ 
// 0 <= apples[i], days[i] <= 2 * 10⁴ 
// 只有在 apples[i] = 0 时，days[i] = 0 才成立 
// 
//
// Related Topics 贪心 数组 堆（优先队列） 👍 186 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1705 {
    /* 吃苹果的最大数目: https://leetcode.cn/problems/maximum-number-of-eaten-apples/
     * 同类型题:
     *   1353.最多可以参加的会议数目: https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/
     *
     * 每天都会长若干苹果，这些苹果会在某些天之后腐烂。
     * 即每个苹果都有独立的可食用时间区间 (单位天), 每个单位时间内我们只能吃一个苹果。
     * 求我们最多可以吃多少个苹果
     * 目前为止，该题目和 《1353.最多可以参加的会议数目》相同，不过数量级更大
     *
     * 1. 求出所有苹果的可食用区间列表
     * 2. 同 1353 思路处理,
     *   保证开始时间升序，同区间范围的可以记录次数，不必每个都枚举; 这样时间复杂度可做到 N*lgN
     *   每次吃之前，更新可食用集合（我们只关心能吃，过期时间和数目)
     *   可食用集合中，找最快过期的下嘴
     */
    public int eatenApples(int[] apples, int[] days) {
        if (apples == null || apples.length == 0) return 0;

        Deque<int[]> foods = new LinkedList<>();
        for (int i = 0; i < apples.length; i++) {
            // 开始时间，结束时间，数量
            if (apples[i] == 0) continue;

            int[] food = new int[3];
            food[0] = i+1;
            food[1] = i+days[i];
            food[2] = apples[i];
            foods.add(food);
        }

        // [结束时间, 数量]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparing(e -> e[0]));
        int count = 0;
        for (int cur = 1; !foods.isEmpty() || !minHeap.isEmpty(); ) {
            // 移除不可食用的苹果
            while (!minHeap.isEmpty() && minHeap.peek()[0] < cur) {
                minHeap.poll();
            }

            // 补充可食用的苹果
            while (!foods.isEmpty() && foods.getFirst()[0] == cur) {
                int[] food = foods.pollFirst();
                minHeap.offer(new int[]{food[1], food[2]});
            }

            // 尝试吃一个最快过期苹果
            if (!minHeap.isEmpty()) {
                count++;
                // 移除吃掉的苹果
                int[] peek = minHeap.peek();
                if (peek[1] == 1)
                    minHeap.poll();
                else
                    peek[1]--;
            }

            // 去下一天
            cur = minHeap.isEmpty() && !foods.isEmpty() ? foods.getFirst()[0] : cur+1;
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
