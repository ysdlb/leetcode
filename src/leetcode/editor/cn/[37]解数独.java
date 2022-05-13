//编写一个程序，通过填充空格来解决数独问题。 
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例 1： 
//
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".
//",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".
//","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6
//"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[
//".",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8
//"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],[
//"4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9",
//"6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4",
//"5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
// 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
// 
// 
// 
// Related Topics 数组 回溯 矩阵 👍 1261 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution37 {

    /**
     * 暴力回溯
     * 类似题目：22，494，698，416
     */
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int x, int y) {
        int m = 9, n = 9;
        // base case
        if (x == m) return true;

        if (y == n) return backtrack(board, x+1, 0);
        // 已有数字, 跳过
        if (board[x][y] != '.') return backtrack(board, x, y+1);

        for (char ch = '1'; ch <= '9'; ch++) {
            if (!isValid(board, x, y, ch)) continue;

            board[x][y] = ch;

            if (backtrack(board, x, y+1))
                return true;

            board[x][y] = '.';
        }
        return false;
    }

    /**
     * 非常好的思路 copy from <a href="https://mp.weixin.qq.com/s/_jacgptmo4yNl516EQArrg>labuladuo</a>
     * 在 board[x][y] == '.' 的情况下
     * @return ch 放在 board[x][y] 位置是否合适
     */
    private boolean isValid(char[][] board, int x, int y, char ch) {
        int baseRow = (x/3)*3, baseCol = (y/3)*3;
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == ch) return false;
            if (board[i][y] == ch) return false;

            if (board[baseRow + i/3][baseCol + i % 3] == ch)
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
