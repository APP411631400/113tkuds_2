// 題目：Find First and Last Position of Element in Sorted Array
// 測資版：可直接在 VS / IntelliJ 執行驗證

import java.util.*;

public class lt_34_FindFirstAndLastPosition {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // 測試案例
        int[] nums1 = {5,7,7,8,8,10};
        int target1 = 8;
        System.out.println(Arrays.toString(sol.searchRange(nums1, target1))); 
        // 輸出: [3, 4]

        int[] nums2 = {5,7,7,8,8,10};
        int target2 = 6;
        System.out.println(Arrays.toString(sol.searchRange(nums2, target2))); 
        // 輸出: [-1, -1]

        int[] nums3 = {};
        int target3 = 0;
        System.out.println(Arrays.toString(sol.searchRange(nums3, target3))); 
        // 輸出: [-1, -1]
    }
}

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                if (isFirst) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}

/*
解題思路：
1. 題目是經典「在排序陣列中找區間」問題，要求 O(log n)。
2. 用 Binary Search 分兩次處理：
   (1) 找最左邊出現的位置 → 每次找到 target 時往左縮。
   (2) 找最右邊出現的位置 → 每次找到 target 時往右縮。
3. 若 target 不存在，回傳 [-1, -1]。
4. 時間複雜度 O(log n)，空間 O(1)。
*/


