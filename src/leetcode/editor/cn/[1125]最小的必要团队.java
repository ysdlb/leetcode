//作为项目经理，你规划了一份需求的技能清单 req_skills，并打算从备选人员名单 people 中选出些人组成一个「必要团队」（ 编号为 i 的备选人员
// people[i] 含有一份该备选人员掌握的技能列表）。 
//
// 所谓「必要团队」，就是在这个团队中，对于所需求的技能列表 req_skills 中列出的每项技能，团队中至少有一名成员已经掌握。可以用每个人的编号来表示团
//队中的成员： 
//
// 
// 例如，团队 team = [0, 1, 3] 表示掌握技能分别为 people[0]，people[1]，和 people[3] 的备选人员。 
// 
//
// 请你返回 任一 规模最小的必要团队，团队成员用人员编号表示。你可以按 任意顺序 返回答案，题目数据保证答案存在。 
//
// 
//
// 示例 1： 
//
// 
//输入：req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],[
//"nodejs","reactjs"]]
//输出：[0,2]
// 
//
// 示例 2： 
//
// 
//输入：req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people 
//= [["algorithms","math","java"],["algorithms","math","reactjs"],["java",
//"csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= req_skills.length <= 16 
// 1 <= req_skills[i].length <= 16 
// req_skills[i] 由小写英文字母组成 
// req_skills 中的所有字符串 互不相同 
// 1 <= people.length <= 60 
// 0 <= people[i].length <= 16 
// 1 <= people[i][j].length <= 16 
// people[i][j] 由小写英文字母组成 
// people[i] 中的所有字符串 互不相同 
// people[i] 中的每个技能是 req_skills 中的技能 
// 题目数据保证「必要团队」一定存在 
// 
//
// Related Topics 位运算 数组 动态规划 状态压缩 👍 172 👎 0


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1125 {

    /* 最小的必要团队: https://leetcode.cn/problems/smallest-sufficient-team/
     * 相似题:
     *
     * 选人的优先级:
     *   和团队技能匹配度越高越被优先选取
     *
     * 给 people 按上述优先级排序, 然后依次取 people
     * 每拿到一个 people，将其所会技能从 req_skills 中去除
     *
     * 上述逻辑问题：选中一个 people 并剔除技能后，优先级顺序也被改变了
     *
     * 暴力求解:
     *   存在所需技能、排名、取 top1、剔除已有技能
     * 不断循环
     * 这个思路也不对，局部最优仍然不是全局最优, 有人符合的技能要求很多，但他的可替代性很强
     */
    public int[] smallestSufficientTeamWrong(String[] req_skills, List<List<String>> people) {
        List<Integer> ret = new ArrayList<>();
        Set<String> skills = new HashSet<>(Arrays.asList(req_skills));
        while (!skills.isEmpty()) {
            int max = -1, index = -1;
            for (int i = 0; i < people.size(); i++) {
                List<String> p = people.get(i);
                long count = p.stream().filter(skills::contains).count();
                if (count > max) {
                    max = (int) count;
                    index = i;
                }
            }
            people.get(index).forEach(skills::remove);
            people.set(index, new ArrayList<>());
            ret.add(index);
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

    /* 最小的必要团队: https://leetcode.cn/problems/smallest-sufficient-team/
     * 相似题: 背包问题
     *
     * 选人的优先级:
     *   和团队技能匹配度越高越被优先选取
     *
     * 给 people 按上述优先级排序, 然后依次取 people
     * 每拿到一个 people，将其所会技能从 req_skills 中去除
     *
     * 上述逻辑问题：选中一个 people 并剔除技能后，优先级顺序也被改变了
     *
     * 暴力求解:
     *   存在所需技能、排名、取 top1、剔除已有技能
     * 不断循环
     * 这个思路也不对，局部最优仍然不是全局最优, 有人符合的技能要求很多，但他的可替代性很强
     *
     *
     * 每一个决策都会导致同模式下后续所有的决策发生变化; 而且当前最优不代表整体最优
     * 这好像是一个背包问题: (一直不怎么懂，留存后续学习 )
     *
     * 对一个长度为 n 的 req_skills, 其全部组合有 2^n 种,
     * 对其中的任意一种技能集合 A，若某个人的技能组是 B, A^B 不为空，
     *   那么 f(A|B) = f(A)+1 ==> f(A|B) = min{f(A)+1, f(A^B)}
     * f(ALL) 就是我们要的最小人数值
     *
     * 题目要求我们返回具体的每一个人，我们需要记住每一次求最优时候的选择
     *
     * 因为 n <= 16, 我们用 unsigned short 表示其中的任意技能组
     */
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        // 数据准备工作
        Map<String, Integer> indexMap = IntStream.range(0, req_skills.length).boxed()
                .collect(Collectors.toMap(i -> req_skills[i], i -> i));
        int[] p = new int[people.size()];
        for (int i = 0; i < people.size(); i++) {
            List<String> one = people.get(i);
            for (String skill: one) {
                p[i] |= 1 << indexMap.get(skill);
            }
        }

        // DP 开始
        int[] dp = new int[(1 << req_skills.length)];
        int[] se = new int[(1 << req_skills.length)];
        int[] from = new int[(1 << req_skills.length)];
        Arrays.fill(dp, Short.MAX_VALUE);
        Arrays.fill(from, -1);
        dp[0] = 0;

        // 若总共 6 个技能，"1","2","3","456","123"
        // f(0) 最后会使 f("123") = f(0) + 1 = 1
        for (int i = 0; i < dp.length; i++) {
            // 未命中过的子集不影响最终结果，可以去掉
            if (dp[i] >= Short.MAX_VALUE) continue;

            for (int j = 0; j < p.length; j++) {
                int one = p[j];
                if ((i^one) == 0) continue;

                int select = (i|one);
                if (dp[select] > dp[i] + 1) {
                    dp[select] = dp[i] + 1;
                    se[select] = j;
                    from[select] = i;
                }
            }
        }

        int select = (1 << req_skills.length) - 1;
        List<Integer> r = new ArrayList<>();
        while (select != 0) {
            r.add(se[select]);
            select = from[select];

        }
        return r.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Solution1125 solution = new Solution1125();
        String[] reqSkills = Arrays.asList("algorithms","math","java","reactjs","csharp","aws")
                .toArray(String[]::new);
        List<List<String>> people = Arrays.asList(
                Arrays.asList("algorithms","math","java"),
                Arrays.asList("algorithms","math","reactjs"),
                Arrays.asList("java", "csharp","aws"),
                Arrays.asList("reactjs","csharp"),
                Arrays.asList("csharp","math"),
                Arrays.asList("aws","java")
        );
        int[] r = solution.smallestSufficientTeam(reqSkills, people);
        System.out.println(Arrays.toString(r));

    }
}
//leetcode submit region end(Prohibit modification and deletion)
