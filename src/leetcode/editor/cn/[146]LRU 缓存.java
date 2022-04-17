//请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。 
//
// 实现 LRUCache 类： 
//
// 
// 
// 
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 
//key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。 
// 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
// 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 10⁵ 
// 最多调用 2 * 10⁵ 次 get 和 put 
// 
// Related Topics 设计 哈希表 链表 双向链表 👍 2103 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * removeFromList(Node) 最多涉及 2 个指针
 * head 或 node.pre.post
 * tail 或 node.post.pre
 *
 * appendTail(Node) 方法可能涉及到 5 个指针
 * head，tail，旧 tail.post，新 node.pre，新 node.post
 *
 * LFU 完全不同的思路, 460
 */
class LRUCache {
    private final Map<Object, Node> map = new HashMap<>();
    private Node head;
    private Node tail;
    private final int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
    }

    /**
     * 如果元素存在
     * 从双向链表移除, 然后添加到尾部
     */
    public int get(int key) {
        Node node = map.get(key);
        if (node == null)
            return -1;

        removeFromLinkedList(node);
        appendTail(node);
        return node.value;
    }

    /**
     * put 时候, 需要判断元素是否已经存在, 如果存在, 需要先把就的从双向链表删除
     *
     *  1. map put
     *  2. 链表尾插
     *
     * 如果 size 大于 cap, 移除最老的元素
     */
    public void put(int key, int value) {
        Node old = map.get(key);
        if (old != null) {
            removeFromLinkedList(old);
        }

        Node node = Node.of(key, value);
        // map put
        // 链表尾插
        map.put(key, node);
        appendTail(node);

        if (map.size() > cap)
            removeOldestE();
    }

    /**
     * 1. 插入到尾部, 旧尾部的 post 指针
     * 2. 处理新 tail 的 pre 指针
     * 3. 更新 tail 指针
     * 4. 更新新 tail 的 post 为 null
     */
    private void appendTail(Node node) {
        if (tail == null) {
            head = node;
        } else {
            tail.post = node;
        }
        node.pre = tail;
        tail = node;
        tail.post = null;
    }

    private void removeOldestE() {
        if (head == null)
            return;
        map.remove(head.key);
        removeFromLinkedList(head);
    }

    /**
     * 从双向链表中移除一个元素
     * 需要改变其前驱的后继 和 后继的前驱
     */
    public void removeFromLinkedList(Node node) {
        if (node.pre == null)
            head = node.post;
        else
            node.pre.post = node.post;

        if (node.post == null)
            tail = node.pre;
        else
            node.post.pre = node.pre;
    }

    private static class Node {
        Integer key;
        Integer value;
        Node pre;
        Node post;

        public static Node of (Integer key, Integer value) {
            Node node = new Node();
            node.key = key;
            node.value = value;
            return node;
        }

    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(3);
        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        lRUCache.put(3, 3);
        lRUCache.put(4, 4);

        lRUCache.get(4);
        lRUCache.get(3);
        lRUCache.get(2);
        lRUCache.get(1);

        lRUCache.put(4,6);
        lRUCache.put(5, 5);

        lRUCache.get(1);
        lRUCache.get(2);
        lRUCache.get(3);
        lRUCache.get(4);
        lRUCache.get(5);

    }

    public static void test1(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
