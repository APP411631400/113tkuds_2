package finalexam;

// 檔名：LC34_SearchRange_DelaySpan.java
// 題意：在已排序（非遞減）的整數陣列中，找出 target 首次與最後一次出現的索引；若不存在輸出 -1 -1。
// 作法：雙二分。
//   - left = lower_bound(target)  → 第一個 >= target 的位置
//   - 若越界或 a[left] != target → 不存在
//   - right = upper_bound(target) - 1 → 最後一個 <= target 的位置
// 複雜度：時間 O(log n)，空間 O(1)

import java.io.*;
import java.util.*;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 讀 n 與 target
        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) {
            System.out.println("-1 -1");
            return;
        }
        st = new StringTokenizer(first);
        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        // 讀 n 個整數（可能跨多行）
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

        if (n == 0) {
            System.out.println("-1 -1");
            return;
        }

        int left = lowerBound(a, target);
        if (left == n || a[left] != target) {
            System.out.println("-1 -1");
            return;
        }
        int right = upperBound(a, target) - 1;
        System.out.println(left + " " + right);
    }

    // 第一個 >= x 的索引；若都小於 x 則回 n
    private static int lowerBound(int[] a, int x) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int mid = l + ((r - l) >>> 1);
            if (a[mid] >= x) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // 第一個 > x 的索引；若都 <= x 則回 n
    private static int upperBound(int[] a, int x) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int mid = l + ((r - l) >>> 1);
            if (a[mid] > x) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}

