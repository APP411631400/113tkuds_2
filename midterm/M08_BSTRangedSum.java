package midterm;

import java.io.*;
import java.util.*;
public class M08_BSTRangedSum {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 由層序（-1 表 null）建立樹
    static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode cur = q.poll();
            if (i < arr.length && arr[i] != -1) {
                cur.left = new TreeNode(arr[i]);
                q.offer(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1) {
                cur.right = new TreeNode(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    static long rangeSumBST(TreeNode node, int L, int R) {
        if (node == null) return 0;
        if (node.val < L) return rangeSumBST(node.right, L, R);
        if (node.val > R) return rangeSumBST(node.left, L, R);
        return node.val + rangeSumBST(node.left, L, R) + rangeSumBST(node.right, L, R);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] parts = br.readLine().trim().split("\\s+");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(parts[i]);
        String[] lr = br.readLine().trim().split("\\s+");
        int L = Integer.parseInt(lr[0]);
        int R = Integer.parseInt(lr[1]);

        TreeNode root = buildTree(arr);
        long sum = rangeSumBST(root, L, R);
        System.out.println("Sum: " + sum);
    }
}
