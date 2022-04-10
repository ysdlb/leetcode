//给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[
//i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。 
//
// 
//
// 示例: 
//
// 
//输入: [1,2,3,4,5]
//输出: [120,60,40,30,24] 
//
// 
//
// 提示： 
//
// 
// 所有元素乘积之和不会溢出 32 位整数 
// a.length <= 100000 
// 
// Related Topics 数组 前缀和 👍 198 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer66 {
    /**
     * 输入数组 a, 输出数组 b
     * b[i] = (a[0] * a[1] * ... * a[i-1]) * (a[i+1] * a[i+2] * ... * a[n-1]
     * 设 b[i] = c[i] * d[i]
     *
     * c[i] = c[i-1] * a[i-1]
     * d[i] = a[i+1] * d[i+1]
     */
    public int[] constructArr(int[] a) {
        if (a.length == 0) return new int[]{};

        int[] c = new int[a.length];
        c[0] = 1;
        for (int i = 1; i < a.length; i++) {
            c[i] = c[i-1] * a[i-1];
        }

        int[] d = new int[a.length];
        d[a.length-1] = 1;
        for (int i = a.length - 2; i >= 0; i--) {
            d[i] = a[i+1] * d[i+1];
        }

        int[] ret = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            ret[i] = c[i] * d[i];
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
