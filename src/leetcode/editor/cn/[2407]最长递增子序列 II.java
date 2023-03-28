//给你一个整数数组 nums 和一个整数 k 。
//
// 找到 nums 中满足以下要求的最长子序列： 
//
// 
// 子序列 严格递增 
// 子序列中相邻元素的差值 不超过 k 。 
// 
//
// 请你返回满足上述要求的 最长子序列 的长度。 
//
// 子序列 是从一个数组中删除部分元素后，剩余元素不改变顺序得到的数组。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [4,2,1,4,3,4,5,8,15], k = 3
//输出：5
//解释：
//满足要求的最长子序列是 [1,3,4,5,8] 。
//子序列长度为 5 ，所以我们返回 5 。
//注意子序列 [1,3,4,5,8,15] 不满足要求，因为 15 - 8 = 7 大于 3 。
// 
//
// 示例 2： 
//
// 输入：nums = [7,4,5,1,8,12,4,7], k = 5
//输出：4
//解释：
//满足要求的最长子序列是 [4,5,8,12] 。
//子序列长度为 4 ，所以我们返回 4 。
// 
//
// 示例 3： 
//
// 输入：nums = [1,5], k = 1
//输出：1
//解释：
//满足要求的最长子序列是 [1] 。
//子序列长度为 1 ，所以我们返回 1 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i], k <= 10⁵ 
// 
//
// Related Topics 树状数组 线段树 队列 数组 分治 动态规划 单调队列 👍 54 👎 0


import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2407 {
    /**
     * 连续递增
     * 一个值是否可以放入子序列，需要判断这个影响是正面的还是负面的，判断需要额外一层 for 循环
     * 可以通过贪心二分/树状数组/线段树 将它降低到 lg(n)
     * <p></p>
     *
     * d[i] 为 i 位置的最长子序列;
     * 对于任意的位置 j 属于 [0,i)，只要符合 nums[i] > nums[j] && nums[i] - nums[j] <= k
     * 至多有 i-i 种选择,
     * d[i] = max{d[j]} + nums[i]
     * <p></p>
     * 时间复杂度 o(n^2)
     */
    public int lengthOf_LIS(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] d = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && nums[i] - nums[j] <= k) {
                    d[i] = Math.max(d[i], d[j]);
                }
            }
            d[i]++;
            max = Math.max(max, d[i]);
        }
        return max;
    }

    /**
     * 连续递增
     * 一个值是否可以放入子序列，需要判断这个影响是正面的还是负面的，判断需要额外一层 for 循环
     * 可以通过贪心二分/树状数组/线段树 将它降低到 lg(n)
     * <p></p>
     * 索引的做法需要判断条件 nums[i] > nums[j] && nums[i] - nums[j] <= k
     * 无法保证 d[0...i] 的单调性, 进而就必须挨个遍历，来剔除不符合判断条件的值, 顺便找符合条件的最大值
     * <p></p>
     * 所以需要我们改用值域的做法, 符合条件的值域相当于已值，我们找到这个值域里的最大值即可
     * 最大值的更新以及获取最大值都被限定在一定的值域范围内
     * <p></p>
     * m[x] 表示最大数字为 x 的最长序列的值, x = nums[i], 其大小顺序是完全无序的
     * 对任意一个数字 nums[i], m[nums[i] = max{m[0...nums[i]-1} + 1
     * <p></p>
     * 对于数组 m 的下标，若 a < b, b < c; 若 m[a] < m[b] && m[b] < m[c] 必定有 m[a] < m[c];
     * m 单调
     * 这个证明是不对的, 因为有效的 a b c 不连续
     * <p></p>
     * 树状数组前缀和、前缀最大值以及区间和都比较好求：
     * 但区间最大值比较难求：
     *  1. 若 c[i] 管辖的数据范围超过区间的范围大小，则这个 c[i] 元素不能用来求最大值。
     *  2. 此时树状数组需要下沉，值 a[i] 将参与最大值的比较。
     *  3. 求最大的值的过程中，超过区间范围大小的 c 元素永远不会用到，所以我们在更新的过程中，更新与否不重要。
     */
    public int lengthOfLIS_BIT(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] tree = new int[100_000 + 1];
        int[] dp = new int[100_000 + 1];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            // 当 k 很小的时候，时间复杂度 k，当 k 很大的时候，时间复杂度 lgN
            int calc = query(tree, dp, Math.max(0, v-k), v-1) + 1;
            dp[v] = Math.max(calc, dp[v]);
            // 当 k 很小的时候，时间复杂度 k，当 k 很大的时候，时间复杂度 lgN
            update(tree, v, dp[v], k);
            max = Math.max(max, dp[v]);
        }
        return max;
    }

    /**
     * 求 dp[l,r] 的最大值, tree 为数组 dp 的树状数组
     * 	1. 若 c[i] 管辖的数据范围超过区间的范围大小，则这个 c[i] 元素不能用来求最大值，
     * 	2. 此时树状数组需要下沉，值 a[i] 将参与最大值的比较。
     */
    public int query(int[] tree, int[] dp, int l, int r) {
        int max = 0;
        while (l <= r) {
            // 这一步放在 for 循环后面，会导致死循环
            max = Math.max(max, dp[r]);
            r--;
            for (; r - (r&-r) >= l; r -= (r&-r)) {
                max = Math.max(max, tree[r]);
            }
        }
        return max;
    }

    public void update(int[] tree, int v, int max, int k) {
        int N = Math.min(v + k, tree.length);
        for (; v < N; v += (v & -v)) {
            tree[v] = Math.max(tree[v], max);
        }
    }

    public static void main(String[] args) {
        Solution2407 solution = new Solution2407();
//        int[] a = new int[]{4, 2, 1, 4, 3, 4, 5, 8, 15};
//        System.out.println(solution.lengthOfLIS_BIT(a, 3));
        int[] a = new int[]{14,20,3,10,14,20,9}; //expect 3
        System.out.println(solution.lengthOfLIS_BIT(a, 6));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
