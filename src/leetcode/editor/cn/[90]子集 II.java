//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// 
// 
// 
// Related Topics 位运算 数组 回溯 👍 761 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 参考
 * <a href="https://leetcode-cn.com/problems/combinations/">leetcode-77-组合</a>
 * <a href="https://leetcode-cn.com/problems/subsets/">leetcode-78-子集</a>
 *
 * <a href="https://leetcode-cn.com/problems/subsets-ii/">leetcode-90</a>
 */
class Solution90 {
    /**
     * 参考 78 v2
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        backTrack(ret, deque, nums, 0);
        return ret;
    }

    private void backTrack(List<List<Integer>> ret, Deque<Integer> deque, int[] nums, int start) {
        ret.add(new ArrayList<>(deque));

        for (int i = start; i < nums.length; i++) {
            // 相同元素的最后一个, 不适合遍历
            // 剪枝逻辑, 值相同的相邻树枝, 只遍历第一条
            if (i > start && nums[i] == nums[i-1])
                continue;
            deque.addLast(nums[i]);
            backTrack(ret, deque, nums, i+1);
            deque.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] arg = new int[]{1,2,2};
        new Solution90().subsetsWithDup(arg);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
