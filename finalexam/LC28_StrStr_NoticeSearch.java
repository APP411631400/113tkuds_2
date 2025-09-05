package finalexam;

// 檔名：LC28_StrStr_NoticeSearch.java
// 題意：回傳 needle 在 haystack 中首次出現的起始索引；不存在則 -1；若 needle 為空字串回 0。
// 作法：KMP（建失敗函數 lps），避免重複比較；時間 O(n+m)，空間 O(m)。

import java.io.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String haystack = br.readLine();
        String needle = br.readLine();

        if (haystack == null) haystack = "";
        if (needle == null) needle = "";

        System.out.println(strStrKMP(haystack, needle));
    }

    private static int strStrKMP(String s, String p) {
        int n = s.length(), m = p.length();
        if (m == 0) return 0;
        if (m > n) return -1;

        int[] lps = buildLPS(p);
        int i = 0, j = 0; // i: s 的索引，j: p 的索引
        while (i < n) {
            if (s.charAt(i) == p.charAt(j)) {
                i++; j++;
                if (j == m) return i - m; // 完全匹配
            } else {
                if (j > 0) {
                    j = lps[j - 1]; // 失敗函數回退
                } else {
                    i++;           // j==0 只能推進 i
                }
            }
        }
        return -1;
    }

    // 建立部分匹配表（Longest Prefix Suffix）
    private static int[] buildLPS(String p) {
        int m = p.length();
        int[] lps = new int[m];
        int len = 0; // 目前最長相等前後綴長度
        for (int i = 1; i < m; ) {
            if (p.charAt(i) == p.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }
}

