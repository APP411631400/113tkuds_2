import java.util.*;

public class TreeReconstruction {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder  = {4, 2, 5, 1, 6, 3, 7};
        int[] postorder= {4, 5, 2, 6, 7, 3, 1};
        int[] level    = {1, 2, 3, 4, 5, 6, 7};

        Node root1 = buildFromPreIn(preorder, inorder);
        Node root2 = buildFromPostIn(postorder, inorder);
        Node root3 = buildCompleteFromLevel(level);

        System.out.println("前中重建的中序: " + toList(inorderTraversal(root1)));
        System.out.println("後中重建的中序: " + toList(inorderTraversal(root2)));
        System.out.println("層序重建輸出: " + toList(levelOrder(root3)));

        System.out.println("前中重建正確: " + isSameTree(root1, root3));
        System.out.println("後中重建正確: " + isSameTree(root2, root3));
    }

    public static Node buildFromPreIn(int[] pre, int[] in) {
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < in.length; i++) idx.put(in[i], i);
        return buildPI(pre, 0, pre.length - 1, in, 0, in.length - 1, idx);
    }

    private static Node buildPI(int[] pre, int ps, int pe, int[] in, int is, int ie, Map<Integer,Integer> idx) {
        if (ps > pe) return null;
        int rootVal = pre[ps];
        int ri = idx.get(rootVal);
        int leftSize = ri - is;
        Node root = new Node(rootVal);
        root.left = buildPI(pre, ps + 1, ps + leftSize, in, is, ri - 1, idx);
        root.right= buildPI(pre, ps + leftSize + 1, pe, in, ri + 1, ie, idx);
        return root;
    }

    public static Node buildFromPostIn(int[] post, int[] in) {
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < in.length; i++) idx.put(in[i], i);
        return buildPO(post, 0, post.length - 1, in, 0, in.length - 1, idx);
    }

    private static Node buildPO(int[] post, int ps, int pe, int[] in, int is, int ie, Map<Integer,Integer> idx) {
        if (ps > pe) return null;
        int rootVal = post[pe];
        int ri = idx.get(rootVal);
        int leftSize = ri - is;
        Node root = new Node(rootVal);
        root.left = buildPO(post, ps, ps + leftSize - 1, in, is, ri - 1, idx);
        root.right= buildPO(post, ps + leftSize, pe - 1, in, ri + 1, ie, idx);
        return root;
    }

    public static Node buildCompleteFromLevel(int[] lvl) {
        int n = lvl.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new Node(lvl[i]);
        for (int i = 0; i < n; i++) {
            int l = 2 * i + 1, r = 2 * i + 2;
            if (l < n) nodes[i].left = nodes[l];
            if (r < n) nodes[i].right= nodes[r];
        }
        return n > 0 ? nodes[0] : null;
    }

    private static List<Integer> preorderTraversal(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        res.add(root.val);
        res.addAll(preorderTraversal(root.left));
        res.addAll(preorderTraversal(root.right));
        return res;
    }

    private static List<Integer> inorderTraversal(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        res.addAll(inorderTraversal(root.left));
        res.add(root.val);
        res.addAll(inorderTraversal(root.right));
        return res;
    }

    private static List<Integer> levelOrder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            res.add(cur.val);
            if (cur.left != null) q.add(cur.left);
            if (cur.right!= null) q.add(cur.right);
        }
        return res;
    }

    public static boolean isSameTree(Node a, Node b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    private static String toList(List<Integer> list) {
        return list.toString();
    }
}

