//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个下标。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// 0 <= nums[i] <= 10⁵ 
// 
// Related Topics 贪心 数组 动态规划 👍 1834 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution55 {
    /**
     * 不断扩展自己的右边界, 如果最后没有到达终点, 返回 false
     * 其实只要比较下 farthest >= n - 1
     */
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i <= max; i++) {
            max = Math.max(max, i+nums[i]);
            if (max >= nums.length-1)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arg = new int[]{3,0,8,2,0,0,1};
        Solution55 solution55 = new Solution55();
        solution55.canJump(arg);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
