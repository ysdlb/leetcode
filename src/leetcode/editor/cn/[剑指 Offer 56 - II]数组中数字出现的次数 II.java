//在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [3,4,3,3]
//输出：4
// 
//
// 示例 2： 
//
// 输入：nums = [9,1,7,9,7,9,7]
//输出：1 
//
// 
//
// 限制： 
//
// 
// 1 <= nums.length <= 10000 
// 1 <= nums[i] < 2^31 
// 
//
// 
// Related Topics 位运算 数组 👍 279 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer56_2 {
    /**
     * 对每个数对每一位 1 进行计数统计
     * 如果结果中某一位出现 1 的次数模 3 后为 1(要么是 0, 要么是 0, 那么一定是唯一出现一次的数, 对应位的 1
     */
    public int singleNumber(int[] nums) {
        int[] bits = new int[32];

        // 对每个数对每位进行次数统计
        for (int num: nums) {
            int i = 0;
            while (num != 0) {
                if ((num & 1) == 1)
                    bits[i]++;
                num >>>= 1;
                i++;
            }
        }

        // 只出现一次数对每一位数量一定 %3 == 1
        int base = 1;
        int ret = 0;
        for (int bitNum: bits) {
            if (bitNum % 3 == 1)
                ret |= base;
            base <<= 1;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,4,3,3};
        new SolutionOffer56_2().singleNumber(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
