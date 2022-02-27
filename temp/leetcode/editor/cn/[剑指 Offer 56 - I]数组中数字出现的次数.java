//一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [4,1,4,6]
//输出：[1,6] 或 [6,1]
// 
//
// 示例 2： 
//
// 输入：nums = [1,2,10,4,1,4,3,3]
//输出：[2,10] 或 [10,2] 
//
// 
//
// 限制： 
//
// 
// 2 <= nums.length <= 10000 
// 
//
// 
// Related Topics 位运算 数组 👍 542 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer56_1 {
    /**
     * 异或 + 特殊hash
     */
    public int[] singleNumbers(int[] nums) {
        int allXor = 0;
        for (int i: nums)
            allXor ^= i;

        // allXor 为两个不相同的目标数的异或
        // 找到它们第一个为 1 的位, 对数组进行拆分
        int bitIndex = 0;
        while ((allXor & 1) == 0) {
            bitIndex++;
            allXor = allXor >> 1;
        }

        int[] split = new int[nums.length];
        int left = -1, right = nums.length;
        for (int i: nums) {
            if (((i >>> bitIndex) & 1) == 1)
                split[++left] = i;
            else
                split[--right] = i;
        }

        // 类似 hash, 重复出现两次的数字肯定被分配到了同一边
        // 但只有那两个出现一次的数字被分配到了两边
        // 这是只针对那两个数字的 hash
        int[] ret = new int[2];
        for (int i = 0; i <= left; i++)
            ret[0] ^= split[i];
        for (int j = right; j < split.length; j++)
            ret[1] ^= split[j];
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,3};
        new SolutionOffer56_1().singleNumbers(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
