//给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。 
//
// 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。 
//
// nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
//输出: [-1,3,-1]
//解释:
//    对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
//    对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
//    对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。 
//
// 示例 2: 
//
// 
//输入: nums1 = [2,4], nums2 = [1,2,3,4].
//输出: [3,-1]
//解释:
//    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
//    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums1.length <= nums2.length <= 1000 
// 0 <= nums1[i], nums2[i] <= 10⁴ 
// nums1和nums2中所有整数 互不相同 
// nums1 中的所有整数同样出现在 nums2 中 
// 
//
// 
//
// 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？ 
// Related Topics 栈 数组 哈希表 单调栈 👍 496 👎 0


import java.util.HashMap;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution496 {
    /**
     * 简单单调栈
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        if (nums1.length == 0) return res;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++)
            map.put(nums1[i], null);

        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            int k = nums2[i];
            while (!stack.empty() && stack.peek() <= k)
                stack.pop();

            if (map.containsKey(k))
                map.put(k, stack.isEmpty() ? -1 : stack.peek());

            stack.push(k);
        }
        for (int i = 0; i < nums1.length; i++)
            res[i] = map.get(nums1[i]);

        return res;
    }
}

class SolutionSimple496 {
    /**
     * 简单单调栈
     * 哈希放进一个 null 太憨憨了
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        if (nums1.length == 0) return res;

        HashMap<Integer, Integer> map = new HashMap<>();

        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            int k = nums2[i];
            while (!stack.empty() && stack.peek() <= k)
                stack.pop();

            map.put(k, stack.isEmpty() ? -1 : stack.peek());
            stack.push(k);
        }
        for (int i = 0; i < nums1.length; i++)
            res[i] = map.get(nums1[i]);
        return res;
    }
}

class SolutionInverse496 {
    /**
     * 正序遍历单调栈
     * 因为处理的不是当前元素，而是栈中元素, 所以栈用来保存数组索引
     * 只要后面元素比栈顶索引对应的元素大，索引出栈，更改res[sta.pop()]的数值
     * 遍历完后栈中索引位置无下一个最大的元素
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        if (nums1.length == 0) return res;


        HashMap<Integer, Integer> map = new HashMap<>();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.empty() && nums2[stack.peek()] < nums2[i])
                map.put(nums2[stack.pop()], nums2[i]);

            stack.push(i);
        }
        for (int i = 0; i < nums1.length; i++)
            res[i] = map.getOrDefault(nums1[i], -1);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
