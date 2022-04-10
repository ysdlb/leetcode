//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。 
//
// 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。 
//
// 
//
// 示例 1： 
//
// 输入：target = 9
//输出：[[2,3,4],[4,5]]
// 
//
// 示例 2： 
//
// 输入：target = 15
//输出：[[1,2,3,4,5],[4,5,6],[7,8]]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= target <= 10^5 
// 
//
// 
// Related Topics 数学 双指针 枚举 👍 374 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer57_2 {
    /**
     * [left, right] 窗口
     * sum > target 时候, sum-; left++;
     * sum < target 时候, right++; sum+
     * sum == target 时候, record, right++; sum++ (枚举下一个序列)
     *
     * 终止条件 left >= right (至少含有两个数)
     * 因为是正整数序列, 所以起始值为 1,2
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> ret = new ArrayList<>();
        int left = 1, right = 2, sum = 3;
        while (left < right) {
            if (sum > target) {
                sum -= left;
                left++;
            } else if (sum < target) {
                right++;
                sum += right;
            } else {
                int[] seq = new int[right-left+1];
                ret.add(seq);
                int start = left;
                for (int i = 0; i < seq.length; i++)
                    seq[i] = start++;

                right++;
                sum += right;
            }
        }
        int[][] r = new int[ret.size()][];
        for (int i = 0; i < r.length; i++) {
            r[i] = ret.get(i);
        }
        return r;
    }

    public static void main(String[] args) {
        new SolutionOffer57_2().findContinuousSequence(9);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
