//给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个
//单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。 
//
// 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。 
//
// 你需要计算完成所有任务所需要的 最短时间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：tasks = ["A","A","A","B","B","B"], n = 2
//输出：8
//解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
//     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
//
// 示例 2： 
//
// 
//输入：tasks = ["A","A","A","B","B","B"], n = 0
//输出：6
//解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
//["A","A","A","B","B","B"]
//["A","B","A","B","A","B"]
//["B","B","B","A","A","A"]
//...
//诸如此类
// 
//
// 示例 3： 
//
// 
//输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
//输出：16
//解释：一种可能的解决方案是：
//     A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待
//命) -> (待命) -> A
// 
//
// 
//
// 提示： 
//
// 
// 1 <= task.length <= 10⁴ 
// tasks[i] 是大写英文字母 
// n 的取值范围为 [0, 100] 
// 
// Related Topics 贪心 数组 哈希表 计数 排序 堆（优先队列） 👍 850 👎 0


import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution621 {
    /**
     * 下面的进入了细节地狱, 有些情况是错的, 参考过答案后尝试从全局来完成这个调度流程
     * 1. 将任务按类型分组, 然后按照任务数量排序
     * 2. 优先排列数量最多的任务, 这个任务必占时间 (count-1) * (n+1) + 1
     * 3. 如果后面的任务数量和最多的一样多, 那么每次需要在原来的基础上 +1 (最后一个任务排在前一个任务后面)
     * 4. 经过上述三步操作之后, 最开始的空隙只有填满和没有填满两种可能.
     *    a. 如果空位都插满还有任务, 那么计算的任务时间肯定比 tasks.len 小, 结果应该是 tasks.len
     *    b. 如果空位没有填满, 那么计算的时间肯定比 tasks.len 大, 结果应该是计算的时间
     */
    public int leastInterval_v2(char[] tasks, int n) {
        // 1. 将任务按类型分组, 然后按照任务数量排序
        int[] count = new int[26];
        for (char task: tasks) {
            count[task - 'A']++;
        }
        Arrays.sort(count);

        // 2. 优先排列数量最多的任务, 这个任务必占时间 (count-1) * (n+1) + 1
        int ret = (count[25] - 1) * (n+1) + 1;

        // 3. 如果后面的任务数量和最多的一样多, 那么每次需要在原来的基础上 +1 (最后一个任务排在前一个任务后面)
        for (int i = 24; i >= 0; i--) {
            if (count[i] != count[i+1])
                break;
            ret++;
        }
        // 4. 经过上述三步操作之后, 最开始的空隙只有填满和没有填满两种可能.
        return Math.max(ret, tasks.length);
    }

    /**
     * 一般操作系统的任务调度使用优先队列来实现, 这个题怎么做呢, 有时长为 n 的冷静期
     * 试着模拟下调度过程, 能继续调度就调度, 不能继续调度就等
     * 感觉这个可以直接用数学算出来, 但我不知道怎么算
     * ['A','A','A','B','B','B', 'C','C','C', 'D', 'D', 'E'], n = 2
     * 对上述序列, 能调度就调度算出来的时间是 13, 但如果尽可能找一个冷静期最长的, 计算出来是 12
     * 即贪心的思路
     *
     * 发现冷静期最长也不是最优解
     * ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
     * 最优的冷静期应该是当前重复任务的组数 - 1
     *
     * 还需要一个前提, 重复最多的任务越排在前面
     *
     * 这是下面版本的修正
     * 看了答案后的思考.
     * 问题归类: 如果当前有多种任务不在冷却中, 我们应该如何挑选定时任务的问题
     * 答案: 不在冷却中, 剩余次数最多的那个任务
     *
     * 所以昨天的前提, 重复最多的任务排在前面就够了, 没有最优冷静期一说
     */
    public int leastInterval_v1(char[] tasks, int n) {
        // 调度时钟
        int time = 0;
        // 任务数量
        int num = tasks.length;
        // 任务上次执行时间
        int[] timeRecord = new int[26];
        Arrays.fill(timeRecord, -n);

        // 统计下任务数量
        // [0] 记录任务 id, [1] 记录任务出现次数
        int[][] count = new int[26][2];
        for (char c: tasks) {
            count[c-'A'][0] = c-'A';
            count[c-'A'][1]++;
        }

        // 优先级队列, 任务越多的排越前面
        Arrays.sort(count, (e1, e2) -> e2[1] - e1[1]);

        while (num > 0) {
            // 每次调度时钟 + 1
            time++;
            // 尝试调度, 可能是一次空调度
            // 这里应该尝试贪心的思路, 找一个冷静期最长的
            for (int i = 0; i < count.length; i++) {
                int[] select = count[i];
                int this_duration = time - timeRecord[select[0]];
                if (select[1] > 0 && this_duration > n) {
                    // 该类型任务数量减 1
                    select[1]--;
                    // 记录新的时间
                    timeRecord[select[0]] = time;
                    // 任务数量减 1
                    num--;

                    // 每次我们都需要选当前不在冷静期, 数量最多的任务, 所以要保证数量最多的任务始终在最前面
                    if (i < count.length - 1 && select[1] < count[i+1][1]) {
                        count[i] = count[i+1];
                        count[i+1] = select;
                    }
                    break;
                }
            }

        }
        return time;
    }

    /**
     * 一般操作系统的任务调度使用优先队列来实现, 这个题怎么做呢, 有时长为 n 的冷静期
     * 试着模拟下调度过程, 能继续调度就调度, 不能继续调度就等
     * 感觉这个可以直接用数学算出来, 但我不知道怎么算
     * ['A','A','A','B','B','B', 'C','C','C', 'D', 'D', 'E'], n = 2
     * 对上述序列, 能调度就调度算出来的时间是 13, 但如果尽可能找一个冷静期最长的, 计算出来是 12
     * 即贪心的思路
     *
     * 发现冷静期最长也不是最优解
     * ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
     * 最优的冷静期应该是当前重复任务的组数 - 1
     *
     * 还需要一个前提, 重复最多的任务越排在前面
     */
    public int leastInterval(char[] tasks, int n) {
        // 调度时钟
        int time = 0;
        // 任务数量
        int num = tasks.length;
        // 任务上次执行时间
        int[] timeRecord = new int[26];
        Arrays.fill(timeRecord, -n);

        // 统计下任务数量
        // [0] 记录任务 id, [1] 记录任务出现次数
        int[][] count = new int[26][2];
        // 记录重复任务的组数
        int group = 0;
        for (char c: tasks) {
            count[c-'A'][0] = c-'A';
            if (++count[c-'A'][1] == 2)
                // 超过 1 的时候, 记录一次
                group++;
        }
        // 优先级队列, 任务越多的排越前面
        Arrays.sort(count, (e1, e2) -> e2[1] - e1[1]);

        while (num > 0) {
            // 每次调度时钟 + 1
            time++;
            // 尝试调度, 可能是一次空调度
            // 这里应该尝试贪心的思路, 找一个冷静期最长的
            int[] select = null;
            int best_duration = Math.max(n, group-1);
            for (int[] task: count) {
                int this_duration = time - timeRecord[task[0]];
                if (task[1] > 0 && this_duration > n) {
                    select = task;
                    if (this_duration > best_duration) {
                        break;
                    }
                }
            }
            if (select != null) {
                // 从重复变成无重复之后, 重复数量减去一
                if (--select[1] == 1)
                    group--;
                // 记录新的时间
                timeRecord[select[0]] = time;
                // 任务数量减 1
                num--;
            }
        }
        return time;
    }

    public static void main(String[] args) {
        char[] tasks = new char[]{'A','A','A','B','B','B', 'C','C','C', 'D', 'D', 'E'};
        Solution621 solution621 = new Solution621();
        solution621.leastInterval(tasks, 2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
