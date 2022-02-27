//统计一个数字在排序数组中出现的次数。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [5,7,7,8,8,10], target = 8
//输出: 2 
//
// 示例 2: 
//
// 
//输入: nums = [5,7,7,8,8,10], target = 6
//输出: 0 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
//
// 
//
// 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-
//position-of-element-in-sorted-array/ 
// Related Topics 数组 二分查找 👍 268 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 同 34 题, 不过这里写的更成熟
 */
class SolutionOffer53_1 {
    /**
     * 先二分找到 target
     * 在分别二分找到第一个 target 和 最后一个 target
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) return 0;

        // 先二分找到 target
        int l = 0, r = nums.length - 1, mid = (l+r) >> 1;
        // 终结条件, l == r 或 nums[mid] == target
        while (l < r && nums[mid] != target) {
            if (nums[mid] > target)
                r = mid-1;
            else if (nums[mid] < target)
                l = mid+1;
            mid = (l + r) >> 1;
        }

        int first = findFirst(nums, target, l, mid);
        int last = findLast(nums, target, mid, r);
        // 有可能 target 不存在
        return nums[first] == target ? last - first + 1 : 0;
    }

    private int findFirst(int[] nums, int target, int l, int r) {
        int mid = (l+r) >> 1;
        while (l < r) {
            if (nums[mid] >= target)  // mid 可能就是第一个, 所以不能取 mid - 1, 只能让 l 往 r 方向靠
                r = mid;
            else
                l = mid+1;
            mid = (l + r) >> 1;
        }
        return l;
    }

    private int findLast(int[] nums, int target, int l, int r) {
        // 向上取整
        int mid = (l+r+1) >> 1;
        while (l < r) {
            if (nums[mid] <= target)  // mid 可能就是最后一个, 所以不能取 mid + 1, 只能让 r 往 l 方向靠
                l = mid;
            else
                r = mid-1;
            mid = (l + r + 1) >> 1;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,3,3,3,4,5,9};
        int t = new SolutionOffer53_1().search(nums, 3);
        System.out.println(t);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
