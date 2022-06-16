//一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。 
//
// 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。开始时， 青蛙默认已站在第一
//块石子上，并可以假定它第一步只能跳跃 1 个单位（即只能从单元格 1 跳至单元格 2 ）。 
//
// 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
// 
//
// 
//
// 示例 1： 
//
// 
//输入：stones = [0,1,3,5,6,8,12,17]
//输出：true
//解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然
//后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。 
//
// 示例 2： 
//
// 
//输入：stones = [0,1,2,3,4,8,9,11]
//输出：false
//解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。 
//
// 
//
// 提示： 
//
// 
// 2 <= stones.length <= 2000 
// 0 <= stones[i] <= 2³¹ - 1 
// stones[0] == 0 
// stones 按严格升序排列 
// 
// Related Topics 数组 动态规划 👍 431 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution403 {
    /**
     * 这只青蛙每次至多有三种选择, 但它当前的状态需要当前所在位置+上一次所在位置来确定
     *
     * dp[i][k] 表示青蛙跳了 k 个单位在 i 位置
     *
     * 因为 k 的值取决于一个在 i 之前的 j 位置, 青蛙需要跳 k-1、k、k+1 步到 j 位置
     *
     * 所以 dp[i][k] = dp[j][k-1] || dp[j][k] || dp[j][k+1]
     * (j < i) (0 <= k < len) (k == i-j)
     *
     * 初始值 dp[0][0] == true
     */
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0)
            throw new IllegalArgumentException("invalid param!");

        int len = stones.length;
        boolean[][] dp = new boolean[len][len];
        dp[0][0] = true;
        for (int i = 1; i < len; i++) {
            for (int j = i-1; j >= 0; j--) {
                int k = stones[i] - stones[j];
                if (k > i) {
                    // 0 1 3 6
                    //   1 2 3
                    // 每跳一次最多递增1, k > i 了是不可能跳到 i 位置的
                    // 可推倒出 k < len
                    break;
                }
                // dp[i][k] = dp[j][k-1] || dp[j][k] || dp[j][k+1]
                dp[i][k] = getState(dp, j, k);
            }
        }

        for (int k = 0; k < len; k++) {
            if (dp[len-1][k])
                return true;
        }
        return false;
    }

    private boolean getState(boolean[][] dp, int i, int k) {
        boolean ret = dp[i][k];
        if (k-1 >= 0)
            ret = ret || dp[i][k-1];
        if (k+1 < dp.length)
            ret = ret || dp[i][k+1];
        return ret;
    }

    public static void main(String[] args) {
        int[] arg = new int[]{0,1,3,5,6,8,12,17};
        Solution403 solution403 = new Solution403();
        solution403.canCross(arg);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
