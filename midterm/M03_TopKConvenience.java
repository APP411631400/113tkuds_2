package midterm;

import java.io.*;
import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int idx; // 紀錄輸入順序以維持穩定性
        Item(String n, int q, int i) { name=n; qty=q; idx=i; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(first[0]);
        int k = Integer.parseInt(first[1]);

        PriorityQueue<Item> pq = new PriorityQueue<>(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) return a.qty - b.qty; // 最小在前
                return b.idx - a.idx; // 保證穩定性：較後輸入的先丟掉
            }
        });

        for (int i=0;i<n;i++) {
            String[] parts = br.readLine().trim().split("\\s+");
            String name = parts[0];
            int qty = Integer.parseInt(parts[1]);
            Item it = new Item(name, qty, i);
            if (pq.size()<k) pq.add(it);
            else {
                Item top = pq.peek();
                if (qty > top.qty || (qty == top.qty && i < top.idx)) {
                    pq.poll();
                    pq.add(it);
                }
            }
        }

        List<Item> list = new ArrayList<>(pq);
        list.sort((a,b)->{
            if (b.qty!=a.qty) return b.qty-a.qty;
            return a.idx-b.idx; // 輸入順序小的先
        });

        for (Item it:list) {
            System.out.println(it.name+" "+it.qty);
        }
    }
}

/*
 * Time Complexity: O(n log k)
 * 說明：逐一將 n 筆商品放入 Min-Heap，維護大小 K，每次操作 O(log k)，
 *      總計 O(n log k)。若 K = n，等同排序 O(n log n)。
 */
