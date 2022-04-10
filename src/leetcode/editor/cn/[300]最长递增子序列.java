//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
//
// 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子
//序列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶： 
//
// 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 数组 二分查找 动态规划 👍 2297 👎 0


import java.lang.reflect.Array;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 该通用解法可用于 334
 */
class Solution300 {
    /**
     * 状态: 当前位置
     * 选择: 将当前位置加入子序列可得的最大长度
     * dp[i] = max{dp[j]} + 1, j 满足 nums[j] < nums[i]
     *
     * return max{dp[i]}
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int ret = dp[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }

            if (dp[i] > ret)
                ret = dp[i];
        }
        return ret;
    }

    /**
     * 贪心解法
     * 设数组 d[i] 为 nums[0...j] 范围内 i 长度的所有连续递增子序列中最后一个元素的最小值
     *
     * 证明: 当 i_1 < i_2 则 d[i_1] < d[i_2]
     * 只要我们可以找出任意一个长度为 i_1 的序列, 它的最后一个元素小于 d[i_2], 那么上述推理自然成立
     *
     * 因为 d[i_2] 表示遍历到目前位置 i_2 长度的所有递增子序列的最后一个元素的最小值为 d[i_2], 设该序列为 x
     * x 序列取前 i_1 个元素, 组成一个新序列, 新序列的最后一个元素一定小于 d[i_2]
     *
     *
     * 很显然, 遍历第一个元素的时候, d[1] = nums[0]
     *
     * 遍历第二个元素的时候, 如果 nums[1] > d[1]; 那么 d[2] = nums[1]; 否则 d[1] = nums[1];
     *
     * 遍历第 n 个元素的时候, d 数组的长度为 i,
     * 如果 nums[n] > d[i], 那么 d[++i] = nums[n];
     * 否则如果 d[j-1] < nums[n] < d[j], 那么 d[j] = nums[n]; (j <= i, 公式表示 j 长度的连续递增子序列的最小值可以用 nums[n] 来代替)
     *
     * 这里 d 数组的有效长度就是最大连续递增子序列的长度
     * 因为 d 数组有序, 我们可以用二分来找上述 d[j] 的更新条件
     *
     * 该贪心解法可以用在 334 题上
     */
    public int lengthOfLIS_v2(int[] nums) {
        int[] d = new int[nums.length+1];
        int i = 0;

        d[++i] = nums[0];
        for (int j = 1; j < nums.length; j++) {
            int num = nums[j];
            if (d[i] < num) {
                d[++i] = num;
            } else if (d[i] > num) {
                // 二分
                int l = 1, r = i;
                int pos = 0; // 如果在 [1...i] 中, 找不到 d[i] < num, 那么应该设置 d[1] = num, 所以这里设置 pos 为 1
                while (l <= r) {
                    int mid = (l+r)/2;
                    if (d[mid] < num) {
                        pos = mid;
                        l = mid+1;
                    } else {
                        // 因为是向下取整, mid = (l+r)/2 可能会跨过两个元素, 直接和 l 相等
                        // 比如 l = 1, r = 3, mid = 2; 下一步 r = mid - 1 = 1;
                        // 要么让 r = mid; 要么让 l <= r
                        r = mid+1;
                    }
                }
                d[pos+1] = num;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] arg = new int[]{3,5,6,2,5,4,19,5,6,7,12};
        Solution300 solution300 = new Solution300();
        System.out.println(solution300.lengthOfLIS_v2(arg));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
