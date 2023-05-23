//我们有一个 n 项的集合。给出两个整数数组 values 和 labels ，第 i 个元素的值和标签分别是 values[i] 和 labels[i]。还
//会给出两个整数 numWanted 和 useLimit 。 
//
// 从 n 个元素中选择一个子集 s : 
//
// 
// 子集 s 的大小 小于或等于 numWanted 。 
// s 中 最多 有相同标签的 useLimit 项。 
// 
//
// 一个子集的 分数 是该子集的值之和。 
//
// 返回子集 s 的最大 分数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
//输出：9
//解释：选出的子集是第一项，第三项和第五项。
// 
//
// 示例 2： 
//
// 
//输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
//输出：12
//解释：选出的子集是第一项，第二项和第三项。
// 
//
// 示例 3： 
//
// 
//输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
//输出：16
//解释：选出的子集是第一项和第四项。
// 
//
// 
//
// 提示： 
//
// 
// n == values.length == labels.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= values[i], labels[i] <= 2 * 10⁴ 
// 1 <= numWanted, useLimit <= n 
// 
//
// Related Topics 贪心 数组 哈希表 计数 排序 👍 56 👎 0


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1090 {
    /* 1090.受标签影响的最大值: https://leetcode.cn/problems/largest-values-from-labels/
     * 贪心思路
     * 有一筐水果，里面有苹果、香蕉、鸭梨(labels)，每个水果的重量用values数组表示;
     * 允许拿总计不超过numWanted个水果，且每一类水果最多拿useLimit个
     * 请问最重可以拿走多重的水果？
     *
     * 该题对顺序没有特殊约束
     * 按照 value 从大到小排序, 依次价值最高的即可
     */
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        Integer[] items = IntStream.range(0, values.length).boxed().toArray(Integer[]::new);
        Arrays.sort(items, Comparator.<Integer>comparingInt(i -> values[i]).reversed());

        Map<Integer, Integer> labelMap = new HashMap<>();
        int ans = 0;
        for (Integer i: items) {
            if (numWanted <= 0)
                break;

            int value = values[i];
            int label = labels[i];
            if (labelMap.getOrDefault(label, 0) >= useLimit)
                continue;

            ans += value;
            numWanted--;
            labelMap.put(label, labelMap.getOrDefault(label, 0) + 1);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
