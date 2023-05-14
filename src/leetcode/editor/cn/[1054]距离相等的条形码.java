//在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。 
//
// 请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：barcodes = [1,1,1,2,2,2]
//输出：[2,1,2,1,2,1]
// 
//
// 示例 2： 
//
// 
//输入：barcodes = [1,1,1,1,2,2,3,3]
//输出：[1,3,1,3,2,1,2,1] 
//
// 
//
// 提示： 
//
// 
// 1 <= barcodes.length <= 10000 
// 1 <= barcodes[i] <= 10000 
// 
//
// Related Topics 贪心 数组 哈希表 计数 排序 堆（优先队列） 👍 146 👎 0


import java.util.*;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1054 {
    /* 1054.距离相等的条形码: https://leetcode.cn/problems/distant-barcodes/
     * 相似题: 有点类似桶排序
     *
     * 题目保证元素可以两两不相等排列
     * 留存证明:
     * 先找出出现次数最多的元素, 将它们依次放在偶数索引位置上; 然后依次将剩余的数，
     * 按同值分组，依次码在奇数索引位置上
     */
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num: barcodes) {
            count.compute(num, (_num, v) -> v == null ? 1 : v+1);
        }
        int numCount = 0;
        int key = 0;
        for (Map.Entry<Integer, Integer> entry: count.entrySet()) {
            if (entry.getValue() > numCount) {
                numCount = entry.getValue();
                key = entry.getKey();
            }
        }
        // 不需要排序，只要先安排出现次数最多的那个数就可以了：
        numCount = count.remove(key);
        Deque<Map.Entry<Integer, Integer>> list = new LinkedList<>(count.entrySet());
        for (int k = 0; k < 2; k++) {
            for (int i = k; i < barcodes.length; i+=2) {
                if (numCount == 0) {
                    Map.Entry<Integer, Integer> entry = list.pollFirst();
                    key = entry.getKey();
                    numCount = entry.getValue();
                }
                barcodes[i] = key;
                numCount--;
            }
        }
        return barcodes;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
