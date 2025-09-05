package finalexam;

// 檔名：LC17_PhoneCombos_CSShift.java
// 題意：給定只含 '2'~'9' 的數字字串，輸出所有電話鍵盤對應的字母組合（每行一組）。
// 作法：回溯（深度優先）。每個 digit 對應一組候選字母，逐位展開。
// 邊界：空字串 → 依題意可不輸出任何行。
// 複雜度：時間 O(乘積)，空間 O(深度)。


import java.io.*;
import java.util.*;

public class LC17_PhoneCombos_CSShift {
    private static final String[] MAP = new String[]{
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String digits = br.readLine();
        if (digits == null) digits = "";
        digits = digits.trim();
        if (digits.isEmpty()) return; // 空字串 → 無輸出

        StringBuilder sb = new StringBuilder();
        backtrack(digits, 0, sb);
    }

    private static void backtrack(String digits, int idx, StringBuilder path) {
        if (idx == digits.length()) {
            System.out.println(path.toString());
            return;
        }
        char d = digits.charAt(idx);
        // 題目保證只含 '2'~'9'
        String letters = MAP[d - '2'];
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            backtrack(digits, idx + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}

