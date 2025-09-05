package finalexam;

// 檔名：LC19_RemoveNth_Node_Clinic.java
// 題意：給定單向鏈結串列（長度 n），刪除倒數第 k 個節點，並輸出刪除後的整個序列。
// 作法：雙指標（快慢指針）。
//   - 建立 dummy 節點指向 head，避免刪除頭節點時特殊處理。
//   - fast 先走 k 步，然後 fast 與 slow 同步走，直到 fast 到尾。
//   - 此時 slow 停在待刪節點前一個，調整指標刪除即可。
// 複雜度：時間 O(n)，空間 O(1)

import java.io.*;
import java.util.*;

public class LC19_RemoveNth_Node_Clinic {
    // 定義鏈結串列節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 讀 n
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        int n = Integer.parseInt(line.trim());

        // 讀 n 個節點值
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int filled = 0;
        while (filled < n) {
            String ln = br.readLine();
            if (ln == null) break;
            StringTokenizer st = new StringTokenizer(ln);
            while (st.hasMoreTokens() && filled < n) {
                tail.next = new ListNode(Integer.parseInt(st.nextToken()));
                tail = tail.next;
                filled++;
            }
        }
        ListNode head = dummy.next;

        // 讀 k
        int k = Integer.parseInt(br.readLine().trim());

        head = removeNthFromEnd(head, k);

        // 輸出刪除後序列
        StringBuilder out = new StringBuilder();
        ListNode cur = head;
        while (cur != null) {
            out.append(cur.val);
            if (cur.next != null) out.append(' ');
            cur = cur.next;
        }
        System.out.println(out.toString());
    }

    private static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        // fast 先走 n+1 步，讓 slow 剛好停在待刪節點的前一個
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // fast 與 slow 同步移動
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow.next 即為待刪節點
        slow.next = slow.next.next;

        return dummy.next;
    }
}

