package midterm;

import java.io.*;
import java.util.*;

public class M11_HeapSortWithTie {
    static int[] a;      // 分數
    static int[] idx;    // 原始索引（0-based）
    static int n;

    // 回傳 i 是否「比」 j 大（用於 Max-Heap）
    static boolean greater(int i, int j) {
        if (a[i] != a[j]) return a[i] > a[j];
        return idx[i] > idx[j]; // 分數相同：索引較大者視為較大（先放右邊）
    }

    static void swap(int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
        int ti = idx[i]; idx[i] = idx[j]; idx[j] = ti;
    }

    static void heapifyDown(int i, int size) {
        while (true) {
            int l = i * 2 + 1, r = i * 2 + 2, best = i;
            if (l < size && greater(l, best)) best = l;
            if (r < size && greater(r, best)) best = r;
            if (best == i) break;
            swap(i, best);
            i = best;
        }
    }

    static void buildHeap() {
        for (int i = n / 2 - 1; i >= 0; i--) heapifyDown(i, n);
    }

    static void heapSort() {
        buildHeap();
        for (int end = n - 1; end > 0; end--) {
            swap(0, end);             // 最大的放到右端
            heapifyDown(0, end);      // 縮小堆
        }
        // 現在 a[] 已是遞增序，且同分時索引較小者在前
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        a = new int[n];
        idx = new int[n];

        String[] parts = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(parts[i]);
            idx[i] = i;
        }

        heapSort();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：以自底向上建 Max-Heap O(n)，每次取最大並下濾 O(log n)，共 n 次 → O(n log n)。
 * 平手規則：分數相同時，以索引較小者優先（輸入較早者）。為了讓最終遞增序滿足此規則，
 *          在 Max-Heap 的比較中，分數相同時「索引較大者視為較大」先被放到右端。
 */
