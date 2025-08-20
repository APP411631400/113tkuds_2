package midterm;

import java.io.*;
import java.util.*;

public class M10_RBPropertiesCheck {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        // 讀足 2n 個 token：val1 col1 val2 col2 ...
        List<String> tokens = new ArrayList<>(2 * n);
        while (tokens.size() < 2 * n) {
            String line = br.readLine();
            if (line == null) break;
            String[] ps = line.trim().split("\\s+");
            for (String p : ps) if (!p.isEmpty()) tokens.add(p);
        }

        int[] val = new int[n];
        char[] col = new char[n];

        for (int i = 0, t = 0; i < n; i++) {
            val[i] = Integer.parseInt(tokens.get(t++));
            col[i] = tokens.get(t++).charAt(0); // 'B' or 'R'；若 val=-1 仍讀掉但當作 'B'
            if (val[i] == -1) col[i] = 'B';     // NIL 視為黑
        }

        // 1) 根節點為黑
        if (n > 0 && val[0] != -1 && col[0] != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 2) 不得有相鄰紅節點（紅紅相鄰）
        for (int i = 0; i < n; i++) {
            if (i >= n || val[i] == -1) continue;
            if (col[i] == 'R') {
                int l = 2 * i + 1, r = 2 * i + 2;
                if (l < n && val[l] != -1 && col[l] == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
                if (r < n && val[r] != -1 && col[r] == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }

        // 3) 自任一節點至所有葉(NIL)的黑高度一致
        int bh = blackHeightOrInvalid(0, val, col, n);
        if (bh == INVALID) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    // ---- 黑高度檢查 ----
    static final int INVALID = -2; // 空子樹高度使用 -1；因此 -2 代表不合法

    static int blackHeightOrInvalid(int i, int[] val, char[] col, int n) {
        // 超界或陣列中的空位都視為 NIL（黑），黑高 = 1
        if (i >= n || val[i] == -1) return 1;

        int left = blackHeightOrInvalid(2 * i + 1, val, col, n);
        if (left == INVALID) return INVALID;
        int right = blackHeightOrInvalid(2 * i + 2, val, col, n);
        if (right == INVALID) return INVALID;

        if (left != right) return INVALID; // 黑高不一致
        return left + (col[i] == 'B' ? 1 : 0);
    }
}
