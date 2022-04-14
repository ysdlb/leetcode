//给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3], k = 3
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -1000 <= nums[i] <= 1000 
// -10⁷ <= k <= 10⁷ 
// 
// Related Topics 数组 哈希表 前缀和 👍 1425 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution560 {
    /**
     * 因为数组元素有正有负, 所以每个子数组都有可能符合条件
     * 子数组的数量量级为 n^2, 每个子数组求和的时间复杂度为 n, 暴力的时间复杂度为 O(n^2)
     * 其实因为数组可以累加, 所以子数组求和的平均时间复杂度为 O(1), 总的为 O(n^2)
     *
     * 前缀和可以使子数组求和的时间复杂度降低为 O(1), 总时间复杂度为 O(n^2)
     * 对 [0...i] 的和 sum, 我们需要找出有几个 ji < i, [0...ji] 的 和 si, 符合 (sum - si = k --> si == sum - k
     * 边遍历边缓存, 边累积和可以了, 且不需要显式的前缀和
     * 参考 两个数只和 1. <a herf="https://leetcode-cn.com/problems/two-sum">两数之和</a>
     * 前缀和参考 303, 304
     */
    public int subarraySum_v2(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.compute(sum, (key, oldV) -> oldV == null ? 1 : oldV + 1);

        int ret = 0;
        for (int num: nums) {
            sum += num;
            ret += map.getOrDefault(sum - k, 0);
            map.compute(sum, (key, oldV) -> oldV == null ? 1 : oldV + 1);
        }
        return ret;
    }


    /**
     * 因为数组元素有正有负, 所以每个子数组都有可能符合条件
     * 子数组的数量量级为 n^2, 每个子数组求和的时间复杂度为 n, 暴力的时间复杂度为 O(n^2)
     * 其实因为数组可以累加, 所以子数组求和的平均时间复杂度为 O(1), 总的为 O(n^2)
     *
     * 前缀和可以使子数组求和的时间复杂度降低为 O(1), 总时间复杂度为 O(n^2)
     * 前缀和参考 303, 304
     */
    public int subarraySum(int[] nums, int k) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i-1];
        }

        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int minus = i > 0 ? nums[i-1] : 0;
                if (nums[j] - minus == k)
                    ret++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arg = new int[]{1,2,3};
        Solution560 solution560 = new Solution560();
        solution560.subarraySum_v2(arg, 3);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
