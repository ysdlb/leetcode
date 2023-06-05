//给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。 
//
// 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：grid = [[3,2,1],[1,7,6],[2,7,7]]
//输出：1
//解释：存在一对相等行列对：
//- (第 2 行，第 1 列)：[2,7,7]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
//输出：3
//解释：存在三对相等行列对：
//- (第 0 行，第 0 列)：[3,1,2,2]
//- (第 2 行, 第 2 列)：[2,4,2,2]
//- (第 3 行, 第 2 列)：[2,4,2,2]
// 
//
// 
//
// 提示： 
//
// 
// n == grid.length == grid[i].length 
// 1 <= n <= 200 
// 1 <= grid[i][j] <= 10⁵ 
// 
//
// Related Topics 数组 哈希表 矩阵 模拟 👍 28 👎 0


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2352 {

    /* 2352.相等行列对: https://leetcode.cn/problems/equal-row-and-column-pairs/
     * 相似题:
     *  1923.最长公共子路径: https://leetcode.cn/problems/longest-common-subpath/
     *
     * 如果需要暴力比较的化，每一行都会比较全部的列，总时间复杂度 O(N^3)
     *
     * hash 拿到一个行集合和列集合, 两集合的交集就是结果
     * 时间复杂度 2*N^2
     * 只需要列集合的 hash，然后一行一行去比
     *
     * hash 直接用字符串拼接 (哈哈反hash)
     * 可能存在多列相等的情况，所以用 map 更合适
     * ⚠️：11,1 和 1,11 是不同的
     * */
    public int equalPairs(int[][] grid) {
        Map<String, Integer> setCount = new HashMap<>();
        for (int j = 0; j < grid[0].length; j++) {
            StringBuilder builder = new StringBuilder();
            for (int[] ints : grid) {
                builder.append(ints[j]);
                builder.append('#');
            }
            setCount.compute(builder.toString(), (_key, oldV) -> oldV == null ? 1 : oldV+1);
        }

        int ans = 0;
        for (int[] ints : grid) {
            StringBuilder builder = new StringBuilder();
            for (int anInt : ints) {
                builder.append(anInt);
                builder.append('#');
            }
            String s = builder.toString();
            ans += setCount.getOrDefault(s, 0);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
