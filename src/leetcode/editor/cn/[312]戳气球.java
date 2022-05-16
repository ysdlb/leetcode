//有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。 
//
// 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i -
// 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。 
//
// 求所能获得硬币的最大数量。 
//
// 
//示例 1：
//
// 
//输入：nums = [3,1,5,8]
//输出：167
//解释：
//nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167 
//
// 示例 2： 
//
// 
//输入：nums = [1,5]
//输出：10
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 995 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution312 {
    /**
     * 从气球戳破的次序来看, 这是一个全排列, 而且每 n 个数的组合都有自己的最优解 (时间线后面的不依赖时间线前面的, 没有因果关系)
     * 上面需要回溯穷举，时间复杂度不好控制，代码不好写
     *
     * DP 做法
     * 假设开区间 f(i,j) 表示戳爆所有气球的最高得分, 那么一定有一个 k 属于 (i,j) 是最后戳爆的(此时只剩下 k 一个气球)
     * 我们在两端各自加一个哨兵, 值为 1, 现在总是剩下 i and j 两个额外的气球(它们有可能也是哨兵)
     *
     * 可得 f(i,j) = f(i,k) + f(k,j) + nums[i]*nums[k]* nums[j]
     * 我们只需要遍历 (i,j) 内的所有 k 就可以找到 f(i,j) 的值
     *
     * 这里我们需要穷举 (i,j) 的所有状态, f(i,j) 计算的时候需要 f(i,k) and f(k,j) 已经计算好
     * 所以可以从左到右, 从下到上遍历
     *
     * [1,2,2,1]
     * f(1,3) = 4
     * f(0,1) + f(1,3) + n[0]*n[1]*n[3] = 0+4+2= 6;
     *
     * 留存：最开始排列组合实现记忆化搜索的答案
     */
    public int maxCoins(int[] nums) {
        int len = nums.length;
        if (len == 0)
            throw new RuntimeException("invalid nums array!");

        int[] balls = new int[len+2];
        balls[0] = 1; balls[len+1] = 1;
        for (int i = 1; i <= len; i++) {
            balls[i] = nums[i-1];
        }

        int[][] dp = new int[len+2][len+2];
        // dp[i][i+1] 以下区间没有元素, 所以值全部为 0
        for (int i = len+1; i >= 0; i--) {
            for (int j = i+2; j < len+2; j++) {
                for (int k = i+1; k < j; k++) {
                    int ret = dp[i][k] + dp[k][j] + balls[i]*balls[k]*balls[j];
                    dp[i][j] = Math.max(dp[i][j], ret);
                }
            }
        }
        return dp[0][len+1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
