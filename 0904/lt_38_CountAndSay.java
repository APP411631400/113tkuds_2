// 題目：38. Count and Say
// 給定正整數 n，輸出 count-and-say 序列的第 n 項。
// countAndSay(1) = "1"
// countAndSay(n) = 對 countAndSay(n-1) 做 Run-length Encoding (連續數字壓縮表示法)

public class lt_38_CountAndSay {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 測試範例
        int n1 = 1;
        System.out.println("n = " + n1 + " → " + solution.countAndSay(n1));

        int n2 = 4;
        System.out.println("n = " + n2 + " → " + solution.countAndSay(n2));

        int n3 = 6;
        System.out.println("n = " + n3 + " → " + solution.countAndSay(n3));
    }
}

class Solution {
    public String countAndSay(int n) {
        // 基本情況，n=1 回傳 "1"
        if (n == 1) return "1";

        // 從 "1" 開始，逐步生成直到第 n 項
        String result = "1";
        for (int i = 2; i <= n; i++) {
            result = say(result); // 對上一個字串進行描述
        }
        return result;
    }

    // 幫助方法：對字串進行 "count and say" 編碼
    private String say(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1; // 計數器

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                // 相同字元 → 累加數量
                count++;
            } else {
                // 不同字元 → 先輸出前一段數量+字元
                sb.append(count).append(s.charAt(i - 1));
                count = 1; // 重置計數器
            }
        }
        // 最後一段字元也要輸出
        sb.append(count).append(s.charAt(s.length() - 1));

        return sb.toString();
    }
}

/*
解題思路：
1. count-and-say 序列的定義是遞迴的，第一項為 "1"。
2. 從第 2 項開始，每一項都是對前一項字串進行「描述」：
   - 例如 "1" → "11" (一個 1)
   - "11" → "21" (兩個 1)
   - "21" → "1211" (一個 2，一個 1)
3. 我們可以從 "1" 開始，用迴圈不斷生成直到第 n 項。
4. 生成過程使用雙指針或計數器，統計連續相同字元的個數，轉換為「數字+字元」加入結果。
5. 時間複雜度約 O(n * m)，其中 m 為字串長度（會隨 n 增長），但 n 最大為 30，效率足夠。
*/

