import java.util.*;
// 
// 
// 
// RandomizedSet() 初始化 RandomizedSet 对象 
// bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。 
// bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。 
// int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。 
// 
//
// 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。 
//
// 
//
// 示例： 
//
// 
//输入
//["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", 
//"insert", "getRandom"]
//[[], [1], [2], [2], [], [1], [2], []]
//输出
//[null, true, false, true, 2, true, false, 2]
//
//解释
//RandomizedSet randomizedSet = new RandomizedSet();
//randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
//randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
//randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
//randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
//randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
//randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
//randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
// 
//
// 
//
// 提示： 
//
// 
// -2³¹ <= val <= 2³¹ - 1 
// 最多调用 insert、remove 和 getRandom 函数 2 * 10⁵ 次 
// 在调用 getRandom 方法时，数据结构中 至少存在一个 元素。 
// 
// 
// 
// Related Topics 设计 数组 哈希表 数学 随机化 👍 393 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * O(1) 时间的 insert 和 remove 只能用 hashSet 或者 hashMap 来做
 * O(1) 时间的 getRandom 必须借助随机数和有序且常数时间读取的数组来完成
 * 类似题目:
 * 水塘抽样 382, 398
 * 数组映射 710
 */
class RandomizedSet {

    private final List<Integer> array;
    private final Map<Integer, Integer> map;
    private final Random random;

    public RandomizedSet() {
        array = new ArrayList<>();
        map = new HashMap<>();
        random = new Random(System.currentTimeMillis());
    }
    
    public boolean insert(int val) {
        Integer v = val;
        if (map.containsKey(v)) return false;

        map.put(v, array.size());
        array.add(v);
        return true;
    }

    /**
     * 1. map remove
     * 2. array move and remove
     * 3. map move, 注意只是 move, 如果第一步恰好 remove 的是 array 的最后一个元素, 要专门考虑
     *
     * 22-4-17 补充: 其实就是 index，和 array.size - 1 位置交换
     */
    public boolean remove(int val) {
        Integer v = val;
        if (!map.containsKey(v)) return false;

        int index = map.remove(v);

        int last = array.size() - 1, lastV = array.get(last);
        array.set(index, lastV);
        array.remove(last);

        if (index != last) {
            map.put(lastV, index);
        }
        return true;
    }
    
    public int getRandom() {
        return array.get(random.nextInt(array.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
//leetcode submit region end(Prohibit modification and deletion)
