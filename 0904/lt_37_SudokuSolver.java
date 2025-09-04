// 檔名：lt_37_SudokuSolver.java
// 功能：本地可執行，測試 Sudoku Solver。與 LeetCode 版邏輯一致。

public class lt_37_SudokuSolver {
    public static void main(String[] args) {
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        Solution sol = new Solution();
        sol.solveSudoku(board);

        // 輸出結果
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
                System.out.print(j == 8 ? '\n' : ' ');
            }
        }
    }
}

class Solution {
    int[] rows = new int[9], cols = new int[9], boxes = new int[9];
    char[][] board;

    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue;
                int bit = 1 << (ch - '1');
                int b = (r / 3) * 3 + (c / 3);
                rows[r] |= bit; cols[c] |= bit; boxes[b] |= bit;
            }
        }
        backtrack(0, 0);
    }

    private boolean backtrack(int r, int c) {
        if (c == 9) { r++; c = 0; }
        if (r == 9) return true;

        if (board[r][c] != '.') return backtrack(r, c + 1);

        int b = (r / 3) * 3 + (c / 3);
        int used = rows[r] | cols[c] | boxes[b];
        int candidates = (~used) & 0x1FF;
        while (candidates != 0) {
            int pick = candidates & -candidates;
            candidates -= pick;

            int d = Integer.numberOfTrailingZeros(pick);
            char ch = (char)('1' + d);

            board[r][c] = ch;
            rows[r]  |= pick;
            cols[c]  |= pick;
            boxes[b] |= pick;

            if (backtrack(r, c + 1)) return true;

            board[r][c] = '.';
            rows[r]  &= ~pick;
            cols[c]  &= ~pick;
            boxes[b] &= ~pick;
        }
        return false;
    }
}

/*
解題思路（完整）：
- 以回溯（DFS）依序填空格；每格可填的數字 = {1..9} 扣掉「該列、該行、該宮」已出現者。
- 用 rows/cols/boxes 三個 9 長度 bitmask 記錄使用情況，檢查與更新皆為 O(1)。
- 候選以 bitset 表示，透過 x & -x 提取最低位 1，快速枚舉候選值，失敗即回溯。
- 題目保證唯一解，找到即結束。
*/

