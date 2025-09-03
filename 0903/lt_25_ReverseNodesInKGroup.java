// 題目：25. Reverse Nodes in k-Group（VS Code 版本，含 main 測試）
// 方法：每 k 個為一組就地反轉；時間 O(n)，空間 O(1)

public class lt_25_ReverseNodesInKGroup {
    // 節點定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        // 範例：head=[1,2,3,4,5], k=2 → [2,1,4,3,5]
        ListNode head = build(new int[]{1,2,3,4,5});
        int k = 2;

        head = reverseKGroup(head, k);

        print(head); // 期望：2 1 4 3 5
    }

    static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode kth = getKthNode(prevGroupEnd, k);
            if (kth == null) break;
            ListNode groupEndNext = kth.next;

            ListNode prev = groupEndNext;
            ListNode curr = prevGroupEnd.next;
            while (curr != groupEndNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            ListNode newGroupStart = kth;
            ListNode newGroupEnd   = prevGroupEnd.next;
            prevGroupEnd.next = newGroupStart;
            prevGroupEnd = newGroupEnd;
        }
        return dummy.next;
    }

    static ListNode getKthNode(ListNode node, int k) {
        while (node != null && k-- > 0) node = node.next;
        return node;
    }

    // 工具：建表、印表
    static ListNode build(int[] a) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : a) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }
    static void print(ListNode h) {
        while (h != null) {
            System.out.print(h.val + (h.next!=null?" ":"\n"));
            h = h.next;
        }
    }
}

/*
解題思路（放在程式碼後）：
- 以 dummy 開頭，每回合確認是否有滿 k 個節點（getKthNode）。
- 若滿足，將該段以「頭插法」原地反轉，尾端暫接到 groupEndNext，確保 O(1) 空間。
- 反轉完重新接回，再把 prevGroupEnd 移到新組尾，繼續下一組。
- 尾段不足 k 個不動。
*/

