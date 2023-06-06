//给你两个 从小到大排好序 且下标从 0 开始的整数数组 nums1 和 nums2 以及一个整数 k ，请你返回第 k （从 1 开始编号）小的 nums1
//[i] * nums2[j] 的乘积，其中 0 <= i < nums1.length 且 0 <= j < nums2.length 。
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [2,5], nums2 = [3,4], k = 2
//输出：8
//解释：第 2 小的乘积计算如下：
//- nums1[0] * nums2[0] = 2 * 3 = 6
//- nums1[0] * nums2[1] = 2 * 4 = 8
//第 2 小的乘积为 8 。
// 
//
// 示例 2： 
//
// 输入：nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
//输出：0
//解释：第 6 小的乘积计算如下：
//- nums1[0] * nums2[1] = (-4) * 4 = -16
//- nums1[0] * nums2[0] = (-4) * 2 = -8
//- nums1[1] * nums2[1] = (-2) * 4 = -8
//- nums1[1] * nums2[0] = (-2) * 2 = -4
//- nums1[2] * nums2[0] = 0 * 2 = 0
//- nums1[2] * nums2[1] = 0 * 4 = 0
//第 6 小的乘积为 0 。
// 
//
// 示例 3： 
//
// 输入：nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
//输出：-6
//解释：第 3 小的乘积计算如下：
//- nums1[0] * nums2[4] = (-2) * 5 = -10
//- nums1[0] * nums2[3] = (-2) * 4 = -8
//- nums1[4] * nums2[0] = 2 * (-3) = -6
//第 3 小的乘积为 -6 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums1.length, nums2.length <= 5 * 10⁴ 
// -10⁵ <= nums1[i], nums2[j] <= 10⁵ 
// 1 <= k <= nums1.length * nums2.length 
// nums1 和 nums2 都是从小到大排好序的。 
// 
//
// Related Topics 数组 二分查找 👍 39 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution2040 {
    /* 2040.两个有序数组的第 K 小乘积: https://leetcode.cn/problems/kth-smallest-product-of-two-sorted-arrays/
     * 相似题汇总:
     *  719.找出第 k 小的距离对: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     *
     * 2022-05 微软一面面试题类似, 不过它给的是两个正整数有序数组, 比这个简单点
     * 难点是正负混合
     * 但很容易求出积为正数量和积为负的数量
     * neg1*pos2 + pos1*neg2 是所有负数的数量
     *
     * [min, max] 二分
     * 满足 count(x) <= k 中最小的一个 x
     *
     * 还是分类讨论比较简单
     * 1,2 | -4,-3,-2,-1
     *
     * -4,-3,-2,-1
     * -8,-6,-4,-1
     *
     * neg1 & neg2
     *  左到右，上到下；递减。/ 右边就是更小的
     * pos1 & pos2
     *  左到右，上到下；递增。/ 左边就是更小的
     * pos1 & neg2
     *  左到右递增，上到下递减。\ 左边就是更小的
     * neg1 & pos2
     *  左到右递减，上到下递增。\ 右边就是更小的
     * 注意
     *  1. 正/负如何向下取整
     *  2. 10^5 相乘，超出了 int 的范围
     *  3. 最小值和最大值在 4 个数中选两个
     *  4. count 的值也会溢出
     */
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        int neg1 = 0;
        for (int n: nums1)
            if (n < 0) neg1++;

        int neg2 = 0;
        for (int n: nums2)
            if (n < 0) neg2++;

        int n1 = nums1.length-1, n2 = nums2.length-1;
        long l = Math.min((long)nums1[0]*nums2[0], (long)nums1[n1]*nums2[n2]);
        l = Math.min(l, (long)nums1[0] * nums2[n2]);
        l = Math.min(l, (long)nums1[n1]*nums2[0]);
        long r = Math.max((long)nums1[n1]*nums2[n2], (long)nums1[0]*nums2[0]);
        r = Math.max(r, (long)nums1[0] * nums2[n2]);
        r = Math.max(r, (long)nums1[n1]*nums2[0]);

        while (l < r) {
            // 向下取整
            // int mid = l+r < 0 ? (l+r-1)/2 : (l+r)/2;
            // 正负均向下取整
            long mid = (l+r) >> 1;
            if (count(nums1, nums2, neg1, neg2, mid) >= k) {
                r = mid;
            } else {
                l = mid+1;
            }
        }
        return l;
    }

    /**
     * @return 逻辑二维矩阵中, <= x 的数的数量
     */
    private long count(int[] nums1, int[] nums2, int neg1, int neg2, long x) {
        int pos1 = nums1.length - neg1;
        int pos2 = nums2.length - neg2;

        long count = 0;
        if (x < 0) {
            // 越往下越大，越往右越小
            for (int i = 0, j = neg2; i < neg1; i++) {
                while (j < nums2.length && (long)nums1[i]*nums2[j] > x)
                    j++;
                count += nums2.length-j;
            }

            // 越往上越大，越往左越小
            for (int i = nums1.length-1, j = neg2-1; i >= neg1; i--) {
                while (j >= 0 && (long)nums1[i]*nums2[j] > x)
                    j--;
                count += j+1;
            }
            return count;
        }

        // 越往上越大, 越往右越小 /
        for (int i = neg1-1, j = 0; i >= 0; i--) {
            while (j < neg2 && (long)nums1[i]*nums2[j] > x)
                j++;
            count += neg2-j;
        }
        // 越往下越大, 越往左越小 /
        for (int i = neg1, j = nums2.length-1; i < nums1.length; i++) {
            while (j >= neg2 && (long)nums1[i]*nums2[j] > x)
                j--;
            count += j-neg2+1;
        }
        count += neg1*pos2 + pos1*neg2;
        return count;
    }


    public static void main(String[] args) {
        {
            // long r = Math.max(nums1[n1]*nums2[n2], nums1[0]*nums2[0]);
            // 这样过不了，还是溢出
            int[] nums1 = new int[]{-10,-10,-10,-10,-9,-1};
            int[] nums2 = new int[]{-10,-10,-10,-10,-9,-1};
            Solution2040 solu = new Solution2040();
            long l = solu.kthSmallestProduct(nums1, nums2,21);
            System.out.println(l);
            l = solu.kthSmallestProduct(nums1, nums2,20);
            System.out.println(l);
        }
        {
            // long r = Math.max(nums1[n1]*nums2[n2], nums1[0]*nums2[0]);
            // 这样过不了，还是溢出
            int[] nums1 = new int[]{-99982,-114,-98,-61,-36,1,4,40,47,77,99,110,112,119,128,130,174,179,196,227,246,258,263,275,303,305,306,312,314,341,351,358,380,382,385,386,406,409,414,423,450,468,99542,99563,99648,99664,99673,99677,99702,99703,99704,99714,99747,99750,99774,99809,99826,99837,99895,99916,99921,99946,99959,99994,99997};
            int[] nums2 = new int[]{-99973,-99947,-99944,-99860,-99824,-99812,-99800,-99756,-99733,-99639,-99623,-99622,-99586,-99547,-99517,-99512,-99512,-99500,-99375,-99358,-99337,-99255,-99170,-99144,-99138,-99130,-601,-574,-536,-515,-462,-399,-362,-334,-309,-303,-288,-286,-208,-188,-185,-145,-81,-70,-55,-40,-36,-29,44,89,102,137,183,187,99900};
            Solution2040 solu = new Solution2040();
            long l = solu.kthSmallestProduct(nums1, nums2,3575);
            System.out.println(l);
        }
        {
            Solution2040 solu = new Solution2040();
            int[] nums1 = new int[]{1,6};
            int[] nums2 = new int[]{-10,-10,-5,-4,-3,-1};
            long l = solu.kthSmallestProduct(nums1, nums2, 9);
            System.out.println(l);
        }
        {
            Solution2040 solu = new Solution2040();
            int[] nums1 = new int[]{-100000,100000};
            int[] nums2 = new int[]{-100000,100000};
            long l = solu.kthSmallestProduct(nums1, nums2, 1);
            System.out.println(l);
        }
        {
            Solution2040 solu = new Solution2040();
            int[] nums1 = new int[]{-2, -1, 0, 1, 2};
            int[] nums2 = new int[]{-3, -1, 2, 4, 5};
            long l = solu.kthSmallestProduct(nums1, nums2, 3);
            System.out.println(l);
        }
        {
            Solution2040 solu = new Solution2040();
            int[] nums1 = new int[]{-4, -2, 0, 3};
            int[] nums2 = new int[]{2, 4};
            long l = solu.kthSmallestProduct(nums1, nums2, 6);
            System.out.println(l);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
