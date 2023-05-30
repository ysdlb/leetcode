//给你一个正整数数组 arr，考虑所有满足以下条件的二叉树： 
//
// 
// 每个节点都有 0 个或是 2 个子节点。 
// 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。 
// 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。 
// 
//
// 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。 
//
// 如果一个节点有 0 个子节点，那么该节点为叶节点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：arr = [6,2,4]
//输出：32
//解释：有两种可能的树，第一种的非叶节点的总和为 36 ，第二种非叶节点的总和为 32 。 
// 
//
// 示例 2： 
// 
// 
//输入：arr = [4,11]
//输出：44
// 
//
// 
//
// 提示： 
//
// 
// 2 <= arr.length <= 40 
// 1 <= arr[i] <= 15 
// 答案保证是一个 32 位带符号整数，即小于 2³¹ 。 
// 
//
// Related Topics 栈 贪心 数组 动态规划 单调栈 👍 272 👎 0


import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1130 {
    /* 1130.叶值的最小代价生成树: https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values/
     *
     * 每个节点都有 0/2 个子节点: 非叶子节点比叶子节点的数量少 1 个
     * arr 是中序遍历的叶子节点顺序: 非叶子节点插入到它们的间隙中
     * 非叶子节点的值为叶子节点最大值的乘积:
     *   递归分割
     *   如果我们将 arr 分为 arr_l, arr_r, 分别作为 node_r 的左右子树的叶子节点, 那么无论子树怎么分, 非 node_r 的值都已经固定下来了
     *   我们只需要确保 arr_l 以及 arr_r 分割为最小代价生成树即可,
     *     此时 arr 的最小代价生成树为 arr_l_max * arr_r_max + arr_l_sum + arr_r_sum
     *
     * 时间复杂度 O(n^3)
     * 记忆化搜索这个思路可以转化成动态规划
     *
     * 考虑用单调栈(留存)
     * 问题转化:
     *  给定一个数组 arr, 不断地合并相邻的数, 合并代价为两个数的乘积, 合并之后的数为两个数的最大值, 直到数组只剩一个数, 求最小合并代价和
     * 代价树有种哈夫曼树的感觉，越下的叶子节点用的越多，要最终权值小，小的尽量都往下优先和掉，只是需要把相邻小的优先合掉。
     * 需要贪心条件证明
     */
    public int mctFromLeafValues(int[] arr) {
        int len = arr.length;
        int[][][] record = new int[len][len][];
        int[] ans = dfs(arr, 0, len-1, record);
        return ans[1];
    }

    /**
     * @param record 记忆化搜索的 map
     * @return {叶子节点最大值, 非叶子节点的和}
     * 这样会有很多重复计算, 比如 [x,x,...,x,6,2,4,x,...] 中 624 的最优解就是 32
     * 所有用一个 map 做记忆化搜索
     */
    public int[] dfs(int[] arr, int l, int r, int[][][] record) {
        if (l == r) return new int[]{arr[l], 0};

        if (record[l][r] != null) return record[l][r];
        int max = Integer.MIN_VALUE;
        int sum = Integer.MAX_VALUE;
        for (int i = l; i < r; i++) {
            int[] left = dfs(arr, l, i, record);
            int[] right = dfs(arr, i+1, r, record);
            max = Math.max(left[0], right[0]);
            sum = Math.min(sum, left[0]*right[0]+left[1]+right[1]);
        }
        int[] ans = new int[]{max, sum};
        record[l][r] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Solution1130 so = new Solution1130();
        {
            int[] arg = new int[]{6, 2, 4};
            int i = so.mctFromLeafValues(arg);
            System.out.println(i);
        }
        {
            int[] arg = new int[]{4,11};
            int i = so.mctFromLeafValues(arg);
            System.out.println(i);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
