// 檔名：lt_35_SearchInsertPosition.java
// 功能：本地可執行，測試第 35 題。採用 lower_bound 二分搜尋。

import java.util.*;

public class lt_35_SearchInsertPosition {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 範例測試
        System.out.println(sol.searchInsert(new int[]{1,3,5,6}, 5)); // 2
        System.out.println(sol.searchInsert(new int[]{1,3,5,6}, 2)); // 1
        System.out.println(sol.searchInsert(new int[]{1,3,5,6}, 7)); // 4
        System.out.println(sol.searchInsert(new int[]{1,3,5,6}, 0)); // 0

        // 其他測試
        System.out.println(sol.searchInsert(new int[]{1}, 1)); // 0
        System.out.println(sol.searchInsert(new int[]{1}, 2)); // 1
    }
}

class Solution {
    // 回傳第一個 >= target 的索引（lower_bound）
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length; // 使用半開區間 [l, r)
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid;        // 可能命中或需往左找
            } else {
                l = mid + 1;    // 必定在右半
            }
        }
        return l;
    }
}

/*
解題思路（完整）：
- 由於 nums 嚴格遞增，要在 O(log n) 找到 target 的位置或插入點，
  直接做 lower_bound（二分找第一個 >= target 的索引）。
- 若 target 存在：lower_bound 會落在 target 的第一個位置 → 即其索引。
- 若 target 不存在：lower_bound 會落在比 target 大的第一個元素位置，
  這正是插入點（插在此仍可保持排序）。
- 以半開區間 [l, r) 實作，避免邊界煩惱；收斂後 l==r 即答案。
- 時間 O(log n)、空間 O(1)。
*/

