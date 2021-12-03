//给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。 
//
// 返回 A 的任意排列，使其相对于 B 的优势最大化。 
//
// 
//
// 示例 1： 
//
// 输入：A = [2,7,11,15], B = [1,10,4,11]
//输出：[2,11,7,15]
// 
//
// 示例 2： 
//
// 输入：A = [12,24,8,32], B = [13,25,32,11]
//输出：[24,32,8,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length = B.length <= 10000 
// 0 <= A[i] <= 10^9 
// 0 <= B[i] <= 10^9 
// 
// Related Topics 贪心 数组 排序 👍 158 👎 0


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution870 {
    /**
     * 核心思想：双方都按照战力从高到低排列, 比得过就比, 比不过就送
     *
     * 具体到细节, 输出的结果要和 nums2 的原顺序去比, 所以要记录原来的索引
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        PriorityQueue<int[]> maxQueue = new PriorityQueue<>(Comparator.comparingInt((int[] c) -> c[0]).reversed());
        for (int i = 0; i < nums2.length; i++) {
            maxQueue.offer(new int[]{nums2[i], i});
        }

        Arrays.sort(nums1);

        int[] res = new int[nums1.length];
        for (int left = 0, right = nums1.length - 1; !maxQueue.isEmpty();) {
            int[] jPair = maxQueue.poll();
            int jNum = jPair[0], jIndex = jPair[1];
            if (nums1[right] <= jNum) {
                // 比不过送一个, 选一个后面的(肯定更比不过), 因为这里是从小到大排列, 所以选一个前面的
                res[jIndex] = nums1[left];
                left++;
            } else {
                res[jIndex] = nums1[right];
                right--;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
