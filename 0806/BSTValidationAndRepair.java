import java.util.*;

public class BSTValidationAndRepair {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) {
        // 範例建樹（包含交換錯誤節點）
        Node root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right.right = new Node(14);
        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);
        // 交換 6 與 10，造成 BST 違規
        int tmp = root.left.right.val;
        root.left.right.val = root.right.val;
        root.right.val = tmp;

        System.out.println("驗證結果: " + isValidBST(root));
        List<Node> bad = findInvalidNodes(root);
        System.out.print("不合法節點值: ");
        for (Node n : bad) System.out.print(n.val + " ");
        System.out.println();

        recoverTree(root);
        System.out.println("修復後驗證: " + isValidBST(root));

        int total = countNodes(root);
        int largestBST = largestBSTSize(root).maxBST;
        int removals = total - largestBST;
        System.out.println("總節點數: " + total + "，最大子BST節點數: " + largestBST +
                           "，最少移除: " + removals);
    }

    public static boolean isValidBST(Node node) {
        return isValid(node, null, null);
    }

    private static boolean isValid(Node n, Integer min, Integer max) {
        if (n == null) return true;
        if (min != null && n.val <= min) return false;
        if (max != null && n.val >= max) return false;
        return isValid(n.left, min, n.val) && isValid(n.right, n.val, max);
    }

    public static List<Node> findInvalidNodes(Node root) {
        List<Node> res = new ArrayList<>();
        dfsInvalid(root, null, null, res);
        return res;
    }

    private static void dfsInvalid(Node n, Integer min, Integer max, List<Node> res) {
        if (n == null) return;
        boolean ok = true;
        if (min != null && n.val <= min) ok = false;
        if (max != null && n.val >= max) ok = false;
        if (!ok) res.add(n);
        dfsInvalid(n.left, min, Math.min(max == null ? n.val : max, n.val), res);
        dfsInvalid(n.right, Math.max(min == null ? n.val : min, n.val), max, res);
    }

    static Node first, second, prev;
    public static void recoverTree(Node root) {
        first = second = prev = null;
        inorderRecover(root);
        if (first != null && second != null) {
            int t = first.val;
            first.val = second.val;
            second.val = t;
        }
    }

    private static void inorderRecover(Node n) {
        if (n == null) return;
        inorderRecover(n.left);
        if (prev != null && prev.val > n.val) {
            if (first == null) first = prev;
            second = n;
        }
        prev = n;
        inorderRecover(n.right);
    }

    private static int countNodes(Node n) {
        if (n == null) return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }

    private static class Info {
        boolean isBST;
        int size, min, max, maxBST;
        Info(boolean b, int sz, int mi, int ma, int mB) {
            isBST = b; size = sz; min = mi; max = ma; maxBST = mB;
        }
    }

    private static Info largestBSTSize(Node n) {
        if (n == null) return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        Info L = largestBSTSize(n.left);
        Info R = largestBSTSize(n.right);
        int sz = L.size + R.size + 1;
        int mi = Math.min(n.val, L.min);
        int ma = Math.max(n.val, R.max);
        boolean bst = L.isBST && R.isBST && n.val > L.max && n.val < R.min;
        int mB = bst ? sz : Math.max(L.maxBST, R.maxBST);
        return new Info(bst, sz, mi, ma, mB);
    }
}

