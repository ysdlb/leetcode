//给定 m x n 矩阵 matrix 。 
//
// 你可以从中选出任意数量的列并翻转其上的 每个 单元格。（即翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。） 
//
// 返回 经过一些翻转后，行与行之间所有值都相等的最大行数 。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[0,1],[1,1]]
//输出：1
//解释：不进行翻转，有 1 行所有值都相等。
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[0,1],[1,0]]
//输出：2
//解释：翻转第一列的值之后，这两行都由相等的值组成。
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[0,0,0],[0,0,1],[1,1,0]]
//输出：2
//解释：翻转前两列的值之后，后两行由相等的值组成。 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 300 
// matrix[i][j] == 0 或 1 
// 
//
// Related Topics 数组 哈希表 矩阵 👍 88 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1072 {
    /* 1072. 按列翻转得到最大值等行数: https://leetcode.cn/problems/flip-columns-for-maximum-number-of-equal-rows/
     *
     * 由于题目kk的等价关系，保证首位都是 0 的情况下，哈希分组计数，数量最多的就是答案
     */
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int ans = 0;
        Map<String, Integer> count = new HashMap<>();
        for (int[] ints : matrix) {
            StringBuilder buffer = new StringBuilder();
            int xor = 0;
            for (int j = 0; j < ints.length; j++) {
                if (j == 0 && ints[j] == 1) xor = 1;
                buffer.append(ints[j] ^ xor);
            }
            Integer num = count.compute(buffer.toString(), (_key, value) -> value == null ? 1 : value + 1);
            ans = Math.max(ans, num);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution1072 so = new Solution1072();
        int[][] arr = new int[][]{{0,0,0}, {0,0,1}, {1,1,0}};
        int i = so.maxEqualRowsAfterFlips(arr);
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
