import java.util.*;
public class AVLRangeQueryExercise {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);
        tree.insert(25);
        tree.insert(35);

        List<Integer> result = rangeQuery(tree, 12, 28);
        System.out.println("範圍查詢結果 (12~28): " + result);
    }

    public static List<Integer> rangeQuery(AVLTree tree, int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(tree.getRoot(), min, max, result);
        return result;
    }

    private static void rangeQueryHelper(AVLNode node, int min, int max, List<Integer> result) {
        if (node == null) return;

        if (node.data > min) {
            rangeQueryHelper(node.left, min, max, result);
        }

        if (node.data >= min && node.data <= max) {
            result.add(node.data);
        }

        if (node.data < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }
}
