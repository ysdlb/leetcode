//给你一个数组 nums ，请你完成两类查询。 
//
// 
// 其中一类查询要求 更新 数组 nums 下标对应的值 
// 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right 
// 
//
// 实现 NumArray 类： 
//
// 
// NumArray(int[] nums) 用整数数组 nums 初始化对象 
// void update(int index, int val) 将 nums[index] 的值 更新 为 val 
// int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元
//素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]） 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：
//["NumArray", "sumRange", "update", "sumRange"]
//[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
//输出：
//[null, 9, null, 8]
//
//解释：
//NumArray numArray = new NumArray([1, 3, 5]);
//numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
//numArray.update(1, 2);   // nums = [1,2,5]
//numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// -100 <= nums[i] <= 100 
// 0 <= index < nums.length 
// -100 <= val <= 100 
// 0 <= left <= right < nums.length 
// 调用 update 和 sumRange 方法次数不大于 3 * 10⁴ 
// 
// Related Topics 设计 树状数组 线段树 数组 👍 507 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class NumArray307 {

    private final int[] tree;
    private final int[] nums;

    /**
     * 树状数组实现线段树
     * 类似题不同解法：303，304
     * 参考视频: https://www.bilibili.com/video/BV1cb411t7AM?spm_id_from=333.999.0.0&vd_source=0d1fbb2cf5c5aa0481227bd9f73b3a3a
     */
    public NumArray307(int[] nums) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("Invalid parm!");
        this.nums = nums;

        int[] tree = new int[calculateTreeLen(nums.length)];
        buildSegmentTree(nums, 0, nums.length-1, tree, 0);
        this.tree = tree;
    }

    public void update(int index, int val) {
        if (index < 0 || index >= nums.length)
            throw new IllegalArgumentException("Out of range");
        this.nums[index] = val;

        int[] tree = this.tree;
        updateTree(0, nums.length-1, tree, 0, index, val);
    }
    
    public int sumRange(int left, int right) {
        if (left < 0 || left >= nums.length)
            throw new IllegalArgumentException("Out of range");
        if (right < 0 || right >= nums.length)
            throw new IllegalArgumentException("Out of range");

        return sumRangeTree(0, nums.length-1, tree, 0, left, right);
    }

    /**
     * @param nums 原始数组
     * @param start nums left edge
     * @param end nums right edge
     * @param tree segment tree
     * @param node tree 中的节点
     *
     * 对 nums[start...end] 范围内建立线段树
     */
    private void buildSegmentTree(int[] nums, int start, int end, int[] tree, int node) {
        // base case
        if (start == end) {
            tree[node] = nums[start];
            return;
        }

        int mid = (start + end) >> 1;
        int leftNode = 2*node + 1;
        int rightNode = 2*node + 2;

        buildSegmentTree(nums, start, mid, tree, leftNode);
        buildSegmentTree(nums, mid+1, end, tree, rightNode);

        tree[node] = tree[leftNode] + tree[rightNode];
    }

    /**
     * @param start nums arr left edge
     * @param end arr right edge
     * @param index arr index, change target
     * @param val change val
     */
    private void updateTree(int start, int end, int[] tree, int node, int index, int val) {
        if (start == end) { // means start == index
            tree[node] = val;
            return;
        }

        int mid = (start + end) >> 1;
        int leftNode = 2*node + 1;
        int rightNode = 2*node + 2;

        if (start <= index && index <= mid) {
            updateTree(start, mid, tree, leftNode, index, val);
        } else {
            updateTree(mid+1, end, tree, rightNode, index, val);
        }
        tree[node] = tree[leftNode] + tree[rightNode];
    }

    /**
     * @param start nums arr left edge
     * @param end nums arr right edge
     * @param tree segment tree
     * @param node tree node
     * @param L sumRange Left
     * @param R sumRange Right
     * @return [L, R] sum
     */
    private int sumRangeTree(int start, int end, int[] tree, int node, int L, int R) {
        // base case
        if (R < start || L > end) {
            return 0;
        }
        // 这样复杂度不对
        // if (start == end) {
            // return tree[node];
        // }
        // 如果 [L, R] 包含了 [start, end] 区间, 返回 node 的值
        if (L <= start && end <= R)
            return tree[node];

        int mid = (start + end) >> 1;
        int leftNode = 2*node + 1;
        int rightNode = 2*node + 2;

        int leftSum = sumRangeTree(start, mid, tree, leftNode, L, R);
        int rightSum = sumRangeTree(mid+1, end, tree, rightNode, L, R);
        return leftSum + rightSum;
    }

    /**
     * 将其对 2^n 向上取整
     * 然后 *2 - 1 就是需要树状数组的长度
     */
    private int calculateTreeLen(int numLen) {
        int roundedUp = -1 >>> Integer.numberOfLeadingZeros(numLen - 1);
        roundedUp = roundedUp < 0 ? 1 : roundedUp + 1;
        return roundedUp < 0 ? 1 : (roundedUp << 1) - 1;
    }

    public static void main(String[] args) {
        NumArray307 numArray = new NumArray307(new int[]{9, -8});
        numArray.update(0, 3);
        numArray.sumRange(1, 1);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
