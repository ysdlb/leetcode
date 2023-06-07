//珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。 
//
// 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后
//这一小时内不会再吃更多的香蕉。 
//
// 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。 
//
// 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入: piles = [3,6,7,11], H = 8
//输出: 4
// 
//
// 示例 2： 
//
// 输入: piles = [30,11,23,4,20], H = 5
//输出: 30
// 
//
// 示例 3： 
//
// 输入: piles = [30,11,23,4,20], H = 6
//输出: 23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= piles.length <= 10^4 
// piles.length <= H <= 10^9 
// 1 <= piles[i] <= 10^9 
// 
// Related Topics 数组 二分查找 👍 277 👎 0


import javax.imageio.stream.ImageOutputStreamImpl;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution875 {
    /* 875.爱吃香蕉的珂珂: https://leetcode.cn/problems/koko-eating-bananas/
     * 相似题:
     *  1283.使结果不超过阈值的最小除数: https://leetcode.cn/problems/find-the-smallest-divisor-given-a-threshold/
     *  2187.完成旅途的最少时间: https://leetcode.cn/problems/minimum-time-to-complete-trips/
     *  1011:
     * 二分问题汇总:
     *  719.找出第 k 小的距离对: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     *
     * 吃香蕉的速度 x, 有意义的取值范围为 [1...maxPile]
     * 第一个可以吃完所有香蕉的 x 取值就是可以吃掉所有香蕉的最小速度 K
     * 吃不完在取值范围内的左边, 吃的完在取值范围内右边. 这里可以用二分
     * 每次判断能不能吃完需要 O(n) 的时间
     *
     * 注: 吃每一堆香蕉的所需时间要向上取整
     *
     * 这个题是全程自己想的（提示了二分）
     */
    public int minEatingSpeed(int[] piles, int h) {
        if (piles.length < 1) return -1;
        if (piles.length > h) return -1;

        int max = Integer.MIN_VALUE;
        for (int pile: piles)
            max = Math.max(max, pile);

        int min = 1;

        while (min < max) {
            int mid = (min + max) / 2;
            if (isEat(piles, mid, h)) {
                // mid 本身就有向下取整的趋势
                // 最终值可能就是 mid, 我们不减 1
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        return min;
    }

    private boolean isEat(int[] piles, int speed, int h) {
        int need = 0;
        for (int pile: piles) {
            need += (pile-1) / speed + 1;
        }
        return need <= h;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
