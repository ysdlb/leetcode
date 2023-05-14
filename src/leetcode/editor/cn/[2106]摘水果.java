//在一个无限的 x 坐标轴上，有许多水果分布在其中某些位置。给你一个二维整数数组 fruits ，其中 fruits[i] = [positioni, 
//amounti] 表示共有 amounti 个水果放置在 positioni 上。fruits 已经按 positioni 升序排列 ，每个 positioni 互不
//相同 。 
//
// 另给你两个整数 startPos 和 k 。最初，你位于 startPos 。从任何位置，你可以选择 向左或者向右 走。在 x 轴上每移动 一个单位 ，就
//记作 一步 。你总共可以走 最多 k 步。你每达到一个位置，都会摘掉全部的水果，水果也将从该位置消失（不会再生）。 
//
// 返回你可以摘到水果的 最大总数 。 
//
// 
//
// 示例 1： 
// 输入：fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
//输出：9
//解释：
//最佳路线为：
//- 向右移动到位置 6 ，摘到 3 个水果
//- 向右移动到位置 8 ，摘到 6 个水果
//移动 3 步，共摘到 3 + 6 = 9 个水果
// 
//
// 示例 2： 
// 输入：fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
//输出：14
//解释：
//可以移动最多 k = 4 步，所以无法到达位置 0 和位置 10 。
//最佳路线为：
//- 在初始位置 5 ，摘到 7 个水果
//- 向左移动到位置 4 ，摘到 1 个水果
//- 向右移动到位置 6 ，摘到 2 个水果
//- 向右移动到位置 7 ，摘到 4 个水果
//移动 1 + 3 = 4 步，共摘到 7 + 1 + 2 + 4 = 14 个水果
// 
//
// 示例 3： 
// 输入：fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
//输出：0
//解释：
//最多可以移动 k = 2 步，无法到达任一有水果的地方
// 
//
// 
//
// 提示： 
//
// 
// 1 <= fruits.length <= 10⁵ 
// fruits[i].length == 2 
// 0 <= startPos, positioni <= 2 * 10⁵ 
// 对于任意 i > 0 ，positioni-1 < positioni 均成立（下标从 0 开始计数） 
// 1 <= amounti <= 10⁴ 
// 0 <= k <= 2 * 10⁵ 
// 
//
// Related Topics 数组 二分查找 前缀和 滑动窗口 👍 129 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution2106 {
    /* 摘水果: https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps/
     * 滑动窗口相似题:
     *  3.无重复字符的最长子串: https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     *  209. 长度最小的子数组: https://leetcode.cn/problems/minimum-size-subarray-sum/
     *  713. 乘积小于 K 的子数组: https://leetcode.cn/problems/subarray-product-less-than-k/
     *  1004. 最大连续1的个数 III: https://leetcode.cn/problems/max-consecutive-ones-iii/
     *  1234. 替换子串得到平衡字符串: https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
     *  1658. 将 x 减到 0 的最小操作数: https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
     *
     * 思路:
     *   (pos,k) 共同组成我们的状态, 在每个位置都可以选择向左或者向右
     *   这个思路不对，每次摘了水果后, fruits 的状态也跟着改变了
     *
     * 看了题解之后:
     *   区间和问题如果不涉及负数, 优先用滑动窗口解决
     * 贪心思想:
     *  反复左右运动，会白白浪费移动次数，所以要么不折返，要么只折返一次
     * 用 [l, r] 指明一个窗口, 遍历所有以 startPos 为起点，至少需要 step 步才可以走完的窗口
     *  1. 当 r < startPos 时, l++ => step 减小; r++ step 不变
     *  2. 当 l <= startPos <= r 时, l++ => step 减小; r++ => step 增大
     *  3. 当 startPos < l 时, l++ => step 不变； r++ => step 增大
     * 可知 l/r 均有单调减和单调增的性质，所以可以用滑动窗口
     *
     * left <- 0; right <- 0
     *  1. 窗口扩张: right++
     *  2. 窗口收缩: while(step(startPos, left, right) > k) left++
     *
     * left, right 直接用 fruits[i][0] 来遍历，连续的位置没有意义
     */
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int windowSum = 0;
        int max = windowSum;
        // fruits[j][0] > startPos+k 时，窗口不可达, 有没有它不影响结果
        for (int i = 0, j = 0; j < fruits.length && fruits[j][0] <= startPos+k;) {
            windowSum += fruits[j][1];
            // 不可达的窗口 [j+1,j]; windowSum = 0;
            while (i <= j && countStep(fruits, startPos, i, j) > k) {
                windowSum -= fruits[i][1];
                i++;
            }
            max = Math.max(windowSum, max);
            j++;
        }
        return max;
    }

    /* 计算 step */
    private int countStep(int[][] fruits, int startPos, int i, int j) {
        return fruits[j][0] - fruits[i][0]
                + Math.min(Math.abs(fruits[i][0] - startPos), Math.abs(fruits[j][0] - startPos));
    }

    public static void main(String[] args) {
        int[][] fruits = new int[][]{{0,3}, {6,4}, {8,5}};
        Solution2106 solu = new Solution2106();
        int i = solu.maxTotalFruits(fruits, 3, 2);
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
