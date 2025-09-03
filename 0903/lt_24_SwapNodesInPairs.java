// 題目：24. Swap Nodes in Pairs
// 方法：迭代法 (Dummy 節點) + 測試案例
// 時間複雜度：O(n)，空間複雜度：O(1)

public class lt_24_SwapNodesInPairs {
    public static void main(String[] args) {
        // 測試：輸入 [1,2,3,4]
        ListNode head = new ListNode(1,
                        new ListNode(2,
                        new ListNode(3,
                        new ListNode(4))));
        Solution sol = new Solution();
        ListNode res = sol.swapPairs(head);

        System.out.print("交換後結果：");
        while (res != null) {
            System.out.print(res.val + (res.next != null ? "->" : ""));
            res = res.next;
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
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            first.next = second.next;
            second.next = first;
            prev.next = second;

            prev = first; // 移到下一對之前
        }
        return dummy.next;
    }
}

/*
解題思路：
- Dummy 節點簡化交換頭節點情況。
- 每輪交換 prev 後的兩個節點：first、second。
- 修改鏈結順序：prev → second → first → 後續。
- 更新 prev = first，繼續處理下一對。
- 範例：1→2→3→4 → 交換後變 2→1→4→3。
*/

