//一个国家由 n 个编号为 0 到 n - 1 的城市组成。在这个国家里，每两个 城市之间都有一条道路连接。 
//
// 总共有 m 个编号为 0 到 m - 1 的朋友想在这个国家旅游。他们每一个人的路径都会包含一些城市。每条路径都由一个整数数组表示，每个整数数组表示一个朋
//友按顺序访问过的城市序列。同一个城市在一条路径中可能 重复 出现，但同一个城市在一条路径中不会连续出现。 
//
// 给你一个整数 n 和二维数组 paths ，其中 paths[i] 是一个整数数组，表示第 i 个朋友走过的路径，请你返回 每一个 朋友都走过的 最长公共
//子路径 的长度，如果不存在公共子路径，请你返回 0 。 
//
// 一个 子路径 指的是一条路径中连续的城市序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 5, paths = [[0,1,2,3,4],
//                     [2,3,4],
//                     [4,0,1,2,3]]
//输出：2
//解释：最长公共子路径为 [2,3] 。
// 
//
// 示例 2： 
//
// 
//输入：n = 3, paths = [[0],[1],[2]]
//输出：0
//解释：三条路径没有公共子路径。
// 
//
// 示例 3： 
//
// 
//输入：n = 5, paths = [[0,1,2,3,4],
//                     [4,3,2,1,0]]
//输出：1
//解释：最长公共子路径为 [0]，[1]，[2]，[3] 和 [4] 。它们长度都为 1 。 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10⁵ 
// m == paths.length 
// 2 <= m <= 10⁵ 
// sum(paths[i].length) <= 10⁵ 
// 0 <= paths[i][j] < n 
// paths[i] 中同一个城市不会连续重复出现。 
// 
//
// Related Topics 数组 二分查找 后缀数组 哈希函数 滚动哈希 👍 40 👎 0


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1923 {
    /* 1923.最长公共子路径: https://leetcode.cn/problems/longest-common-subpath/
     * 经典LCS:
     *  1143.最长公共子序列: https://leetcode.cn/problems/longest-common-subsequence/
     * 二分问题:
     *  719.找出第 k 小的距离对: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     *
     * LCS(最长公共子序列) 的递推公式为
     *  若 s1[i] == s2[j]
     *    f(i,j) = f(i-1,j-1)+1
     *  若 s1[i] != s2[j]
     *    f(i,j) = max{f(i-1,j), f(i,j-1), f(i-1,j-1)}
     *           = max{f(i-1,j), f(i,j-1)}
     * 该题目更像多个字符串的最长公共子串
     *
     * 滚动 hash
     *   "abcde"
     * hash[j+1] = (hash[j]- a[j]*k^(len-1))*k + a[j+len]
     *
     * 题目要求的返回值 len 越大, 同公共子序列的 path 数量就越少, 这个关系单调
     * 更简化一下, 最长公共子路径的长度为 max
     *   1. len > max 时, count(paths, len) < paths.size 恒成立
     *   2. len <= max 时, count(paths, len) = paths.size 恒成立
     *
     * 注意取模之后的减法
     * a - b > 0 取模之后很可能 a - b < 0
     */
    public int longestCommonSubpath(int n, int[][] paths) {
        int minLen = Integer.MAX_VALUE;
        for (int[] path: paths)
            minLen = Math.min(minLen, path.length);

        int l = 0, r = minLen;
        while (l < r) {
            int len = (l + r + 1) >>> 1;
            if (count(paths, n, len)) {
                l = len;
            } else {
                r = len - 1;
            }
        }
        return l;
    }

    // MOD 取值要将后续所有的运算值域限制在 long 之内
    // BASE < 10^5
    private final long MOD = 1000007L * 1000009L;


    /**
     * 时间复杂度 O(m*n)
     * 本质上 hash 相等并不意味着子串也相等, 只是做题这样简单判断下
     * @return true: paths 中存在长度为 len 的公共子路径
     */
    private boolean count(int[][] paths, int BASE, int len) {
        long idempotency = 1;
        // len-1 次方
        for (int k = 1; k < len; k++) {
            idempotency = (idempotency*BASE) % MOD;
        }

        Set<Long> set = new HashSet<>();
        for (int i = 0; i < paths.length; i++) {
            long hash = 0;
            Set<Long> nowSet = new HashSet<>();
            for (int j = 0; j < len; j++) {
                hash = (hash*BASE + paths[i][j]) % MOD;
            }
            // 一层一层取交集
            if (i == 0 || set.contains(hash)) {
                nowSet.add(hash);
            }

            for (int j = len; j < paths[i].length; j++) {
                // 注意取模之后的减法
                // a - b > 0 取模之后很可能 a - b < 0
                hash = ((hash+MOD - (paths[i][j-len]*idempotency)%MOD)*BASE)%MOD + paths[i][j];
                if (i == 0 || set.contains(hash))
                    nowSet.add(hash);
            }
            // 如果交集为空, 退出出
            if (nowSet.isEmpty())
                return false;
            set = nowSet;
        }
        // 一直到最后一个 path, 都存在交集; 返回 true
        return true;
    }

    public static void main(String[] args) {
        int[][] paths = new int[][]{
                {1,7,0,6,9,0,7,4,3,9,1,5,0,8,0,6,3,6,0,8,3,7,8,3,5,3,7,4,0,6,8,1,4},
                {1,7,0,6,9,0,7,4,3,9,1,5,0,8,0,6,3,6,0,8,3,7,8,3,5,3,7,4,0,6,8,1,5},
                {8,1,7,0,6,9,0,7,4,3,9,1,5,0,8,0,6,3,6,0,8,3,7,8,3,5,3,7,4,0,6,8,1}
        };
        Solution1923 solu = new Solution1923();
        int i = solu.longestCommonSubpath(10, paths);
        System.out.println(i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
