import java.util.*;

public class MultiLevelCacheSystem {

    static class Node {
        final int key;
        String value;
        int freq;
        long time;
        final long id;
        Node(int key, String value, int freq, long time, long id) {
            this.key = key; this.value = value; this.freq = freq; this.time = time; this.id = id;
        }
    }

    static class Level {
        final int capacity;
        final TreeSet<Node> order;
        final Map<Integer, Node> map = new HashMap<>();
        Level(int capacity, Comparator<Node> cmp) {
            this.capacity = capacity;
            this.order = new TreeSet<>(cmp);
        }
        boolean contains(int key) { return map.containsKey(key); }
        Node get(int key) { return map.get(key); }
        void add(Node n) { map.put(n.key, n); order.add(n); }
        void remove(Node n) { order.remove(n); map.remove(n.key); }
        Node evict() { Node n = order.first(); remove(n); return n; }
        int size() { return map.size(); }
        List<Integer> keys() { return new ArrayList<>(map.keySet()); }
    }

    private final Level[] levels;
    private final Map<Integer, Integer> keyLevel = new HashMap<>();
    private final Map<Integer, Node> nodes = new HashMap<>();
    private long tick = 0, gid = 0;
    private final Comparator<Node> cmp = (a, b) -> {
        if (a.freq != b.freq) return Integer.compare(a.freq, b.freq);
        if (a.time != b.time) return Long.compare(a.time, b.time);
        return Long.compare(a.id, b.id);
    };

    public MultiLevelCacheSystem(int c1, int c2, int c3) {
        levels = new Level[]{ new Level(c1, cmp), new Level(c2, cmp), new Level(c3, cmp) };
    }

    public String get(int key) {
        Integer li = keyLevel.get(key);
        if (li == null) return null;
        Level L = levels[li];
        Node n = L.get(key);
        L.order.remove(n);
        n.freq++;
        n.time = ++tick;
        L.order.add(n);
        if (li > 0) promote(n, li);
        return n.value;
    }

    public void put(int key, String value) {
        Integer li = keyLevel.get(key);
        if (li != null) {
            Node n = levels[li].get(key);
            levels[li].order.remove(n);
            n.value = value;
            n.freq++;
            n.time = ++tick;
            levels[li].order.add(n);
            if (li > 0) promote(n, li);
            return;
        }
        Node n = new Node(key, value, 1, ++tick, ++gid);
        nodes.put(key, n);
        levels[0].add(n);
        keyLevel.put(key, 0);
        rebalanceFrom(0);
    }

    private void promote(Node n, int from) {
        levels[from].remove(n);
        keyLevel.put(n.key, from - 1);
        levels[from - 1].add(n);
        rebalanceFrom(from - 1);
    }

    private void rebalanceFrom(int start) {
        for (int i = start; i < levels.length; i++) {
            while (levels[i].size() > levels[i].capacity) {
                Node moved = levels[i].evict();
                if (i + 1 < levels.length) {
                    levels[i + 1].add(moved);
                    keyLevel.put(moved.key, i + 1);
                } else {
                    keyLevel.remove(moved.key);
                    nodes.remove(moved.key);
                }
            }
        }
    }

    public List<Integer> levelKeys(int idx) {
        return levels[idx].keys();
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem(2, 5, 10);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println("L1: " + cache.levelKeys(0));
        System.out.println("L2: " + cache.levelKeys(1));
        System.out.println("L3: " + cache.levelKeys(2));

        cache.get(1);
        cache.get(2);
        cache.get(2);
        cache.get(2);
        System.out.println("L1: " + cache.levelKeys(0));
        System.out.println("L2: " + cache.levelKeys(1));
        System.out.println("L3: " + cache.levelKeys(2));

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        System.out.println("L1: " + cache.levelKeys(0));
        System.out.println("L2: " + cache.levelKeys(1));
        System.out.println("L3: " + cache.levelKeys(2));
    }
}

