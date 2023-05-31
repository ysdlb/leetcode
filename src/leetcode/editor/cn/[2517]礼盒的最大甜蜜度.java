//给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。 
//
// 商店组合 k 类 不同 糖果打包成礼盒出售。礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。 
//
// 返回礼盒的 最大 甜蜜度。 
//
// 
//
// 示例 1： 
//
// 
//输入：price = [13,5,1,8,21,2], k = 3
//输出：8
//解释：选出价格分别为 [13,5,21] 的三类糖果。
//礼盒的甜蜜度为 min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8 。
//可以证明能够取得的最大甜蜜度就是 8 。
// 
//
// 示例 2： 
//
// 
//输入：price = [1,3,1], k = 2
//输出：2
//解释：选出价格分别为 [1,3] 的两类糖果。 
//礼盒的甜蜜度为 min(|1 - 3|) = min(2) = 2 。
//可以证明能够取得的最大甜蜜度就是 2 。
// 
//
// 示例 3： 
//
// 
//输入：price = [7,7,7,7], k = 2
//输出：0
//解释：从现有的糖果中任选两类糖果，甜蜜度都会是 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= price.length <= 10⁵ 
// 1 <= price[i] <= 10⁹ 
// 2 <= k <= price.length 
// 
//
// Related Topics 数组 二分查找 排序 👍 37 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2517 {
    /* 2517.礼盒的最大甜蜜度: https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/
     * 相似题:
     *  719.找出第 k 小的距离对: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     *  1552.两球之间的磁力: https://leetcode.cn/problems/magnetic-force-between-two-balls/
     *
     * 最大化最小值问题
     * 最小值: 给定一个甜蜜度 sweetness, 可以很方便计算在有序 prize 里最多可以找出多少个不同糖果 count, 保证它们之前都满足这个甜蜜度
     *  有序包含了贪心的思想,
     *   1. 初始将最小价格的糖果 计入 count
     *   2. 越过第一个遍历 prize 列表, 若当前糖果的价格 - 最后计入 count 的那个糖果价格 >= sweetness, 那么将这个糖果也计入 count
     *   3. 重复步骤 2
     * 时间复杂度 O(n)
     *
     * 显然 count(sweetness) 单调递减, sweetness 越大, 糖果就越不容易计入 count
     * 我们的目标是找到满足 count(sweetness) = k 时, 最大的 sweetness 值
     *
     * tastiness 的值域为 [0, max-min]
     * 值域二分
     *
     * 时间复杂度 O(n*lgn) 排序 + O(lgD * n)
     *
     */
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);

        int l = 0, r = price[price.length-1] - price[0];
        while (l < r) {
            int mid = (l+r+1) >>> 1;
            // == 条件不 +/- 1
            // 另外一个 else 一定 +/- 1, 否则 l,r 相邻时会死循环
            if (count(price, mid) >= k) {
                // 增加甜蜜度, 降低 count 的值
                l = mid;
            } else {
                // /2 已经向上取整了
                r = mid - 1;
            }
        }
        return l;
    }

    private int count(int[] prize, int sweetness) {
        int pre = Integer.MIN_VALUE/2;
        int cnt = 0;
        for (int p : prize) {
            if (p - pre >= sweetness) {
                cnt++;
                pre = p;
            }
        }
        return cnt;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
