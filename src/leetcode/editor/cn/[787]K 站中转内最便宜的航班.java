//有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城
//市 fromi 开始，以价格 pricei 抵达 toi。 
//
// 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便
//宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。 
//
// 
//
// 示例 1： 
//
// 
//输入: 
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 1
//输出: 200
//解释: 
//城市航班图如下
//
//
//从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。 
//
// 示例 2： 
//
// 
//输入: 
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 0
//输出: 500
//解释: 
//城市航班图如下
//
//
//从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 100 
// 0 <= flights.length <= (n * (n - 1) / 2) 
// flights[i].length == 3
// 0 <= fromi, toi < n 
// fromi != toi 
// 1 <= pricei <= 10⁴ 
// 航班没有重复，且不存在自环 
// 0 <= src, dst, k < n 
// src != dst 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 动态规划 最短路 堆（优先队列） 👍 459 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution787 {
    /**
     * 状态: 当前所在城市, 当前已经经过了 几 站
     * 选择: 价格最便宜
     * f(dst, k) 为经过 k 站后从 src 到 dst 的价格
     * dst 可以到达的城市数组为 graph[dst], (destiny, prize) in graph[dst]
     * cost = prize      k == 0;
     * cost = f(dst, k) + prize           k > 0
     * f(destiny, k+1) = cost      f(destiny, k+1) == 0 || cost < f(destiny, k+1)
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] countMap = new int[n];
        for (int[] flight: flights)
            countMap[flight[0]] ++;

        int[][][] graph = new int[n][][];
        for (int i = flights.length - 1; i >= 0; i--) {
            int flightSrc = flights[i][0];
            if (graph[flightSrc] == null)
                graph[flightSrc] = new int[countMap[flightSrc]][];
            int[][] path = graph[flightSrc];
            path[--countMap[flightSrc]] = new int[]{flights[i][1], flights[i][2]};
        }

        // bfs, 我们的 k 比入参的 k 多一个
        int[][] dp = new int[n][k+1];
        int levelK = -1;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(src);
        while (deque.size() > 0 && levelK < k) {
            levelK++;
            // 注意这里必须 size 的拷贝, 否则 deque.size() 是一个不断增长的值, '这一层' 永远也遍历不完
            int sizeCopy = deque.size();
            for (int i = 0; i < sizeCopy; i++) {
                int city = deque.pollFirst();
                int[][] destinies = graph[city];
                // 可能有的城市哪里也去不了
                if (destinies == null)
                    continue;
                for (int[] destinyPair : destinies) {
                    int destiny = destinyPair[0], prize = destinyPair[1];
                    // 超出内存限制
                    // deque.offerLast(destiny);
                    int cost = levelK == 0 ? prize : dp[city][levelK - 1] + prize;
                    if (dp[destiny][levelK] == 0 || cost < dp[destiny][levelK]) {
                        dp[destiny][levelK] = cost;
                        deque.offerLast(destiny);
                    }
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        boolean isCan = false;
        for (int i = 0; i <= k; i++) {
            if (dp[dst][i] == 0)
                continue;
            if (dp[dst][i] <= ret) {
                ret = dp[dst][i];
                isCan = true;
            }
        }
        return isCan ? ret : -1;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] flights = new int[][]{{0,1,100},{1,2,100},{0,2,500}};
        int src = 0;
        int dst = 2;
        int k = 1;
        new Solution787().findCheapestPrice(n, flights, src, dst, k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
