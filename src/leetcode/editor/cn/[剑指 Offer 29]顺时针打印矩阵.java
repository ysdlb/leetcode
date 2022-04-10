//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。 
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 限制： 
//
// 
// 0 <= matrix.length <= 100 
// 0 <= matrix[i].length <= 100 
// 
//
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/ 
// Related Topics 数组 矩阵 模拟 👍 347 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer29 {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return new int[]{};

        int[] out = new int[matrix.length*matrix[0].length];
        int outIndex = 0;
        for (int start = 0; matrix.length > 2*start && matrix[0].length > 2*start; start++) {
            outIndex = printOut(matrix, start, out, outIndex);
        }
        return out;
    }

    // 注意单横行或者单竖行时候, 不应该回去
    private int printOut(int[][] matrix, int start, int[] out, int outIndex) {
        int rows = matrix.length, columns = matrix[0].length;
        int endX = columns - start, endY = rows - start;
        for (int i = start; i < endX; i++) {
            out[outIndex++] = matrix[start][i];
        }

        for (int i = start + 1; i < endY; i++) {
            out[outIndex++] = matrix[i][endX-1];
        }

        if (start < endY - 1) {
            for (int i = endX - 2; i >= start; i--) {
                out[outIndex++] = matrix[endY - 1][i];
            }
        }

        if (start < endX - 1) {
            for (int i = endY - 2; i > start; i--) {
                out[outIndex++] = matrix[i][start];
            }
        }
        return outIndex;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int[] ints = new SolutionOffer29().spiralOrder(matrix);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
