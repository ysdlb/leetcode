//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 419 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer40 {
    /**
     * 方法 1, 优先级队列, 可以处理无限流, 时间复杂度 O(n*lgk)
     * 方法 2, 选择快排, 时间复杂度 O(n)
     * 这里是选择快排实现
     * 极相似题目: 215, 703
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length == 0 || k == 0) return new int[]{};

        int l = 0, r = arr.length - 1;
        int pSite = partition(arr, l, r);
        while (pSite != k-1) {
            if (pSite > k-1)
                r = pSite - 1;
            else
                l = pSite + 1;

            pSite = partition(arr, l, r);
        }

        return Arrays.copyOf(arr, k);
    }

    /**
     * @param arr 原数组
     * @param l 左边界
     * @param r 右边界
     * @return 分隔点
     */
    private int partition(int[] arr, int l, int r) {
        int p = arr[l];
        while (l < r) {
            while (l < r && arr[r] >= p)
                r--;
            arr[l] = arr[r];

            while (l < r && arr[l] < p)
                l++;
            arr[r] = arr[l];
        }
        arr[l] = p;
        return l;
    }

    public static void main(String[] args) {
        int[] arg = new int[]{0,0,0,2,0,5};
        int k = 0;

        //
    }
}
//leetcode submit region end(Prohibit modification and deletion)
