//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。 
//
// 
//
// 示例： 
//
// 
//输入：nums = [1,2,3,4]
//输出：[1,3,2,4] 
//注：[3,1,2,4] 也是正确的答案之一。 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 50000 
// 0 <= nums[i] <= 10000 
// 
// Related Topics 数组 双指针 排序 👍 189 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer21 {
    public int[] exchange(int[] nums) {
        for (int i = 0, j = nums.length - 1;;) {
            // 从前往后找偶数
            while (i < nums.length && (nums[i] & 1) != 0)
                i++;
            // 从后往前找奇数
            while (j >= 0 && (nums[j] & 1) == 0)
                j--;

            if (i >= j)
                break;;
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
