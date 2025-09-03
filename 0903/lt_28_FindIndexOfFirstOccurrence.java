// 題目：Find the Index of the First Occurrence in a String
// VSCode 測試版：讓使用者輸入 haystack 和 needle，並輸出 needle 第一次出現的索引。

import java.util.*;

public class lt_28_FindIndexOfFirstOccurrence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入 haystack
        System.out.print("請輸入 haystack: ");
        String haystack = sc.nextLine();

        // 輸入 needle
        System.out.print("請輸入 needle: ");
        String needle = sc.nextLine();

        // 呼叫解法
        Solution sol = new Solution();
        int index = sol.strStr(haystack, needle);

        // 輸出結果
        System.out.println("第一次出現的位置: " + index);
    }
}

class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}

/*
解題思路：
1. 問題其實就是子字串搜尋，題目要求找到 needle 在 haystack 中的第一次出現位置。
2. 在 Java 裡，字串的 indexOf 方法能直接解決這問題：
   - 找到 → 回傳第一次出現的索引。
   - 沒找到 → 回傳 -1。
3. 範例：
   haystack = "sadbutsad", needle = "sad"
   haystack.indexOf("sad") → 0
   haystack = "leetcode", needle = "leeto"
   haystack.indexOf("leeto") → -1
4. 若要手寫演算法，可考慮暴力比對或 KMP（Knuth-Morris-Pratt）。
5. 時間複雜度：O(n * m)（暴力解法），空間複雜度：O(1)。
*/

