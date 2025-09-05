package finalexam;

// 檔名：LC18_4Sum_Procurement.java
// 題意：給定 n 個整數與 target，輸出所有不重複升序四元組 (a<=b<=c<=d)，使 a+b+c+d = target。
// 作法：排序後固定 i、j，內層以兩指針 L、R 尋找；全程嚴格去重。
// 注意：數值與 target 可能達 |1e9|，四數和跨到 |4e9|，比較時請用 long。
// 複雜度：時間 O(n^3)，空間 O(1)（不計輸出）。


import java.io.*;
import java.util.*;

public class LC18_4Sum_Procurement {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 讀 n 與 target
        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) return;
        st = new StringTokenizer(first);
        int n = Integer.parseInt(st.nextToken());
        long target = Long.parseLong(st.nextToken());

        // 讀 n 個整數（可能跨行）
        int[] a = new int[n];
        int filled = 0;
        while (filled < n) {
            String line = br.readLine();
            if (line == null) break;
            st = new StringTokenizer(line);
            while (st.hasMoreTokens() && filled < n) {
                a[filled++] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(a);
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (i > 0 && a[i] == a[i - 1]) continue; // 去重 i
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && a[j] == a[j - 1]) continue; // 去重 j

                int L = j + 1, R = n - 1;
                while (L < R) {
                    long sum = (long)a[i] + a[j] + a[L] + a[R];
                    if (sum == target) {
                        out.append(a[i]).append(' ')
                           .append(a[j]).append(' ')
                           .append(a[L]).append(' ')
                           .append(a[R]).append('\n');

                        // L 去重
                        int vL = a[L];
                        while (L < R && a[L] == vL) L++;
                        // R 去重
                        int vR = a[R];
                        while (L < R && a[R] == vR) R--;
                    } else if (sum < target) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }

        // 無解時不輸出任何行
        System.out.print(out.toString());
    }
}

