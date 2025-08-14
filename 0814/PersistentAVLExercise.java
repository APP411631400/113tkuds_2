import java.util.*;

class PersistentAVLNode {
    final int key;
    final int height;
    final PersistentAVLNode left;
    final PersistentAVLNode right;
    final int size;

    PersistentAVLNode(int key, PersistentAVLNode left, PersistentAVLNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(height(left), height(right));
        this.size = 1 + size(left) + size(right);
    }

    private int height(PersistentAVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    private int size(PersistentAVLNode node) {
        return (node == null) ? 0 : node.size;
    }
}

public class PersistentAVLExercise {
    private List<PersistentAVLNode> versions = new ArrayList<>();

    private int height(PersistentAVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    private int size(PersistentAVLNode node) {
        return (node == null) ? 0 : node.size;
    }

    private int getBalance(PersistentAVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private PersistentAVLNode rightRotate(PersistentAVLNode y) {
        PersistentAVLNode x = y.left;
        PersistentAVLNode T2 = x.right;
        return new PersistentAVLNode(x.key, x.left, new PersistentAVLNode(y.key, T2, y.right));
    }

    private PersistentAVLNode leftRotate(PersistentAVLNode x) {
        PersistentAVLNode y = x.right;
        PersistentAVLNode T2 = y.left;
        return new PersistentAVLNode(y.key, new PersistentAVLNode(x.key, x.left, T2), y.right);
    }

    private PersistentAVLNode balance(PersistentAVLNode node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                PersistentAVLNode newLeft = leftRotate(node.left);
                node = new PersistentAVLNode(node.key, newLeft, node.right);
            }
            return rightRotate(node);
        }

        if (balance < -1) {
            if (getBalance(node.right) > 0) {
                PersistentAVLNode newRight = rightRotate(node.right);
                node = new PersistentAVLNode(node.key, node.left, newRight);
            }
            return leftRotate(node);
        }

        return node;
    }

    private PersistentAVLNode insert(PersistentAVLNode node, int key) {
        if (node == null) return new PersistentAVLNode(key, null, null);

        if (key < node.key) {
            PersistentAVLNode newLeft = insert(node.left, key);
            node = new PersistentAVLNode(node.key, newLeft, node.right);
        } else {
            PersistentAVLNode newRight = insert(node.right, key);
            node = new PersistentAVLNode(node.key, node.left, newRight);
        }

        return balance(node);
    }

    public void insertVersion(int versionIndex, int key) {
        PersistentAVLNode root = (versionIndex >= 0 && versionIndex < versions.size()) ? versions.get(versionIndex) : null;
        PersistentAVLNode newRoot = insert(root, key);
        versions.add(newRoot);
    }

    public PersistentAVLNode getVersion(int versionIndex) {
        if (versionIndex < 0 || versionIndex >= versions.size()) return null;
        return versions.get(versionIndex);
    }

    public boolean isValidAVL(PersistentAVLNode node) {
        if (node == null) return true;
        int balance = getBalance(node);
        if (balance < -1 || balance > 1) return false;
        if (node.left != null && node.left.key >= node.key) return false;
        if (node.right != null && node.right.key <= node.key) return false;
        return isValidAVL(node.left) && isValidAVL(node.right);
    }

    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();

        tree.insertVersion(-1, 10);
        tree.insertVersion(0, 20);
        tree.insertVersion(1, 30);
        tree.insertVersion(2, 25);
        tree.insertVersion(3, 15);

        for (int i = 0; i < tree.versions.size(); i++) {
            System.out.println("Version " + i + " is valid AVL: " + tree.isValidAVL(tree.getVersion(i)));
        }
    }
}

