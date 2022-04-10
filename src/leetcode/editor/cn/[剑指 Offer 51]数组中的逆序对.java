//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。 
//
// 
//
// 示例 1: 
//
// 输入: [7,5,6,4]
//输出: 5 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 621 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer51 {
    public int reversePairs(int[] nums) {
        int[] tmp = new int[nums.length];
        return mergeSort(nums, tmp, 0, nums.length - 1);
    }

    /**
     * 归并排序 (后续遍历)
     */
    private int mergeSort(int[] nums, int[] tmp, int lPtr, int rPtr) {
        if (lPtr >= rPtr) return 0;

        int mid = (lPtr + rPtr) / 2;
        int subCounter = mergeSort(nums, tmp, lPtr, mid)
                + mergeSort(nums, tmp, mid + 1, rPtr);

        // 两个有序数组归并
        int i = lPtr, j = mid + 1, k = lPtr;
        int counter = 0;
        // 右边有几个比左边当前元素小
        while (i <= mid) {
            while (j <= rPtr && nums[i] > nums[j])
                tmp[k++] = nums[j++];
            counter += j - mid - 1;
            tmp[k++] = nums[i++];
        }
        while (j <= rPtr)
            tmp[k++] = nums[j++];

        // 将 tmp 的有序结果拷贝回 num, 方便更上一级别归并
        System.arraycopy(tmp, lPtr, nums, lPtr, rPtr - lPtr + 1);

        return counter + subCounter;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
