//集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有
//一个数字重复 。 
//
// 给定一个数组 nums 代表了集合 S 发生错误后的结果。 
//
// 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2,4]
//输出：[2,3]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 10⁴ 
// 1 <= nums[i] <= 10⁴ 
// 
// Related Topics 位运算 数组 哈希表 排序 👍 254 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution645 {
    /**
     * 主要讨论时间复杂度为 O(n), 空间复杂度为 O(1) 的解法
     * 大小为 n 的数组包含 [1...n] 的整数, 这种情况下可以使用时间复杂度 O(n) 的桶排序 ( 需要修改数组 )
     *
     * 代码:
     * 在索引为 i 的位置的元素为 x, 如果 i <= x-1 且 i != x-1, 那么将 x-1 位置的元素和 i 位置的元素交换, 如果 nums
     * 如果 i > x-1 那么 x 就是重复的元素, 缺失的元素暂不确定
     *
     * 1,2,3,4,2,5,6,8
     */
    public int[] findErrorNums(int[] nums) {
        return null;
    }

    /**
     * 主要讨论时间复杂度为 O(n), 空间复杂度为 O(1) 的解法
     * 三次位运算, 不仅适用于缺失一个, 重复一个的情况; 还适用于重复一个的情况
     * 第一次位运算: 通过异或整个数组以及 [1...n], 计算 xor = a ^ b;
     * 第二次位运算: 遍历整个数组, 比较元素的某位是否和 xor 相同从而决定将其异或到 xor1 还是 xor2
     * 第二次位运算: 遍历[1...n], 比较元素的某位是否和 xor 相同从而决定将其异或到 xor1 还是 xor2
     *
     * xor1 和 xor2 其中一个是缺失的元素, 一个重复的元素
     * 可以遍历整个数组, 如果 xor1 在数组中存在, 那么 xor1 就是重复的元素, 否则 xor2 是重复的元素
     */
    public int[] findErrorNums_v2(int[] nums) {
        int xor = 0;
        // 第一次位运算 a^b
        for (int num: nums)
            xor ^= num;
        for (int i = 1; i <= nums.length; i++)
            xor ^= i;

        // 第二次位运算, 数组分组
        int xorA = 0, xorB = 0;
        int lowBit = xor & (-xor);
        for (int num: nums) {
            if ((num & lowBit) == 0)
                xorA ^= num;
            else
                xorB ^= num;
        }

        // 第三次位运算, 【1，n】分组
        for (int i = 1; i <= nums.length; i++) {
            if ((i & lowBit) == 0)
                xorA ^= i;
            else
                xorB ^= i;
        }

        for (int num: nums) {
            if (num == xorA)
                return new int[]{xorA, xorB};
        }
        return new int[]{xorB, xorA};
    }

    /**
     * 主要讨论时间复杂度为 O(n), 空间复杂度为 O(1) 的解法
     * 映射法
     * 重复的元素其索引会被重复映射; 缺失的元素, 对应索引位置不会被映射
     * 可将正数标记为负数作为映射
     */
    public int[] findErrorNums_v3(int[] nums) {
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
