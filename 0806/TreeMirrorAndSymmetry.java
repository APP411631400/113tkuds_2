import java.util.*;

public class TreeMirrorAndSymmetry {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) {
        // 建立範例樹
        Node root = new Node(1);
        root.left  = new Node(2);
        root.right = new Node(2);
        root.left.left  = new Node(3);
        root.left.right = new Node(4);
        root.right.left  = new Node(4);
        root.right.right = new Node(3);

        System.out.println("原樹是否對稱: " + isSymmetric(root));

        mirror(root);
        System.out.print("鏡像後先序遍歷: ");
        preorder(root);
        System.out.println();

        // 還原成原本樹做比較
        Node other = new Node(1);
        other.left  = new Node(2);
        other.right = new Node(2);
        other.left.left  = new Node(3);
        other.left.right = new Node(4);
        other.right.left  = new Node(4);
        other.right.right = new Node(3);

        System.out.println("鏡像樹與原樹是否互為鏡像: " + isMirror(root, other));

        // 子樹測試
        Node sub = new Node(2);
        sub.left = new Node(3);
        sub.right = new Node(4);
        System.out.println("原樹是否含有該子樹: " + isSubtree(other, sub));
    }

    public static boolean isSymmetric(Node root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public static void mirror(Node node) {
        if (node == null) return;
        Node tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        mirror(node.left);
        mirror(node.right);
    }

    public static boolean isMirror(Node a, Node b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val
            && isMirror(a.left, b.right)
            && isMirror(a.right, b.left);
    }

    public static boolean isSubtree(Node root, Node sub) {
        if (root == null) return false;
        if (isSame(root, sub)) return true;
        return isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }

    private static boolean isSame(Node a, Node b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val
            && isSame(a.left, b.left)
            && isSame(a.right, b.right);
    }

    public static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        preorder(node.left);
        preorder(node.right);
    }
}

