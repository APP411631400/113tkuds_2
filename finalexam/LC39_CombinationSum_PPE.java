package finalexam;

// 檔名：LC39_CombinationSum_PPE.java
// 題意：給定 candidates 與 target，求所有組內遞增且「元素可重複使用」的組合，使其和為 target。
// 作法：回溯 + 剪枝。先排序；每層循環從當前索引 start 開始，若選用 candidates[i]，下一層仍傳 i（可重複）。
// 複雜度：最壞指數；排序 O(n log n)。n<=30, target<=500 可接受。

import java.io.*;
import java.util.*;

public class LC39_CombinationSum_PPE {
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

        Arrays.sort(a); // 有助於剪枝與輸出有序
        List<Integer> path = new ArrayList<>();
        StringBuilder out = new StringBuilder();
        dfs(a, 0, target, path, out);
        System.out.print(out.toString());
    }

    private static void dfs(int[] a, int start, int remain, List<Integer> path, StringBuilder out) {
        if (remain == 0) {
            // 輸出一行
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) out.append(' ');
                out.append(path.get(i));
            }
            out.append('\n');
            return;
        }
        for (int i = start; i < a.length; i++) {
            int x = a[i];
            if (x > remain) break; // 剪枝
            path.add(x);
            // 可重複 → 下一層仍從 i 開始
            dfs(a, i, remain - x, path, out);
            path.remove(path.size() - 1);
        }
    }
}

