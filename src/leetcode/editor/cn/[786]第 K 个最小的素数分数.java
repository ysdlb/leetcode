//给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数 组成，且其中所有整数互不相同。 
//
// 对于每对满足 0 <= i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。 
//
// 那么第 k 个最小的分数是多少呢? 以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == 
//arr[j] 。 
//
// 示例 1： 
//
// 
//输入：arr = [1,2,3,5], k = 3
//输出：[2,5]
//解释：已构造好的分数,排序后如下所示: 
//1/5, 1/3, 2/5, 1/2, 3/5, 2/3
//很明显第三个最小的分数是 2/5
// 
//
// 示例 2： 
//
// 
//输入：arr = [1,7], k = 1
//输出：[1,7]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= arr.length <= 1000 
// 1 <= arr[i] <= 3 * 10⁴ 
// arr[0] == 1 
// arr[i] 是一个 素数 ，i > 0 
// arr 中的所有数字 互不相同 ，且按 严格递增 排序 
// 1 <= k <= arr.length * (arr.length - 1) / 2 
// 
//
// 
//
// 进阶：你可以设计并实现时间复杂度小于 O(n²) 的算法解决此问题吗？ 
//
// Related Topics 数组 二分查找 排序 堆（优先队列） 👍 261 👎 0


import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution786 {
    /* 786.第 K 个最小的素数分数: https://leetcode.cn/problems/k-th-smallest-prime-fraction/
     * 相似题目:
     *  373.查找和最小的 K 对数字: https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/
     *  719.找出第 k 小的距离对: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     *
     *
     * n 的量级在 10^3, 所以可以使用 size 为 k 的优先队列，枚举 n*(n-1) 个数对来做
     * 同 719 中描述的思路, 但时间复杂度比较高, 为 O(n^2 * lgk)
     *
     *
     * 因为 arr 有序, 所以 arr 隐含了一个 n-1 行有序的数组, 如果 arr = [1,2,3,5]
     *  [1/2]
     *  [1/3, 2/3]
     *  [1/5, 2/5, 3/5]
     * 每一行为 arr[i]/arr[j] (i 枚举 0<=i<j)
     *
     * 第一步将第一列放入一个小根堆中, 时间复杂度 O(n*lgn)
     * 然后归并 k 次, 时间复杂度 O(k*lgn)
     *
     *
     * 也可参考 719 二分做法, 时间复杂度 O(n*lgD)
     */
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int len = arr.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingDouble(e -> (double)arr[e[0]]/arr[e[1]]));
        for (int i = 1; i < len; i++)
            minHeap.offer(new int[]{0, i});

        int[] min = null;
        for (int x = 0; x < k; x++) {
            min = minHeap.poll();
            int i = min[0], j = min[1];
            // i 枚举 0<=i<j
            if (++i < j)
                minHeap.offer(new int[]{i,j});
        }
        return new int[]{arr[min[0]], arr[min[1]]};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
