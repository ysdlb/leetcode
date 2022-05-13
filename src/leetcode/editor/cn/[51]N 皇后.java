//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 
// 
// 
// Related Topics 数组 回溯 👍 1333 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution51 {
    /**
     * 相似题目: 37
     * 经典回溯
     * 每一行只能放一个 Q, 所以可以直接换到下一行
     */
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> ret = new ArrayList<>();
        backtrack(board, 0, 0, ret);
        return ret;
    }

    private void backtrack(char[][] board, int x, int numOfQueue, List<List<String>> ret) {
        int size = board.length;
        if (x == size && numOfQueue == size) {
            ret.add(serialize(board));
            return;
        }

        for (int y = 0; y < size; y++) {
            // 选择一个位置放皇后放皇后
            if (isValid(board, x, y)) {
                board[x][y] = 'Q';
                // 直接换下一行
                backtrack(board, x+1, numOfQueue+1, ret);
                board[x][y] = '.';
            }

        }
    }

    /**
     * 在 board[x][y] == '.' 的情况下
     * @return 'Q' 放在 board[x][y] 位置是否合适
     */
    private boolean isValid(char[][] board, int x, int y) {
        int size = board.length;
        for (int i = 0; i < size; i++) {
            if (board[x][i] == 'Q') return false;
            if (board[i][y] == 'Q') return false;
        }

        // 只需检查右上方 和 左上方
        for (int i = x-1, j = y+1; i >= 0 && j < size; i--,j++) {
            if (board[i][j] == 'Q') return false;
        }
        for (int i = x-1, j = y-1; i >= 0 && j >= 0; i--,j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private List<String> serialize(char[][] board) {
        List<String> ret = new ArrayList<>();
        int size = board.length;
        for (int i = 0; i < size; i++) {
            ret.add(new String(board[i]));
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
