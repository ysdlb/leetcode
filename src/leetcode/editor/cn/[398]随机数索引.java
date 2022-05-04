//给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。 
//
// 注意： 
//数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。 
//
// 示例: 
//
// 
//int[] nums = new int[] {1,2,3,3,3};
//Solution solution = new Solution(nums);
//
//// pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
//solution.pick(3);
//
//// pick(1) 应该返回 0。因为只有nums[0]等于1。
//solution.pick(1);
// 
// Related Topics 水塘抽样 哈希表 数学 随机化 👍 125 👎 0


import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * 2022/05/04补充，类似题目：382
 * 710, 380
 */
class Solution398 {
    private final int[] nums;
    private final Random random;

    public Solution398(int[] nums) {
        this.nums = nums;
        random = new Random(System.currentTimeMillis());
    }

    /**
     * 随机返回给定数字的索引
     * 特意强调了数组很大, 以及空间的紧俏性
     * 所以我们不能把给定数字的索引值都取出来然后随机返回一个(这样效率最高)
     * 题目变成了一个类似无限流等概率返回的问题, 这样可以使用 O(1) 的空间复杂度
     * 即单容量的蓄水池采样算法
     */
    public int pick(int target) {
        int count = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (random.nextInt(++count) == 0)
                    res = i;
            }
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
//leetcode submit region end(Prohibit modification and deletion)
