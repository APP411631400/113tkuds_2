package finalexam;

// 檔名：LC11_MaxArea_FuelHoliday.java
// 題意：給 heights[0..n-1]，選 i<j 使 (j-i) * min(heights[i], heights[j]) 最大，回傳最大值。
// 作法：雙指針夾逼（O(n)）。從最左 L 與最右 R 開始，每步以較短邊為瓶頸：
//      只有移動較短邊，才可能提高下一步的 min 高度，進而改善面積；移動較長邊不會變好。
// 複雜度：時間 O(n)，空間 O(1)。
// 讀入格式：第一行 n；第二行起讀 n 個整數（可能跨多行）。
// 注意：高度最大可至 1e9，距離可至 1e5，面積最高 ~1e14，用 long 承接。

import java.io.*;
import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println(0);
            return;
        }
        int n = Integer.parseInt(line.trim());

        long[] h = new long[n];
        int filled = 0;
        while (filled < n) {
            String ln = br.readLine();
            if (ln == null) break;
            StringTokenizer st = new StringTokenizer(ln);
            while (st.hasMoreTokens() && filled < n) {
                h[filled++] = Long.parseLong(st.nextToken());
            }
        }

        long ans = 0L;
        int L = 0, R = n - 1;
        while (L < R) {
            long width = R - L;
            long height = Math.min(h[L], h[R]);
            ans = Math.max(ans, width * height);

            // 移動較短邊（因為只有它變高才可能提升面積上限）
            if (h[L] <= h[R]) {
                L++;
            } else {
                R--;
            }
        }

        System.out.println(ans);
    }
}
