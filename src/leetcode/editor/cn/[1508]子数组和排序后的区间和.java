//给你一个数组 nums ，它包含 n 个正整数。你需要计算所有非空连续子数组的和，并将它们按升序排序，得到一个新的包含 n * (n + 1) / 2 个数
//字的数组。 
//
// 请你返回在新数组中下标为 left 到 right （下标从 1 开始）的所有数字和（包括左右端点）。由于答案可能很大，请你将它对 10^9 + 7 取模
//后返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,4], n = 4, left = 1, right = 5
//输出：13 
//解释：所有的子数组和为 1, 3, 6, 10, 2, 5, 9, 3, 7, 4 。将它们升序排序后，我们得到新的数组 [1, 2, 3, 3, 4, 5
//, 6, 7, 9, 10] 。下标从 le = 1 到 ri = 5 的和为 1 + 2 + 3 + 3 + 4 = 13 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4], n = 4, left = 3, right = 4
//输出：6
//解释：给定数组与示例 1 一样，所以新数组为 [1, 2, 3, 3, 4, 5, 6, 7, 9, 10] 。下标从 le = 3 到 ri = 4 的和
//为 3 + 3 = 6 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,2,3,4], n = 4, left = 1, right = 10
//输出：50
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10^3 
// nums.length == n 
// 1 <= nums[i] <= 100 
// 1 <= left <= right <= n * (n + 1) / 2 
// 
//
// Related Topics 数组 双指针 二分查找 排序 👍 69 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1508 {
    /* 1508.子数组和排序后的区间和: https://leetcode.cn/problems/range-sum-of-sorted-subarray-sums/
     * 相似题目:
     *  719.找出第 K 小的数对距离: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     *  378.有序矩阵中第 K 小的元素: https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/
     * 不同类汇总题目:
     *  373.查找和最小的 K 对数字: https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/
     *
     *
     * 连续非空子数组和为 n^2
     * 排序时间复杂度 n^2 * lgn^2 = n^2 * lgn
     * 这种方式不是一个好思路
     *
     * 原数组(n): 1,2,3,4
     * 前缀和(n): 1,3,6,9
     * 区间和 & 前缀和矩阵(n * n-1):
     * 0,1,3,6,10
     *   1,3,6,10
     *     2,5,9
     *       3,7
     *         4
     * 从左到右递增, 从上到下递减
     * 对每一层而言, 从左往右的每个元素都减去第 1 个元素, 就是下一层的值
     * preSum[j] - preSum[i] 为第 i 行，j 位置区间和的值 (0<=i<n; j>i)
     *
     * 设区间和为新数组
     * 1. 首先求出新数组中第 k 小的元素 x
     *   给定一个任意值 x，可在 O(n) 的时间内求出满足 e <= x 的值的数量
     * 2. 求新数组的前 k 项和
     *   注意 x 可能有多个，比如 ...a,a,x,x,[x],x,c,d...，第 k 小元素为 x
     *   a. 先求所有严格小于 x 的元素和 sum，和 <x 的元素个数 cnt
     *   b. f(k) = sum + (k-cnt)*x
     *
     * 如何求 sum
     *  对于矩阵的 i 行，将 preSum[j] - preSum[i] < x 的所有值相加; (j 从 i+1 开始遍历, 0<=i<n)
     *  等价于 ppSum[j] - ppSum[i] - (j-i)*preSum[i]
     * 将 i 行全部相加
     *
     * 时间复杂度 O(n*lgS)  (getKth 方法)
     */
    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] pSum = new int[n+1], ppSum = new int[n+1];
        for (int i = 0; i < n; i++) {
            pSum[i+1] = pSum[i]+nums[i];
            ppSum[i+1] = ppSum[i] + pSum[i+1];
        }

        left--;
        int lNum = getKth(ppSum, pSum, left);
        int rNum = getKth(ppSum, pSum, right);

        long[] l = getLessThanXSum(ppSum, pSum, lNum);
        long[] r = getLessThanXSum(ppSum, pSum, rNum);

        long lPreSum = (l[0] + (left-l[1])*lNum) % MOD;
        long rPreSum = (r[0] + (right-r[1])*rNum) % MOD;
        return (int)(rPreSum - lPreSum);
    }

    private static final int MOD = 1000000007;

    /**
     * @param pSum pSum O(n) 的前缀和用来逻辑表示 O(n^2) 的区间和数组
     * @param x x
     * @return 区间和数组中严格小于 x 的元素的和以及数量
     * 和 count 方法有点重复
     */
    private long[] getLessThanXSum(int[] ppSum, int[] pSum, int x) {
        long sum = 0;
        long count = 0;
        int n = pSum.length - 1;
        for (int i = 0, j = 0; i < n; i++) {
            while (j+1 <= n && pSum[j+1] - pSum[i] < x)
                j++;
            j = Math.max(j, i);
            sum = (sum + ppSum[j] - ppSum[i] - (long) (j - i) *pSum[i]) % MOD;
            count += j-i;
        }
        return new long[]{sum, count};
    }

    /**
     * @param pSum O(n) 的前缀和用来逻辑表示 O(n^2) 的区间和数组
     * @param k k
     * @return 区间和数组 sum 中, 第 k 大的数
     */
    private int getKth(int[] ppSum, int[] pSum, int k) {
        int n = pSum.length - 1;
        int l = 0, r = pSum[n];
        while (l < r) {
            int x = (l+r+1) >>> 1;
            if (getLessThanXSum(ppSum, pSum, x)[1] < k) {
                l = x;
            } else { // count(e<x) >= k, 一定 (count(e <= x) > k
                r = x - 1;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        Solution1508 solu = new Solution1508();
        int[] nums = new int[]{1,2,3,4};
        int i = solu.rangeSum(nums, nums.length, 1, 10);
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
