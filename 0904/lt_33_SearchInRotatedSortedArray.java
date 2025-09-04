// 檔名：lt_33_SearchInRotatedSortedArray.java
// 功能：本地可執行測試第 33 題，含 main，方便驗證。

import java.util.*;

public class lt_33_SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.search(new int[]{4,5,6,7,0,1,2}, 0)); // 4
        System.out.println(sol.search(new int[]{4,5,6,7,0,1,2}, 3)); // -1
        System.out.println(sol.search(new int[]{1}, 0));             // -1
        System.out.println(sol.search(new int[]{6,7,8,1,2,3,4,5}, 3));// 5
    }
}

class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] == target) return mid;

            // 左半段有序
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else { // 右半段有序
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

/*
解題思路（完整）：
- 旋轉後的遞增陣列，每次在 mid 處總有一側是連續遞增的。
- 以二分搜尋迭代：
  1) 若 nums[l] <= nums[mid]，左側有序；檢查 target 是否在 [nums[l], nums[mid])，若是往左，否則往右。
  2) 否則右側有序；檢查 target 是否在 (nums[mid], nums[r]]，若是往右，否則往左。
- 如此每輪縮小一半，直到找到或區間空。
- 時間複雜度 O(log n)，空間 O(1)。
*/

