package finalexam;

// 檔名：LC20_ValidParentheses_AlertFormat.java
// 題意：給定只含 ()[]{} 的字串，檢查是否為合法括號字串。
// 作法：使用堆疊模擬。
//   - 遇到開括號 → push
//   - 遇到閉括號 → 若堆疊空或頂端不匹配 → false
//   - 結束後堆疊必須為空才是 true
// 複雜度：時間 O(n)，空間 O(n)


import java.io.*;
import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) s = "";

        System.out.println(isValid(s) ? "true" : "false");
    }

    private static boolean isValid(String s) {
        Map<Character, Character> match = new HashMap<>();
        match.put(')', '(');
        match.put(']', '[');
        match.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (match.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != match.get(c)) {
                    return false;
                }
                stack.pop();
            } else {
                // 非法字元（題目未出現，但保險）
                return false;
            }
        }

        return stack.isEmpty();
    }
}

