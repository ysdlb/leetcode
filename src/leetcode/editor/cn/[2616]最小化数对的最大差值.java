//给你一个下标从 0 开始的整数数组 nums 和一个整数 p 。请你从 nums 中找到 p 个下标对，每个下标对对应数值取差值，你需要使得这 p 个差值的
// 最大值 最小。同时，你需要确保每个下标在这 p 个下标对中最多出现一次。 
//
// 对于一个下标对 i 和 j ，这一对的差值为 |nums[i] - nums[j]| ，其中 |x| 表示 x 的 绝对值 。 
//
// 请你返回 p 个下标对对应数值 最大差值 的 最小值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,1,2,7,1,3], p = 2
//输出：1
//解释：第一个下标对选择 1 和 4 ，第二个下标对选择 2 和 5 。
//最大差值为 max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1 。所以我们返回 1 
//。
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,2,1,2], p = 1
//输出：0
//解释：选择下标 1 和 3 构成下标对。差值为 |2 - 2| = 0 ，这是最大差值的最小值。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// 0 <= nums[i] <= 10⁹ 
// 0 <= p <= (nums.length)/2 
// 
//
// Related Topics 贪心 数组 二分查找 👍 13 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2616 {

    /* 2616.最小化数对的最大差值: https://leetcode.cn/problems/minimize-the-maximum-difference-of-pairs/
     * 相似题:
     *  2528.最大化城市的最小供电站数目: https://leetcode.cn/problems/maximize-the-minimum-powered-city/
     *
     * 若指定 p 个数对, 最大差值 mi, 求 min{mi}
     * 其实就是第 p 小的数对
     *
     * 指定一个值 x，minX = 0, maxX = max{nums[0:-1]}
     * 显然 x 越大，符合差值 delta <= x 的数对越多
     * 如果数组有序，计算 count(delta <= x) 时间为 O(n)
     *
     * 总时间复杂度 O(n*lgn + n*lgS)
     *
     * 不对，有个条件: 每个下标在这 p 个下标对中最多出现一次
     * count 方法的计算逻辑有变动
     */
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int l = 0, r = nums[nums.length-1]-nums[0];
        while (l < r) {
            int mid = (l+r) >> 1;
            if (count(nums, mid) >= p) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /* 贪心1: 先排序。为了让匹配的数对尽量多，应尽量选相邻的元素，这样更能满足要求
     * 最多能匹配多少个数对：
     *  1. 如果可以选 nums[0] 和 nums[1] ，那么答案等于「n−2 个数的最多数对个数」+1。
     *  2. 如果不选 nums[0]，那么答案等于「n−1 个数的最多数对个数」。
     *  3. 这两种情况取最大值。
     *
     * 贪心二: 直接选（原因留存）
     */
    private int count(int[] nums, int x) {
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len-1; i++) {
            if (nums[i+1]-nums[i] <= x) {
                ans++;
                i++;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
