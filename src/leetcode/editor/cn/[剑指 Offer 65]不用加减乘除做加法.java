//写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。 
//
// 
//
// 示例: 
//
// 输入: a = 1, b = 1
//输出: 2 
//
// 
//
// 提示： 
//
// 
// a, b 均可能是负数或 0 
// 结果不会溢出 32 位整数 
// 
// Related Topics 位运算 数学 👍 262 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer65 {
    /**
     * 1+1=10, 1+0=01, 0+0 = 00
     * 对二进制对每一位, 分别计算本位相加的值 x (^) 与本位相加进位的值 y (& and <<)
     * 然后 x,y 作为新的 a,b 继续上一步骤, 直到 y == 0 为止（没有进位了)
     */
    public int add(int a, int b) {
        int x, y;
        while (b != 0) {
            x = a ^ b;
            y = (a & b) << 1;

            a = x;
            b = y;
        }
        return a;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
