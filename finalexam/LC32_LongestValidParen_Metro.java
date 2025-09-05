package finalexam;

// 檔名：LC32_LongestValidParen_Metro.java
// 題意：給定只含 '('、')' 的字串，求最長「括號合法」子字串長度。
// 作法（索引堆疊 O(n)）：
//   - 棧底先放 -1 當基準。
//   - 掃描：遇 '(' → push 當前索引；遇 ')' → pop；若此時棧空 → push 當前索引作新基準；否則用 i - 棧頂 更新答案。
// 複雜度：時間 O(n)，空間 O(n)

import java.io.*;
import java.util.*;

public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) s = "";

        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1); // 初始基準
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(i);
            } else { // c == ')'
                if (!st.isEmpty()) st.pop();
                if (st.isEmpty()) {
                    // 沒有可配對的 '('，把當前 i 當作新基準
                    st.push(i);
                } else {
                    // 當前合法片段長度 = i - 棧頂（上一個未匹配位置）
                    ans = Math.max(ans, i - st.peek());
                }
            }
        }

        System.out.println(ans);
    }
}

