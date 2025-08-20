package midterm;

import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is){ in = is; }
        private int read() throws IOException {
            if (ptr >= len) { len = in.read(buffer); ptr = 0; if (len <= 0) return -1; }
            return buffer[ptr++];
        }
        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do { sb.append((char)c); c = read(); } while (c != -1 && c > ' ');
            return sb.toString();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }

    static int toMinutes(String t){
        String[] p = t.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }
    static String toHHMM(int m){
        int h = m / 60, mm = m % 60;
        return String.format("%02d:%02d", h, mm);
    }

    static class Node {
        int time, which, idx;
        Node(int t, int w, int i){ time=t; which=w; idx=i; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int K = Integer.parseInt(fs.next());     // 列表數

        List<int[]> lists = new ArrayList<>();
        boolean useHHMM = false;

        for (int i = 0; i < K; i++) {
            int len = Integer.parseInt(fs.next());
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                String tok = fs.next();
                if (tok.indexOf(':') >= 0) {
                    useHHMM = true;
                    arr[j] = toMinutes(tok);
                } else {
                    arr[j] = Integer.parseInt(tok);
                }
            }
            lists.add(arr);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override public int compare(Node a, Node b) {
                if (a.time != b.time) return a.time - b.time;
                if (a.which != b.which) return a.which - b.which; // 穩定：來源較小者先
                return a.idx - b.idx;                              // 同來源依原順序
            }
        });

        for (int i = 0; i < K; i++) {
            int[] arr = lists.get(i);
            if (arr.length > 0) pq.offer(new Node(arr[0], i, 0));
        }

        StringBuilder out = new StringBuilder();
        boolean first = true;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!first) out.append(' ');
            first = false;
            out.append(useHHMM ? toHHMM(cur.time) : Integer.toString(cur.time));

            int ni = cur.idx + 1;
            int[] arr = lists.get(cur.which);
            if (ni < arr.length) pq.offer(new Node(arr[ni], cur.which, ni));
        }

        System.out.println(out.toString());
    }
}
