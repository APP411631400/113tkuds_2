// 檔名：lt_31_NextPermutation.java
// 功能：本地可執行的 Next Permutation，含測試 main。

import java.util.*;

public class lt_31_NextPermutation {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] a1 = {1, 2, 3};
        sol.nextPermutation(a1);
        System.out.println(Arrays.toString(a1)); // [1, 3, 2]

        int[] a2 = {3, 2, 1};
        sol.nextPermutation(a2);
        System.out.println(Arrays.toString(a2)); // [1, 2, 3]

        int[] a3 = {1, 1, 5};
        sol.nextPermutation(a3);
        System.out.println(Arrays.toString(a3)); // [1, 5, 1]
    }
}

class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        // 1) 找「第一個降序」位置 i
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        if (i >= 0) {
            // 2) 在右側找第一個 > nums[i] 的 j，交換 i 與 j
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }

        // 3) 將 i+1..n-1 反轉成遞增（最小化右半部）
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private void reverse(int[] a, int l, int r) {
        while (l < r) swap(a, l++, r--);
    }
}

/*
解題思路（完整）：
- 目標：在「原地」得到下一個字典序排列。
- 步驟：
  1) 自右向左找第一個 i 使 nums[i] < nums[i+1]；右側為非增序（最大化的尾巴）。
  2) 在右側由右向左找第一個 > nums[i] 的 j，交換 i、j，得到「最小的增幅」。
  3) 將 i+1..end 反轉為遞增，使整體最小化。
- 若整列為非升序（找不到 i），代表已是最大排列，直接反轉得到最小排列。
複雜度：時間 O(n)，空間 O(1)。
*/
