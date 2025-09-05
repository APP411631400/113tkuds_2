package finalexam;

// 檔名：LC24_SwapPairs_Shifts.java
// 題意：給定單向鏈結串列，將相鄰節點成對交換（1↔2, 3↔4, …），若為奇數長度則最後一個不動。
// 作法：dummy 節點 + prev 指標。每次取出 (a,b) 兩個節點進行交換：
//       prev.next = b, a.next = b.next, b.next = a，然後 prev 移到 a。
// 複雜度：時間 O(n)，空間 O(1)。

import java.io.*;
import java.util.*;

public class LC24_SwapPairs_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println(); // 空串列 → 輸出空行
            return;
        }

        StringTokenizer st = new StringTokenizer(line);
        ListNode dummy = new ListNode(0), tail = dummy;
        while (st.hasMoreTokens()) {
            tail.next = new ListNode(Integer.parseInt(st.nextToken()));
            tail = tail.next;
        }

        ListNode head = swapPairs(dummy.next);

        // 輸出
        StringBuilder out = new StringBuilder();
        ListNode cur = head;
        while (cur != null) {
            out.append(cur.val);
            if (cur.next != null) out.append(' ');
            cur = cur.next;
        }
        System.out.println(out.toString());
    }

    private static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換
            a.next = b.next;
            b.next = a;
            prev.next = b;

            // prev 移到下一對的前一個
            prev = a;
        }
        return dummy.next;
    }
}

