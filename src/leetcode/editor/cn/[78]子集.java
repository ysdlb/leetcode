//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
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
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯 👍 1519 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <a href="https://leetcode-cn.com/problems/combinations/">leetcode-77-组合</a>
 * <a href="https://leetcode-cn.com/problems/subsets/">leetcode-78-子集</a>
 */
class Solution78 {
    /**
     * 求数量为 [0,nums.len] 的所有组合
     * 参考 77 题
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int k = 0; k <= nums.length; k++) {
            backTrack(ret, deque, nums, 0, k);
        }
        return ret;
    }

    private void backTrack(List<List<Integer>> ret, Deque<Integer> path, int[] nums, int i, int k) {
        if (path.size() == k) {
            ret.add(new ArrayList<>(path));
            return;
        }
        if (i >= nums.length && nums.length - i < k - path.size())
            return;

        // 不同于排列, 组合不需要在这个位置把所有数组都选一遍
        // 不加入
        backTrack(ret, path, nums, i+1, k);

        path.addLast(nums[i]);
        backTrack(ret, path, nums, i+1, k);
        path.removeLast();
    }

    public static void main(String[] args) {
        int[] arg = new int[]{1,2,3};
        new Solution78().subsets(arg);
    }

}

class Solution78_v2 {
    /**
     * 由 n 个元素的子集推出 n+1 个元素的子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        backTrack(ret, deque, nums, 0);
        return ret;
    }

    /**
     * n 个元素数量的子集应当从 n-1 个元素的子集中来, 这样效率更高
     */
    private void backTrack(List<List<Integer>> ret, Deque<Integer> path, int[] nums, int start) {
        ret.add(new ArrayList<>(path));

        // 任意节点的至都是一个子集合
        for (int i = start; i < nums.length; i++) {
            path.addLast(nums[i]);
            backTrack(ret, path, nums, i+1);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] arg = new int[]{1, 2, 3};
        new Solution78_v2().subsets(arg);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
