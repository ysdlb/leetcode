//递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。 
//
// 示例1: 
//
// 
// 输入：A = 1, B = 10
// 输出：10
// 
//
// 示例2: 
//
// 
// 输入：A = 3, B = 4
// 输出：12
// 
//
// 提示: 
//
// 
// 保证乘法范围不会溢出 
// 
// Related Topics 位运算 递归 数学 👍 68 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionInterview_08_05 {
    /**
     * 参考 快速幂
     * 15*10
     * 10 的分解: 5*2 -> ((2*2)+1)*2 -> ((((2)*2)+1))*2
     * 这里只能用递归的写法, *2 的操作可以直接自举
     * 快速幂参考: 50 Pow(x,y)
     *
     * 题解有神似快速幂的非递归实现，可参考
     */
    public int multiply(int A, int B) {
        if (A <= 0 || B <= 0) throw new RuntimeException("invalid param!");
        if (A < B) {
            int tmp = A;
            A = B;
            B = tmp;
        }
        if (B == 1) return A;
        if (B == 2) return A << 1;

        int ret = multiply(multiply(A, B >> 1), 2);
        return (B & 1) == 1 ? ret + A : ret;
    }
}

class SolutionInterview_08_05_v2 {
    /**
     * a*b = a*(b >> 1 + b >> 1) + 1?
     */
    private Map<Integer, Integer> mem = new HashMap<>();
    public int multiply(int A, int B) {
        if (A <= 0 || B <= 0) return -1;
        if (A < B) {
            int tmp = A;
            A = B;
            B = tmp;
        }

        if (B == 1) return A;
        Integer ret = mem.get(B);
        if (ret != null) return ret;

        int r = multiply(A, B >> 1) + multiply(A, B >> 1);
        // 如果是奇数
        if ((B & 1) == 1) {
            r += A;
        }
        mem.put(B, r);
        return r;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
