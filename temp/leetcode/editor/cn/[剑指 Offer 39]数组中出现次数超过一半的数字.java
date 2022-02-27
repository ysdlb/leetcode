//数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。 
//
// 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
//输出: 2 
//
// 
//
// 限制： 
//
// 1 <= 数组长度 <= 50000 
//
// 
//
// 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/ 
//
// 
// Related Topics 数组 哈希表 分治 计数 排序 👍 244 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer39_MOL {
    /**
     * 摩尔投票法（适用于出现次数超过一半的情况）
     * <p>
     * 结合数组特点, 我们可以考虑在遍历数组的时候保存两个值, 一个是数组中的一个数字
     * 另一个是出现次数;
     *
     * 当我们遍历下一个数字的时候, 如果下一个数字和我们之前保存的数字相同, 则次数加 1,
     * 反之, 次数减 1。
     *
     * 如果数字为 0, 那么我们需要保存下一个数字, 并把次数 +1
     *
     * <p>
     * 由于我们要找的数字出现次数比其它所有数字出现的和还要多, 那么要找的数字肯定是最后一次
     * 把次数设置为 1 的时候
     */
    public int majorityElement(int[] nums) {
        int num = -1, count = 0;
        for (int n: nums) {
            if (count == 0) {
                num = n;
                count++;
            } else if (n != num) {
                count--;
            } else {
                count++;
            }
        }
        return num;
    }
}

class SolutionOffer39_QuickSort {
    /**
     * 因为一定存在某个数出现次数超过一半, 所以中位数必定是数组中（中位数不需要数组全局有序）出现次数超过一半的数
     * 选择快排找中位数
     */
    public int majorityElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        int index = partition(nums, left, right);
        int middle = nums.length / 2;
        while (index != middle) {
            if (index > middle) {
                right = index - 1;
            } else {
                left = index + 1;
            }
            index = partition(nums, left, right);
        }
        return nums[index];
    }

    private int partition(int[] nums, int left, int right) {
        int part = nums[left];
        while (left < right) {
            // 只找右边比 part 小的, 相等的不交换, 否则会造成死循环
            while (left < right && nums[right] >= part)
                right--;
            nums[left] = nums[right];

            // 同理, 小心死循环
            while (left < right && nums[left] <= part)
                left++;
            nums[right] = nums[left];
        }
        nums[left] = part;
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
