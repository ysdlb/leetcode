//数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。 
//
// 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。
//返回 所有数对距离中 第 k 小的数对距离。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,1], k = 1
//输出：0
//解释：数对和对应的距离如下：
//(1,3) -> 2
//(1,1) -> 0
//(3,1) -> 2
//距离第 1 小的数对是 (1,1) ，距离为 0 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,6,1], k = 3
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 2 <= n <= 10⁴ 
// 0 <= nums[i] <= 10⁶ 
// 1 <= k <= n * (n - 1) / 2 
// 
// Related Topics 数组 双指针 二分查找 排序 👍 249 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution719 {
    /**
     * 两两一对有 n(n-1) 个数对, 配合 size 为 k 的小顶堆;
     * 可在 O(n^2*lgk) + O(k) 的时间内找到第 k 小的数对距离
     *
     * 如果数对距离 distance == x 有不少于 k 个数对符合条件, 且 distance == x-1 只有少于 k 个 数对符合条件
     * 那么 x 就是我们需要的解
     *
     * 记 count(x) 为所有距离 <= x 的数对数量; 显然函数 count(x) 单调递增
     * 在 nums 数组有序的情况下, 每次求 count(x) 只需要一次遍历, 时间复杂度为 O(n)
     *
     * 而距离对的取值范围为 [0, nums(-1)-nums(0)] ⚠️ 这里最小是不是 nums(1) - nums(0)
     * 因为单调, 所以可以用二分
     *
     * 总时间复杂度为 O(n*lgn + n*lgM)    M 为最大距离之差
     *
     */
    public int smallestDistancePair(int[] nums, int k) {
        if (nums == null || nums.length < 2)
            throw new IllegalArgumentException("Invalid param!");

        Arrays.sort(nums);
        int len = nums.length;
        int min = 0, max = nums[len-1] - nums[0];
        while (min < max) {
            int mid = (min + max) >> 1;
            if (count(nums, mid) >= k) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    /**
     * count(x)
     * i, j 两个指针,
     *
     * 在 i 不超过 j 的情况下, 找到第一个 nums[j] - nums[i] <= x 的 i 指针
     * i, i+1, ... , j-1 于 nums[j] 的距离对肯定都 <= x
     *
     * 每次 j 往后移动一位; (换 j+1 继续计算有多少距离对符合条件
     */
    private int count(int[] nums, int x) {
        int total = 0;
        for (int i = 0, j = 0; j < nums.length;) {
            while (i < j && nums[j] - nums[i] > x) {
                i++;
            }
            total += j-i;
            j++;
        }
        return total;
    }

    public static void main(String[] args) {
        int[] arg = new int[]{9, 10, 7, 10, 6, 1, 5, 4, 9, 8};
        Solution719 solution719 = new Solution719();
        solution719.smallestDistancePair(arg, 11);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
