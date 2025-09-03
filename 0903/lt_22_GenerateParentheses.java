// 題目：22. Generate Parentheses
// 方法：回溯（Backtracking）
// 時間複雜度：~Catalan(n)；空間：O(n)

import java.util.*;

public class lt_22_GenerateParentheses {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("請輸入 n（對數，1 <= n <= 8）：");
        int n = sc.nextInt();
        sc.close();

        Solution sol = new Solution();
        List<String> res = sol.generateParenthesis(n);

        System.out.println("所有合法組合：");
        System.out.println(res);
    }
}

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(n, 0, 0, new StringBuilder(), res);
        return res;
    }

    private void backtrack(int n, int open, int close, StringBuilder path, List<String> res) {
        if (path.length() == 2 * n) {
            res.add(path.toString());
            return;
        }
        if (open < n) {            // 還能放 '('
            path.append('(');
            backtrack(n, open + 1, close, path, res);
            path.deleteCharAt(path.length() - 1);
        }
        if (close < open) {        // ')' 不能超過 '('
            path.append(')');
            backtrack(n, open, close + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }
}

/*
解題思路（摘要）：
- 狀態為已放 '(' 數 open、已放 ')' 數 close。
- 規則：open <= n；close <= open。
- 深度優先建構到長度 2n 即收集。
*/

