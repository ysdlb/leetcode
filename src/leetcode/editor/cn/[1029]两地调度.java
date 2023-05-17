//公司计划面试 2n 人。给你一个数组 costs ，其中 costs[i] = [aCosti, bCosti] 。第 i 人飞往 a 市的费用为 
//aCosti ，飞往 b 市的费用为 bCosti 。 
//
// 返回将每个人都飞到 a 、b 中某座城市的最低费用，要求每个城市都有 n 人抵达。 
//
// 
//
// 示例 1： 
//
// 
//输入：costs = [[10,20],[30,200],[400,50],[30,20]]
//输出：110
//解释：
//第一个人去 a 市，费用为 10。
//第二个人去 a 市，费用为 30。
//第三个人去 b 市，费用为 50。
//第四个人去 b 市，费用为 20。
//
//最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。
// 
//
// 示例 2： 
//
// 
//输入：costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
//输出：1859
// 
//
// 示例 3： 
//
// 
//输入：costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,35
//9],[631,42]]
//输出：3086
// 
//
// 
//
// 提示： 
//
// 
// 2 * n == costs.length 
// 2 <= costs.length <= 100 
// costs.length 为偶数 
// 1 <= aCosti, bCosti <= 1000
// 
// Related Topics 贪心 数组 排序 👍 239 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1029 {

    /* 1029.两地调度: https://leetcode.cn/problems/two-city-scheduling/
     *
     * 贪心思路:
     * 每个人可以选择 a,b, 且至少去一个城市
     * 为了花费最低，哪个城市低去哪个, 但这样很可能导致 a/b 不平衡
     * 如果去 a 的多了，需要指定几个人去 b，这几人的移动使成本上涨越少越好;
     *   1. 从 a 里面找出几个人去 b, 寻找条件是 b-a 最小
     *   2. 同理, 如果去 b 的多了, 那么从 a 里面找出几个 a-b 最小的
     *
     * 去 a 的记录 b-a 的差值, 组成一个列表
     * 去 b 的记录 a-b 的差值, 组成一个列表
     * 若差值为 k, 则在对应列表里找最小的 k/2 个值, 最低总花费加上这些值就是两地均匀调度的最低花费
     *
     * 寻找最小 k 个值的时间复杂度为 O(n), 总时间复杂度 O(n);
     */
    public int twoCitySchedCost(int[][] costs) {
        int n = costs.length;
        int[] a = new int[n], b = new int[n];
        // 对应数组的长度
        int ai = 0, bi = 0;
        int ans = 0;
        for (int[] c: costs) {
            int diff = c[0] - c[1];
            if (diff <= 0) {
                a[ai++] = -diff;
                ans += c[0];
            } else {
                b[bi++] = diff;
                ans += c[1];
            }
        }

        if (ai - n/2 > 0) {
            int k = bottomK(a, 0, ai-1, ai-n/2);
            for (int i = 0; i <= k; i++)
                ans += a[i];
        } else if (bi - n/2 > 0) {
            int k = bottomK(b, 0, bi-1, bi-n/2);
            for (int i = 0; i <= k; i++)
                ans += b[i];
        }
        return ans;
    }

    private int bottomK(int[] cost, int l, int r, int k) {
        int L = l, R = r;
        int p = cost[l];
        while (l < r) {
            while (l < r && cost[r] > p)
                r--;
            cost[l] = cost[r];

            // 小心选择快排同值死循环
            while (l < r && cost[l] <= p)
                l++;
            cost[r] = cost[l];
        }
        cost[l] = p;

        if (l+1 == k) return l;
        return l+1 < k ? bottomK(cost, l+1, R, k)
                : bottomK(cost, L, r-1, k);
    }

    public static void main(String[] args) {
        Solution1029 so = new Solution1029();
        {
            int[][] test = new int[][]{{10,10},{30,30},{400,400},{30,30}};
            int k = so.twoCitySchedCost(test);
            System.out.println(k);
        }
        {
            int[] test = new int[]{0,0,0,0};
            int k = so.bottomK(test, 0, test.length - 1, 2);
            System.out.println(k);
            System.out.println(Arrays.toString(test));
        }
        {
            int[][] test = new int[][]{{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}};
            int k = so.twoCitySchedCost(test);
            System.out.println(k);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
