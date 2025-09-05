package finalexam;

// 檔名：LC03_NoRepeat_TaipeiMetroTap.java
// 題意：給定字串 s，求最長「不含重複字元」的連續區間長度。
// 作法：滑動視窗（Two Pointers）。
// 右指針 r 每次向右擴；若 s[r] 曾出現在視窗內，將左指針 l 移到「該字元上次位置 + 1」以去重。
// 以 last[pos] 紀錄每個字元上次出現的位置 + 1（0 代表未出現），可避免與 -1 判斷。
// 複雜度：時間 O(n)，空間 O(k)（k 為字元集合大小，這裡以 ASCII 256）。


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) s = ""; // 防止空輸入

        // 假設 ASCII，可用固定 256 長度陣列；值存「上次出現位置 + 1」
        int[] last = new int[256];

        int ans = 0;
        int l = 0; // 視窗左界（含）
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            int code = c < 256 ? c : 0; // 若超出 ASCII，當作 0 位（題目假設 ASCII 可見字集）

            // 若此字元在當前視窗內出現過，左界推進到其上次出現的下一位
            if (last[code] > 0) {
                l = Math.max(l, last[code]);
            }

            // 更新答案（當前視窗長度）
            ans = Math.max(ans, r - l + 1);

            // 紀錄此字元「下一次左界應到達的位置」= r + 1
            last[code] = r + 1;
        }

        System.out.println(ans);
    }
}

