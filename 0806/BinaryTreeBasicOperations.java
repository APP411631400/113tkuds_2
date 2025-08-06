public class BinaryTreeBasicOperations {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.left = new Node(12);
        root.right.right = new Node(17);

        int sum = sumNodes(root);
        int count = countNodes(root);
        double avg = (double) sum / count;
        int max = findMax(root);
        int min = findMin(root);
        int width = treeWidth(root);
        boolean full = isFull(root);

        System.out.println("節點總和: " + sum);
        System.out.printf("平均值: %.2f%n", avg);
        System.out.println("最大值: " + max + "，最小值: " + min);
        System.out.println("最大寬度: " + width);
        System.out.println("是否為完全二元樹: " + (full ? "是" : "否"));
    }

    static int sumNodes(Node n) {
        if (n == null) return 0;
        return n.val + sumNodes(n.left) + sumNodes(n.right);
    }

    static int countNodes(Node n) {
        if (n == null) return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }

    static int findMax(Node n) {
        if (n == null) return Integer.MIN_VALUE;
        int left = findMax(n.left);
        int right = findMax(n.right);
        return Math.max(n.val, Math.max(left, right));
    }

    static int findMin(Node n) {
        if (n == null) return Integer.MAX_VALUE;
        int left = findMin(n.left);
        int right = findMin(n.right);
        return Math.min(n.val, Math.min(left, right));
    }

    static int treeWidth(Node root) {
        if (root == null) return 0;
        java.util.Queue<Node> q = new java.util.LinkedList<>();
        q.add(root);
        int maxW = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            maxW = Math.max(maxW, sz);
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }
        }
        return maxW;
    }

    static boolean isFull(Node n) {
        if (n == null) return true;
        if (n.left == null && n.right == null) return true;
        if (n.left != null && n.right != null)
            return isFull(n.left) && isFull(n.right);
        return false;
    }
}

