//从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，
//可以看成任意数字。A 不能视为 14。 
//
// 
//
// 示例 1: 
//
// 
//输入: [1,2,3,4,5]
//输出: True 
//
// 
//
// 示例 2: 
//
// 
//输入: [0,0,1,2,5]
//输出: True 
//
// 
//
// 限制： 
//
// 数组长度为 5 
//
// 数组的数取值为 [0, 13] . 
// Related Topics 数组 排序 👍 201 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer61 {
    /**
     * 先排序
     * 大小王(0) 一定排在前面, 统计几个王记为 numKing
     * 后面是正常牌, 后 - 前 + 1 为需要的空隙, 一个空隙需要消耗一个王
     * 返回 numKing >= 0
     */
    public boolean isStraight(int[] nums) {
        if (nums == null || nums.length != 5)
            throw new RuntimeException("invalid input");

        Arrays.sort(nums);
        int numKing = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                numKing++;
            } else if (i > 0 && nums[i-1] != 0) {
                int diff = nums[i] - nums[i-1] - 1;
                if (diff < 0) // 重复数字肯定不为顺子
                    return false;
                numKing -= diff;
            }
        }
        return numKing >= 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
