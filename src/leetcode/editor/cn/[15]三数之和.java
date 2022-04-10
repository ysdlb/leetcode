//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
// Related Topics 数组 双指针 排序 👍 4082 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution15 {
    /**
     * 排序, 遍历元素 O(n), 对后面元素计算两数和 O(n)
     * 总时间复杂度 O(n^2)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < nums.length - 2;) {
            int element = nums[i];
            List<List<Integer>> pairs =
                    towSum(nums, i+1, nums.length - 1, -element);
            for (List<Integer> pair: pairs) {
                pair.add(element);
                ret.add(pair);
            }
            // 跳过相同元素
            while (i < nums.length - 2 && nums[i] == element)
                i++;
        }
        return ret;
    }

    /**
     * @param nums 有序数组
     * left 右移是增大, right 左移是减小
     */
    private List<List<Integer>> towSum(int[] nums, int left, int right, int target) {
        List<List<Integer>> ret = new ArrayList<>();
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

                // 跨过相同的元素, 不用判断 i VS i+1 是否相等, 直接比较同一个值就行
                while (left < right && nums[left] == leftV)
                    left++;
                while (left < right && nums[right] == rightV)
                    right--;
            }
        }

        return ret;
    }
}

class Solution15_v2 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return nSum(nums, 0, 0, nums.length-1, 3);
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

    public static void main(String[] args) {
        new Solution15_v2().threeSum(new int[]{-2,-5,5,-7,8,4,1,9,-2,-1});
    }
}
//leetcode submit region end(Prohibit modification and deletion)
