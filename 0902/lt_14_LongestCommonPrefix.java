// 題目：14. Longest Common Prefix
// 方法：水平掃描（逐字比較）
// 時間複雜度：O(n * m)，n=字串數量，m=最短字串長度
// 空間複雜度：O(1)

import java.util.*;

public class lt_14_LongestCommonPrefix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 測試輸入
        System.out.println("請輸入字串數量：");
        int n = sc.nextInt();
        sc.nextLine(); // 吃掉換行

        String[] strs = new String[n];
        System.out.println("請逐行輸入字串：");
        for (int i = 0; i < n; i++) {
            strs[i] = sc.nextLine();
        }
        sc.close();

        Solution sol = new Solution();
        String ans = sol.longestCommonPrefix(strs);
        System.out.println("最長共同前綴 = \"" + ans + "\"");
    }
}

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0]; // 先假設第一個字串是共同前綴
        for (int i = 1; i < strs.length; i++) {
            // 不斷縮短 prefix，直到符合當前字串的開頭
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}

/*
解題思路：
1. 選第一個字串當作候選 prefix。
2. 逐一比對其他字串，若不符合，縮短 prefix。
3. 若縮到空字串就直接返回 ""。
4. 複雜度：
   - 最差情況要逐字縮短 prefix，每次比對 O(m)，總共 O(n*m)。
   - 空間只用一個變數 prefix → O(1)。
*/

