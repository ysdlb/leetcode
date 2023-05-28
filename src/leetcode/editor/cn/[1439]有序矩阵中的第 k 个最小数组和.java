//给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。 
//
// 你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 最小 数组和。 
//
// 
//
// 示例 1： 
//
// 输入：mat = [[1,3,11],[2,4,6]], k = 5
//输出：7
//解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
//[1,2], [1,4], [3,2], [3,4], [1,6]。其中第 5 个的和是 7 。  
//
// 示例 2： 
//
// 输入：mat = [[1,3,11],[2,4,6]], k = 9
//输出：17
// 
//
// 示例 3： 
//
// 输入：mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
//输出：9
//解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
//[1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]。其中第 7 个的和是 9 。 
// 
//
// 示例 4： 
//
// 输入：mat = [[1,1,10],[2,2,9]], k = 7
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == mat.length 
// n == mat.length[i] 
// 1 <= m, n <= 40 
// 1 <= k <= min(200, n ^ m) 
// 1 <= mat[i][j] <= 5000 
// mat[i] 是一个非递减数组 
// 
//
// Related Topics 数组 二分查找 矩阵 堆（优先队列） 👍 147 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1439 {
    /* 1439.有序矩阵中的第 k 个最小数组和: https://leetcode.cn/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/
     * 第 k 大相似题目:
     *   378.有序矩阵中第 K 小的元素: https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/
     *   1439.有序矩阵中的第 k 个最小数组和: https://leetcode.cn/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/
     *
     * mat[i] 是一个非递减数组
     * 每列取一个, 一共有 n^m 种组合, 我们从这里面找出第 k 小的一种组合
     *
     * 纯暴力思路,
     * 一共有 m 个游标, 初始值都为 0 时, 一定是第 1 小的组合;
     * 选下一个值最小的行右移 1 个位置，一定是第 2 小的组合;
     * 对于第 3 小的组合, 可能需要之前右移的那一行回退到 0 号位置
     * 难点: 一是值域很大, k 很大的时候, 性能很差；而是本身值域有序遍历就很困难（涉及游标回退）
     * 如何有序遍历值域:
     *   一列和
     *   二列和相加，排序变一个更长列的和
     *   更长的列和下一个列相加再排序
     * 可以改成两两归并更快些
     *
     * 二分思路:
     * 设 x 为值域中的任意一个数, count(x) 为比 x 小的数的数量, 显然它单调
     * 但难点是 count(x) 本身不好计算, 计算有序的 n 个数组中，之和小于 x 的数量
     *
     * 先来一个暴力的，其他方式留存
     */
    public int kthSmallest(int[][] mat, int k) {
        int[] base = new int[]{0};
        for (int[] nums: mat) {
            int[] change = new int[base.length*nums.length];
            int i = 0;
            for (int x: base) {
                for (int y: nums) {
                    change[i++] = x+y;
                }
            }
            Arrays.sort(change);
            if (change.length > k)
                change = Arrays.copyOfRange(change, 0, k);
            base = change;
        }
        return base[k-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
