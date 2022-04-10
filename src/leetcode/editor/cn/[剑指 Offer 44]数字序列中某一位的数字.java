//数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，
//等等。 
//
// 请写一个函数，求任意第n位对应的数字。 
//
// 
//
// 示例 1： 
//
// 输入：n = 3
//输出：3
// 
//
// 示例 2： 
//
// 输入：n = 11
//输出：0 
//
// 
//
// 限制： 
//
// 
// 0 <= n < 2^31 
// 
//
// 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/ 
// Related Topics 数学 二分查找 👍 200 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer44 {
    /**
     * 1 位数有 10 个
     * 2 位数有 99-10+1 = 90 个
     * 3 位数有 999-100+1 = 900 个
     *
     * 对于第n个数字等价于:
     *  求1位以上的第n个数字
     *  求2位以上的第n = n-(10*1)个数字
     *  求3位以上的第n = n-(90*2)个数字
     */
    public int findNthDigit(int n) {
        // n 下标从 0 开始, 所以 1 位时, 只减去 9 个
        int bits = 1, total = 9;
        // 找到启始数
        // n 下标从 0 开始, 所以 1 位时, (n-1)/bits 往左挪了一位, 需要修正
        int start = 1;
        while (total > 0 && n > total) {
            n = n - total;
            bits++;
            start = start*10;
            total = bits*start*9;
        }

        // 找到目标数
        // 此时求 bits 位及其以上的第 n 个数字
        int target = start + (n-1)/bits; // 第 index = (n-1)/bits 个数, 从 0 开始
        int i_index = (n-1)%bits; // 数上第第几位, 从 0 开始

        // 找到目标数字
        int foreach_count = bits - i_index - 1;
        while (foreach_count > 0) {
            target = target/10;
            foreach_count--;
        }
        return target%10;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
