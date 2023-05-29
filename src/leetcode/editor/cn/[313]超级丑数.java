//超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。 
//
// 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。 
//
// 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12, primes = [2,7,13,19]
//输出：32 
//解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,
//28,32] 。 
//
// 示例 2： 
//
// 
//输入：n = 1, primes = [2,3,5]
//输出：1
//解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
// 
//
// 
//
// 
// 
// 
// 提示： 
// 
// 
// 
//
// 
// 1 <= n <= 10⁵ 
// 1 <= primes.length <= 100 
// 2 <= primes[i] <= 1000 
// 题目数据 保证 primes[i] 是一个质数 
// primes 中的所有值都 互不相同 ，且按 递增顺序 排列 
// 
//
// Related Topics 数组 数学 动态规划 👍 376 👎 0


import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution313 {
    /* 313.超级丑数: https://leetcode.cn/problems/super-ugly-number/
     * 相似题目:
     *  373.查找和最小的 K 对数字: https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/
     *  264.丑数 II: https://leetcode.cn/problems/ugly-number-ii/
     *
     * 题目可视为 x*y 的二维数组
     *   x == primes.len, y 无限大
     * 该二维数组行有序, 每行由初始数组依次乘以 primes[i] 得来 (0 <= i < x)
     * 计算出来的数也将加入初始数组中
     *
     * 初始值 1 最小, y 游标在 0
     * 第二小的数, 是初始值乘以 primes[i] 中最小的数, 该列 y 游标 +1, 该位置值为 ans[y+1]*primes[i]
     * 每列游标后面的数肯定比当前游标位置大, 每列游标之前的数同理更小, 下一个更小的数就是 x 列游标位置中最小的值, 这一列 y++
     *
     * 值的注意的是, 多路归并可以不用 set 去重
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ans = new int[n];
        ans[0] = 1;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));
        for (int x = 0; x < primes.length; x++)
            minHeap.offer(new int[]{x, 0, primes[x]});

        for (int i = 1; i < n && !minHeap.isEmpty(); i++) {
            // 2*7 7*2 重复, 但重复的数字一定会在一起比较
            // 若 x1 列和 x2 列某个数相等; 如果我们选中 x1 的 a1, 将 x1 下一个数 a2(a2>a1) 放入集合
            // 那么下一个更大的数只能 x2 的那个数, 这样可以说明相等的数一定会放在一起比较
            int[] min = minHeap.peek();
            while (!minHeap.isEmpty() && minHeap.peek()[2] == min[2]) {
                int[] dup = minHeap.poll();
                int x = dup[0], y = dup[1];

                ans[i] = dup[2];
                y++;
                // 根据题意, 超过整型的不需要考虑
                long product = (long)primes[x]*ans[y];
                if (product <= Integer.MAX_VALUE)
                    minHeap.offer(new int[]{x, y, (int)product});
            }
        }
        return ans[n-1];
    }



    public static void main(String[] args) {
        Solution313 so = new Solution313();
        int[] primes = new int[]{2,7,13,19};
        so.nthSuperUglyNumber(12, primes);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
