// 檔名：lt_30_SubstringWithConcatenationOfAllWords.java
// 功能：本地可執行，測試 LeetCode 30 題的滑動窗口解法。

import java.util.*;

public class lt_30_SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 範例 1
        String s1 = "barfoothefoobarman";
        String[] words1 = {"foo", "bar"};
        System.out.println(sol.findSubstring(s1, words1)); // 期望 [0, 9]

        // 範例 2
        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word","good","best","word"};
        System.out.println(sol.findSubstring(s2, words2)); // 期望 []

        // 範例 3
        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = {"bar","foo","the"};
        System.out.println(sol.findSubstring(s3, words3)); // 期望 [6, 9, 12]
    }
}

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return ans;

        int n = s.length();
        int m = words.length;
        int L = words[0].length();
        int windowLen = m * L;
        if (n < windowLen) return ans;

        Map<String, Integer> need = new HashMap<>();
        for (String w : words) need.put(w, need.getOrDefault(w, 0) + 1);

        for (int offset = 0; offset < L; offset++) {
            int left = offset, count = 0;
            Map<String, Integer> have = new HashMap<>();

            for (int right = offset; right + L <= n; right += L) {
                String w = s.substring(right, right + L);

                if (!need.containsKey(w)) {
                    have.clear();
                    count = 0;
                    left = right + L;
                    continue;
                }

                have.put(w, have.getOrDefault(w, 0) + 1);
                count++;

                while (have.get(w) > need.get(w)) {
                    String drop = s.substring(left, left + L);
                    have.put(drop, have.get(drop) - 1);
                    left += L;
                    count--;
                }

                if (count == m) {
                    ans.add(left);
                    String drop = s.substring(left, left + L);
                    have.put(drop, have.get(drop) - 1);
                    left += L;
                    count--;
                }
            }
        }
        return ans;
    }
}

/*
解題思路（完整）：
- 由於所有單字長度相同（L），合法的連接字串可視為「連續 m 塊、每塊長度 L 的拼接」。
- 以 L 為步長的滑動窗口能保證塊與塊對齊，不會切斷單字。
- 對每個 offset ∈ [0..L-1]：
  1) 右指針每次取長度 L 的片段加入窗口 have，count++。
  2) 若某詞超出 need 的要求，移動左指針，每次丟掉一塊直到不超標。
  3) 當窗口剛好包含 m 塊時，left 即為一個答案；為了找下一個，丟掉最左一塊繼續。
- 這個方法本質是「固定長度單字的多集合匹配」，用字頻控制即可。
複雜度：
- 時間：O(n) ~ O(n * L)（取決於 substring 的成本與實作），實務上相對高效。
- 空間：O(k)（k 為不同單字的數量）。
*/

