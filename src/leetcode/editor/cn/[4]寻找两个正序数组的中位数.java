//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1：
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m
// nums2.length == n 
// 0 <= m <= 1000
// 0 <= n <= 1000
// 1 <= m + n <= 2000
// -10⁶ <= nums1[i], nums2[i] <= 10⁶
// 
// Related Topics 数组 二分查找 分治 👍 5206 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution4 {
    /**
     * 有序数组的中位数可以在 O(1) 的时间范围内得到, 其中位数左边的元素数量和右边的元素数量相等
     * 两个有序数组可分割为四段:
     * leftLen1 + leftLen2 = rightLen1 + rightLen2
     * or
     * leftLen1 + leftLen2 + 1 = rightLen1 + rightLen2
     *
     * max{left1, left2} < min{right1, right2}
     *
     * 这里设下标 split, 将 nums 数组切割成 nums[0...split] 和 nums(split...len-1] 两段数组
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int split1 = (nums1.length-1)/2;
        int split2 = (nums2.length-1)/2;
        // -1 / 2 == 0
        if (nums1.length == 0) {
            return nums2.length % 2 == 0 ?
                    ((double) nums2[split2] + nums2[split2+1]) / 2 : nums2[split2];
        }
        if (nums2.length == 0) {
            return nums1.length % 2 == 0 ?
                    ((double) nums1[split1] + nums1[split1+1]) / 2 : nums1[split1];
        }

        // 奇数保证左边比右边多一个, 偶数保证左右两边数量相等
        int len = nums1.length + nums2.length, leftLen = split1 + split2 + 2;
        if (leftLen - (len - leftLen) == 2)
            split1--;
        while (split1 >= 0 && split2 < nums2.length - 1 && nums1[split1] > nums2[split2+1]) {
            // nums2 的 split 向右移动, nums1 的 split 向左移动
            split1--;
            split2++;
        }
        while (split2 >= 0 && split1 < nums1.length - 1 && nums2[split2] > nums1[split1+1]) {
            // nums1 的 split 向右移动, nums2 的 split 向左移动
            split1++;
            split2--;
        }
        int maxLeft = Integer.MIN_VALUE;
        if (split1 >= 0 && split1 < nums1.length)
            maxLeft = Math.max(maxLeft, nums1[split1]);
        if (split2 >= 0 && split2 < nums2.length)
            maxLeft = Math.max(maxLeft, nums2[split2]);
        // 如果是奇数个
        if ((nums1.length + nums2.length) % 2 != 0) {
            return maxLeft;
        }
        // 如果是偶数个
        int minRight = Integer.MAX_VALUE;
        if (split1 < nums1.length - 1)
            minRight = Math.min(minRight, nums1[split1+1]);
        if (split2 < nums2.length - 1)
            minRight = Math.min(minRight, nums2[split2+1]);
        return ((double) (maxLeft + minRight)) / 2;
    }

    /**
     * 有序数组的中位数可以在 O(1) 的时间范围内得到, 其中位数左边的元素数量和右边的元素数量相等
     * 两个有序数组可分割为四段:
     * leftLen1 + leftLen2 = rightLen1 + rightLen2  or leftLen1 + leftLen2 + 1 = rightLen1 + rightLen2
     * max{left1, left2} < min{right1, right2}
     * 这里设下标 split, 将 nums 数组切割成 nums[0...split] 和 nums(split...len-1] 两段数组
     * 上述思路的逻辑优化
     *
     * 对任意长度为 len 的数组 nums, 都有 len + 1 个位置可以把数组划分为两个部分, 一旦确定了任意一个位置
     * 都可以在另一个数组中确定另外一个划分点使两个左半部分和两个右部分平分
     *
     * 所以我们只针对最短一个数组, 用二分在 len+1 的范围内找到符合以下条件的位置
     * max{left1, left2} < min{right1, right2}
     *
     * 举例, l = -1; r = len-1, 共有 len + 1 个点
     * 我们确定一个点 split1 = (l+r)/2, 就可以确定 split2 = (len1+len2) - split1 - 2;
     *
     * 时间复杂度 O(log(min{m,n}))
     */
    public double findMedianSortedArrays_v2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            return findMedianSortedArrays_v2(nums2, nums1);
        int left1 = 0, left2 = 0;
        int right1 = 0, right2 = 0;

        // 二分查找在 len + 1 个点中确定某一个点
        int l = -1, r = nums1.length - 1;
        // l == r 的时候, 一定划分好了, 最后一次执行记录四个边界值
        while (l <= r) {
            int split1 = (l+r)/2;
            // 向上取整, 保证只会左边多一个
            int split2 = (nums1.length+nums2.length+1)/2 - split1 - 2;
            left1 = split1 == -1 ? Integer.MIN_VALUE : nums1[split1];
            left2 = split2 == -1 ? Integer.MIN_VALUE : nums2[split2];
            right1 = split1 == nums1.length - 1 ? Integer.MAX_VALUE : nums1[split1+1];
            right2 = split2 == nums2.length - 1 ? Integer.MAX_VALUE : nums2[split2+1];
            if (left1 > right2) {
                // nums1 的 split 向左移动
                r = split1 - 1;
            } else if (left2 > right1) {
                // nums1 的 split 向右移动
                l = split1 + 1;
            } else {
                // left1 <= right2 && left2 <= right1 代表划分好了
                break;
            }
        }
        double mid1 = Math.max(left1, left2), mid2 = Math.min(right1, right2);
        return (nums1.length + nums2.length) % 2 == 0 ? (mid1+mid2)/2 : mid1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
