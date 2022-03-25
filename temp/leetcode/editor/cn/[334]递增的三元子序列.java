//给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。 
//
// 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 
//true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,4,5]
//输出：true
//解释：任何 i < j < k 的三元组都满足题意
// 
//
// 示例 2： 
//
// 
//输入：nums = [5,4,3,2,1]
//输出：false
//解释：不存在满足题意的三元组 
//
// 示例 3： 
//
// 
//输入：nums = [2,1,5,0,4,6]
//输出：true
//解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
//
// 
//
// 进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？ 
// Related Topics 贪心 数组 👍 548 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 贪心通用方法看 300 贪心思路
 */
class Solution334 {
    /**
     * 这里是递增子序列, 不需要连续, 所以用单调栈 (单调递增)
     * 一旦栈的 size 达到 3, 就可以返回 true
     * 上面思路不对 1 8 1 3 1 5, 这个样例不行
     *
     * 贪心思路
     * a 始终记录最小元素, b 为某个子序列排在 a 后面的第二大的元素
     * 接下来不断更新 a, 同时保证 b 尽可能的小
     * 如果下一个元素比 b 大, 则证明找到里长度为 3 的递增子序列
     *
     * b 总是隐含里一个条件: 排在 b 前面有一个比 b 更小的元素 a
     *
     *
     *
     * 双向遍历 ( 记录左边最小数组和右边最大数组 )
     * 左边最小 i 属于 [1,len-1] leftMin[i] = min{leftMin[i-1], nums[i]}
     * 右边最大 i 属于 [len-2,0] rightMax[i] = max{rightMax[i+1], nums[i]}
     *
     * 第三次遍历
     * 对 nums 数组, i 属于 [1, len-2], 如果 leftMin[i] < nums[i] < rightMax[i] 则返回 true
     * 如果所有的 i 都不满足上述条件, 返回 false
     */
    public boolean increasingTriplet(int[] nums) {
        int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE;
        for (int num: nums) {
            if (num <= a)
                a = num;
            else if (num <= b)
                b = num;
            else
                return true;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
