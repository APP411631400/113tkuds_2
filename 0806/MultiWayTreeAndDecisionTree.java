import java.util.*;

public class MultiWayTreeAndDecisionTree {
    static class Node {
        String name;
        List<Node> children = new ArrayList<>();
        Node(String n) { name = n; }
        void add(Node c) { children.add(c); }
    }

    public static void main(String[] args) {
        // 建立多路樹範例
        Node root = new Node("根");
        Node a = new Node("A"); Node b = new Node("B"); Node c = new Node("C");
        root.add(a); root.add(b); root.add(c);
        a.add(new Node("A1")); a.add(new Node("A2"));
        b.add(new Node("B1"));
        c.add(new Node("C1")); c.add(new Node("C2")); c.add(new Node("C3"));

        System.out.println("深度優先遍歷：");
        dfs(root);
        System.out.println("\n廣度優先遍歷：");
        bfs(root);

        System.out.println("\n多路樹高度：" + height(root));
        System.out.println("各節點度數：");
        printDegrees(root);

        System.out.println("\n開始猜數字遊戲（1-100）：");
        guessNumber();
    }

    static void dfs(Node n) {
        System.out.print(n.name + " ");
        for (Node c : n.children) dfs(c);
    }

    static void bfs(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node n = q.poll();
            System.out.print(n.name + " ");
            for (Node c : n.children) q.add(c);
        }
    }

    static int height(Node n) {
        int h = 0;
        for (Node c : n.children) h = Math.max(h, height(c));
        return h + 1;
    }

    static void printDegrees(Node n) {
        System.out.println(n.name + " 的度 = " + n.children.size());
        for (Node c : n.children) printDegrees(c);
    }

    static void guessNumber() {
        Scanner sc = new Scanner(System.in);
        int secret = new Random().nextInt(100) + 1;
        int guess, attempts = 0;
        do {
            System.out.print("請猜一個數字：");
            guess = sc.nextInt();
            attempts++;
            if (guess < secret) System.out.println("太小了！");
            else if (guess > secret) System.out.println("太大了！");
        } while (guess != secret);
        System.out.println("恭喜猜對！答案是 " + secret + "，共嘗試 " + attempts + " 次。");
    }
}

