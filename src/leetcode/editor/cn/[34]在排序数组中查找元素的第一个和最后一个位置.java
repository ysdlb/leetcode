//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 二分查找 👍 1405 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution34 {
    /**
     * 因为计算机除法向下取整
     * mid = (1 + 2)/2 永远是 1
     * 所以 left = mid 会造成死循环
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0)
            return new int[]{-1, -1};

        int left = 0, right = nums.length - 1;
        int mid = (left + right) >> 1;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] != target) {
                    // 是最左边的
                    break;
                } else {
                    right = mid - 1;
                }
            }
        }
        if (nums[mid] != target) {
            return new int[]{-1, -1};
        }
        int L = mid;
        left = mid;
        right = nums.length - 1;
        mid = (left + right) >> 1;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                if (mid == nums.length - 1 || nums[mid + 1] != target) {
                    // 是最右边的
                    break;
                } else {
                    left = mid + 1;
                }
            }
        }
        return new int[]{L, mid};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
