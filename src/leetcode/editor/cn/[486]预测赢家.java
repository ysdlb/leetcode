//给你一个整数数组 nums 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。 
//
// 玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 0 。每一回合，玩家从数组的任意一端取一个数字（即，nums[0
//] 或 nums[nums.length - 1]），取到的数字将会从数组中移除（数组长度减 1 ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取
//时，游戏结束。 
//
// 如果玩家 1 能成为赢家，返回 true 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true 。你可以假设每个玩家的玩法都会使他的分
//数最大化。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,2]
//输出：false
//解释：一开始，玩家 1 可以从 1 和 2 中进行选择。
//如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）
//可选。 
//所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
//因此，玩家 1 永远不会成为赢家，返回 false 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,5,233,7]
//输出：true
//解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
//最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 true，表示玩家 1 可以成为赢家。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 10⁷ 
// 
// Related Topics 递归 数组 数学 动态规划 博弈 👍 486 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution486 {
    public boolean PredictTheWinner(int[] nums) {
        return predict(nums, 0, nums.length - 1) >= 0;
    }

    /**
     * 最重要的假设
     * 假设每个玩家的玩法都会使他的分数最大化, 也就是说, 给定一个 nums[l, h], 不管是谁, 它的最高得分是固定的
     * 互相博弈的关系, 一方总是减去另一方的得分
     *
     * 返回 nums[l, h] 某人可以赢的最大分数 (双方总会选自己的最优解)
     * 下次轮到对手选
     *
     * 所以在本次我们可以获得的分数可能是
     * predict 返回的是对手在某范围内的最优解
     * nums[l] - predict[nums, l+1, h)
     * 或
     * nums[h] - predict[nums, l, h-1]
     * 选比较大的就可以了
     *
     * 当 l == h 时, 此人则可以白嫖 nums[l] 分
     */
    private int predict(int[] nums, int l, int h) {
        if (l == h) return nums[l];
        return Math.max(nums[l] - predict(nums, l+1, h),
                nums[h] - predict(nums, l, h-1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class DP486 {
    /**
     * 最重要的假设
     * 假设每个玩家的玩法都会使他的分数最大化, 也就是说, 给定一个 nums[l, h], 不管是谁, 它的最高得分是固定的
     * 互相博弈的关系, 一方总是减去另一方的得分
     *
     * 返回 nums[l, h] 某人可以赢的最大分数 (双方总会选自己的最优解)
     * 下次轮到对手选
     *
     * 关系式
     * dp[l,h] = // 意义: 在 nums[l,h] 内可以获得较竞争对手获得的最高分
     *      if l == h, return nums[l]
     *      else, return max(nums[l] - dp[l+1,h]), nums[h] - dp[l, h-1])
     */
    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];

        // i 代表 0,0   0,1   0,2 这样的间距 0, 2, 3, 4
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i; j++) {
                if (i == 0)
                    dp[j][j] = nums[j];
                else
                    dp[j][j+i] = Math.max(nums[j] - dp[j+1][j+i],
                            nums[j+i] - dp[j][j+i-1]);
            }
        }

        return dp[0][nums.length-1] >= 0;
    }
}

class EnhanceDP486 {
    /**
     * 最重要的假设
     * 假设每个玩家的玩法都会使他的分数最大化, 也就是说, 给定一个 nums[l, h], 不管是谁, 它的最高得分是固定的
     * 互相博弈的关系, 一方总是减去另一方的得分
     *
     * 返回 nums[l, h] 某人可以赢的最大分数 (双方总会选自己的最优解)
     * 下次轮到对手选
     *
     * 关系式
     * dp[l,h] = // 意义: 在 nums[l,h] 内可以获得较竞争对手获得的最高分
     *      if l == h, return nums[l]
     *      else, return max(nums[l] - dp[l+1,h]), nums[h] - dp[l, h-1])
     */
    public boolean PredictTheWinner(int[] nums) {
        int[] dp = new int[nums.length];

        System.arraycopy(nums, 0, dp, 0, nums.length);

        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                // 用于确定 nums 的两端位置
                int k = nums.length - i;
                dp[j] = Math.max(nums[j] - dp[j+1],
                        nums[j+k] - dp[j]);
            }
        }

        return dp[0] >= 0;
    }
}
