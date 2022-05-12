//给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。 
//
// 
//
// 示例 1： 
//
// 
//输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//输出： True
//说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。 
//
// 示例 2: 
//
// 
//输入: nums = [1,2,3,4], k = 3
//输出: false 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= len(nums) <= 16 
// 0 < nums[i] < 10000 
// 每个元素的频率在 [1,4] 范围内 
// 
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩 👍 511 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution698 {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int[] buckets = new int[k];
        Arrays.sort(nums);
        int max = 0, sum = 0;
        for (int num: nums) {
            max = Math.max(max, num);
            sum += num;
        }
        if (sum % k != 0 || max > sum / k) return false;

        return backtrack(nums, nums.length-1, buckets, sum/k);
    }

    /**
     * 整体就是一个暴力的解法，先算出子集的和是多少，并抽象成k个桶，每个桶的值是子集的和。
     * 然后尝试所有不同的组合（即放数到桶中），如果存在一种组合可以使每个桶都正好放下，那么返回可以。如果不存在，返回不可以。
     *
     * 对每个数字, 遍历 k 个桶, 尝试放到一个桶中, 然后递归处理下一个数字
     * 如果全部数字全部处理完, 检查满足 target 要求的情况下, 则返回 true
     * 复杂度 k^n
     *
     */
    private boolean backtrack(int[] nums, int index, int[] buckets, int target) {
        if (index == -1) {
            // 这里不用检查每个桶是否等于 target
            // target * k = sum
            // 每个bucket 的值不会超过 target,
            // 遍历到 -1, sum(bucket) == sum, 所以每个桶一定等于 target
            return true;
        }

        // 每个数字有 k 个选择
        for (int i = 0; i < buckets.length; i++) {
            // 这个桶放不下, 换一个桶,  算是一次减枝
            // 放下这个数后, 还缺点, 但没机会放前一个数了
            int diff = target - (buckets[i] + nums[index]);
            if (diff < 0 || (index > 0 && diff > 0 && diff < nums[0]))
                continue;

            buckets[i] += nums[index];
            // 递归处理下一个数字
            if (backtrack(nums, index-1, buckets, target)) {
                return true;
            }
            buckets[i] -= nums[index];
        }
        // 所有桶都放不下, 直接 gg
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
