//给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
//输出: 4
//解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
// 
//
// 示例 2: 
//
// 
//
// 
//输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
//输出: 10
// 
//
// 
//
// 提示: 
//
// 
// m == heightMap.length 
// n == heightMap[i].length 
// 1 <= m, n <= 200 
// 0 <= heightMap[i][j] <= 2 * 10⁴ 
// 
//
// 
// Related Topics 广度优先搜索 数组 矩阵 堆（优先队列） 👍 587 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution407 {
    /**
     * 参考 42 双指针解法
     *
     * [12,13, 1,12],
     * [13, 4,13,12],
     * [13, 8,10,12],
     * [12,13,12,12],
     * [13,13,13,13]
     *
     * [ 0, 0, 0, 0],
     * [ 0, 9, 0, 0],
     * [ 0, 4, 2, 0],
     * [ 0, 0, 0, 0],
     * [ 0, 0, 0, 0],
     *
     * 9 的位置加到了 13 高, 会沿着 8, 10, 12 的路径漏出去的
     */
    public int trapRainWater(int[][] heightMap) {
        int[][] minColH = new int[heightMap.length][heightMap[0].length];
        for (int i = 0; i < heightMap.length; i++) {
            int l = 0, r = heightMap[0].length - 1;
            int lMax = 0, rMax = 0;
            while (l <= r) {
                int leftV = heightMap[i][l], rightV = heightMap[i][r];
                lMax = Math.max(leftV, lMax);
                rMax = Math.max(rightV, rMax);
                if (lMax <= rMax) {
                    minColH[i][l] = lMax;
                    l++;
                } else {
                    minColH[i][r] = rMax;
                    r--;
                }
            }
        }

        int[][] minRowH = new int[heightMap.length][heightMap[0].length];
        for (int i = 0; i < heightMap[0].length; i++) {
            int l = 0, r = heightMap.length - 1;
            int lMax = 0, rMax = 0;
            while (l <= r) {
                int leftV = heightMap[l][i], rightV = heightMap[r][i];
                lMax = Math.max(leftV, lMax);
                rMax = Math.max(rightV, rMax);
                if (lMax <= rMax) {
                    minRowH[l][i] = lMax;
                    l++;
                } else {
                    minRowH[r][i] = rMax;
                    r--;
                }
            }
        }

        int ret = 0;
        // int[][] map = new int[heightMap.length][heightMap[0].length];
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[0].length; j++) {
                ret += Math.min(minColH[i][j], minRowH[i][j]) - heightMap[i][j];
                // map[i][j] = Math.min(minColH[i][j], minRowH[i][j]) - heightMap[i][j];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] arg = new int[][]{{12,13,1,12},{13,4,13,12},{13,8,10,12},{12,13,12,12},{13,13,13,13}};
        Solution407 solution407 = new Solution407();
        solution407.trapRainWater(arg);


    }
}
//leetcode submit region end(Prohibit modification and deletion)
