//
//
// 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿
//过地下城并通过对抗恶魔来拯救公主。 
//
// 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。 
//
// 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么
//包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。 
//
// 为了尽快到达公主，骑士决定每次只向右或向下移动一步。 
//
// 
//
// 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。 
//
// 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。 
//
// 
// 
// -2 (K) 
// -3 
// 3 
// 
// 
// -5 
// -10 
// 1 
// 
// 
// 10 
// 30 
// -5 (P) 
// 
// 
//
//
// 
//
// 说明: 
//
// 
// 
// 骑士的健康点数没有上限。 
// 
// 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。 
// Related Topics 数组 动态规划 矩阵 👍 586 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution174 {
    /*
     * 状态: 骑士当前位置, 骑士当前增减血量, 骑士最大扣血量
     * 穷举法: 穷举所有从 起点 到 终点 到路径
     * 通过前面房间到血量增减值计算当前的值, 更新此路径下最大的扣血量
     * 如果检测到达终点, 取所有路径中的最小扣血量, 计算起点到终点所需到最低初始血量值 (最大扣血量 + 1)
     */
    public int calculateMinimumHP(int[][] dungeon) {
        M = dungeon.length - 1;
        N = dungeon[0].length - 1;
        ret = Integer.MAX_VALUE;
        calculate(dungeon, 0, 0, 0, 0);
        return ret;
    }

    private int M;
    private int N;
    private int ret;

    private void calculate(int[][] dungeon, int i, int j, int hp, int minus) {
        int buff = dungeon[i][j];
        hp = hp + buff;
        minus = Math.min(hp, minus);
        if (i == M && j == N) {
            ret = Math.min(ret, 1-minus);
        }
        if (i < M) {
            calculate(dungeon, i+1, j, hp, minus);
        }
        if (j < N) {
            calculate(dungeon, i, j+1, hp, minus);
        }
    }
}

class Solution174_DP {
    /*
     * 状态: 骑士当前位置, 骑士当前增减血量, 骑士最大扣血量
     * 设起点开始到当前位置的增减血量为 f(i,j), 最多扣血量为 g(i,j)
     * 如果从 (i-1,j) 达到 (i,j):  f(i,j) = f(i-1,j) + dungeon[i,j];    g(i,j) = min{g(i-1,j), f(i,j)}
     * 如果从 (i,j-1) 达到 (i,j):  f(i,j) = f(i,j-1) + dungeon[i,j];    g(i,j) = min{g(i,j-1), f(i,j)}
     *
     * 对每条路径 g(i,j) 的最优解, 取决于其前节点 f(I,J) + dungeon[i,j] < g(I,J) 是否成立,
     * 对同一点 (i,j) 来自不同路径的 f(i,j) 和 g(i,j) 都不相同, 我们总是倾向于 f(i,j) 尽量大并且 g(i,j) 也尽量大 (-1 > -10)
     * 每条路径 g(i,j) 最小值里的较大值
     *
     * 假如 1 路径全是 0, 那么 f1(i,j) = 0, g1(i,j) = 0;
     * 2 路径开始是负值, 后面全是正数(加起来比负数大), 那么 f2(i,j) > 0, g2(i,j) < 0;
     *
     * 那么如果从 (i,j) (不包括(i,j)) 到终点全是正数, 即 F(i, j) > 0; G(i,j) = 0; 那么 g1(m-1, n-1) = 0, g2(m-1,n-1) < 0 选路径 1 是合适的
     * 那么如果从 (i,j) (不包括(i,j)) 到终点出现过负数, 即可能 F(i, j) 未知; G(i,j) < 0;
     *      g1(m-1,n-1) = min{f1(i,j) + G(i,j), g1(i,j)} = G(i,j)
     *      g2(m-1,n-1) = min{f2(i,j) + G(i,j), g2(i,j)} = 未知
     *      max(g1, g2) = max{G(i,j), g2(i,j)}, 如果 g2(i,j) > G(i,j) 那么选路径 2 是合适的
     *
     * 综上, 从 (0,0) 到 （i,j) 有 2^(i+j) 量级到路径, 具体选哪条路径, 总是需要 (i,j) 到 （m-1, n-1) 的最多扣血量, 即 G(i,j) 参与决定
     * 由于当前增减血量 f值 是后续路程的决策参照, 最多减血量 g值 也是后续路程的决策参照, 他俩还没有必然联系( f值可能很大, g值可能极小 - 一个负无穷的房间 )
     * 所以从起点到终点正推, 不可避免的使用穷举法: 穷举所有从 起点 到 终点 到路径, 时间复杂度 O(2^(M+N))
     *
     * 如果我们反推的话, 只需要判断, G 值就好了, 重新定义下函数 G
     * G(i,j) 表示从 (i,j) 到终点 (m-1, n-1) 最少需要的血量, 最少需要血量为 1
     * 那么 (i,j) 可能需要通过 (i+1,j) 和 (i,j+1) 到达 终点, 遇到正数扣( 下限为 1), 遇到负数加
     * G1(i,j) = max{G(i+1,j) - dungeon(i,j), 1}
     * G2(i,j) = max{G(i,j+1) - dungeon(i,j), 1}
     * G(i,j) = min(G1, G2);
     *
     * 因为边界原因, 共有四种情况讨论, 见代码
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        if (m < 1)
            throw new RuntimeException("param error!");
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];

        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (i < m-1 && j < n-1) {
                    dp[i][j] = Math.max(Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j], 1);
                } else if (i < m-1) {
                    dp[i][j] = Math.max(dp[i+1][j] - dungeon[i][j], 1);
                } else if (j < n-1) {
                    dp[i][j] = Math.max(dp[i][j+1] - dungeon[i][j], 1);
                } else {
                    dp[i][j] = Math.max(1 - dungeon[i][j], 1);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] arg = new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}};
        new Solution174_DP().calculateMinimumHP(arg);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
