//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"ABCCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], word = "abcd"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/ 
// Related Topics 数组 回溯 矩阵 👍 491 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * @see <a href="https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/">leetcode</a>
 */
class SolutionOffer12 {

    private boolean isExist = false;

    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                recurseBack(board, visited, i, j, word.toCharArray(), 0);
            }
        }
        return isExist;
    }

    private void recurseBack(char[][] board, boolean[][] visited,
                                int i, int j,
                                char[] word, int wordIndex) {

        if (visited[i][j] || isExist)
            return;

        if (word[wordIndex] == board[i][j]) {
            if (wordIndex == word.length - 1) {
                isExist = true;
                return;
            }
            visited[i][j] = true;
            if (i > 0)
                recurseBack(board, visited, i-1, j, word, wordIndex+1);
            if (i < board.length - 1)
                recurseBack(board, visited, i+1, j, word, wordIndex+1);
            if (j > 0)
                recurseBack(board, visited, i, j-1, word, wordIndex+1);
            if (j < board[0].length - 1)
                recurseBack(board, visited, i, j+1, word, wordIndex+1);
        }
        visited[i][j] = false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
