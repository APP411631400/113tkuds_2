package finalexam;

// 檔名：LC40_CombinationSum2_Procurement.java
// 題意：給定 candidates（可含重複值）與 target，找所有組內遞增且「每個數字最多用一次」的組合，並去除重複組合。
// 作法：回溯 + 去重。先排序；同一層若 candidates[i]==candidates[i-1] 則跳過，以避免生成重複組合。
//       因為每個值只能用一次，遞迴時下一層從 i+1 開始。
// 複雜度：最壞指數；排序 O(n log n)。

import java.io.*;
import java.util.*;

public class LC40_CombinationSum2_Procurement {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        int filled = 0;
        while (filled < n) {
            String line = br.readLine();
            if (line == null) break;
            st = new StringTokenizer(line);
            while (st.hasMoreTokens() && filled < n) {
                a[filled++] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(a);
        List<Integer> path = new ArrayList<>();
        StringBuilder out = new StringBuilder();
        dfs2(a, 0, target, path, out);
        System.out.print(out.toString());
    }

    private static void dfs2(int[] a, int start, int remain, List<Integer> path, StringBuilder out) {
        if (remain == 0) {
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) out.append(' ');
                out.append(path.get(i));
            }
            out.append('\n');
            return;
        }
        for (int i = start; i < a.length; i++) {
            // 同層去重：避免以相同數值作為本層起點重複展開
            if (i > start && a[i] == a[i - 1]) continue;

            int x = a[i];
            if (x > remain) break; // 剪枝

            path.add(x);
            // 每個元素最多用一次 → 下一層從 i+1
            dfs2(a, i + 1, remain - x, path, out);
            path.remove(path.size() - 1);
        }
    }
}

