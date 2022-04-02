//给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。 
//
// 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
// 
// 
//
// 必须在不使用库的sort函数的情况下解决这个问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,0,1]
//输出：[0,1,2]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// nums[i] 为 0、1 或 2 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以不使用代码库中的排序函数来解决这道题吗？ 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
// Related Topics 数组 双指针 排序 👍 1206 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution75 {
    /**
     * 两趟扫描
     * 第一次确定颜色 1 的开始位置
     * 第二次交换的方式分组, 先处理 0 范围的, 然后双指针向中间靠拢处理 1, 2
     *
     * 同样是遍历两次, 另一种思路更好一点
     * 单指针
     * 第一次遍历, 将数组中的 0 都交换到数组头部
     * 第二次遍历, 将数组中的 1 都交换到 1 之后
     *
     * 但是如何只遍历 1 次呢
     */
    public void sortColors(int[] nums) {
        int count = 0;
        for (int num: nums) {
            if (num == 0)
                count++;
        }

        int start0 = 0;
        int start1 = count;
        int end2 = nums.length - 1;
        // 处理 0
        while (start0 < count) {
            while (nums[start0] != 0) {
                if (nums[start0] == 1) {
                    while (nums[start1] == 1)
                        start1++;
                    int tmp = nums[start0];
                    nums[start0] = nums[start1];
                    nums[start1] = tmp;
                } else if (nums[start0] == 2) {
                    while (nums[end2] == 2)
                        end2--;
                    int tmp = nums[start0];
                    nums[start0] = nums[end2];
                    nums[end2] = tmp;
                }
            }
            start0++;
        }

        // 处理 1 和 2
        while (start1 < end2) {
            while (start1 < end2 && nums[start1] == 1)
                start1++;
            while (start1 < end2 && nums[end2] == 2)
                end2--;
            if (start1 < end2) {
                int tmp =  nums[start1];
                nums[start1] = nums[end2];
                nums[end2] = tmp;
            }
        }
    }
}

class Solution75_TwoPoint_1 {
    /**
     * 双指针, p0 用来交换 0; p1 用来交换 1
     * 遍历数组过程中
     * 如果 i 位置是 1, 那么直接和 p1 交换
     * 如果 i 位置是 0, 便和 p0 交换.
     *  a. 如果 p0 == p1, p0++; p1++
     *  b. 如果此时 p0 < p1, 说明目前 p0 位置是 1, 我们不小心把原来交换好的 1 又换出来了,
     *     此时可以将 换出来的 1 再和 p1 交换, 换到 连续1 的末尾
     *     p0++; p1++
     *
     * 如果是 4 个数，需要三个指针，0 的时候最多需要交换 3 次，1 的时候最多交换 2 次？
     * 桶排序需要遍历两遍, 但它比较通用, 可以支持很多个数
     */
    public void sortColors(int[] nums) {
        int p0 = 0, p1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, i, p0);
                if (p0 < p1) {
                    swap(nums, i, p1);
                }
                p0++;
                p1++;
            } else if (nums[i] == 1) {
                swap(nums, i, p1);
                p1++;
            }

        }
    }

    private void swap(int[] nums, int a, int b) {
        if (a == b) return;

        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
