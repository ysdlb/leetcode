//一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出
//这个数字。 
//
// 
//
// 示例 1: 
//
// 输入: [0,1,3]
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [0,1,2,3,4,5,6,7,9]
//输出: 8 
//
// 
//
// 限制： 
//
// 1 <= 数组长度 <= 10000 
// Related Topics 位运算 数组 哈希表 数学 二分查找 👍 225 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer53_2 {
    /**
     * 特点
     * 1. 递增
     * 2. 唯一
     * 推导出缺失的数字位置发生首次串位, 后面一直串位
     * 即找到第一个串位的数字 ( 最左边串位的数字 )
     */
    public int missingNumber(int[] nums) {
        // 计算机的除法天然向下取整
        int l = 0, r = nums.length - 1, mid = (l+r) >> 1;
        // 可能右边就没有串位的数字, 比如 [0], [0,1], 此时找不到最左边串位的数字
        if (nums[r] == r) return nums.length;

        while (l < r) {
            if (nums[mid] > mid)
                r = mid;
            else // 不可能 nums[mid] < mid, 这里只能是 == 的情况
                l = mid + 1;
            mid = (l+r) >> 1;
        }
        return nums[l] - 1;
    }

    /**
     * 即找到最后一个不串位的数字 ( 最右边不串位的数字 )
     */
    public int missingNumberV2(int[] nums) {
        // 向上取整
        int l = 0, r = nums.length - 1, mid = (l+r+1) >> 1;
        // 可能左边就没有不串位的数字, 根本找不到最右边不串位的数字
        if (nums[0] == 1) return 0;

        while (l < r) {
            if (nums[mid] == mid)
                l = mid;
            else // 不可能 nums[mid] < mid, 这里只能是 > 的情况
                r = mid - 1;
            mid = (l+r+1) >> 1;
        }
        return nums[l] + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
