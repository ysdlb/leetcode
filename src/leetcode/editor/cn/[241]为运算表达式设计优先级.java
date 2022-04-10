//给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：expression = "2-1-1"
//输出：[0,2]
//解释：
//((2-1)-1) = 0 
//(2-(1-1)) = 2
// 
//
// 示例 2： 
//
// 
//输入：expression = "2*3-4*5"
//输出：[-34,-14,-10,-10,10]
//解释：
//(2*(3-(4*5))) = -34 
//((2*3)-(4*5)) = -14 
//((2*(3-4))*5) = -10 
//(2*((3-4)*5)) = -10 
//(((2*3)-4)*5) = 10
// 
//
// 
//
// 提示： 
//
// 
// 1 <= expression.length <= 20 
// expression 由数字和算符 '+'、'-' 和 '*' 组成。 
// 输入表达式中的所有整数值在范围 [0, 99] 
// 
// Related Topics 递归 记忆化搜索 数学 字符串 动态规划 👍 521 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution241 {
    public List<Integer> diffWaysToCompute(String expression) {
        return compute(expression, 0, expression.length()-1);
    }

    /**
     * 分治策略, 每次只拆分某个运算符左右两组(单运算符没有优先级的问题), 将其拆分成更小的子问题
     * 1+2*3-4*5
     *
     * (1)+(2*3-4*5)
     * (1+2)*(3-4*5)
     * (1+2*3)-(4*5)
     * (1+2*3-4)*(5)
     *
     * 存在子问题重复计算, 可以考虑加备忘录
     */
    private List<Integer> compute(String expression, int left, int right) {
        // 单个数字
        int firstSymbol = 0;
        for (int i = left; i <= right; i++) {
            char ch = expression.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                firstSymbol = i;
                break;
            }
        }

        List<Integer> ret = new ArrayList<>();
        if (firstSymbol == 0) {
            int v = Integer.parseInt(expression.substring(left, right + 1));
            ret.add(v);
            return ret;
        }

        for (int i = left; i <= right; i++) {
            char ch = expression.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                List<Integer> vLs = compute(expression, left, i-1);
                List<Integer> vRs = compute(expression, i+1, right);
                for (Integer vL: vLs)
                    for (Integer vR: vRs) {
                        if (ch == '+')
                            ret.add(vL + vR);
                        else if (ch == '-')
                            ret.add(vL - vR);
                        else
                            ret.add(vL * vR);
                    }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        List<Integer> ret = new Solution241().diffWaysToCompute("2*3-4*5");
        System.out.println(ret);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
