import java.util.*;

class LeaderboardNode {
    int score;
    String name;
    int height;
    int size;
    LeaderboardNode left, right;

    LeaderboardNode(String name, int score) {
        this.name = name;
        this.score = score;
        this.height = 1;
        this.size = 1;
    }
}

public class AVLLeaderboardSystem {
    private LeaderboardNode root;
    private Map<String, Integer> playerScores = new HashMap<>();

    private int height(LeaderboardNode node) {
        return (node != null) ? node.height : 0;
    }

    private int size(LeaderboardNode node) {
        return (node != null) ? node.size : 0;
    }

    private void update(LeaderboardNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        node.size = size(node.left) + size(node.right) + 1;
    }

    private int getBalance(LeaderboardNode node) {
        return (node != null) ? height(node.left) - height(node.right) : 0;
    }

    private LeaderboardNode rightRotate(LeaderboardNode y) {
        LeaderboardNode x = y.left;
        LeaderboardNode T2 = x.right;

        x.right = y;
        y.left = T2;

        update(y);
        update(x);

        return x;
    }

    private LeaderboardNode leftRotate(LeaderboardNode x) {
        LeaderboardNode y = x.right;
        LeaderboardNode T2 = y.left;

        y.left = x;
        x.right = T2;

        update(x);
        update(y);

        return y;
    }

    private LeaderboardNode insert(LeaderboardNode node, String name, int score) {
        if (node == null) return new LeaderboardNode(name, score);

        if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
            node.left = insert(node.left, name, score);
        } else {
            node.right = insert(node.right, name, score);
        }

        update(node);

        int balance = getBalance(node);

        if (balance > 1 && score > node.left.score) return rightRotate(node);
        if (balance < -1 && score <= node.right.score) return leftRotate(node);
        if (balance > 1 && score <= node.left.score) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && score > node.right.score) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private LeaderboardNode delete(LeaderboardNode node, String name, int score) {
        if (node == null) return null;

        if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
            node.left = delete(node.left, name, score);
        } else if (score < node.score || (score == node.score && name.compareTo(node.name) > 0)) {
            node.right = delete(node.right, name, score);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                LeaderboardNode temp = getMax(node.left);
                node.name = temp.name;
                node.score = temp.score;
                node.left = delete(node.left, temp.name, temp.score);
            }
        }

        if (node == null) return null;

        update(node);

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private LeaderboardNode getMax(LeaderboardNode node) {
        while (node.right != null) node = node.right;
        return node;
    }

    public void addScore(String name, int score) {
        if (playerScores.containsKey(name)) {
            int oldScore = playerScores.get(name);
            root = delete(root, name, oldScore);
        }
        playerScores.put(name, score);
        root = insert(root, name, score);
    }

    public void updateScore(String name, int score) {
        addScore(name, score);
    }

    public int getRank(String name) {
        if (!playerScores.containsKey(name)) return -1;
        return getRank(root, name, playerScores.get(name)) + 1;
    }

    private int getRank(LeaderboardNode node, String name, int score) {
        if (node == null) return 0;

        if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
            return getRank(node.left, name, score);
        } else if (score < node.score || (score == node.score && name.compareTo(node.name) > 0)) {
            return size(node.left) + 1 + getRank(node.right, name, score);
        } else {
            return size(node.left);
        }
    }

    public List<String> getTopK(int k) {
        List<String> result = new ArrayList<>();
        getTopK(root, result, k);
        return result;
    }

    private void getTopK(LeaderboardNode node, List<String> result, int k) {
        if (node == null || result.size() >= k) return;

        getTopK(node.left, result, k);
        if (result.size() < k) result.add(node.name);
        getTopK(node.right, result, k);
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();
        leaderboard.addScore("Amy", 50);
        leaderboard.addScore("Bob", 70);
        leaderboard.addScore("Cathy", 60);
        leaderboard.addScore("David", 80);

        System.out.println(leaderboard.getRank("Bob"));    // 2
        System.out.println(leaderboard.getTopK(3));        // [David, Bob, Cathy]

        leaderboard.updateScore("Amy", 90);
        System.out.println(leaderboard.getRank("Amy"));    // 1
        System.out.println(leaderboard.getTopK(2));        // [Amy, David]
    }
}

