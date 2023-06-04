//给你一个下标从 0 开始长度为 n 的整数数组 stations ，其中 stations[i] 表示第 i 座城市的供电站数目。 
//
// 每个供电站可以在一定 范围 内给所有城市提供电力。换句话说，如果给定的范围是 r ，在城市 i 处的供电站可以给所有满足 |i - j| <= r 且 0
// <= i, j <= n - 1 的城市 j 供电。 
//
// 
// |x| 表示 x 的 绝对值 。比方说，|7 - 5| = 2 ，|3 - 10| = 7 。 
// 
//
// 一座城市的 电量 是所有能给它供电的供电站数目。 
//
// 政府批准了可以额外建造 k 座供电站，你需要决定这些供电站分别应该建在哪里，这些供电站与已经存在的供电站有相同的供电范围。 
//
// 给你两个整数 r 和 k ，如果以最优策略建造额外的发电站，返回所有城市中，最小供电站数目的最大值是多少。 
//
// 这 k 座供电站可以建在多个城市。 
//
// 
//
// 示例 1： 
//
// 
//输入：stations = [1,2,4,5,0], r = 1, k = 2
//输出：5
//解释：
//最优方案之一是把 2 座供电站都建在城市 1 。
//每座城市的供电站数目分别为 [1,4,4,5,0] 。
//- 城市 0 的供电站数目为 1 + 4 = 5 。
//- 城市 1 的供电站数目为 1 + 4 + 4 = 9 。
//- 城市 2 的供电站数目为 4 + 4 + 5 = 13 。
//- 城市 3 的供电站数目为 5 + 4 = 9 。
//- 城市 4 的供电站数目为 5 + 0 = 5 。
//供电站数目最少是 5 。
//无法得到更优解，所以我们返回 5 。
// 
//
// 示例 2： 
//
// 
//输入：stations = [4,4,4,4], r = 0, k = 3
//输出：4
//解释：
//无论如何安排，总有一座城市的供电站数目是 4 ，所以最优解是 4 。
// 
//
// 
//
// 提示： 
//
// 
// n == stations.length 
// 1 <= n <= 10⁵ 
// 0 <= stations[i] <= 10⁵ 
// 0 <= r <= n - 1 
// 0 <= k <= 10⁹ 
// 
//
// Related Topics 贪心 队列 数组 二分查找 前缀和 滑动窗口 👍 28 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2528 {

    /* 2528.最大化城市的最小供电站数目: https://leetcode.cn/problems/maximize-the-minimum-powered-city/
     * 二分汇总:
     *  719.找出第 k 小的距离对: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     * 最大化最小值二分
     *  1552.两球之间的磁力: https://leetcode.cn/problems/magnetic-force-between-two-balls/
     *  2439.最小化数组中的最大值: https://leetcode.cn/problems/minimize-maximum-of-array/
     *  2513.最小化两个数组中的最大值: https://leetcode.cn/problems/minimize-the-maximum-of-two-arrays/
     *  2517.礼盒的最大甜蜜度: https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/
     *
     * 量级: 上万座城市, 每个城市上万个供电站, k 进亿
     * 显然, 要想最差城市的供电站数目越大, 所需的新增供电站数量 k 就越大
     *
     * 给定一个供电站数目, 如何快速求出最少需要新增多少座供电站呢
     * 要最少，则尽可能让供电作用范围大
     * 每次加在窗口最右边
     * 时间复杂度一样，但是很慢
     */
    public long maxPower(int[] stations, int r, int k) {
        long low = 0, high = Long.MAX_VALUE;
        while (low < high) {
            long x = (low + high + 1) >>> 1;
            if (count(stations, r, x) <= k) {
                low = x;
            } else {
                high = x - 1;
            }
        }
        return low;
    }

    /**
     * @return 达成 x 供电站数目, 需要最少的供电站数量
     */
    private long count(int[] stations, int r, long x) {
        int n = stations.length;
        int[] copy = Arrays.copyOf(stations, n);
        long sum = 0;
        long cnt = 0;
        for (int i = 0, j = 0, a = 0; a < n; a++) {
            while (j < n && j - a <= r) {
                sum += copy[j];
                j++;
            }
            while (a - i > r) {
                sum -= copy[i];
                i++;
            }
            long delta = x-sum;
            if (delta > 0) {
                copy[Math.min(j-1, n-1)] += delta;
                cnt += delta;
                sum = x;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        {
            Solution2528 solu = new Solution2528();
            int[] stations = new int[]{1, 2, 4, 5, 0};
            long l = solu.maxPower(stations, 1, 2);
            System.out.println(l);
        }
        {
            Solution2528 solu = new Solution2528();
            int[] stations = new int[]{4,4,4,4};
            long l = solu.maxPower(stations, 0, 3);
            System.out.println(l);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
