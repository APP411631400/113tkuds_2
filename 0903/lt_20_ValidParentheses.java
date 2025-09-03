// 題目：20. Valid Parentheses
// 方法：使用堆疊（Stack）
// 時間複雜度：O(n)，空間複雜度：O(n)

import java.util.*;

public class lt_20_ValidParentheses {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入括號字串：");
        String s = sc.nextLine().trim();
        sc.close();

        Solution sol = new Solution();
        boolean ans = sol.isValid(s);

        System.out.println("是否為有效括號字串: " + ans);
    }
}

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

/*
解題思路：
- 使用堆疊來追蹤未匹配的左括號。
- 掃描字串：
  - 遇到左括號 → 入堆疊。
  - 遇到右括號 → 檢查堆疊頂是否正確配對。
- 若不匹配或堆疊空 → false。
- 掃描結束後，若堆疊為空 → true，否則 → false。
*/

