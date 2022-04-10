//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 数组 回溯 👍 914 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> output = new ArrayList<>();
        for (int num: nums) {
            output.add(num);
        }
        List<List<Integer>> resCol = new ArrayList<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        backtrack(resCol, output, 0, map);
        return resCol;
    }

    /**
     * 取巧做法, 用 set 更直白些
     * 回溯搜索 + 剪枝
     * nums 有重复, 只要保证 在当前位置搜索当时候 ( 某一层递归 ）搜索 （ 填入 ）的值不重复就可以了
     * @param resCol 结果集合
     * @param output 回溯列表, size 表示总数量, [first, ...] 是将要回溯的内容
     * @param first 回溯内容的启始位置
     * @param map 每一层出现的
     */
    public void backtrack(List<List<Integer>> resCol, List<Integer> output, int first, Map<Integer, Set<Integer>> map) {
        if (first == output.size()) {
            resCol.add(new ArrayList<>(output));
            return;
        }

        Set<Integer> set = map.computeIfAbsent(first, _key -> new HashSet<>());
        for (int i = first; i < output.size(); i++) {
            if (set.contains(output.get(i))) continue;

            // 选第 i 个元素加入
            Collections.swap(output, first, i);
            set.add(output.get(i));
            // 递归去填下一个数
            backtrack(resCol, output, first + 1, map);
            // 恢复现场
            Collections.swap(output, first, i);
        }
    }

    public static class Test {
        public static void main(String[] args) {
            int[] nums = new int[]{1,1,2,2};
            Solution47A solution = new Solution47A();
            solution.permuteUnique(nums);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/**
 * 上面那个对某一层的全局集合反而不对
 *
 * <a href="https://leetcode-cn.com/problems/permutations-ii/">leetcode-47</a>
 * List 即当回溯内容，又当回溯暂存列表
 * 不用这种方法的话，必须在回溯前 used[i] 标记已使用, 暂存列表 add
 * 回溯完成后 remove, 标记为未使用
 */
class Solution47A {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> output = new ArrayList<>();
        for (int num: nums) {
            output.add(num);
        }
        List<List<Integer>> resCol = new ArrayList<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        backtrack(resCol, output, 0);
        return resCol;
    }

    /**
     * 取巧做法, 用 set 更直白些
     * 回溯搜索 + 剪枝
     * nums 有重复, 只要保证 在当前位置搜索 ( 仅限这个节点) 的时候 ( 某一层递归 ）搜索 （ 填入 ）的值不重复就可以了
     * @param resCol 结果集合
     * @param output 回溯列表, size 表示总数量, [first, ...] 是将要回溯的内容
     * @param first 回溯内容的启始位置
     */
    public void backtrack(List<List<Integer>> resCol, List<Integer> output, int first) {
        if (first == output.size()) {
            resCol.add(new ArrayList<>(output));
            return;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = first; i < output.size(); i++) {
            if (set.contains(output.get(i))) continue;

            set.add(output.get(i));
            // 选第 i 个元素加入
            Collections.swap(output, first, i);
            // 递归去填下一个数
            backtrack(resCol, output, first + 1);
            // 恢复现场
            Collections.swap(output, first, i);
        }
    }
}
