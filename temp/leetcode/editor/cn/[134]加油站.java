//在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。 
//
// 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。 
//
// 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。 
//
// 
//
// 示例 1: 
//
// 
//输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
//输出: 3
//解释:
//从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
//开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
//开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
//开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
//开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
//开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
//因此，3 可为起始索引。 
//
// 示例 2: 
//
// 
//输入: gas = [2,3,4], cost = [3,4,3]
//输出: -1
//解释:
//你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
//我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
//开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
//开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
//你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
//因此，无论怎样，你都不可能绕环路行驶一周。 
//
// 
//
// 提示: 
//
// 
// gas.length == n 
// cost.length == n 
// 1 <= n <= 10⁵ 
// 0 <= gas[i], cost[i] <= 10⁴ 
// 
// Related Topics 贪心 数组 👍 857 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution134 {
    /*
     * 1. 暴力解法 O(n^2)
     * 遍历每个站点, 从每个站点起走 n 步
     * int n = gas.length;
     * for (int start = 0; start < n; start++) {
     *     for (int step = 0; step < n; step++) {
     *         int i = (start + step) % n;
     *         tank += gas[i];
     *         tank -= cost[i];
     *         // 判断油箱中的油是否耗尽
     *     }
     * }
     *
     * 2. 盈亏数组最低点解法
     * 如果总收入大于总支出, 一定存在开完一圈的点
     * 找到最低点的下一个节点就可以, 从 0 开始计算支收和, 这个环无论怎么分, 最底点总是最低点
     *
     * 3. 贪心
     * 结论: 如果选择站点 i 作为起点 恰好 无法走到站点 j, 那么 i 和 j 中间的任意站点 k 都不可能作为起点
     * 证明: 设 i 点开始的油量为 tank[i], 则 tank[i] == 0, 因为'恰好' 所以 tank[j-1] >= 0, tank[j] < 0
     * 从 i 做到 i,j 之间的任意一个站点 k, tank[k] >= 0, 前面提到 tank[j] < 0, 设从这个任意站点 k 到 j 的收支为 T
     * tank[k] + T = tank[j], 必然有 T < 0
     * 方法 1 中的暴力方法可以用这种方案优化, 即贪心
     * 时间复杂度 O(n)
     */
    public int canCompleteCircuit_v2(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length;) {
            // 每一个站点作为起点, 走 n 步骤
            int start = i;
            int sum = 0;
            for (int step = 0; step < gas.length; step++) {
                sum += gas[start] - cost[start];
                start = (start+1) % gas.length;
                // 走了一圈且最终和 > 0
                if (step == gas.length - 1 && sum >= 0)
                    return i;
                if (sum < 0 && start > i) {
                    break;
                }
            }
            if (start > i)
                i = start;
            else // i 永远到不了终点
                break;
        }
        return -1;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0, minSum = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            if (sum < minSum) {
                minSum = sum;
                start = i+1;
            }
        }
        if (sum < 0)
            return -1;
        // i+1
        return start == gas.length ? 0 : start;

    }

    public static void main(String[] args) {
        int[] argv0 = new int[]{1,2,3,4,5};
        int[] argv1 = new int[]{3,4,5,1,2};
        new Solution134().canCompleteCircuit_v2(argv0, argv1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
