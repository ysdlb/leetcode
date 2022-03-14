//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[
//b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）： 
//
// 
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// 你可以按 任意顺序 返回答案 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 双指针 排序 👍 1147 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution18 {
    /**
     * 排序遍历, 对后面剩余元素做三数和
     * <a href="https://leetcode-cn.com/problems/3sum/">leetcode-15 三数和</a>
     *
     * <p> <a href="https://leetcode-cn.com/problems/4sum/">leetcode-18  四数和</a>
     *
     * 注意和三数和一样, 跳过重复元素
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSum(nums, target, 0, nums.length-1, 4);
    }

    /**
     * @param nums 有序数组
     */
    private List<List<Integer>> nSum(int[] nums, int target, int left, int right, int n) {
        List<List<Integer>> ret = new ArrayList<>();
        if (n == 2) {
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum < target)
                    left++;
                else if (sum > target)
                    right--;
                else {
                    List<Integer> pair = new ArrayList<>();
                    int leftV = nums[left], rightV = nums[right];
                    pair.add(leftV); pair.add(rightV);
                    ret.add(pair);

                    // 跳过重复元素
                    while (left < right && nums[left] == leftV)
                        left++;
                    while (left < right && nums[right] == rightV)
                        right--;
                }
            }
            return ret;
        }

        // 最后一次, i+1, i+2
        for (int i = left; i <= right - n + 1;) {
            int element = nums[i];
            int targetI = target - element;
            List<List<Integer>> lists = nSum(nums, targetI, i+1, right, n-1);
            for (List<Integer> list: lists) {
                list.add(element);
                ret.add(list);
            }

            while (i <= right - n + 1 && nums[i] == element)
                i++;
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
