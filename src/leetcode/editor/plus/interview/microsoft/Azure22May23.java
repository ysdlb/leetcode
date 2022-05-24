package interview.microsoft;

import java.util.List;

public class Azure22May23 {
    /**
     * Given two sorted lists of positive integers,
     * find out the first k pairs with the smallest product.
     * Each pair contains one number from each lists.
     *
     *
     * Given nums1 = [1,2,5,9], nums2 = [1,3,4,6], k = 5
     * m, n
     *
     * Return: [1,1],[2,1],[1,3], [1,4], [5,1]
     *
     * [1,54]
     *
     * // <= val 的元素数量
     * count(val) >= 5;
     * count(val) 单调
     *
     * val = 27
     * count(27) = n;
     * n > 5
     *
     * 1, 2, 5, 9
     * 3, 6,15,27
     * 4, 8,20,36
     * 6,12,30,54
     *
     * (1+54)/2 = 27
     * count(27) = 4 + 4 + 3 + 2 = 13 >= 5
     *
     * [1,27] /2 = 14
     * count(14) = 4+2+2+2 = 10 >= 5
     *
     * [1,10] /2 = 5
     * count(5) = 3+1+ 1+ 0 = 5 >= 5 true
     *
     * [1,5]  /2 = 3;
     * count(3) = 3 < 5 false
     *
     * [3+1,5] /2 = 4
     * count(4) 4 < 5 false
     *
     * [4+1,5]
     * val = 5
     *
     * 二分次数 lg(max-min)
     * count(val): O(m+n)
     *
     * 用的这道题的思路
     * <a href="https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/">leetcode-668</a>
     * 通用解法其实是双指针 + 堆，时间复杂度 O(m*n)*lgk, 这里性能更高一些
     */
    public List<int[]> smallestKProducts(int[] nums1, int[] nums2) {
        return null;
    }
}
