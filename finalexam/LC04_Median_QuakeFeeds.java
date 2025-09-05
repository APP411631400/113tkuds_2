package finalexam;

// 檔名：LC04_Median_QuakeFeeds.java
// 題意：給兩個「各自已排序」的數列，求聯合集的中位數（偶數長度取中間兩數平均），不可直接合併。
// 作法：在較短陣列做二分切割 i，令左半元素數量 = (n+m+1)/2。
//      檢查 Aleft<=Bright 且 Bleft<=Aright 成立即找到正確切割；再依奇偶長度取值。
// 複雜度：時間 O(log(min(n,m)))；空間 O(1)

import java.io.*;
import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 讀 n, m
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 讀 n 個浮點數（可能跨多行）
        double[] A = new double[n];
        int filled = 0;
        while (filled < n) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens() && filled < n) {
                A[filled++] = Double.parseDouble(st.nextToken());
            }
        }

        // 讀 m 個浮點數（可能跨多行）
        double[] B = new double[m];
        filled = 0;
        while (filled < m) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens() && filled < m) {
                B[filled++] = Double.parseDouble(st.nextToken());
            }
        }

        // 確保 A 是較短陣列
        if (A.length > B.length) {
            double[] tmp = A; A = B; B = tmp;
            int t = n; n = m; m = t;
        }

        // 邊界：若較短為 0，直接從另一個取中位
        if (n == 0) {
            double median = medianOfSingleSorted(B);
            System.out.println(String.format(Locale.US, "%.1f", median));
            return;
        }

        int totalLeft = (n + m + 1) / 2;
        int lo = 0, hi = n;

        final double INF = Double.POSITIVE_INFINITY;
        final double NINF = Double.NEGATIVE_INFINITY;

        while (lo <= hi) {
            int i = (lo + hi) >>> 1;       // A 左半取 i 個
            int j = totalLeft - i;         // B 左半取 j 個

            double Aleft  = (i == 0) ? NINF : A[i - 1];
            double Aright = (i == n) ? INF  : A[i];
            double Bleft  = (j == 0) ? NINF : B[j - 1];
            double Bright = (j == m) ? INF  : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                // 找到正確切割
                if (((n + m) & 1) == 1) {
                    double median = Math.max(Aleft, Bleft);
                    System.out.println(String.format(Locale.US, "%.1f", median));
                } else {
                    double median = (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                    System.out.println(String.format(Locale.US, "%.1f", median));
                }
                return;
            } else if (Aleft > Bright) {
                // i 太大，往左縮
                hi = i - 1;
            } else {
                // Bleft > Aright → i 太小，往右擴
                lo = i + 1;
            }
        }

        // 正常不會到這裡（輸入已排序且 n+m>=1）
        System.out.println("0.0");
    }

    // 單一已排序陣列的中位數
    private static double medianOfSingleSorted(double[] arr) {
        int len = arr.length;
        if ((len & 1) == 1) return arr[len / 2];
        return (arr[len / 2 - 1] + arr[len / 2]) / 2.0;
    }
}

