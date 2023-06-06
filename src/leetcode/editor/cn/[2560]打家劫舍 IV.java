//沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。 
//
// 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。 
//
// 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。 
//
// 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。 
//
// 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。 
//
// 返回小偷的 最小 窃取能力。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,5,9], k = 2
//输出：5
//解释：
//小偷窃取至少 2 间房屋，共有 3 种方式：
//- 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
//- 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
//- 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
//因此，返回 min(5, 9, 9) = 5 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,7,9,3,1], k = 2
//输出：2
//解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁹ 
// 1 <= k <= (nums.length + 1)/2 
// 
//
// Related Topics 数组 二分查找 👍 50 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution2560 {
    /* 2560.打家劫舍 IV: https://leetcode.cn/problems/house-robber-iv/
     * 相似题:
     *  198.打家劫舍: https://leetcode.cn/problems/house-robber/
     *  2528.最大化城市的最小供电站数目: https://leetcode.cn/problems/maximize-the-minimum-powered-city/
     *
     * 求最小的条件序列最大值 x
     * 若 k = 1，x 为数组中最小的元素
     * 若 k = (nums.length+1)/2，x 为最大奇数位和最大偶数位中的最小值
     *
     * 疑问1: x 越大，需要的 k 越大?
     * 1,11,2,12,3
     * 但这里 k=3(min{1,2,3} 明显比 k=2 小
     *
     * 我们的目标是求一个最小的 x, 满足偷 k 间房屋
     * 给定一个 x(单间房屋可以偷的最大值)，count(x) 表示小偷可以最多偷多少件房屋
     * 显然 x 越大，小偷越自由，可偷的房屋数量 k 就越多;
     * 上面疑问1，x >= 3 时，k 恒等于 3; x < 3 是，k <= 2
     */
    public int minCapability(int[] nums, int k) {
        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        for (int n: nums) {
            l = Math.min(l, n);
            r = Math.max(r, n);
        }

        while (l < r) {
            int mid = (l+r) >> 1;
            if (count(nums, mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * @return 将窃取能力控制在 x 之内，小偷至多可以偷多少间房屋
     * 显然 x 越大，可偷的房屋越多
     *
     * 若 n > x 则不可选 f(i) = f(i-1)
     * 若 n <= x 则可选 f(i) = Math.max{max(f(0:i)), f(i-2)+1}
     */
    private int count(int[] nums, int x) {
        int pre1 = 0, pre2 = 0;
        for (int n: nums) {
            if (n > x) {
                // f(i) = f(i-1);
                pre2 = pre1;
            } else {
                int t = pre1;
                pre1 = Math.max(pre1, pre2 + 1);
                pre2 = t;
            }
        }
        return pre1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
