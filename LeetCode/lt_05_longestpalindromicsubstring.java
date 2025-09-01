package LeetCode;

public class lt_05_longestpalindromicsubstring {
    private int start = 0, maxLen = 1;

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        for (int i = 0; i < s.length(); i++) {
            // 奇數長度回文
            expandAroundCenter(s, i, i);
            // 偶數長度回文
            expandAroundCenter(s, i, i + 1);
        }

        return s.substring(start, start + maxLen);
    }

    private void expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 更新最大長度與起始位置
        int len = right - left - 1;
        if (len > maxLen) {
            maxLen = len;
            start = left + 1;
        }
    }
}
