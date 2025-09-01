package LeetCode;

public class lt_02_addtwonumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 建立虛擬頭節點 dummy，用來簡化鏈結串接邏輯
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        int carry = 0;

        // 當還有數字或還有進位
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;  // 更新進位
            int digit = sum % 10;

            current.next = new ListNode(digit);
            current = current.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }
}
