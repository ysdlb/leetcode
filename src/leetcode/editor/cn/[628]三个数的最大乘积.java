//给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：6
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4]
//输出：24
// 
//
// 示例 3： 
//
// 
//输入：nums = [-1,-2,-3]
//输出：-6
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10⁴ 
// -1000 <= nums[i] <= 1000 
// 
// Related Topics 数组 数学 排序 👍 367 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution628 {
    /**
     * 如果这些数都是非负数, 那么最大的三个数乘积肯定数最大的三个数相乘
     * 如果这些数都是负数, 那么最大的三个数乘积也是最大的三个数相乘 ( 负数的绝对值越小越大 )
     * 如果有正有负, 可能数最大的三个数相乘, 也可能是最大的数和最小的两个数相乘
     *
     * 即我们需要找出最大的三个数和最小的两个数
     * 因为数量不多, 所以完全可以硬编码
     */
    public int maximumProduct(int[] nums) {
        if (nums.length < 3) throw new RuntimeException("length isn`t valid");

        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int n: nums) {
            // 最大的三个数
            if (n >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {
                max3 = n;
            }

            // 最小的两个数
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {
                min2 = n;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
