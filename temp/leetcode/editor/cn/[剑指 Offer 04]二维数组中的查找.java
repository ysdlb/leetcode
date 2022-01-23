//在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个
//整数，判断数组中是否含有该整数。 
//
// 
//
// 示例: 
//
// 现有矩阵 matrix 如下： 
//
// 
//[
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// 给定 target = 5，返回 true。 
//
// 给定 target = 20，返回 false。 
//
// 
//
// 限制： 
//
// 0 <= n <= 1000 
//
// 0 <= m <= 1000 
//
// 
//
// 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/ 
// Related Topics 数组 二分查找 分治 矩阵 👍 546 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer4 {
    /**
     * 在矩阵中, 如果一个数比 target 大, 那么该数所在位置的右边和下边都比 target 大.
     * 由于有序, 很容易联想到二分查找, 然后结合上面对特性,
     * 顺着思路划分成四个矩形处理, 由于我们只能排除一个矩形, 剩下了一多边形, 变得很难处理
     *
     * 正确思路: 从右上角逐列逐行排除
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) return false;

        int row = matrix.length, col = matrix[0].length;
        for (int c = col - 1, r = 0; c >= 0 && r < row;) {
            if (matrix[r][c] > target) { // 这一列下边都比 target 大
                c--;
            } else if (matrix[r][c] < target){ // 这一行左边都比 target 小
                r++;
            } else {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
