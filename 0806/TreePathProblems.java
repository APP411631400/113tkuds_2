import java.util.*;

public class TreePathProblems {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        List<List<Integer>> paths = allPaths(root);
        System.out.println("所有根到葉路徑: " + paths);

        int target = 7;
        System.out.println("存在根到葉等於" + target + "的路徑: " + hasPathSum(root, target));

        int maxRootSum = maxRootToLeafSum(root);
        System.out.println("最大根到葉路徑和: " + maxRootSum);

        int maxAny = maxPathSum(root);
        System.out.println("任意兩節點間最大路徑和: " + maxAny);
    }

    public static List<List<Integer>> allPaths(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        dfsPaths(root, new ArrayList<>(), res);
        return res;
    }

    private static void dfsPaths(Node n, List<Integer> cur, List<List<Integer>> res) {
        if (n == null) return;
        cur.add(n.val);
        if (n.left == null && n.right == null) {
            res.add(new ArrayList<>(cur));
        } else {
            dfsPaths(n.left, cur, res);
            dfsPaths(n.right, cur, res);
        }
        cur.remove(cur.size() - 1);
    }

    public static boolean hasPathSum(Node root, int target) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == target;
        return hasPathSum(root.left, target - root.val)
            || hasPathSum(root.right, target - root.val);
    }

    public static int maxRootToLeafSum(Node root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;
        int left = maxRootToLeafSum(root.left);
        int right = maxRootToLeafSum(root.right);
        return root.val + Math.max(left, right);
    }

    static int maxSum;
    public static int maxPathSum(Node root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    private static int maxGain(Node node) {
        if (node == null) return 0;
        int left = Math.max(0, maxGain(node.left));
        int right = Math.max(0, maxGain(node.right));
        int price = node.val + left + right;
        maxSum = Math.max(maxSum, price);
        return node.val + Math.max(left, right);
    }
}

