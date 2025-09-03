// 題目：19. Remove Nth Node From End of List
// 方法：快慢指針（Two Pointers）
// 時間複雜度：O(n)，空間複雜度：O(1)

import java.util.*;

public class lt_19_RemoveNthNode {
    public static void main(String[] args) {
        // 建立範例鏈表 head = [1,2,3,4,5]
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int n = 2; // 移除倒數第 2 個節點

        Solution sol = new Solution();
        ListNode newHead = sol.removeNthFromEnd(head, n);

        // 輸出結果鏈表
        System.out.print("刪除後的鏈表: ");
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy, slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}

/*
解題思路：
- 使用快慢指針，先讓 fast 移動 n+1 步。
- fast 和 slow 一起移動，直到 fast 到達尾端。
- 此時 slow 指向被刪節點的前一個，修改指標刪除節點。
- dummy node 避免刪除頭節點時出錯。
*/

