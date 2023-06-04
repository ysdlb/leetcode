//给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。 
//请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。 
//
// 你必须找到一个内存复杂度优于 O(n²) 的解决方案。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
//输出：13
//解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[-5]], k = 1
//输出：-5
// 
//
// 
//
// 提示： 
//
// 
// n == matrix.length 
// n == matrix[i].length 
// 1 <= n <= 300 
// -10⁹ <= matrix[i][j] <= 10⁹ 
// 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列 
// 1 <= k <= n² 
// 
//
// 
//
// 进阶： 
//
// 
// 你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题? 
// 你能在 O(n) 的时间复杂度下解决这个问题吗?这个方法对于面试来说可能太超前了，但是你会发现阅读这篇文章（ this paper ）很有趣。 
// 
// Related Topics 数组 二分查找 矩阵 排序 堆（优先队列） 👍 801 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution378 {
    /* 378.有序矩阵中第 K 小的元素: https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/
     * 第 k 大相似题目:
     *  668.乘法表中第k小的数: https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/
     *  1439.有序矩阵中的第 k 个最小数组和: https://leetcode.cn/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/
     * 一个描述相似的搜索问题:
     *  240.搜索二维矩阵 II: https://leetcode.cn/problems/search-a-2d-matrix-ii/
     * 二分汇总题目:
     *  719.找出第 k 小的距离对: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     *
     * 普通思路: (归并排序, 只利用了每行有序)
     * n 个指针, 每次找出这里面最小的一个数, 计数+1, 被选中的指针指向下一个. O(k*n)
     * 找最小的数可以用堆, 时间复杂度降低至 O(k*lgn)
     * 该思路参考 <a href="https://leetcode.cn/problems/merge-k-sorted-lists/">leetcode-23</a>
     * k 可能很大, 接近 m*n
     *
     *
     * 空间复杂度为 O(1) 的思路: 二分
     * 由于每行有序且每列有序, 所以我们指定一个 mid 属于 [matrix(0,0), matrix(n-1,n-1)]
     * 可以在 O(n) 的时间内计算出小于等于 mid 的数量有多少个, 大于 mid 的数量有多少个
     *
     * count(mid) 具备单调性
     * 如果 count(mid) < k, 那么目标值一定比 mid 大
     * 如果 count(mid) > k, 那么目标值可能比 mid 小, 也可能就是 mid
     * 最右边的 count(mid) >= k 的 mid 值就是我们要的结果
     * 时间复杂度 O(n*lg(max-min)), 空间复杂度为 O(1)
     *
     * 类似题目：240(完全不同的解法，稍微利用了共同的性质), 668(很像)
     */
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int min = matrix[0][0], max = matrix[m-1][n-1];
        while (min < max) {
            // 因为负数天然的向上取整, 正数向下取整
            int sum = min + max;
            int mid = sum > 0 ? (min + max) / 2 : (min + max - 1) / 2;
            int count = countLessThanAndEqual(matrix, mid);
            if (count < k) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;
    }

    /**
     * @param matrix 矩阵
     * @param target target
     * @return 计算矩阵中小于 target 的元素有多少个
     *
     * 时间复杂度 O(n)
     */
    private int countLessThanAndEqual(int[][] matrix, int target) {
        int count = 0;
        for (int i = 0, j = matrix[0].length - 1; i < matrix.length; i++) {
            while (j >= 0 && matrix[i][j] > target)
                j--;
            count += j+1;
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
