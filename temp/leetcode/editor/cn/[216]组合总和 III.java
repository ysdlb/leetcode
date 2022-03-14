//找出所有相加之和为 n 的 k 个数的组合，且满足下列条件： 
//
// 
// 只使用数字1到9 
// 每个数字 最多使用一次 
// 
//
// 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。 
//
// 
//
// 示例 1: 
//
// 
//输入: k = 3, n = 7
//输出: [[1,2,4]]
//解释:
//1 + 2 + 4 = 7
//没有其他符合的组合了。 
//
// 示例 2: 
//
// 
//输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
//解释:
//1 + 2 + 6 = 9
//1 + 3 + 5 = 9
//2 + 3 + 4 = 9
//没有其他符合的组合了。 
//
// 示例 3: 
//
// 
//输入: k = 4, n = 1
//输出: []
//解释: 不存在有效的组合。
//在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
// 
//
// 
//
// 提示: 
//
// 
// 2 <= k <= 9 
// 1 <= n <= 60 
// 
// Related Topics 数组 回溯 👍 433 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * <a href="https://leetcode-cn.com/problems/combination-sum-iii/">leetcode-216</a>
 */
class Solution216 {
    /**
     * 参考 39,40
     * [1...9] 数组中 k 个元素的子集, 且其和为 n
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        backTrack(ret, deque, 1, n, k);
        return ret;
    }

    /**
     * @param start start
     * @param sum 子集的和
     * @param k 子集的元素 固定值
     */
    private void backTrack(List<List<Integer>> ret, Deque<Integer> deque, int start, int sum, int k) {
        if (sum == 0 && deque.size() == k)
            ret.add(new ArrayList<>(deque));

        // 循环做了 i > sum 的判断, 这里可不用
        // if (sum < 0) return;

        for (int i = start; i <= 9; i++) {
            // 后面的都大, break, 不用 continue
            if (i > sum)
                break;
            deque.addLast(i);
            backTrack(ret, deque, i+1, sum - i, k);
            deque.removeLast();
        }
    }

    public static void main(String[] args) {
        new Solution216().combinationSum3(3, 9);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
