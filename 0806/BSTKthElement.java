import java.util.*;

public class BSTKthElement {
    static class Node {
        int val, size;
        Node left, right;
        Node(int v) { val = v; size = 1; }
    }

    public static Node insert(Node node, int v) {
        if (node == null) return new Node(v);
        if (v < node.val) node.left = insert(node.left, v);
        else if (v > node.val) node.right = insert(node.right, v);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public static Node delete(Node node, int v) {
        if (node == null) return null;
        if (v < node.val) node.left = delete(node.left, v);
        else if (v > node.val) node.right = delete(node.right, v);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node succ = minNode(node.right);
            node.val = succ.val;
            node.right = delete(node.right, succ.val);
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private static Node minNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private static int size(Node node) {
        return node == null ? 0 : node.size;
    }

    public static int kthSmallest(Node node, int k) {
        if (node == null) throw new NoSuchElementException();
        int leftSize = size(node.left);
        if (k == leftSize + 1) return node.val;
        else if (k <= leftSize) return kthSmallest(node.left, k);
        else return kthSmallest(node.right, k - leftSize - 1);
    }

    public static int kthLargest(Node node, int k) {
        return kthSmallest(node, size(node) - k + 1);
    }

    public static List<Integer> range(Node node, int k, int j) {
        List<Integer> list = new ArrayList<>();
        inorder(node, list);
        if (k < 1) k = 1;
        if (j > list.size()) j = list.size();
        return list.subList(k - 1, j);
    }

    private static void inorder(Node node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    public static void main(String[] args) {
        Node root = null;
        int[] vals = {10,5,15,3,7,12,18};
        for (int v : vals) root = insert(root, v);

        int k = 3, j = 6;
        System.out.println("第" + k + "小元素: " + kthSmallest(root, k));
        System.out.println("第" + k + "大元素: " + kthLargest(root, k));
        System.out.println("第" + k + "小到第" + j + "小之間的元素: " + range(root, k, j));

        root = insert(root, 6);
        System.out.println("插入6後，第4小元素: " + kthSmallest(root, 4));
        root = delete(root, 15);
        System.out.println("刪除15後，第4小元素: " + kthSmallest(root, 4));
    }
}

