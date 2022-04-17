//请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。 
//
// 实现 LFUCache 类： 
//
// 
// LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象 
// int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。 
// void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 
//capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。 
// 
//
// 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。 
//
// 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
//
// 
//
// 示例： 
//
// 
//输入：
//["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", 
//"get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
//输出：
//[null, null, null, 1, null, -1, 3, null, -1, 3, 4]
//
//解释：
//// cnt(x) = 键 x 的使用计数
//// cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
//LFUCache lfu = new LFUCache(2);
//lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
//lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
//lfu.get(1);      // 返回 1
//                 // cache=[1,2], cnt(2)=1, cnt(1)=2
//lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
//                 // cache=[3,1], cnt(3)=1, cnt(1)=2
//lfu.get(2);      // 返回 -1（未找到）
//lfu.get(3);      // 返回 3
//                 // cache=[3,1], cnt(3)=2, cnt(1)=2
//lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
//                 // cache=[4,3], cnt(4)=1, cnt(3)=2
//lfu.get(1);      // 返回 -1（未找到）
//lfu.get(3);      // 返回 3
//                 // cache=[3,4], cnt(4)=1, cnt(3)=3
//lfu.get(4);      // 返回 4
//                 // cache=[3,4], cnt(4)=2, cnt(3)=3 
//
// 
//
// 提示： 
//
// 
// 0 <= capacity <= 10⁴ 
// 0 <= key <= 10⁵ 
// 0 <= value <= 10⁹ 
// 最多调用 2 * 10⁵ 次 get 和 put 方法 
// 
// Related Topics 设计 哈希表 链表 双向链表 👍 522 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 不能用 LRU 的思路来做, LRU：146
 */
class LFUCache {

    /**
     * 如果参考 LRU 实现 LFU, 一个 hashmap, 一个双链表
     * 双链表从 head -> tail 访问数量递增, 那每次访问通过 hashmap 拿到 node 后, 频率加 1, 需要重新在双向链表中找一个合适的位置插入
     * 虽然每次都是频率 +1, 但最坏的情况需要从 head 找到 tail, 使用二分或跳表的最坏时间复杂度也是 O(lgn)
     * 所以这终方法直接否掉
     *
     * 上面的难点在于指定频率的位置查找无法做到 O(1)
     * 下面用两个 map 来 O(1) 时间找到指定频率的列表
     *
     * 又引入来一个问题: 当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项
     * 如果记录 minFreq, 当 minFreq 的队列清空了, 怎么更新 minFreq
     * 满容移除的时候不需要管, 新插入的 freq 一定是 1, minFreq 是 1
     */
    public LFUCache(int capacity) {
        this.cap = capacity;
    }

    private static final int BASE_FREQ = 1;

    private final Map<Integer, Node> map = new HashMap<>();
    private final Map<Integer, DoubleList> frequency = new HashMap<>();
    private int minFreq = 0;
    private final int cap;

    /**
     * 1. 找到 node
     * 2. 找到原频率队列
     * 3. 更新 minFreq
     * 4. node.freq ++
     * 5. 放入新的频率队列
     */
    public int get(int key) {
        Node node = map.get(key);
        if (node == null)
            return -1;

        increaseFreq(node);
        return node.value;
    }

    /**
     * 如果键 key 已存在，则变更其值；
     * 如果键不存在，请插入键值对。
     *      当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。
     *      在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
     */
    public void put(int key, int value) {
        // cap 等于 0 的时候, 没的删
        if (cap == 0) return;

        Node node = map.get(key);
        // key 已存在, 变更其值
        if (node != null) {
            node.value = value;
            increaseFreq(node);

        } else { // key 不存在, 判断容量, 然后插入
            if (map.size() == cap) {
                // 如果 LFU 满了, 移除 minFreq 最早访问的那个
                DoubleList list = frequency.get(minFreq);
                Node willRemoved = list.getHead();
                list.remove(willRemoved);
                // 这里不需要更新 minFreq

                map.remove(willRemoved.key);
            }

            node = Node.of(key, value);
            DoubleList minFreqList = frequency.computeIfAbsent(BASE_FREQ, k -> new DoubleList());
            minFreqList.append(node);
            minFreq = BASE_FREQ;

            map.put(key, node);
        }
    }

    /**
     * 将 node 访问频率 + 1
     */
    private void increaseFreq(Node node) {
        DoubleList originList = frequency.get(node.freq);
        originList.remove(node);
        // 如果最小频率队列空了, 更新最小频率
        if (minFreq == node.freq && originList.isEmpty())
            minFreq++;

        // freq + 1, 移动到新队列里去
        node.freq++;
        DoubleList movedList = frequency.computeIfAbsent(node.freq, k -> new DoubleList());
        movedList.append(node);
    }


    private static class Node {
        Integer key;
        Integer value;
        Node prev;
        Node next;
        int freq;

        public static Node of (Integer key, Integer value) {
            Node node = new Node();
            node.key = key;
            node.value = value;
            node.freq = 1;
            return node;
        }

    }

    private static class DoubleList {
        private Node head;
        private Node tail;

        private void remove(Node node) {
            if (node.prev == null)
                head = node.next;
            else
                node.prev.next = node.next;

            if (node.next == null)
                tail = node.prev;
            else
                node.next.prev = node.prev;
        }

        /**
         * head, tail.next, 新 node.prev, tail, 新 node.next
         */
        public void append(Node node) {
            if (tail == null) {
                head = node;
            } else {
                tail.next = node;
            }
            node.prev = tail;
            tail = node;
            node.next = null;
        }

        public Node getHead() {
            return head;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.get(1);      // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        lfu.get(2);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        lfu.get(1);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        lfu.get(4);      // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
