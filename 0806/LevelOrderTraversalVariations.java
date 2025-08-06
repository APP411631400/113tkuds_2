import java.util.*;

public class LevelOrderTraversalVariations {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left  = new Node(2);
        root.right = new Node(3);
        root.left.left   = new Node(4);
        root.left.right  = new Node(5);
        root.right.left  = new Node(6);
        root.right.right = new Node(7);

        List<List<Integer>> byLevel = groupByLevel(root);
        System.out.println("各層節點: " + byLevel);

        List<Integer> zigzag = zigzagOrder(root);
        System.out.println("Z 字形遍歷: " + zigzag);

        List<Integer> lastOfEach = rightmostOfEachLevel(root);
        System.out.println("每層最後一個節點: " + lastOfEach);

        List<List<Integer>> vertical = verticalOrder(root);
        System.out.println("垂直層序分組: " + vertical);
    }

    public static List<List<Integer>> groupByLevel(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                Node n = q.poll();
                layer.add(n.val);
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
            res.add(layer);
        }
        return res;
    }

    public static List<Integer> zigzagOrder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            int sz = q.size();
            LinkedList<Integer> layer = new LinkedList<>();
            for (int i = 0; i < sz; i++) {
                Node n = q.poll();
                if (leftToRight) layer.addLast(n.val);
                else layer.addFirst(n.val);
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
            res.addAll(layer);
            leftToRight = !leftToRight;
        }
        return res;
    }

    public static List<Integer> rightmostOfEachLevel(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            Node last = null;
            for (int i = 0; i < sz; i++) {
                last = q.poll();
                if (last.left != null) q.add(last.left);
                if (last.right != null) q.add(last.right);
            }
            res.add(last.val);
        }
        return res;
    }

    public static List<List<Integer>> verticalOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Node> nq = new LinkedList<>();
        Queue<Integer> cq = new LinkedList<>();
        nq.add(root);
        cq.add(0);
        while (!nq.isEmpty()) {
            Node n = nq.poll();
            int col = cq.poll();
            map.computeIfAbsent(col, k -> new ArrayList<>()).add(n.val);
            if (n.left != null) {
                nq.add(n.left);
                cq.add(col - 1);
            }
            if (n.right != null) {
                nq.add(n.right);
                cq.add(col + 1);
            }
        }
        for (List<Integer> list : map.values()) {
            res.add(list);
        }
        return res;
    }
}

