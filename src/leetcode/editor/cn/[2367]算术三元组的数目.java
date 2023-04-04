//给你一个下标从 0 开始、严格递增 的整数数组 nums 和一个正整数 diff 。如果满足下述全部条件，则三元组 (i, j, k) 就是一个 算术三元组
// ： 
//
// 
// i < j < k ， 
// nums[j] - nums[i] == diff 且 
// nums[k] - nums[j] == diff 
// 
//
// 返回不同 算术三元组 的数目。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [0,1,4,6,7,10], diff = 3
//输出：2
//解释：
//(1, 2, 4) 是算术三元组：7 - 4 == 3 且 4 - 1 == 3 。
//(2, 4, 5) 是算术三元组：10 - 7 == 3 且 7 - 4 == 3 。
// 
//
// 示例 2： 
//
// 输入：nums = [4,5,6,7,8,9], diff = 2
//输出：2
//解释：
//(0, 2, 4) 是算术三元组：8 - 6 == 2 且 6 - 4 == 2 。
//(1, 3, 5) 是算术三元组：9 - 7 == 2 且 7 - 5 == 2 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 200 
// 0 <= nums[i] <= 200 
// 1 <= diff <= 50 
// nums 严格 递增 
// 
//
// Related Topics 数组 哈希表 双指针 枚举 👍 53 👎 0


import java.util.*;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2367 {

    /* 假设有 3 个下标, i < j < k;
     *   nums[j] - nums[i] == diff && nums[k] - nums[j] == k
     * 因为数组严格递增，所以在 i 固定的情况下，不存在新的 i < j < jj <= k < kk, 使得上述条件继续满足
     *   nums[jj] - nums[i] > diff    (jj < j 无意义，因为我们是从小到大推过来的)
     * 所以一旦出现满足条件的三元组, i++，此时显然也需要 j++, k++
     *
     * 为了满足条件，我们总是优先找到一组 (i,j)，使得 nums[j] - nums[i] == diff
     *   由于数组严格递增，所以要么 j++, 要么 i++，找这样一组数据无需回头
     *
     * 基于上述特点，下标 i < j < k 可以不回头一次遍历完, 时间复杂度为 O(n), 空间复杂度为 O(1)
     * hash 的方式时间复杂度相同，也更简单，但空间复杂度为 O(n)
     * */
    public int arithmeticTriplets(int[] nums, int diff) {
        if (nums.length < 3) return 0;

        int count = 0;
        for (int i = 0, j = 1, k = 2;
             i < nums.length && j < nums.length && k < nums.length;) {
            int d1 = nums[j] - nums[i];
            int d2 = nums[k] - nums[j];
            if (d1 == diff && d2 == diff) {
                i++; j++; k++;
                count++;
            // 在值域范围不大的情况下，我们根据差值来修复 j 或者 k 的值，
            // 使 i < j < k 继续满足条件
            // 注意: 过大的值域中，减法会出问题
            } else if (d1 < diff) {
                j++;
            } else if (d1 > diff) {
                i++;
            } else if (d2 < diff) {
                k++;
            } else { // d2 > diff
                j++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution2367 s = new Solution2367();
        // int[] arg = new int[]{0,1,4,6,7,10};
        int[] arg = new int[]{4,5,6,7,8,9};
        int i = s.arithmeticTriplets(arg, 2);
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
