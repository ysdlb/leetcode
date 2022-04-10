//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 
//
// 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组 [3
//,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为1。 
//
// 示例 1： 
//
// 
//输入：[3,4,5,1,2]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：[2,2,2,0,1]
//输出：0
// 
//
// 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-
//sorted-array-ii/ 
// Related Topics 数组 二分查找 👍 492 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer11 {
    /**
     * 旋转数组的特点: 被旋转到前面的部分肯定比后面部分要大
     * 正常数组的特点: 后面比前面大
     *
     * 如何用代码定义一个旋转数组
     * 判断旋转数组: nums[mid] > nums[right] or nums[mid] < nums[left]
     *
     * 旋转数组的特点
     * 1. nums[mid] > mums[right]: 在前部分, left = mid + 1
     * 2. nums[mid] < mums[left]: 在后部分, right = mid;
     * 但是该方式无法判断 数组为 [2,2,1,2] or [2,1,2,2] 等形式等时候 mid 在前部分还是后部分
     * 3. nums[mid] == nums[left] && nums[mid] == nums[right] left++; right--;
     *
     * 不满足上述特点, 数组为正常升序数组, 最左边的即为最小
     */
    public int minArray(int[] numbers) {
        if (numbers.length == 0)
            throw new RuntimeException("numbers length must greater than 0");

        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (numbers[mid] > numbers[right]) // 前部分比后部分大, 在旋转后前部分
                left = mid + 1;
            else if (numbers[mid] < numbers[left]) // 后部分比前部分小, 在后部分
                right = mid;
            else if (numbers[mid] == numbers[left] && numbers[mid] == numbers[right]) {
                left ++;
                right --;
            } else // 正常升序数组
                right = left;
        }
        return numbers[left];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
