//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
// Related Topics 数组 动态规划 回溯 👍 1202 👎 0


import javax.management.ObjectName;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution494 {
    /**
     * 动态规划
     */
    public int findTargetSumWays(int[] nums, int target) {
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);
        for (int num: nums) {
            countMap = countRet(countMap, num);
        }
        return countMap.getOrDefault(target, 0);
    }

    private Map<Integer, Integer> countRet(Map<Integer, Integer> map, int num) {
        Map<Integer, Integer> ret = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            ret.compute(entry.getKey() + num,
                    (key, oldV) -> oldV == null ? entry.getValue() : oldV + entry.getValue());
            ret.compute(entry.getKey() - num,
                    (key, oldV) -> oldV == null ? entry.getValue() : oldV + entry.getValue());
        }
        return ret;
    }
}

class Solution494_Simple_Backtrack {
    /**
     * 每个数只有 + - 两种选择
     * 时间复杂度 2^n
     */
    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, 0, 0, target);
        return count;
    }

    int count = 0;

    private void backtrack(int[] nums, int i, int ret, int target) {
        if (i == nums.length) {
            if (ret == target)
                count++;
            return;
        }

        backtrack(nums, i + 1, ret + nums[i], target);
        backtrack(nums, i + 1, ret - nums[i], target);
    }

    public static void main(String[] args) {
        int[] arg = new int[]{1, 1, 1, 1, 1};
        Solution494_Simple_Backtrack solution494 = new Solution494_Simple_Backtrack();
        solution494.findTargetSumWays(arg, 3);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
