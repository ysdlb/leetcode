//给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第
//一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。 
//
// 示例 1: 
//
// 
//输入: [1,2,1]
//输出: [2,-1,2]
//解释: 第一个 1 的下一个更大的数是 2；
//数字 2 找不到下一个更大的数； 
//第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
// 
//
// 注意: 输入数组的长度不会超过 10000。 
// Related Topics 栈 数组 单调栈 👍 529 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution503 {
    /**
     * 传统逆序遍历单调栈
     * 如果栈顶元素不比当前元素大, 则出栈, 直到找到一个比当前元素大的元素
     * 此时该元素为当前元素的下一个比较大的值
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 2*nums.length - 2; i >= 0 ; i--) {
            int index = i % nums.length;
            while (!stack.isEmpty() && stack.peek() <= nums[index])
                stack.pop();

            res[index] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[index]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
