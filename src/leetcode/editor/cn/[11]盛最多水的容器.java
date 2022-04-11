//给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。 
//
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 返回容器可以储存的最大水量。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 2 <= n <= 10⁵ 
// 0 <= height[i] <= 10⁴ 
// 
// Related Topics 贪心 数组 双指针 👍 3392 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution11 {
    /**
     * 笨方法, 可以找出所有的组合 O(n^2)
     * 双指针: 从两边往中间凑
     * 要点: 一旦两个指针确定了一个桶, 桶的高度由两个指针中较小的值确定, 暂时记这个值为 x, 此时的距离为 t
     * 则桶的大小为 x*t;
     * 假设我们保留 x, 挪动另一个较大的指针, 那么距离 T < t, 桶的高度 H <= x, T*H < x*t
     *
     * 即可以证明, 确定较小的那个指针后, 较大的那个指针无论向里移动到哪个未知取值, 最终桶的大小都不会超过当前大小
     * 所以这些选择都可以直接排除掉了, 我们直接移动较小的指针排除这些选择
     *
     * 每一步我们都可以排除 O(n) 级别的选择, 最终可以由 O(n^2) 降低为 O(n)
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ret = 0;
        while (l < r) {
            int left = height[l];
            int right = height[r];
            if (left < right) {
                ret = Math.max(ret, left * (r - l));
                l ++;
            } else {
                ret = Math.max(ret, right * (r - l));
                r --;
            }
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
