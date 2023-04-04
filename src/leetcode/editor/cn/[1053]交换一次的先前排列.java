//给你一个正整数数组 arr（可能存在重复的元素），请你返回可在 一次交换（交换两数字 arr[i] 和 arr[j] 的位置）后得到的、按字典序排列小于 
//arr 的最大排列。 
//
// 如果无法这么操作，就请返回原数组。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [3,2,1]
//输出：[3,1,2]
//解释：交换 2 和 1
// 
//
// 示例 2： 
//
// 
//输入：arr = [1,1,5]
//输出：[1,1,5]
//解释：已经是最小排列
// 
//
// 示例 3： 
//
// 
//输入：arr = [1,9,4,6,7]
//输出：[1,7,4,6,9]
//解释：交换 9 和 7
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10⁴ 
// 1 <= arr[i] <= 10⁴ 
// 
//
// Related Topics 贪心 数组 👍 83 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1053 {

    /* 两个条件
     *   1. 交换一次
     *   2. 在满足 1 的情况下，比原来小但字典序最大
     *
     * 12345 --> 12345
     * 对任意一个上升序列，它本身就是字典序最小的排列,
     *
     * 2 12345 --> 122345
     * 9 12345 --> 512349
     * 如果要找比它小的序列，就需要找到这个上升序列前面之外的第一个值, 设值为 x
     * 随便找一个比 x 值小的元素，与之交换，新序列肯定比旧序列要小
     *
     * 如果要求比之前小但最大的序列，则找一个比 x 小但最大的元素即可
     * 如果不限制交换次数, 那么第一次交换完后，需要对原来上升序列范围内的所有元素进行从大到小排序
     *
     *
     * 如何寻找最后一个上升序列
     * 从后向前遍历下降序列
     */
    public int[] prevPermOpt1(int[] arr) {
        if (arr.length == 0) return new int[]{};

        int i = arr.length - 2;
        while (i >= 0 && arr[i] <= arr[i+1]) i--;

        // i 为上升序列前面之外的第一个值
        if (i < 0) return arr;

        int x = arr[i];
        // 二分找 找第一个比 x 小但最大的元素即可
        int left = i+1, right = arr.length - 1, mid = (left + right + 1) / 2;
        while (left < right) {
            // 51
            if (arr[mid] >= x) right = mid - 1;
            else left = mid;
            // mid = (left + right) / 2; // 向下取整, 所以 right 慎用 mid - 1;
            mid = (left + right + 1) / 2; // < 最右边的元素，所以需要向上取整
        }
        // 二分找 找第一个比 x 小但最大的元素即可
        while (left > 1 && arr[left-1] == arr[left]) left --;

        int swap = arr[left];
        arr[left] = arr[i];
        arr[i] = swap;
        return arr;
    }

    /* 同上面的逻辑相同，只是换一个优雅点的写法
     * 换个优雅点的写法 */
    public int[] prevPermOpt1_Pretty(int[] arr) {
        if (arr.length == 0) return new int[]{};

        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] <= arr[i+1]) continue;

            // 二分找 找一个比 x 小但最大的元素即可
            int left = i+1, right = arr.length - 1, mid = (left + right + 1) / 2;
            while (left < right) {
                if (arr[mid] >= arr[i]) right = mid - 1;
                else left = mid;
                mid = (left + right + 1) / 2; // < 且最右边的元素，所以需要向上取整
            }
            // 二分找 找第一个比 x 小但最大的元素即可
            while (left > 1 && arr[left-1] == arr[left]) left --;

            int swap = arr[left];
            arr[left] = arr[i];
            arr[i] = swap;
            break;
        }

        return arr;
    }

    public static void main(String[] args) {
        Solution1053 solution = new Solution1053();
        // int[] arr = new int[]{3,2,1};
        //int[] arr = new int[]{1,9,4,6,7};
        //int[] arr = new int[]{1,1,5};
        int[] arr = new int[]{3,1,1,3};
        System.out.println(Arrays.toString(solution.prevPermOpt1_Pretty(arr)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
