//输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
//则输出"student. a am I"。 
//
// 
//
// 示例 1： 
//
// 输入: "the sky is blue"
//输出: "blue is sky the"
// 
//
// 示例 2： 
//
// 输入: "  hello world!  "
//输出: "world! hello"
//解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 
//
// 示例 3： 
//
// 输入: "a good   example"
//输出: "example good a"
//解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
// 
//
// 
//
// 说明： 
//
// 
// 无空格字符构成一个单词。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
// 
//
// 注意：本题与主站 151 题相同：https://leetcode-cn.com/problems/reverse-words-in-a-string/ 
//
//
// 注意：此题对比原题有改动 
// Related Topics 双指针 字符串 👍 172 👎 0


import java.util.Arrays;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer58_1 {
    /**
     * 解法 1: 空格 split 反转 join
     * 解法 2: 两波反转
     */
    public String reverseWordsV2(String s) {
        String[] s1 = s.split(" ");
        for (int i = 0, j = s1.length - 1; i < j; i++, j--) {
            String tmp = s1[i];
            s1[i] = s1[j];
            s1[j] = tmp;
        }
        return Arrays.stream(s1).filter(e -> e.length() > 0)
                .collect(Collectors.joining(" "));
    }

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        reverse(chars, 0, chars.length - 1);
        for (int i = 0, j = 0; j < chars.length;) {
            while (j < chars.length && chars[j] != ' ')
                j++;
            reverse(chars, i, j-1);
            i = ++j;
        }
        return new String(chars);
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
