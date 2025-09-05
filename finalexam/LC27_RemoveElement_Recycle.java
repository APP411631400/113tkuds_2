package finalexam;

// 檔名：LC27_RemoveElement_Recycle.java
// 題意：給定陣列與一個值 val，就地移除所有等於 val 的元素，並輸出新長度與更新後前段內容。
// 作法：單指針 write。遍歷 nums，若當前元素 != val，則寫入 nums[write++]。
// 複雜度：時間 O(n)，空間 O(1)。

import java.io.*;
import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 讀 n 和 val
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println(0);
            return;
        }
        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int val = Integer.parseInt(st.nextToken());

        if (n == 0) {
            System.out.println(0);
            return;
        }

        int[] arr = new int[n];
        int filled = 0;
        while (filled < n) {
            String ln = br.readLine();
            if (ln == null) break;
            st = new StringTokenizer(ln);
            while (st.hasMoreTokens() && filled < n) {
                arr[filled++] = Integer.parseInt(st.nextToken());
            }
        }

        int len = removeElement(arr, n, val);

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

    private static int removeElement(int[] nums, int n, int val) {
        int write = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != val) {
                nums[write++] = nums[i];
            }
        }
        return write;
    }
}

