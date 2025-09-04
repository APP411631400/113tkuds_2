// 檔名：lt_32_LongestValidParentheses.java
// 內容：本地可執行，提供兩種解法（Stack 與 雙向線性掃描），並在 main 測試。

import java.util.*;

public class lt_32_LongestValidParentheses {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 基本測試
        System.out.println(sol.longestValidParentheses("(()"));       // 2 -> "()"
        System.out.println(sol.longestValidParentheses(")()())"));    // 4 -> "()()"
        System.out.println(sol.longestValidParentheses(""));          // 0
        System.out.println(sol.longestValidParentheses("()(()"));     // 2
        System.out.println(sol.longestValidParentheses("()(())"));    // 6

        // 也可測另一個雙向掃描方法
        System.out.println(Solution.twoPassScan("(()"));        // 2
        System.out.println(Solution.twoPassScan(")()())"));     // 4
        System.out.println(Solution.twoPassScan("()(())"));     // 6
    }
}

class Solution {
    // ===== 解法一：堆疊索引（與 LeetCode 版相同） =====
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int best = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // 這個右括號成為新的左牆
                } else {
                    best = Math.max(best, i - stack.peek());
                }
            }
        }
        return best;
    }

    // ===== 解法二：雙向線性掃描（O(1) 额外空間）=====
    // 觀念：由左到右計數 '(' 與 ')'，相等時更新答案；若 ')' 超過 '('，歸零重來。
    //       再由右到左做一次，處理左半邊多 '(' 的情況。
    public static int twoPassScan(String s) {
        int best = 0, open = 0, close = 0;

        // 左 -> 右：處理右括號過多的情況
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') open++;
            else close++;
            if (open == close) best = Math.max(best, 2 * close);
            else if (close > open) { // 無法配對，重置
                open = close = 0;
            }
        }

        // 右 -> 左：處理左括號過多的情況
        open = close = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(') open++;
            else close++;
            if (open == close) best = Math.max(best, 2 * open);
            else if (open > close) { // 無法配對，重置
                open = close = 0;
            }
        }
        return best;
    }
}

/*
解題思路（完整）：

A) Stack（索引堆疊）
- 把「不匹配的位置」當作分隔牆，答案來自「右端 i - 左牆 top」。
- 初始化 push(-1) 當最左邊界。
- '(' -> push(i)；')' -> pop 一個，若空了就 push(i) 當新牆；否則用 i - stack.peek() 更新最長。
- 時間 O(n)、空間 O(n)。

B) 兩次線性掃描（Two-pass / 計數法）
- 左到右：統計 open/close，當 close > open，說明無法配對，歸零；當 open==close 更新答案。
- 右到左：對稱地再來一次，處理左括號多於右括號的情況。
- 時間 O(n)、空間 O(1)。

實務建議：
- 需要返回長度 → 兩種都可；若希望 O(1) 額外空間，可用「雙向掃描」法。
- 若要找出區間端點位置，堆疊法更直觀。
*/

