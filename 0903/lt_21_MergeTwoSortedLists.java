// 題目：21. Merge Two Sorted Lists
// 方法：雙指針
// 時間複雜度：O(m+n)，空間複雜度：O(1)

import java.util.*;

public class lt_21_MergeTwoSortedLists {
    public static void main(String[] args) {
        // 建立範例鏈表 list1 = [1,2,4]
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));

        // 建立範例鏈表 list2 = [1,3,4]
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        Solution sol = new Solution();
        ListNode merged = sol.mergeTwoLists(list1, list2);

        // 輸出結果
        System.out.print("合併後鏈表: ");
        while (merged != null) {
            System.out.print(merged.val + " ");
            merged = merged.next;
        }
    }
}

// 鏈表定義
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        cur.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }
}

/*
解題思路：
- 使用 dummy node + cur 指針，從 list1 與 list2 中逐一選擇較小的節點接入。
- 直到一方走完後，將另一方剩餘鏈表直接接入。
- 這樣能保證結果鏈表仍為非遞減排序。
*/

