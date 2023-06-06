//给你一个整数数组 nums 和一个 正 整数 k 。你可以选择数组的任一 子序列 并且对其全部元素求和。 
//
// 数组的 第 k 大和 定义为：可以获得的第 k 个 最大 子序列和（子序列和允许出现重复） 
//
// 返回数组的 第 k 大和 。 
//
// 子序列是一个可以由其他数组删除某些或不删除元素排生而来的数组，且派生过程不改变剩余元素的顺序。 
//
// 注意：空子序列的和视作 0 。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [2,4,-2], k = 5
//输出：2
//解释：所有可能获得的子序列和列出如下，按递减顺序排列：
//- 6、4、4、2、2、0、0、-2
//数组的第 5 大和是 2 。
// 
//
// 示例 2： 
//
// 输入：nums = [1,-2,3,4,-10,12], k = 16
//输出：10
//解释：数组的第 16 大和是 10 。
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 1 <= k <= min(2000, 2ⁿ) 
// 
//
// Related Topics 数组 排序 堆（优先队列） 👍 67 👎 0


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2386 {
    /* 2386.找出数组的第 K 大和: https://leetcode.cn/problems/find-the-k-sum-of-an-array/
     * 相似题:
     *  719.找出第 k 小的距离对: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     *
     *
     * 看题解: 堆方案/二分方案
     *
     * 多路归并堆方案:
     *  1. 求出所有正数的和 sum
     *  2. 将所有的值取绝对值排序组成新 nums，任何一个子序列的和都等价于从 sum 中减去某些数得到
     *
     * 最大的子序列和显然就是 <sum,0> 本身
     *   (0)|1
     * 第 2 大的子序列和显然为 <sum2,1>
     *   (0,1)|2, (1)|2
     * 第 3 大的子序列和 sum2-nums[1] / sum2-nums[1]+nums[0]
     *   (0,1)|2, (1,2)|3, (2)|3
     * ...
     *    (0,1,2)|3, (0,2)|3, (1,2)|3, (2)|3
     * or (0,1)|2, (1,2)|3, (2,3)|4, (3)
     *
     * 总是确保比它更小的数，要么已经出堆，要么仍然在堆里
     *
     * 留存: 二分方案
     */
    public long kSum(int[] nums, int k) {
        long sum = 0L;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0)
                nums[i] = -nums[i];
            else
                sum += nums[i];
        }

        Arrays.sort(nums);
        PriorityQueue<long[]> maxHeap = new PriorityQueue<>(Comparator.<long[]>comparingLong(e -> e[0]).reversed());
        // 此时堆顶元素是最大值
        maxHeap.offer(new long[]{sum, 0});
        while (--k > 0) {
            long[] max = maxHeap.poll();
            if (max[1] >= nums.length) continue;

            long s = max[0];
            int i = (int) max[1];
            maxHeap.offer(new long[]{s-nums[i], i+1}); // 计入 nums[i-1]
            if (i > 0)
                maxHeap.offer(new long[]{s-nums[i]+nums[i-1], i+1});
        }
        return maxHeap.peek()[0];
    }

    public static void main(String[] args) {
        Solution2386 solu = new Solution2386();
        int[] arr = new int[]{2,4,-2};
        long l = solu.kSum(arr, 5);
        System.out.println(l);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
