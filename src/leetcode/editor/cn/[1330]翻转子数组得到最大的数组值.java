//给你一个整数数组 nums 。「数组值」定义为所有满足 0 <= i < nums.length-1 的 |nums[i]-nums[i+1]| 的和。 
//
// 你可以选择给定数组的任意子数组，并将该子数组翻转。但你只能执行这个操作 一次 。 
//
// 请你找到可行的最大 数组值 。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [2,3,1,5,4]
//输出：10
//解释：通过翻转子数组 [3,1,5] ，数组变成 [2,5,1,3,4] ，数组值为 10 。
// 
//
// 示例 2： 
//
// 输入：nums = [2,4,9,24,2,1,10]
//输出：68
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3*10^4 
// -10^5 <= nums[i] <= 10^5 
// 
//
// Related Topics 贪心 数组 数学 👍 84 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1330 {
    /* 1330. 翻转子数组得到最大的数组值: https://leetcode.cn/problems/reverse-subarray-to-maximize-array-value/
     * 分类讨论相似题:
     *
     * 问题是求数组的所有差值和
     *
     * 思路:
     * 一次翻转至多引起两对差值的变化, 翻转连续序列 [i,j], 共有 n(n+1) 中可能，暴力遍历的时间复杂度为 O(n^2)
     * 数组长度上万，大概率会超时
     *
     * 如何优化到 O(n)
     *
     * 看了题解，绝对值可以分类讨论化简成最大/最小值
     *
     * 若 i == 0 或 j == n-1, 即翻转 [0,k-1] 或 [k,n-1]; 1<=k<n
     * 此时只有一对差值受到影响
     *   记 a = nums[k-1], b = nums[k]
     *   受影响的原差值为 d_ab = abs(a - b);
     *   d = max{abs(nums[0]-b), abs(a-nums[n-1])} - d_ab
     *
     * 若 0 < i < j < n-1
     * 此时总是有两对差值受到影响
     *  d = 2*(相邻最小里的最大值 - 相邻最大里的最小值)
     *
     * 求两种情况下 d 的最大值即可
     */
    public int maxValueAfterReverse(int[] nums) {
        int originSum = 0;
        int maxDiff = Integer.MIN_VALUE;
        int n = nums.length;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int k = 1; k < n; k++) {
            int a = nums[k-1], b = nums[k];
            int d_ab = Math.abs(a - b);
            originSum += d_ab;

            // 若 i == 0 或 j == n-1, 翻转 [0,k-1] 或 [k,n-1], 两种情况均只有一对差值受到影响
            // maxDiff = Math.max(Math.abs(nums[0]-b), Math.abs(a-nums[n-1])) - d_ab;
            int d = Math.max(Math.abs(nums[0]-b), Math.abs(a-nums[n-1])) - d_ab;
            maxDiff = Math.max(maxDiff, d);

            // 若 0 < i < j < n-1, 此时总是有两对差值受到影响, d = 2*(相邻最小里的最大值 - 相邻最大里的最小值)
            max = Math.max(max, Math.min(a, b));
            min = Math.min(min, Math.max(a, b));
        }
        maxDiff = Math.max(maxDiff, 2*(max-min));
        return originSum + maxDiff;
    }

    public static void main(String[] args) {
        Solution1330 so = new Solution1330();
        int[] nums = new int[]{63674,-34608,86424,-52056,-3992,93347,2084,-28546,-75702,-28400};
        int i = so.maxValueAfterReverse(nums);
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
