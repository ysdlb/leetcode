//在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。一次 移动 定义为选择 0 与一个相邻的数字（
//上下左右）进行交换. 
//
// 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。 
//
// 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：board = [[1,2,3],[4,0,5]]
//输出：1
//解释：交换 0 和 5 ，1 步完成
// 
//
// 示例 2: 
//
// 
//
// 
//输入：board = [[1,2,3],[5,4,0]]
//输出：-1
//解释：没有办法完成谜板
// 
//
// 示例 3: 
//
// 
//
// 
//输入：board = [[4,1,2],[5,0,3]]
//输出：5
//解释：
//最少完成谜板的最少移动次数是 5 ，
//一种移动路径:
//尚未移动: [[4,1,2],[5,0,3]]
//移动 1 次: [[4,1,2],[0,5,3]]
//移动 2 次: [[0,1,2],[4,5,3]]
//移动 3 次: [[1,0,2],[4,5,3]]
//移动 4 次: [[1,2,0],[4,5,3]]
//移动 5 次: [[1,2,3],[4,5,0]]
// 
//
// 
//
// 提示： 
//
// 
// board.length == 2 
// board[i].length == 3 
// 0 <= board[i][j] <= 5 
// board[i][j] 中每个值都 不同 
// 
// Related Topics 广度优先搜索 数组 矩阵 👍 258 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution773 {
    /**
     * 因为是一个固定 2*3 的数组, 所以我们可以序列化这个数组来表示一个状态(或位置)
     * 这个数组里面只有 0 值元素可以与其上下左右交换, 从而衍生出下一波状态(位置)
     *
     * 此时这个问题就变成里求从初始状态到 target 为 "123450" 的最短寻路问题
     * 因为每次步长一样, 所以只需要用 BFS 就行
     * 类似题 752
     */
    public int slidingPuzzle(int[][] board) {
        if (board.length != 2 || board[0].length != 3)
            throw new RuntimeException("invalid param!");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                builder.append(board[i][j]);
            }
        }
        String start = builder.toString();
        String target = "123450";

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.offer(start);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (target.equals(cur))
                    return step;

                for (String next: adj(cur)) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            // 层级 + 1
            step++;
        }
        return -1;
    }

    private static final int[][] swapBase = new int[][]{
            {1, 3},
            {0, 2, 4},
            {1, 5},
            {0, 4},
            {1, 3, 5},
            {2, 4}
    };

    private List<String> adj(String start) {
        List<String> ret = new ArrayList<>();
        char[] chars = start.toCharArray();
        int zeroIdx = 0;
        while (chars[zeroIdx] != '0')
            zeroIdx++;

        int[] swapArray = swapBase[zeroIdx];
        for (int index: swapArray) {
            this.swap(chars, zeroIdx, index);
            ret.add(new String(chars));
            this.swap(chars, zeroIdx, index);
        }
        return ret;
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
