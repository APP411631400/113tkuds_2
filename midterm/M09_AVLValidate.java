package midterm;


import java.io.*;
import java.util.*;
public class M09_AVLValidate {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v){ val = v; }
    }

    // 由層序（-1 表 null）建立樹
    static TreeNode buildTree(int[] arr){
        if (arr.length == 0 || arr[0] == -1) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while(!q.isEmpty() && i < arr.length){
            TreeNode cur = q.poll();
            if (i < arr.length && arr[i] != -1){
                cur.left = new TreeNode(arr[i]);
                q.offer(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1){
                cur.right = new TreeNode(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    // 驗證 BST（嚴格不允許相等）
    static boolean isBST(TreeNode node, long min, long max){
        if (node == null) return true;
        if (!(min < node.val && node.val < max)) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    // 驗證 AVL：回傳高度；若不平衡回傳特殊值 INVALID
    static final int INVALID = -2; // 因為空樹高度可設為 -1，故 -2 代表不合法
    static int heightIfAVL(TreeNode node){
        if (node == null) return -1;
        int lh = heightIfAVL(node.left);
        if (lh == INVALID) return INVALID;
        int rh = heightIfAVL(node.right);
        if (rh == INVALID) return INVALID;
        if (Math.abs(lh - rh) > 1) return INVALID;
        return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] parts = br.readLine().trim().split("\\s+");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(parts[i]);

        TreeNode root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }
        if (heightIfAVL(root) == INVALID) {
            System.out.println("Invalid AVL");
            return;
        }
        System.out.println("Valid");
    }
}

/*
 * Time Complexity: O(n)
 * 說明：建樹 O(n)；檢查 BST 以上下界遞迴各節點一次；檢查 AVL 以後序傳回高度並即時剪枝，各 O(n)。
 */
