//给定正整数 k ，你需要找出可以被 k 整除的、仅包含数字 1 的最 小 正整数 n 的长度。 
//
// 返回 n 的长度。如果不存在这样的 n ，就返回-1。 
//
// 注意： n 不符合 64 位带符号整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 1
//输出：1
//解释：最小的答案是 n = 1，其长度为 1。 
//
// 示例 2： 
//
// 
//输入：k = 2
//输出：-1
//解释：不存在可被 2 整除的正整数 n 。 
//
// 示例 3： 
//
// 
//输入：k = 3
//输出：3
//解释：最小的答案是 n = 111，其长度为 3。 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= 10⁵ 
// 
//
// Related Topics 哈希表 数学 👍 113 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1015 {
    /* 可被 K 整除的最小整数: https://leetcode.cn/problems/smallest-integer-divisible-by-k/
     *
     * 数学一类题目
     *
     * 设初始值为t (init t=1)
     * 我们只需要不断枚举 t = (10t+t), 然后判断计算 t%k, 第一次 t%k 时 t 的长度就是答案
     *
     * 但这个思路有两个问题:
     *  1. t 会非常大，long 都无法表示
     *  2. 如何判断不存在 t 值满足条件
     *
     * 推论 (可同时解决 1,2):
     * 若 pre = t%k, 且 k != 1, 下一个值取模
     *   newPre = (10*t+1) % k
     *   = ((10*t)%k + 1%k)%k
     *   = (10*t)%k + 1)%k
     *   = (10%k * t%k)%k + 1)%k
     *   = (E*pre)%k + 1)%k
     *   = (E*pre)%k + 1%k)%k
     *   = (E*pre + 1)%k
     * 10%k 一次函数中始终是一个常量, 设为 E
     *
     * 若 k == 1, 因为 t <- 1, 则 len = 1
     *
     * 不断迭代 pre 的值，其值域一定在 [0,k) 内, 解决了问题 1
     * (pre * (E+1)) % k 中只有 pre 一个变量，若 pre 之前出现过一次，一定会出现下一次循环，解决了问题 2
     *
     * 时间复杂度 O(lgN)
     *
     * 模拟:
     * k = 3;
     *   1%3 = 1 ->
     *   10 % 3 = 1
     * 1 % 3 -> 1
     * 11 % 3 -> (1*1 + 1) % 3 -> 2
     * 111 % 3 -> (1*2 + 1) % 3 -> 0
     */
    public int smallestRepunitDivByK(int k) {
        int E = 10 % k;
        Set<Integer> set = new HashSet<>();
        for (int pre = 1%k, len = 1;; len++) {
            if (pre == 0)
                return len;
            else if (set.contains(pre))
                return -1;

            set.add(pre);
            pre = (pre*E + 1) % k;
        }
    }

    public static void main(String[] args) {
        Solution1015 solu = new Solution1015();
        int i = solu.smallestRepunitDivByK(3);
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
