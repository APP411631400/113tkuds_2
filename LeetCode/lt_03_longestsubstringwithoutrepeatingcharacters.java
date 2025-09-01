package LeetCode;
import java.util.HashSet;

public class lt_03_longestsubstringwithoutrepeatingcharacters {
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0, maxLen = 0;
        HashSet<Character> set = new HashSet<>();

        while (right < s.length()) {
            char c = s.charAt(right);

            // 如果沒有重複，就加入 set 並擴張右邊
            if (!set.contains(c)) {
                set.add(c);
                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            } else {
                // 有重複，就從左邊開始縮小視窗
                set.remove(s.charAt(left));
                left++;
            }
        }

        return maxLen;
    }
}
