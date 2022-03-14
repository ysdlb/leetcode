//给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 10
//输出：4
//解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
// 
//
// 示例 2： 
//
// 
//输入：n = 0
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：n = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 5 * 10⁶ 
// 
// Related Topics 数组 数学 枚举 数论 👍 834 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer204 {
    /**
     * <a href="https://leetcode-cn.com/problems/count-primes/">leetcode-204</a>
     *
     * 素数无法确定, 但合数一定是可以分解为比它小的 n 个素数 (非 1) 相乘
     * 近一步可推倒 =>
     * 1. 合数一定等于一个素数 * 非 1 的倍数
     * 2. 一个素数 * 非 1 的倍数得到的只能是合数
     *
     * 这种方法的正确性是比较显然的：
     * 这种方法显然不会将质数标记成合数；
     * 另一方面，当从小到大遍历到数 x 时，倘若它是合数，则它一定是某个小于 x 的质数 y 的整数倍，
     * 故根据此方法的步骤，我们在遍历到 y 时，就一定会在此时将 x 标记为 isPrime[x]=false。
     * 因此，这种方法也不会将合数标记为质数。
     *
     * 类似:
     * <a href="https://leetcode-cn.com/problems/chou-shu-lcof/">leetcode-offer-49</a>
     * <a href="https://leetcode-cn.com/problems/ugly-number-ii/">leetcode-264 (同 offer 49)</a>
     *
     * 我们的目的就是找出所有小于 n 的合数的数量
     * cuo: 显而易见, 我们的时间复杂度最小也是 O(n)
     * 时间复杂度 O(n*loglogn)
     */
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        // 假设所有的数都是素数
        Arrays.fill(isPrime, true);
        for (int i = 2; i < n; i++) {
            // 如果这个数说素数, 那么它的所有倍数都是合数
            if (isPrime[i]) {
                // for (int j = i*2; j < n; j += i) {
                // 如果 i 是素数, 那么小于 i*i 的数一定在之前比 i 小的素数标记过了
                // 正数相乘很容易溢出
                for (long j = ((long)i)*i; j < n; j += i) {
                    isPrime[(int)j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }

    /**
     * 线性筛, 维护一个质数数组 primes, 对每个 [2, n) 内的每个 x 进行筛选, 不区分素数还是合数
     * 1. 如果 x 当前标记为素数, 将其加入质数数组 prime 中,
     * 2. 将 x 乘以质数数组中的每一个值, 目标值标记为合数, 当 x % primeI == 0 时完成 x * primeI 的标记然后截止,
     * 这样可以使每个合数只被它最小的质因子标记, 只标记一次
     */
    public int countPrimes_v2(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for (int x = 2; x < n; x++) {
            if (isPrime[x])
                primes.add(x);
            for (int prime: primes) {
                if (((long)x) * prime >= n)
                    break;

                isPrime[x*prime] = false;
                if (x % prime == 0)
                    break;
            }
        }

        return primes.size();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
