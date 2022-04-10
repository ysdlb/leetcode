//给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
// 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。 
//
// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
//
// 对于给定的输入，保证和为 target 的不同组合数少于 150 个。 
//
// 
//
// 示例 1： 
//
// 
//输入：candidates = [2,3,6,7], target = 7
//输出：[[2,2,3],[7]]
//解释：
//2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
//7 也是一个候选， 7 = 7 。
//仅有这两种组合。 
//
// 示例 2： 
//
// 
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]] 
//
// 示例 3： 
//
// 
//输入: candidates = [2], target = 1
//输出: []
// 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都 互不相同 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯 👍 1814 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <a href="https://leetcode-cn.com/problems/combination-sum/">leetcode-39</a>
 */
class Solution39 {

    /**
     * 参考 77 v2; 78 v2
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        backTrack(ret, deque, candidates, 0, target, 0);
        return ret;
    }

    /**
     * @param deque deque
     * @param start candidates 游标
     * @param target 目标大小, 不变
     * @param sum deque 的和
     */
    private void backTrack(List<List<Integer>> ret, Deque<Integer> deque, int[] candidates, int start, int target, int sum) {
        if (sum == target)
            ret.add(new ArrayList<>(deque));
        if (sum > target)
            return;

        for (int i = start; i < candidates.length; i++) {
            deque.addLast(candidates[i]);
            // 标准的子集/组合问题是如何保证不重复使用元素的？
            // 如果我想让每个元素被重复使用，我只要把 i + 1 改成 i 即可
            backTrack(ret, deque, candidates, i, target, sum + candidates[i]);
            deque.removeLast();
        }
    }
}

/**
 * 优化代码结构, 在 Solution39 的基础上适当减枝
 */
class Solution39_v2 {
    /**
     * 参考 77 v2; 78 v2
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        backTrack(ret, deque, candidates, 0, target);
        return ret;
    }

    /**
     * @param deque deque
     * @param start candidates 游标
     * @param needTarget 目标大小, 变化
     */
    private void backTrack(List<List<Integer>> ret, Deque<Integer> deque, int[] candidates, int start, int needTarget) {
        if (needTarget == 0)
            ret.add(new ArrayList<>(deque));
        if (needTarget < 0)
            return;

        for (int i = start; i < candidates.length; i++) {
            // 适当减去枝干
            if (candidates[i] > needTarget)
                continue;
            deque.addLast(candidates[i]);
            // 标准的子集/组合问题是如何保证不重复使用元素的？
            // 如果我想让每个元素被重复使用，我只要把 i + 1 改成 i 即可
            backTrack(ret, deque, candidates, i, needTarget - candidates[i]);
            deque.removeLast();
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
