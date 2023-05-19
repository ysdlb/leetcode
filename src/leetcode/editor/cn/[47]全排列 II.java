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

    /* 47.全排列 II: https://leetcode.cn/problems/permutations-ii/
     * 相似题目:
     *   1079.活字印刷: https://leetcode.cn/problems/letter-tile-possibilities/
     * 不同约束相似题目:
     *   22.括号生成: https://leetcode.cn/problems/generate-parentheses/
     * 典型的回溯问题:
     * 典型全排列的约束就是已经使用过的不可再次使用, 就是在位置 first 枚举所有可选元素
     * 一个技巧就是将选中的元素交换到当前位置, 这样 s[0,first-1] 都是已经选过的元素，s[first,n) 都是可选元素
     * 在 first 的基础上进入位置 first+1 重复上次操作
     *
     * 若可选的元素集合有重复, 只要保证在任意 i 位置每次枚举可选元素的时候, 过滤掉同值元素即可
     * 下面那个对某一层的全局集合反而不对
     * 证明:
     *   1. s[0] 不重复
     *   2. 同一个 s[i], s[i+1] 一定不重复
     *   3. 所有枚举出的序列 s[0,i] 一定不重复
     *
     */
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

    /*
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

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2,2};
        Solution47 solution = new Solution47();
        solution.permuteUnique(nums);
    }
}
class Solution47Wrong {

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
}
//leetcode submit region end(Prohibit modification and deletion)

