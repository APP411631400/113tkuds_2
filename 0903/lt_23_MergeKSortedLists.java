// 題目：23. Merge k Sorted Lists
// 方法：最小堆（PriorityQueue）
// 時間複雜度：O(N log k)；空間：O(k)

import java.util.*;

public class lt_23_MergeKSortedLists {
    public static void main(String[] args) {
        // 構造測試資料：[[1,4,5],[1,3,4],[2,6]]
        ListNode a = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode b = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode c = new ListNode(2, new ListNode(6));
        ListNode[] lists = new ListNode[]{a, b, c};

        Solution sol = new Solution();
        ListNode merged = sol.mergeKLists(lists);

        System.out.print("合併後：");
        while (merged != null) {
            System.out.print(merged.val + (merged.next != null ? "->" : ""));
            merged = merged.next;
        }
        System.out.println();
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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        for (ListNode node : lists) if (node != null) pq.offer(node);

        ListNode dummy = new ListNode(-1), tail = dummy;

        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            tail.next = cur;
            tail = tail.next;
            if (cur.next != null) pq.offer(cur.next);
        }
        return dummy.next;
    }
}

/*
解題思路：
- 用最小堆維護 k 條鏈表的當前最小節點；每次彈出最小者接到結果。
- 若該節點有 next，將 next 放回堆，直到堆空。
- 複雜度 O(N log k)，適合 k 很大、每條鏈表也較長的情況。
*/

