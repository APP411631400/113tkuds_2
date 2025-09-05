package finalexam;

// 檔名：LC33_SearchRotated_RentHot.java
// 題意：在「被旋轉的升序陣列」中找 target 的索引；不存在回 -1。
// 作法：改良二分。每次看 mid，判斷哪一半有序：
//       - 若 nums[l] <= nums[mid] → 左半有序，決定 target 是否在 [l..mid]；否則丟右半。
//       - 否則右半有序，決定 target 是否在 [mid..r]；否則丟左半。
// 複雜度：時間 O(log n)，空間 O(1)。
// 輸入：第一行 n target；後續讀滿 n 個整數（可能跨多行）。
// 輸出：target 的索引（0-based）或 -1。


import java.io.*;
import java.util.*;

public class LC33_SearchRotated_RentHot {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 讀 n 與 target
        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) {
            System.out.println(-1);
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

        System.out.println(searchRotated(a, target));
    }

    private static int searchRotated(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >>> 1);
            if (nums[mid] == target) return mid;

            // 左半有序
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else { // 右半有序
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}

