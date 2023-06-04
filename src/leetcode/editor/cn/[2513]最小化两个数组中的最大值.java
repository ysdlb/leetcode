//给你两个数组 arr1 和 arr2 ，它们一开始都是空的。你需要往它们中添加正整数，使它们满足以下条件： 
//
// 
// arr1 包含 uniqueCnt1 个 互不相同 的正整数，每个整数都 不能 被 divisor1 整除 。 
// arr2 包含 uniqueCnt2 个 互不相同 的正整数，每个整数都 不能 被 divisor2 整除 。 
// arr1 和 arr2 中的元素 互不相同 。 
// 
//
// 给你 divisor1 ，divisor2 ，uniqueCnt1 和 uniqueCnt2 ，请你返回两个数组中 最大元素 的 最小值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：divisor1 = 2, divisor2 = 7, uniqueCnt1 = 1, uniqueCnt2 = 3
//输出：4
//解释：
//我们可以把前 4 个自然数划分到 arr1 和 arr2 中。
//arr1 = [1] 和 arr2 = [2,3,4] 。
//可以看出两个数组都满足条件。
//最大值是 4 ，所以返回 4 。
// 
//
// 示例 2： 
//
// 
//输入：divisor1 = 3, divisor2 = 5, uniqueCnt1 = 2, uniqueCnt2 = 1
//输出：3
//解释：
//arr1 = [1,2] 和 arr2 = [3] 满足所有条件。
//最大值是 3 ，所以返回 3 。 
//
// 示例 3： 
//
// 
//输入：divisor1 = 2, divisor2 = 4, uniqueCnt1 = 8, uniqueCnt2 = 2
//输出：15
//解释：
//最终数组为 arr1 = [1,3,5,7,9,11,13,15] 和 arr2 = [2,6] 。
//上述方案是满足所有条件的最优解。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= divisor1, divisor2 <= 10⁵ 
// 1 <= uniqueCnt1, uniqueCnt2 < 10⁹ 
// 2 <= uniqueCnt1 + uniqueCnt2 <= 10⁹ 
// 
//
// Related Topics 数学 二分查找 数论 👍 29 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution2513 {
    /* 2513.最小化两个数组中的最大值: https://leetcode.cn/problems/minimize-the-maximum-of-two-arrays/
     * 最大化最小值二分汇总:
     *  2528.最大化城市的最小供电站数目: https://leetcode.cn/problems/maximize-the-minimum-powered-city/
     * 相似题目:
     *  878.第 N 个神奇数字
     *  1201. 丑数 III
     *
     * 假设我们有 x 个正整数, 这 x 个正整数能否满足条件?
     * 能被 d1 整除, 能被 d2 整除, 同时被 d1,d2 整除, 其他
     *  1. 能被 d2 整除但不能被 d1 整除, 只能放在 arr1 中
     *  2. 能被 d1 整除但不能被 d2 整除, 只能放在 arr2 中
     *  3. 不能倍 d1 整除也不能被 d2 整除, 可以放在 arr1, 或 arr2 中
     *
     * 时间复杂度 O(lg(d1+d2) + lg(unique1+unique2))
     */
    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        int l = 0, r = (uniqueCnt1+uniqueCnt2) * 2;
        while (l < r) {
            int mid = (l+r) >>> 1;
            if (check(mid, divisor1, divisor2, uniqueCnt1, uniqueCnt2)) {
                r = mid;
            } else {
                l = mid+1;
            }
        }
        return l;
    }

    /**
     * @return 最大整数为 x 时, 是否可以满足 arr1, arr2 的条件
     */
    public boolean check(int x, int d1, int d2, int uniqueCnt1, int uniqueCnt2) {
        long lcm = this.lcm(d1, d2);
        // x/d2 - x/lcm 表示 arr1 可独享的数量, 计算仍需要多少
        long left1 = Math.max(uniqueCnt1 - (x/d2 - x/lcm), 0);
        long left2 = Math.max(uniqueCnt2 - (x/d1 - x/lcm), 0);
        // 两边都可以放
        long common = x - (x/d1 + x/d2 - x/lcm);
        return common >= left1 + left2;
    }

    // 最小公倍数
    private long lcm(int a, int b) {
        return (a/gcb(a,b)) * b;
    }

    private long gcb(int a, int b) {
        if (a == 0) return b;
        return gcb(b%a, a);
    }

    public static void main(String[] args) {
        Solution2513 solu = new Solution2513();
        int i = solu.minimizeSet(92761, 48337, 208563424, 9115778);
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
