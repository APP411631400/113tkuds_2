// 題目：Remove Element
// VSCode 測試版本：可輸入陣列與 val，並輸出移除後的結果。

import java.util.*;

public class lt_27_RemoveElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入陣列長度
        System.out.print("請輸入陣列長度: ");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("請輸入陣列元素:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // 輸入 val
        System.out.print("請輸入要移除的 val: ");
        int val = sc.nextInt();

        // 呼叫解法
        Solution sol = new Solution();
        int k = sol.removeElement(nums, val);

        // 輸出結果
        System.out.println("剩餘元素數量: " + k);
        System.out.print("移除後的陣列: ");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}

class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0; // 指針，指向下一個應該存放非 val 元素的位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}

/*
解題思路：
1. 使用雙指針法：i 遍歷陣列，k 負責放置不等於 val 的元素。
2. 若 nums[i] != val，就把它放到 nums[k]，並且 k++。
3. 完成後，陣列前 k 個元素即為結果。
4. 範例：
   nums = [3,2,2,3], val = 3
   過程：
   i=0 -> nums[0]=3 跳過
   i=1 -> nums[1]=2 放到 nums[0] → [2,2,2,3], k=1
   i=2 -> nums[2]=2 放到 nums[1] → [2,2,2,3], k=2
   i=3 -> nums[3]=3 跳過
   最後 k=2，結果 [2,2]
*/

