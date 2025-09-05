package finalexam;

// 檔名：LC21_MergeTwoLists_Clinics.java
// 題意：給兩個升序單向鏈結串列，將它們合併為一條升序串列並輸出。
// 作法：雙指針逐步比大小，挑較小者掛到結果尾端，最後將剩餘部分掛尾。
// 複雜度：時間 O(n+m)，空間 O(1)（不計輸出）。
// 輸入：n m，接著 n 個升序整數、m 個升序整數。
// 輸出：合併後的升序序列。

import java.io.*;
import java.util.*;

public class LC21_MergeTwoLists_Clinics {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ListNode l1 = readList(br, n);
        ListNode l2 = readList(br, m);

        ListNode merged = mergeTwoLists(l1, l2);

        // 輸出
        StringBuilder out = new StringBuilder();
        ListNode cur = merged;
        while (cur != null) {
            out.append(cur.val);
            if (cur.next != null) out.append(' ');
            cur = cur.next;
        }
        System.out.println(out.toString());
    }

    private static ListNode readList(BufferedReader br, int len) throws Exception {
        if (len == 0) return null;
        ListNode dummy = new ListNode(0), tail = dummy;
        int filled = 0;
        while (filled < len) {
            String line = br.readLine();
            if (line == null) break;
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens() && filled < len) {
                tail.next = new ListNode(Integer.parseInt(st.nextToken()));
                tail = tail.next;
                filled++;
            }
        }
        return dummy.next;
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}

