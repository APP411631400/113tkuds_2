package finalexam;

// 檔名：LC25_ReverseKGroup_Shifts.java
// 題意：給定單向鏈結串列與整數 k，每 k 個節點為一組反轉；不足 k 的尾端保留原順序。
// 作法：
//   1. 使用 dummy 節點方便處理頭部。
//   2. 每次檢查剩餘節點是否 >= k，若不足則結束。
//   3. 若足夠，反轉該區間並更新 prev 指標。
// 複雜度：時間 O(n)，空間 O(1)。

import java.io.*;
import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) return;
        int k = Integer.parseInt(first.trim());

        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println();
            return;
        }

        StringTokenizer st = new StringTokenizer(line);
        ListNode dummy = new ListNode(0), tail = dummy;
        while (st.hasMoreTokens()) {
            tail.next = new ListNode(Integer.parseInt(st.nextToken()));
            tail = tail.next;
        }

        ListNode head = reverseKGroup(dummy.next, k);

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

    private static ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            // 找出當前組的結尾
            ListNode kth = prevGroupEnd;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) break; // 剩餘不足 k

            // 反轉這一組
            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            // 反轉 [groupStart, kth]
            ListNode prev = nextGroupStart, cur = groupStart;
            while (cur != nextGroupStart) {
                ListNode tmp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = tmp;
            }

            // 接回鏈結
            prevGroupEnd.next = prev;
            prevGroupEnd = groupStart;
        }

        return dummy.next;
    }
}

