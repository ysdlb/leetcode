//给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用 一次 。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
// Related Topics 数组 回溯 👍 871 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <a href="https://leetcode-cn.com/problems/combination-sum-ii/">leetcode-40</a>
 */
class Solution40 {
    /**
     * 参考 39 v2
     * 变化: 元素可重不可复选
     *
     * 参考 90 , 元素可重, 如何避免结果重复
     * 参考 77 v2; 78 v2
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
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
            // 对于重复元素, 只处理第一个
            if (i > start && candidates[i] == candidates[i-1])
                continue;

            // 适当减去枝干
            if (candidates[i] > needTarget)
                continue;
            deque.addLast(candidates[i]);
            // 标准的子集/组合问题是如何保证不重复使用元素的？
            // 如果我想让每个元素被重复使用，我只要把 i + 1 改成 i 即可
            // 不重复使用元素, 把 i 变成 i+1
            backTrack(ret, deque, candidates, i+1, needTarget - candidates[i]);
            deque.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] arg = new int[]{10,1,2,7,6,1,5};
        new Solution40().combinationSum2(arg, 8);

        // [
        //  [1,1,6],
        //  [1,2,5],
        //  [1,7],
        //  [2,6]
        // ]
    }
}
//leetcode submit region end(Prohibit modification and deletion)
