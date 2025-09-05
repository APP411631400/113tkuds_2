package finalexam;

// 檔名：LC26_RemoveDuplicates_Scores.java
// 題意：已排序的整數陣列，移除重複，使每個值僅出現一次，就地修改並輸出新長度與內容。
// 作法：雙指針。write 指向可以寫的位置，從 i=1 開始掃描；若當前值 != 前一個保留值 → 寫入並前進 write。
// 複雜度：時間 O(n)，空間 O(1) 額外。

import java.io.*;
import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println(0);
            return;
        }
        int n = Integer.parseInt(line.trim());
        if (n == 0) {
            System.out.println(0);
            return;
        }

        int[] arr = new int[n];
        int filled = 0;
        while (filled < n) {
            String ln = br.readLine();
            if (ln == null) break;
            StringTokenizer st = new StringTokenizer(ln);
            while (st.hasMoreTokens() && filled < n) {
                arr[filled++] = Integer.parseInt(st.nextToken());
            }
        }

        int len = removeDuplicates(arr, n);

        // 輸出新長度
        System.out.println(len);

        // 輸出前段結果
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < len; i++) {
            out.append(arr[i]);
            if (i < len - 1) out.append(' ');
        }
        System.out.println(out.toString());
    }

    private static int removeDuplicates(int[] nums, int n) {
        if (n == 0) return 0;
        int write = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[write - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }
}

