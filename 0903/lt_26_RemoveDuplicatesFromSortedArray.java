// 檔名：lt_26_RemoveDuplicates.java
// 題目：Remove Duplicates from Sorted Array
// 功能：移除排序陣列中的重複元素，並回傳不重複元素的長度。

import java.util.*;


public class lt_26_RemoveDuplicatesFromSortedArray {
     // ===== 解法：雙指針 =====
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    // 主程式：方便在 VS Code 測試
    public static void main(String[] args) {
        int[] nums1 = {1, 1, 2};
        int k1 = removeDuplicates(nums1);
        System.out.println("不重複長度 = " + k1);
        System.out.print("處理後陣列 = ");
        for (int i = 0; i < k1; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println();

        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};
        int k2 = removeDuplicates(nums2);
        System.out.println("不重複長度 = " + k2);
        System.out.print("處理後陣列 = ");
        for (int i = 0; i < k2; i++) {
            System.out.print(nums2[i] + " ");
        }
        System.out.println();
    }
}

/*
解題思路：
1. 陣列已排序 → 重複元素一定相鄰。
2. 用 slow 指針維護「不重複部分」，fast 負責掃描新數字。
3. 當 fast 指向的新數字與 slow 不同時，將 slow++ 並把 nums[fast] 覆蓋到 nums[slow]。
4. 最後 [0..slow] 即為去重後的新陣列，長度 = slow+1。
時間複雜度：O(n)
空間複雜度：O(1)
*/
