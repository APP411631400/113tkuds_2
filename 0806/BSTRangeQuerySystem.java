import java.util.*;

public class BSTRangeQuerySystem {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) {
        int[] vals = {10, 5, 15, 3, 7, 12, 18, 6, 8};
        Node root = null;
        for (int v : vals) root = insert(root, v);

        int min = 6, max = 17, target = 11;
        List<Integer> inRange = rangeQuery(root, min, max);
        System.out.println("範圍 [" + min + "," + max + "] 內的節點: " + inRange);
        System.out.println("節點數量: " + inRange.size());
        int sum = inRange.stream().mapToInt(Integer::intValue).sum();
        System.out.println("節點總和: " + sum);
        int closest = findClosest(root, target);
        System.out.println("最接近 " + target + " 的節點: " + closest);
    }

    public static Node insert(Node node, int v) {
        if (node == null) return new Node(v);
        if (v < node.val) node.left = insert(node.left, v);
        else if (v > node.val) node.right = insert(node.right, v);
        return node;
    }

    public static List<Integer> rangeQuery(Node node, int min, int max) {
        List<Integer> res = new ArrayList<>();
        traverse(node, min, max, res);
        return res;
    }

    private static void traverse(Node node, int min, int max, List<Integer> res) {
        if (node == null) return;
        if (node.val > min) traverse(node.left, min, max, res);
        if (node.val >= min && node.val <= max) res.add(node.val);
        if (node.val < max) traverse(node.right, min, max, res);
    }

    public static int findClosest(Node node, int target) {
        int closest = node.val;
        Node cur = node;
        while (cur != null) {
            if (Math.abs(cur.val - target) < Math.abs(closest - target)) {
                closest = cur.val;
            }
            if (target < cur.val) cur = cur.left;
            else if (target > cur.val) cur = cur.right;
            else break;
        }
        return closest;
    }
}

