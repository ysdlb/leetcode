//给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 
//endDayi 。 
//
// 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。 
//
// 请你返回你可以参加的 最大 会议数目。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：events = [[1,2],[2,3],[3,4]]
//输出：3
//解释：你可以参加所有的三个会议。
//安排会议的一种方案如上图。
//第 1 天参加第一个会议。
//第 2 天参加第二个会议。
//第 3 天参加第三个会议。
// 
//
// 示例 2： 
//
// 
//输入：events= [[1,2],[2,3],[3,4],[1,2]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= events.length <= 10⁵ 
// events[i].length == 2 
// 1 <= startDayi <= endDayi <= 10⁵ 
// 
//
// Related Topics 贪心 数组 排序 堆（优先队列） 👍 249 👎 0


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1353 {
    /* 最多可以参加的会议数目: https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/
     * 同类型题目:
     *   1705. 吃苹果的最大数目: https://leetcode.cn/problems/maximum-number-of-eaten-apples/
     * 相似描述题目:
     *   1751.最多可以参加的会议数目 II: https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended-ii/
     *
     * #maxEventsWrong
     * 只要命中就算参加，求最多可以参加的会议数量
     * 如果再某一天我们可以参加多个会议，
     * 那么越晚结束的会议越要放在后面参加, 开始时间越早的越要优先参加
     * 优先按 结束时间 升序，再按 开始时间 升序
     * -
     * --
     * ----
     *    -
     *
     * 上述思路有问题
     * [[1,5],[1,5],[1,5],[2,3],[2,3]]
     *  --
     *  --
     * -----
     * -----
     * -----
     * 这种情况没办法解决
     * 参加顺序应该为 [[1,5],[2,3],[2,3],[1,5],[1,5]]
     * 每一个时间点，都有一个可参加会议列表
     * 我们尽量的去选择结束时间最小的会议，因为结束时间更大的会议的选择天数更多
     *
     * 设当前时间为 cur
     * 可参加会议列表 start <= cur <= end, 在这里面选 end 最小的
     *
     * 1. 确保当前时间可参加的会议放入集合, 每次可以放入开始时间为 cur 的会议
     * 2. 删除 cur > end 的会议
     * 3. 选一个 end 最小的会议
     * 4. cur++
     *
     * 如果 start 有序，每次只放入 == cur 的会议，可保证 1；
     * 小顶堆可同时完成 2,3
     * 若集合为空，cur 可直接跳到有序的下一个会议
     *
     * 每个元素只放入一次小顶堆，只出一次
     * cur 的递增也做了处理，不会递增次数超过 n 次；
     * 时间复杂度 N*lgN
     */
    public int maxEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparing(e -> e[0]));
        int count = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0, cur = 1; i < events.length || !minHeap.isEmpty(); ) {
            // 确保当前时间可参加的会议放入集合, 每次可以放入开始时间为 cur 的会议
            while (i < events.length && events[i][0] == cur)
                minHeap.offer(events[i++][1]);

            // 删除 cur > end 的会议
            while (!minHeap.isEmpty() && minHeap.peek() < cur)
                minHeap.poll();

            // 选一个 end 最小的会议
            if (!minHeap.isEmpty() && minHeap.poll() != -9876)
                count++;

            // cur++; 若集合为空，cur 可直接跳到有序的下一个会议
            cur = minHeap.isEmpty() && i < events.length ?
                    events[i][0] : cur + 1;
        }
        return count;
    }

    public int maxEventsWrong(int[][] events) {
        Arrays.sort(events, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int count = 0;
        int site = 1;
        for (int[] event : events) {
            site = Math.max(site, event[0]);
            if (site <= event[1]) {
                count++;
                site++;
            }
        }
        return count;
    }

    /*
     *  --
     *  --
     * -----
     * -----
     * -----
     */
    public static void main(String[] args) {
        //
    }
}
//leetcode submit region end(Prohibit modification and deletion)
