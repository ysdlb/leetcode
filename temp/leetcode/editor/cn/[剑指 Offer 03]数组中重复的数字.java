//找出数组中重复的数字。 
//
// 
//在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请
//找出数组中任意一个重复的数字。 
//
// 示例 1： 
//
// 输入：
//[2, 3, 1, 0, 2, 5, 3]
//输出：2 或 3 
// 
//
// 
//
// 限制： 
//
// 2 <= n <= 100000 
// Related Topics 数组 哈希表 排序 👍 681 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer3 {
    /**
     * <a href="https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/"> leetcode-offer-3</a>
     * 排序的时间复杂度是 O(n*lgn)
     * 哈希表的空间复杂度是 O(n)
     *
     * 我们需要一个时间复杂度是 O(n) 空间复杂度是 O(1) 的算法完成
     * 关键点在于长度为 n 的数组, 元素的取值范围是 0, n-1
     * 理想情况下, 都不重复的时候, 值为 i 的元素在数组中的索引位置也是 i
     *
     * 从前往后遍历
     * 取索引为 i 的一个元素, 如果其值 v == i 跳过
     * 否则, 判断 nums[i](v) == nums[v] 是否成立, 成立则该值是重复的, 返回结束, 不成立的话继续下面流程
     * 将索引为 v 位置的元素与当前位置的元素交换, 继续判断 v == i 是否成立
     * 直到 nums[i] == i 为止
     */
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) continue;;

            while (nums[i] != i) {
                int v = nums[i];
                if (v == nums[v]) // v 值本来就在 nums[v] 的位置, 如果 nums[v] 位置的值也为 v, 则 v 一定重复.
                    return v;
                // 交换 位置 i 与 位置 v 的元素
                nums[i] = nums[v];
                nums[v] = v;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
