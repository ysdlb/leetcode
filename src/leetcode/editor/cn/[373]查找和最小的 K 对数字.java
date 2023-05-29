//给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。 
//
// 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。 
//
// 请找到和最小的 k 个数对 (u1,v1), (u2,v2) ... (uk,vk) 。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//输出: [1,2],[1,4],[1,6]
//解释: 返回序列中的前 3 对数：
//     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
// 
//
// 示例 2: 
//
// 
//输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//输出: [1,1],[1,1]
//解释: 返回序列中的前 2 对数：
//     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
// 
//
// 示例 3: 
//
// 
//输入: nums1 = [1,2], nums2 = [3], k = 3 
//输出: [1,3],[2,3]
//解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums1.length, nums2.length <= 10⁵ 
// -10⁹ <= nums1[i], nums2[i] <= 10⁹ 
// nums1 和 nums2 均为升序排列 
// 1 <= k <= 1000 
// 
//
// Related Topics 数组 堆（优先队列） 👍 487 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution373 {
    /* 373.查找和最小的 K 对数字: https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/
     *
     * 直接暴力排序
     * 时间复杂度 n1*n2*lg(n1*n2)
     * 空间复杂度 n1*n2; 根据题目要求，可以达到 10^10, 直接 OOM
     *
     * 多路归并, nums1(n), nums2(m) 组成一个至少行有序的二维数组, n*m
     * 我们从这个 n*m 中找到最小的 k 个数
     *
     *   1. 每次在 n 列最左端的 n 个数中找最小的值, 计为一个数
     *   2. 将 1 中找到的那个值的后一个数视为改列最左端的值 (i,j+1)
     *   3. 重复 1,2
     *
     * 列(n)要尽可能多
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        return nums1.length >= nums2.length ?
                this.kSmallestPairs(nums1, nums2, k, false) :
                this.kSmallestPairs(nums2, nums1, k, true);
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k, boolean exchange) {
        int n = nums1.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e[0]));
        for (int i = 0; i < n; i++)
            minHeap.offer(new int[]{nums1[i]+nums2[0], i, 0});

        List<List<Integer>> ans = new ArrayList<>();
        while (ans.size() < k && !minHeap.isEmpty()) {
            int[] e = minHeap.poll();
            int i = e[1], j = e[2];
            ans.add(Arrays.asList(
                    exchange ? nums2[j]: nums1[i],
                    exchange ? nums1[i] : nums2[j]
            ));

            if (++j < nums2.length)
                minHeap.offer(new int[]{nums1[i]+nums2[j], i, j});
        }
        return ans;
    }

    public List<List<Integer>> kSmallestPairs_OOM(int[] nums1, int[] nums2, int k) {
        int[][] arr = new int[Math.min(nums1.length, k) * Math.min(nums2.length, k)][];
        int index = 0;
        for (int i = 0; i < nums1.length && i < k; i++) {
            for (int j = 0; j < nums2.length && j < k; j++) {
                arr[index++] = new int[]{nums1[i]+nums2[j], i, j};
            }
        }
        Arrays.sort(arr, Comparator.comparingInt(value -> value[0]));

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length && i < k; i++) {
            int[] e = arr[i];
            ans.add(Arrays.asList(nums1[e[1]], nums2[e[2]]));
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution373 so = new Solution373();
        int[] a1 = new int[]{1,7,11};
        int[] a2 = new int[]{2,4,6};
        so.kSmallestPairs(a1, a2, 3);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
