import java.util.*;

public class BSTConversionAndBalance {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    private static Node dllHead, dllPrev;
    private static int accSum;

    public static void main(String[] args) {
        int[] vals = {10, 5, 15, 3, 7, 12, 18};
        Node root = null;
        for (int v : vals) root = insert(root, v);

        System.out.print("原始中序: ");
        printInOrder(root);
        System.out.println();

        Node dllRoot = copyTree(root);
        Node head = bstToDoublyList(dllRoot);
        System.out.print("排序雙向鍊結串列: ");
        for (Node cur = head; cur != null; cur = cur.right) {
            System.out.print(cur.val + (cur.right != null ? " <-> " : ""));
        }
        System.out.println();

        List<Integer> keys = new ArrayList<>();
        inorderCollect(root, keys);
        Node balanced = sortedArrayToBST(keys, 0, keys.size() - 1);
        System.out.println("檢查平衡因子：");
        boolean isBal = checkBalance(balanced) != -1;
        System.out.println("是否為平衡BST: " + (isBal ? "是" : "否"));

        Node greater = copyTree(root);
        convertToGreaterTree(greater);
        System.out.print("Greater Sum Tree中序: ");
        printInOrder(greater);
        System.out.println();
    }

    public static Node insert(Node node, int v) {
        if (node == null) return new Node(v);
        if (v < node.val) node.left = insert(node.left, v);
        else if (v > node.val) node.right = insert(node.right, v);
        return node;
    }

    public static Node copyTree(Node n) {
        if (n == null) return null;
        Node c = new Node(n.val);
        c.left = copyTree(n.left);
        c.right = copyTree(n.right);
        return c;
    }

    public static Node bstToDoublyList(Node root) {
        dllHead = dllPrev = null;
        buildDLL(root);
        return dllHead;
    }

    private static void buildDLL(Node n) {
        if (n == null) return;
        buildDLL(n.left);
        if (dllPrev == null) dllHead = n;
        else dllPrev.right = n;
        n.left = dllPrev;
        dllPrev = n;
        buildDLL(n.right);
    }

    public static void inorderCollect(Node n, List<Integer> list) {
        if (n == null) return;
        inorderCollect(n.left, list);
        list.add(n.val);
        inorderCollect(n.right, list);
    }

    public static Node sortedArrayToBST(List<Integer> a, int l, int r) {
        if (l > r) return null;
        int m = (l + r) / 2;
        Node n = new Node(a.get(m));
        n.left  = sortedArrayToBST(a, l, m - 1);
        n.right = sortedArrayToBST(a, m + 1, r);
        return n;
    }

    public static int checkBalance(Node n) {
        if (n == null) return 0;
        int lh = checkBalance(n.left);
        if (lh == -1) return -1;
        int rh = checkBalance(n.right);
        if (rh == -1) return -1;
        int bf = lh - rh;
        System.out.println("節點 " + n.val + " 平衡因子 = " + bf);
        if (Math.abs(bf) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }

    public static void convertToGreaterTree(Node n) {
        if (n == null) return;
        convertToGreaterTree(n.right);
        accSum += n.val;
        n.val = accSum;
        convertToGreaterTree(n.left);
    }

    public static void printInOrder(Node n) {
        if (n == null) return;
        printInOrder(n.left);
        System.out.print(n.val + " ");
        printInOrder(n.right);
    }
}


