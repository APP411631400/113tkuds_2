import java.util.*;

public class MergeKSortedArrays {
    static class Node {
        int val, ai, bi;
        Node(int v, int a, int b) { val = v; ai = a; bi = b; }
    }

    public static int[] merge(int[][] arrays) {
        if (arrays == null || arrays.length == 0) return new int[0];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        int total = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null && arrays[i].length > 0) {
                pq.offer(new Node(arrays[i][0], i, 0));
                total += arrays[i].length;
            }
        }
        int[] res = new int[total];
        int k = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            res[k++] = cur.val;
            int ni = cur.bi + 1;
            if (ni < arrays[cur.ai].length) {
                pq.offer(new Node(arrays[cur.ai][ni], cur.ai, ni));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] a = {{1,4,5},{1,3,4},{2,6}};
        int[][] b = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] c = {{1},{0}};
        System.out.println(Arrays.toString(merge(a)));
        System.out.println(Arrays.toString(merge(b)));
        System.out.println(Arrays.toString(merge(c)));
    }
}

