//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 3： 
//
// 
//输入：nums = [1,2,3]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 1000 
// 
// Related Topics 数组 动态规划 👍 942 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution213 {
    /**
     * 递推公式参考 <a href="https://leetcode-cn.com/problems/house-robber/">198</a>
     * 难点在于如果要偷最后一间房子必须确保第一间房子没被偷过
     *
     * 正常的打架截舍可以计算出第一间房子可能被偷情况下的最大值, 由于第一间房子可能被偷过了, 所以最后一间房子一定不能偷
     * 此时的范围为 [0, nums.length-1) 可能存在最大值是第一家没被偷的情况下发生的, 这个时候是有机会偷最后一家, 来达到更大的一个值
     * 所以, 我们需要再用下面的情况来做进一步覆盖,
     * 不偷第一家, 可能偷最后一家 [1, nums.length)
     * (从第二间房子起偷一定可以计算出第一间房子一定不被偷的情况下的最大值)
     */
    public int rob(int[] nums) {
        // 只有一间房子的时候, 无法放弃最后一间或放弃第一间
        if (nums.length == 1) return nums[0];

        return Math.max(
                rob(nums, 0, nums.length - 1),
                rob(nums, 1, nums.length)
        );
    }

    private int rob(int[] nums, int left, int right) {
        int notSteal = 0, steal = 0;
        for (int i = left; i < right; i++) {
            int notStealCopy = notSteal;
            notSteal = Math.max(steal, notStealCopy);
            steal = notStealCopy + nums[i];
        }
        return Math.max(steal, notSteal);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
