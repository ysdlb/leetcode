//在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子
//的位置在 position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。 
//
// 已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。 
//
// 给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：position = [1,2,3,4,7], m = 3
//输出：3
//解释：将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3, 6]。最小磁力为 3 。我们没办法让最小磁力大于 3 。
// 
//
// 示例 2： 
//
// 输入：position = [5,4,3,2,1,1000000000], m = 2
//输出：999999999
//解释：我们使用位于 1 和 1000000000 的篮子时最小磁力最大。
// 
//
// 
//
// 提示： 
//
// 
// n == position.length 
// 2 <= n <= 10^5 
// 1 <= position[i] <= 10^9 
// 所有 position 中的整数 互不相同 。 
// 2 <= m <= position.length 
// 
//
// Related Topics 数组 二分查找 排序 👍 155 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1552 {
    /* 1552.两球之间的磁力: https://leetcode.cn/problems/magnetic-force-between-two-balls/
     * 相似题:
     *  719.找出第 k 小的距离对: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     *  2517.礼盒的最大甜蜜度: https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/
     *
     * 如何从 position[i] 中挑出 m 个元素, 使得它们之前的最小差值最大
     * 思路同 2517
     * 设 count(diff) 为如果最小差值 >= diff, 可以挑出多少个元素;
     * diff 越小就越容易满足, count 越大, count(diff) 单调减
     *
     * 我们求 count(diff) == m 时, 最大的 diff 值
     */
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int l = 0, r = position[position.length-1]-position[0];
        while (l < r) {
            int diff = (l+r+1) >>> 1;
            if (count(position, diff) >= m) {
                // diff 可能正合适, diff 不断向上取整
                l = diff;
            } else {
                // 太小了
                r = diff - 1;
            }
        }
        return l;
    }

    private int count(int[] position, int diff) {
        int cnt = 0, pre = Integer.MIN_VALUE/2;
        for (int p: position)
            if (p-pre >= diff) {
                cnt++;
                pre = p;
            }
        return cnt;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
