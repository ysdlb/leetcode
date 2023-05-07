//给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。 
//
// 返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [1,15,7,9,2,5,10], k = 3
//输出：84
//解释：数组变为 [15,15,15,9,10,10,10] 
//
// 示例 2： 
//
// 
//输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
//输出：83
// 
//
// 示例 3： 
//
// 
//输入：arr = [1], k = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 500 
// 0 <= arr[i] <= 10⁹ 
// 1 <= k <= arr.length 
// 
//
// Related Topics 数组 动态规划 👍 196 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1043 {
    /* 分隔数组以得到最大和: https://leetcode.cn/problems/partition-array-for-maximum-sum/
     * 数组分隔为长度最多为 k 的连续子序列
     *
     * 设 f(i) 为 arr[0...i] 的最大和
     *
     * 处理过程中按数组序列遍历，对每一个元素, 至多有 (2k-1) + (2k-3) + (2k-5) + ... +  种选择，来组建自己的分组
     * 这种思路选择太多，想要处理不太现实
     *
     * 对每一个元素，可以选择加入前面的旧分组，或者创建新分组
     * 1. 加入旧分组 [i-x,i-1] x 属于 [1,k)
     *   f(i) = f(i-x-1) + max{arr[i-x,i]}*(x+1)
     * 2. 创建新分组
     *   f(i) = f(i-1) + arr[i]
     *
     * 可以统一一下:
     *   f(i) = max{f(i-x-1} + max[i-x...i]*(x+1)} x 属于 [0,k)
     *
     * 为了处理边界问题
     * f(i) 表示 arr[0,i) 的最大和
     *   f(i) = max{f(i-x-1} + max[i-x-1...i-1]*(x+1)} x 属于 [0,k)
     *   i-x-1 不超过 arr 的 0 索引
     *
     * 时间复杂度 O(nk)
     * 空间复杂度 O(n)
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dp = new int[arr.length+1];
        dp[0] = 0;

        for (int i = 1; i <= arr.length; i++) {
            int max = 0;
            for (int x = 0; x < k && i-x >= 1; x++) {
                max = Math.max(max, arr[i-x-1]);
                dp[i] = Math.max(dp[i], dp[i-x-1] + max*(x+1));
            }
        }
        return dp[arr.length];
    }

    public static void main(String[] args) {
        Solution1043 solution = new Solution1043();
        int[] arr = new int[]{1,15,7,9,2,5,10};
        int r = solution.maxSumAfterPartitioning(arr, 3);
        System.out.println(r);

        arr = new int[]{1,4,1,5,7,3,6,1,9,9,3};
        r = solution.maxSumAfterPartitioning(arr, 4);
        System.out.println(r);

        arr = new int[]{1};
        r = solution.maxSumAfterPartitioning(arr, 1);
        System.out.println(r);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
