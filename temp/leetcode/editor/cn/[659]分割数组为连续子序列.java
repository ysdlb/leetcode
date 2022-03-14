//给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。 
//
// 如果可以完成上述分割，则返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入: [1,2,3,3,4,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3
//3, 4, 5
// 
//
// 示例 2： 
//
// 
//输入: [1,2,3,3,4,4,5,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3, 4, 5
//3, 4, 5
// 
//
// 示例 3： 
//
// 
//输入: [1,2,3,4,4,5]
//输出: False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10000 
// 
// Related Topics 贪心 数组 哈希表 堆（优先队列） 👍 363 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution659 {
    /**
     * 对于任意但元素 v 如何分配
     * 1. 自成一个队列 (需要 v,v+1,v+2) 都至少存在一个; 自成队列后多了一个可以接 v+3 的队列
     * 2. 添加到其他队列后面, 至少存在一个队列后面可以接一个 v; 添加后多了一个可以接 v+1 的队列
     *
     * 对于两种方法都可以的情况, nums = [1,2,3,4,5,5,6,7]
     * 对于元素 4 我们已经有 [1,2,3] 这样的一个队列
     * 它既可以添加到 [1,2,3] 的后面, 右可以自成一个队列 [4,5,6,7]
     * 正确的划分应该是 [1,2,3,4,5], [5,6,7]
     * 应该优先添加到其它队列后面 (证据不足, 先这样吧, 应该是一个或多个连续子序列的原因)
     *
     * 如果上面两种都不可以, 则无法分配, 返回 false
     *
     * 至少存在一个用 has 的 map 存, 表示存在多少个
     * 至少存在一个队列后面可以接一个 v 用 need 存, 表示这样的队列有多少个
     *
     * 如果 v, has[v] <= 0, 则表示这个元素已经在前面被使用了, 直接下一个元素
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> has = new HashMap<>();
        Map<Integer, Integer> need = new HashMap<>();
        for (int num: nums)
            has.compute(num, (key, oldV) -> oldV == null ? 1 : oldV+1);

        for (int v: nums) {
            // 已经被使用没了
            if (has.get(v) == 0)
                continue;

            if (isLessOne(need, v)) {
                has.put(v, has.get(v) - 1);
                need.put(v, need.get(v) - 1);
                // need[v+1] ++
                need.compute(v+1, (key, oldV) -> oldV == null ? 1 : oldV + 1);
            } else if (isLessOne(has, v) && isLessOne(has, v+1) && isLessOne(has, v+2)) {
                has.put(v, has.get(v) - 1);
                has.put(v+1, has.get(v+1) - 1);
                has.put(v+2, has.get(v+2) - 1);
                // need[v+3] ++
                need.compute(v+3, (key, oldV) -> oldV == null ? 1 : oldV + 1);
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isLessOne(Map<Integer, Integer> map, int v) {
        return map.get(v) != null && map.get(v) >= 1;
    }

    public static void main(String[] args) {
        int[] arg = new int[]{1,2,2,3,3,4,4,5};
        new Solution659().isPossible(arg);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
