// 題目：17. Letter Combinations of a Phone Number
// 方法：回溯（Backtracking）
// 時間複雜度：O(3^a * 4^b)；空間複雜度：O(L)

import java.util.*;

public class lt_17_LetterCombinations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入 digits（只含 2-9），可為空：");
        String digits = sc.nextLine().trim();
        sc.close();

        Solution sol = new Solution();
        List<String> ans = sol.letterCombinations(digits);

        System.out.println("所有組合：");
        System.out.println(ans);
    }
}

class Solution {
    private static final String[] MAP = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return res; // 空輸入直接回傳 []
        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }

    private void backtrack(String digits, int idx, StringBuilder path, List<String> res) {
        if (idx == digits.length()) {      // 長度到位 → 收集結果
            res.add(path.toString());
            return;
        }
        String letters = MAP[digits.charAt(idx) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));    // 選一個字母
            backtrack(digits, idx + 1, path, res); // 處理下一位
            path.deleteCharAt(path.length() - 1);  // 回溯
        }
    }
}

/*
解題思路：
- 以 digits 每一位對應多個字母，形成一棵 k 叉樹（k=3或4）。
- 用回溯深度優先展開所有可能：做選擇 → 遞迴 → 撤銷選擇。
- 空字串直接回傳空列表；其餘位數 ≤ 4，結果數最多 4^4=256。
*/

