package finalexam;

// 檔名：LC15_3Sum_THSRStops.java
// 題意：給定整數陣列，找出所有「和為 0」的三元組，輸出不重複且遞增的解。
// 作法：排序 + 固定 i + 兩指針（L、R）往中間夾逼找 target = -nums[i]
//      去重策略：i 跳過相同值；每次找到解後，L 與 R 皆略過重複值。
// 複雜度：時間 O(n^2)，空間 O(1)（不計輸出）。
// 輸入：第一行 n；後續讀滿 n 個整數（可能跨多行）。
// 輸出：每行一組「a b c」，若無解則不輸出任何行。

import java.io.*;
import java.util.*;

public class LC15_3Sum_THSRStops {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        int n = Integer.parseInt(line.trim());

        int[] a = new int[n];
        int filled = 0;
        while (filled < n) {
            String ln = br.readLine();
            if (ln == null) break;
            StringTokenizer st = new StringTokenizer(ln);
            while (st.hasMoreTokens() && filled < n) {
                a[filled++] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(a);
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            // 若當前基準已 > 0，後面皆 >= 基準 → 不可能和為 0
            if (a[i] > 0) break;

            // 跳過重複基準
            if (i > 0 && a[i] == a[i - 1]) continue;

            int L = i + 1, R = n - 1;
            while (L < R) {
                int sum = a[i] + a[L] + a[R];
                if (sum == 0) {
                    out.append(a[i]).append(' ').append(a[L]).append(' ').append(a[R]).append('\n');

                    // 略過 L 的重複值
                    int vL = a[L];
                    while (L < R && a[L] == vL) L++;

                    // 略過 R 的重複值
                    int vR = a[R];
                    while (L < R && a[R] == vR) R--;
                } else if (sum < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }

        // 若無解，依題意不輸出任何行
        System.out.print(out.toString());
    }
}

