//f(x) 是 x! 末尾是 0 的数量。回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 。 
//
// 
// 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。 
// 
//
// 给定 k，找出返回能满足 f(x) = k 的非负整数 x 的数量。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 0
//输出：5
//解释：0!, 1!, 2!, 3!, 和 4! 均符合 k = 0 的条件。
// 
//
// 示例 2： 
//
// 
//输入：k = 5
//输出：0
//解释：没有匹配到这样的 x!，符合 k = 5 的条件。 
//
// 示例 3: 
//
// 
//输入: k = 3
//输出: 5
// 
//
// 
//
// 提示: 
//
// 
// 0 <= k <= 10⁹ 
// 
// Related Topics 数学 二分查找 👍 80 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution793 {
    /**
     * 0 一旦出现, 乘以任何正整数, 0 的数量只可能变多, 不会变少
     * 设 f(n) 为 n 的阶乘结果末尾 0 的个数, f(n) 是单调不递减的函数
     *
     * 二分找目标值 (mid + 1, mid - 1)
     * 二分找左边界 mid 取下界, 左 mid + 1, 右 mid
     * 二分找右边界 mid 取上界, 左 mid, 右 mid - 1
     *
     * 1000000000 个 0 需要比 int 范围大的数的阶乘
     *
     * 其实只要存在, 必然是 5 个
     */
    public int preimageSizeFZF(int k) {
        long left = 0, right = 1, mid = -1;
        int num;
        while ((num = trailingZeroes(right)) <= k) {
            if (num == k)
                mid = right;
            right *= 2;
        }

        if (mid == -1) {
            long t = (left + right) / 2;
            while (left < right) {
                int midNum = trailingZeroes(t);
                if (midNum > k) {
                    right = t - 1;
                } else if (midNum < k) {
                    left = t + 1;
                } else {
                    mid = t;
                    break;
                }
                t = (left + right) / 2;
            }
        }

        // 没有任何 f(n) 满足 k 个 0
        if (mid == -1) {
            return 0;
        }
        // 只要存在, 必然是 5 个
        // return 5;

        // 找 f(n) == k 的左边界
        long leftL = left, rightL = mid;
        // 找左边界需要向下取整
        long midL = (leftL + rightL) / 2;
        while (leftL < rightL) {
            // midNum <= k
            int midNum = trailingZeroes(midL);
            if (midNum < k) {
                leftL = midL + 1;
            } else {
                rightL = midL;
            }
            midL = (leftL + rightL) / 2;
        }
        // 找 f(n) == k 的右边界
        long leftR = mid, rightR = right;
        // 找右边界需要向上取整
        long midR = (leftR + rightR + 1) / 2;
        while (leftR < rightR) {
            // midNum >= k
            int midNum = trailingZeroes(midR);
            if (midNum > k) {
                rightR = midR - 1;
            } else {
                leftR = midR;
            }
            midR = (leftR + rightR + 1) / 2;
        }

        return (int)(leftR - leftL + 1);
    }

    /**
     * f(n) 函数
     * 1000000000 个 0 需要比 int 范围大的数的阶乘
     */
    private int trailingZeroes(long n) {
        int ret = 0;
        long base = 5;
        while (base <= n) {
            ret += n/base;
            base *= 5;
        }
        return ret;
    }

    public static void main(String[] args) {
        new Solution793().preimageSizeFZF(1000000000);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
