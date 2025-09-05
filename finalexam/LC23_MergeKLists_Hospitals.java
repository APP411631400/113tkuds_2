package finalexam;

// 檔名：LC23_MergeKLists_Hospitals.java
// 題意：合併 k 條「各自已排序」的單向鏈結串列為一條升序串列。
// 作法：最小堆（PriorityQueue）。先把每條非空串列的頭節點丟入堆，反覆取出最小者掛到答案尾端，並把它的 next 再丟回堆。
// 複雜度：時間 O(N log k)，空間 O(k)，N 為總節點數。
// 輸入：第一行 k；接著 k 行升序整數，每行以 -1 結束（可能出現空串列：僅 -1）。
// 輸出：合併後的升序序列（以空白分隔；全空則輸出空行）。

import java.io.*;
import java.util.*;

public class LC23_MergeKLists_Hospitals {
    // 單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 讀取 k
        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) {
            // 沒有任何輸入 → 視為空
            System.out.println();
            return;
        }
        int k = Integer.parseInt(first.trim());

        // 讀取 k 行，各行為一條升序串列（以 -1 為終止）
        List<ListNode> heads = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            heads.add(readOneList(br));
        }

        // 建立最小堆，放各串列的當前頭節點
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        for (ListNode h : heads) if (h != null) pq.offer(h);

        // 逐步彈出最小節點並串接
        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            tail.next = cur;
            tail = tail.next;
            if (cur.next != null) pq.offer(cur.next);
        }

        // 輸出結果
        StringBuilder out = new StringBuilder();
        ListNode p = dummy.next;
        while (p != null) {
            out.append(p.val);
            if (p.next != null) out.append(' ');
            p = p.next;
        }
        System.out.println(out.toString());
    }

    // 讀一行串列（以 -1 作為結尾；允許空行或只有 -1 → 視為空串列）
    private static ListNode readOneList(BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line == null) return null;
        line = line.trim();
        if (line.isEmpty()) return null;             // 空行 → 當作空串列
        StringTokenizer st = new StringTokenizer(line);

        ListNode dummy = new ListNode(0), tail = dummy;
        while (st.hasMoreTokens()) {
            String tok = st.nextToken();
            int v;
            try {
                v = Integer.parseInt(tok);
            } catch (NumberFormatException e) {
                continue; // 防呆：略過例外字串
            }
            if (v == -1) break;                      // -1 為此行終止
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }
}

