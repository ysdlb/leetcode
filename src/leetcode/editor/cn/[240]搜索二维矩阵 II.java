//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性： 
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 5
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 20
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -10⁹ <= matrix[i][j] <= 10⁹ 
// 每行的所有元素从左到右升序排列 
// 每列的所有元素从上到下升序排列 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 二分查找 分治 矩阵 👍 1018 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution240 {
    /* 240.搜索二维矩阵 II: https://leetcode.cn/problems/search-a-2d-matrix-ii/
     * 描述相似的第 k 大问题:
     *   378.有序矩阵中第 K 小的元素: https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/
     *   668.乘法表中第k小的数: https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/
     *
     * 这个二维矩阵的特点:
     * 对任意一个元素, 所在行的前面都比它小; 所在列的下面都比它大
     * 如果这个元素在一个矩阵的右上角,
     *      如果它比 target 小, 那么这一行的所有元素都比 target 小, 可排除这一行
     *      如果它比 target 大, 那么这一行的所有元素都比 target 大, 可排除这一列
     * 时间O(m+n), 空间O(1)
     *
     * 同题：剑指 Offer 04
     * 类似题目：74, 378, 668（hard）
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = 0, n = matrix[0].length - 1;
        while (m < matrix.length && n >= 0) {
            if (matrix[m][n] > target) {
                n--;
            } else if (matrix[m][n] < target) {
                m++;
            } else {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
