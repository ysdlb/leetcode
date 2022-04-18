//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 1333 👎 0


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 相似题目 剑指Offer40, 703
 */
class Head215 {
    /**
     * 一个小顶堆
     * 遍历每一个 nums 元素 num
     * 如果堆的 size < k; 则直接 offer
     * 如果 num > 大于堆顶元素, 则 poll 堆顶元素, offer num
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (heap.size() < k)
                heap.offer(nums[i]);
            else {
                if (nums[i] > heap.peek()) {
                    heap.poll();
                    heap.offer(nums[i]);
                }
            }
        }
        return heap.peek();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class QuickSort215 {
    /**
     * 由于我们只关心第 k 大的, 所以我们可以用
     * 基于快排的选择方法
     *
     * 降序快排的 partition 返回一个分界数的下标, 假设下表为 index
     * 那么 nums[index] 一定为第 index + 1 大的数
     *
     * 我们只需要在 k = index - 1 的时候, 返回 nums[index - 1] 就可以了
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    /**
     * kk 位置的数即为第 kk + 1 大的数
     */
    private int quickSelect(int[] nums, int l, int h, int kk) {
        int index = partition(nums, l, h);
        if (index == kk)
            return nums[kk];
        return index > kk ? quickSelect(nums, l, index - 1, kk)
                :quickSelect(nums, index + 1, h, kk);
    }

    /**
     * 传统 partition
     * 填坑法
     */
    private int partition(int[] nums, int l, int h) {
        int pivot = nums[l];

        while (l < h) {
            while (l < h && nums[h] <= pivot)
                h--;
            nums[l] = nums[h];

            while (l < h && nums[l] >= pivot)
                l++;
            nums[h] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }
}
