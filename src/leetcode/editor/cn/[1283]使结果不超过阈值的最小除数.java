//给你一个整数数组 nums 和一个正整数 threshold ，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。 
//
// 请你找出能够使上述结果小于等于阈值 threshold 的除数中 最小 的那个。 
//
// 每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。 
//
// 题目保证一定有解。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,5,9], threshold = 6
//输出：5
//解释：如果除数为 1 ，我们可以得到和为 17 （1+2+5+9）。
//如果除数为 4 ，我们可以得到和为 7 (1+1+2+3) 。如果除数为 5 ，和为 5 (1+1+1+2)。
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,3,5,7,11], threshold = 11
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：nums = [19], threshold = 5
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10^4 
// 1 <= nums[i] <= 10^6 
// nums.length <= threshold <= 10^6 
// 
//
// Related Topics 数组 二分查找 👍 92 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1283 {

    /* 1283.使结果不超过阈值的最小除数: https://leetcode.cn/problems/find-the-smallest-divisor-given-a-threshold/
     * 相似题:
     *  875.爱吃香蕉的珂珂: https://leetcode.cn/problems/koko-eating-bananas/
     *  719.找出第 k 小的距离对: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     *
     * count(x) 表示除数为 x 时，nums 经运算之后的和的大小
     * 显然 count(x) 单调减
     * x 最小值为 1, 最大值为 max{nums[0:-1]}
     *
     * 时间复杂度 O(n*lgC)
     *
     * ⚠️：向上取整 (n+x-1)/x
     */
    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1, r = 0;
        for (int n: nums) r = Math.max(r, n);

        while (l < r) {
            int mid = (l+r) >> 1;
            // 若太小，则左移
            if (count(nums, mid) <= threshold) {
                r = mid;
            } else {
                l = mid+1;
            }
        }
        return l;
    }

    private int count(int[] nums, int x) {
        int ans = 0;
        for (int n: nums) {
            ans += (n+x-1)/x;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
