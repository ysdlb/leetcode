//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。 
//
// 你可以按 任何顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
//
// 示例 2： 
//
// 
//输入：n = 1, k = 1
//输出：[[1]] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 1 <= k <= n 
// 
// Related Topics 数组 回溯 👍 894 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <a href="https://leetcode-cn.com/problems/combinations/">leetcode-77-组合</a>
 */
class Solution77 {
    /**
     * 求组合问题
     * 每个数字都有加入组合或者不加入组合两种选择
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        backTrack(ret, deque, 1, n, k);
        return ret;
    }

    private void backTrack(List<List<Integer>> ret, Deque<Integer> path, int now, int n, int k) {
        if (path.size() == k) {
            ret.add(new ArrayList<>(path));
            return;
        }
        if (now > n && n - now + 1 < k - path.size())
            return;

        // 不同于排列, 组合不需要在这个位置把所有数组都选一遍
        // 不加入
        backTrack(ret, path, now+1, n, k);

        path.addLast(now);
        backTrack(ret, path, now+1, n, k);
        path.removeLast();
    }

    public static void main(String[] args) {
        new Solution77().combine(4, 2);
    }
}

/**
 * 参考 78 v2
 */
class Solution77_v2 {
    /**
     * 参考 78 v2 子集问题, 只打印某一层
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        backTrack(ret, deque, 1, n, k);
        return ret;
    }

    private void backTrack(List<List<Integer>> ret, Deque<Integer> path, int start, int n, int k) {
        if (path.size() == k) {
            ret.add(new ArrayList<>(path));
            return;
        }
        if (start > n && n - start + 1 < k - path.size())
            return;

        for (int i = start; i <= n; i++) {
            path.addLast(i);
            backTrack(ret, path, i + 1, n, k);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        new Solution77_v2().combine(4, 2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
