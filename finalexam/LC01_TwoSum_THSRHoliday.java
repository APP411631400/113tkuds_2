package finalexam;

// 檔名：LC01_TwoSum_THSRHoliday.java
// 題意：給定班次可釋出座位數陣列與 target（臨時新增需求），找兩個不同索引 i, j 使 seats[i] + seats[j] == target；否則輸出 -1 -1
// 作法（一次遍歷 O(n)）：走到值 x 時，若 map 已經有人「在等 x」（即先前存過 need==x），就輸出那個索引與目前索引；否則把 (target - x) 登記為「尚需」。
// 輸入：第一行 n target；第二行 n 個整數（可能跨多行）
// 輸出：兩個 0-based 索引，或 -1 -1（任一解即可）

import java.io.*;
import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 讀第一行：n 與 target
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long target = Long.parseLong(st.nextToken());

        // 讀取接下來的 n 個整數（可能不只一行，因此用循環補齊）
        List<Long> vals = new ArrayList<>(n);
        while (vals.size() < n) {
            String line = br.readLine();
            if (line == null) break; // 保險
            st = new StringTokenizer(line);
            while (st.hasMoreTokens() && vals.size() < n) {
                vals.add(Long.parseLong(st.nextToken()));
            }
        }

        // HashMap<需要的數, 索引>
        Map<Long, Integer> need2idx = new HashMap<>(Math.max(16, n * 2));

        for (int i = 0; i < n; i++) {
            long x = vals.get(i);
            // 若先前有人在等 x，表示找到配對
            if (need2idx.containsKey(x)) {
                System.out.println(need2idx.get(x) + " " + i);
                return;
            }
            // 否則登記目前這個值還「需要」的另一半
            long need = target - x;
            // 若數據允許重複解，保留第一個遇到的索引即可
            need2idx.putIfAbsent(need, i);
        }

        // 若完全沒找到
        System.out.println("-1 -1");
    }
}

