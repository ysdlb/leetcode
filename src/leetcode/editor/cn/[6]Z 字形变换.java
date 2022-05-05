//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
// Related Topics 字符串 👍 1665 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution6 {
    /**
     * 直接模拟
     * numRows == 1, 只有一个 string, index 始终为 0
     * index == 0， 之后需要向下遍历
     * index == numRows-1, 之后需要向上遍历
     */
    public String convert(String s, int numRows) {
        StringBuilder[] builders = new StringBuilder[numRows];

        int index = 0;
        int addN = 1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (builders[index] == null)
                builders[index] = new StringBuilder();
            StringBuilder builder = builders[index];
            builder.append(ch);

            if (numRows == 1) {
                addN = 0;
            } else if (index == 0) {
                addN = 1;
            } else if (index == numRows-1) {
                addN = -1;
            }
            index += addN;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder builder : builders) {
            if (builder != null)
                ret.append(builder);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        Solution6 solution6 = new Solution6();
        solution6.convert("PAYPALISHIRING", 3);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
