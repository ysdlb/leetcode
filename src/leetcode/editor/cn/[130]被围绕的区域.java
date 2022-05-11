//给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充
//。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O",
//"X","X"]]
//输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都
//会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
// 
//
// 示例 2： 
//
// 
//输入：board = [["X"]]
//输出：[["X"]]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 200 
// board[i][j] 为 'X' 或 'O' 
// 
// 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 656 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

class Solution130 {
    /**
     * 1. 边界上的 'O' 是无敌的, 循环遍历棋盘的四边
     *    用 DFS 算法将与边界 'O' 相连的 'O' 标记为 'o'
     * 2. 然后顺序遍历整个图, 将现有的 'O' 替换为 'X', 'o' 替换为 'O'
     *
     * 另一种思路: 并查集 (相似题目, 990)
     * https://labuladong.gitee.io/algo/2/19/37/
     *
     * 类似题目: 200
     * 同 1020, 1254
     */
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;

        for (int i = 0; i < n; i++) {
            dfs(board, 0, i);
            dfs(board, m - 1, i);
        }
        for (int j = 1; j < m - 1; j++) {
            dfs(board, j, 0);
            dfs(board, j, n - 1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'o')
                    board[i][j] = 'O';
                else if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || y < 0
                || x >= board.length || y >= board[0].length
                || board[x][y] != 'O')
            return;

        board[x][y] = 'o';
        dfs(board, x - 1, y);
        dfs(board, x + 1, y);
        dfs(board, x, y - 1);
        dfs(board, x, y + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
