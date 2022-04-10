//给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。 
//
// 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。 
//
// 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。 
//
// 注意：不允许旋转信封。 
// 
//
// 示例 1： 
//
// 
//输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
//输出：3
//解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。 
//
// 示例 2： 
//
// 
//输入：envelopes = [[1,1],[1,1],[1,1]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= envelopes.length <= 10⁵ 
// envelopes[i].length == 2 
// 1 <= wi, hi <= 10⁵ 
// 
// Related Topics 数组 二分查找 动态规划 排序 👍 699 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution354 {
    /**
     * dp
     * 下面方法的总结
     */
    public int maxEnvelopes_v3(int[][] envelopes) {
        if (envelopes.length < 1) return 0;

        // 改成从小到大排
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] == e2[0])
                return e1[1] - e2[1];
            return e1[0] - e2[0];
        });

        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        int ret = 0;
        for (int i = 1; i < envelopes.length; i++) {
            int[] before = envelopes[i];
            for (int j = 0; j < i; j++) {
                int[] now = envelopes[j];
                if (now[0] < before[0] && now[1] < before[1]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    ret = Math.max(dp[i], ret);
                }
            }
        }
        return dp[envelopes.length - 1];
    }

    public static void main(String[] args) {
        int[][] arg = new int[][]{{30,50},{12,2},{3,4},{12,15}};
        new Solution354().maxEnvelopes_v3(arg);
    }

    /**
     * 优先按宽度从大到小排序, 再按高度从大到小排序
     *
     * 贪心迭代
     * 对比当前信封 now 和前一个加入组合的信封 before
     * 宽相等, 跳过
     * 宽小于, 高大于等于, 跳过
     * 宽小于, 高小于, 将 now 加入组合, 更新 before 为 now
     */

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length < 1) return 0;
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] == e2[0])
                return e2[1] - e1[1];
            return e2[0] - e1[0];
        });

        mem = new int[envelopes.length];
        // 即使是第一个信封, 也有多个候选者
        int[] max = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        return maxEnvelopes(envelopes, max, -1) - 1;
    }

    private int[] mem;

    /**
     * 上述的问题在于 "now 与 before 相比, 宽小于, 高小于" 可能有多个
     * 有的宽度占优, 有的高度占优; 无法确定哪个是最优解
     * 我们可以递归尝试所有 (相等宽度只需要尝试第一个, 记录下这个高度, 每次出现比它大的高度就再尝试就可以了)
     */
    private int maxEnvelopes(int[][] envelopes, int[] before, int bef) {
        int ret = 0;
        int maxh = 0;
        for (int i = bef + 1; i < envelopes.length; i++) {
            int[] now = envelopes[i];
            if (now[0] < before[0] && now[1] < before[1]) {
                if (now[1] > maxh) {
                    maxh = now[1];
                    ret = mem[i] != 0 ?
                            Math.max(mem[i], ret)
                            : Math.max(maxEnvelopes(envelopes, envelopes[i], i), ret);
                }
            }
        }
        if (bef >= 0)
            mem[bef] = ret + 1;
        return ret + 1;
    }

    /**
     * 优先按宽度从大到小排序, 再按高度从大到小排序
     *
     * 贪心迭代
     * 对比当前信封 now 和前一个加入组合的信封 before
     * 宽相等, 跳过
     * 宽小于, 高大于等于, 跳过
     * 宽小于, 高小于, 将 now 加入组合, 更新 before 为 now
     *
     * 上述的问题在于 "now 与 before 相比, 宽小于, 高小于" 可能有多个
     * 有的宽度占优, 有的高度占优; 无法确定哪个是最优解
     * 我们可以递归尝试所有 (相等宽度只需要尝试第一个)
     */
    public int maxEnvelopes_error(int[][] envelopes) {
        if (envelopes.length < 1) return 0;

        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] == e2[0])
                return e2[1] - e1[1];
            return e2[0] - e1[0];
        });

        int ret = 1;
        int[] before = envelopes[0];
        for (int i = 1; i < envelopes.length; i++) {
            int[] now = envelopes[i];
            if (now[0] < before[0] && now[1] < before[1]) {
                before = now;
                ret++;
            }
        }
        return ret;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
