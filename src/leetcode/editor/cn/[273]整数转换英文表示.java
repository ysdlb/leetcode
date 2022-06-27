//将非负整数 num 转换为其对应的英文表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：num = 123
//输出："One Hundred Twenty Three"
// 
//
// 示例 2： 
//
// 
//输入：num = 12345
//输出："Twelve Thousand Three Hundred Forty Five"
// 
//
// 示例 3： 
//
// 
//输入：num = 1234567
//输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
// 
//
// 
//
// 提示： 
//
// 
// 0 <= num <= 2³¹ - 1 
// 
// Related Topics 递归 数学 字符串 👍 281 👎 0


import javax.rmi.ssl.SslRMIClientSocketFactory;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution273 {

    private final static String[] base = {
            "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen",
    };
    private final static String[] tenBase = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private final static int THOUSAND = 1000;
    private final static int MILLION = 1000 * THOUSAND;
    private final static int BILLION = 1000 * MILLION;

    /**
     * | Thousand | Million | Billion |
     * 共有四个空隙, 每个空隙都是一千以内的一个树, 以 23 或 023 这种形式表示
     * 先除法在取余数后只有 23 这种形式
     *
     * 1000, 100 这些的 0 处理比较难
     * 0 有时候需要打印, 有时候不需要打印
     * 重在分类讨论
     */
    public String numberToWords(int num) {
        StringBuilder builder = new StringBuilder();

        int billion = num / BILLION;
        if (billion % THOUSAND > 0) {
            builder.append(lessThanThousandsToWords(billion));
            builder.append(" ");
            builder.append("Billion");
        }

        int million = num / MILLION;
        if (million % THOUSAND > 0) {
            if (builder.length() > 0)
                builder.append(" ");
            builder.append(lessThanThousandsToWords(million));
            builder.append(" ");
            builder.append("Million");
        }

        int thousand = num / THOUSAND;
        if (thousand % THOUSAND > 0) {
            if (builder.length() > 0)
                builder.append(" ");
            builder.append(lessThanThousandsToWords(thousand));
            builder.append(" ");
            builder.append("Thousand");
        }

        // 如果高位有值, 这里为 0 的话, 不应该追加任何东西 (len > 0 && num % 1000 == 0)
        if (num % THOUSAND != 0 || builder.isEmpty()) {
            if (builder.length() > 0)
                builder.append(" ");
            builder.append(lessThanThousandsToWords(num));
        }
        return builder.toString();
    }

    private String lessThanThousandsToWords(int num) {
        num = num % THOUSAND;

        StringBuilder builder = new StringBuilder();
        int hundred = num / 100;
        if (hundred > 0) {
            builder.append(base[hundred]);
            builder.append(" ");
            builder.append("Hundred");
        }

        num = num % 100;
        if (num < 20) {
            // 如果百位有值, 这里不应该给 0 (len > 0 && num == 0)
            if (num != 0 || builder.isEmpty()) {
                builder.append(" ");
                builder.append(base[num]);
            }
        } else {
            int ten = num / 10;
            int one = num % 10;
            builder.append(" ");
            builder.append(tenBase[ten]);
            if (one > 0) {
                builder.append(" ");
                builder.append(base[one]);
            }
        }
        return builder.toString().trim();
    }

    public static void main(String[] args) {
        Solution273 solution = new Solution273();
        System.out.println(solution.lessThanThousandsToWords(1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
