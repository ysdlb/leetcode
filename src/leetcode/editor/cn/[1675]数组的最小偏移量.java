//给你一个由 n 个正整数组成的数组 nums 。 
//
// 你可以对数组的任意元素执行任意次数的两类操作： 
//
// 
// 如果元素是 偶数 ，除以 2 
// 
//
// 
// 例如，如果数组是 [1,2,3,4] ，那么你可以对最后一个元素执行此操作，使其变成 [1,2,3,2] 
// 
// 
// 如果元素是 奇数 ，乘上 2
// 
// 例如，如果数组是 [1,2,3,4] ，那么你可以对第一个元素执行此操作，使其变成 [2,2,3,4] 
// 
// 
//
//
// 数组的 偏移量 是数组中任意两个元素之间的 最大差值 。 
//
// 返回数组在执行某些操作之后可以拥有的 最小偏移量 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,4]
//输出：1
//解释：你可以将数组转换为 [1,2,3,2]，然后转换成 [2,2,3,2]，偏移量是 3 - 2 = 1
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,1,5,20,3]
//输出：3
//解释：两次操作后，你可以将数组转换为 [4,2,5,5,3]，偏移量是 5 - 2 = 3
// 
//
// 示例 3： 
//
// 
//输入：nums = [2,10,8]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 2 <= n <= 5 * 10⁹ 
// 
//
// Related Topics 贪心 数组 有序集合 堆（优先队列） 👍 95 👎 0


import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /* 1675.数组的最小偏移量: https://leetcode.cn/problems/minimize-deviation-in-array/
     *
     * 最小的偶数和最大的奇数决定了数组的偏移量下限
     * 偶数小根堆, 奇数大根堆
     *
     * 只要保证策略的单向性就能利用贪心算法做出正确的结果
     *
     * 偶数降为奇数后不一定能升回原来的偶数，比如 16
     * 奇数只能升级一次偶数，也只能降低一次变回原值
     * 所以将所有奇数升成偶数, 后续就只能缩小这些偶数
     *
     * 一直缩小最大的偶数， 直到
     *  1. 最大值为奇数为止，此时数组的最大值已经确定
     *  2. 降低过程中偏移量不再增加 (直觉) (直觉是错的)
     *
     * 时间复杂度 O(n*lg(max)*lg(n))
     */
    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int n: nums) {
            treeSet.add((n & 1) == 0 ? n : 2 * n);
        }

        int ans = Integer.MAX_VALUE;
        while ((treeSet.last() & 1) == 0) {
            int last = treeSet.pollLast();
            treeSet.add(last >>> 1);
            int delta = treeSet.last() - treeSet.first();

            if (delta <= ans) {
                ans = delta;
            } else {
                break;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
