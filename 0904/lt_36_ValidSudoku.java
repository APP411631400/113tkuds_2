// 檔名：lt_36_ValidSudoku.java
// 內容：可在 VS/IntelliJ 直接執行測試 Valid Sudoku。

public class lt_36_ValidSudoku {
    public static void main(String[] args) {
        Solution sol = new Solution();

        char[][] board1 = {
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
        System.out.println(sol.isValidSudoku(board1)); // true

        char[][] board2 = {
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'}, // 這行與第一列衝突（同宮兩個 8）
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(sol.isValidSudoku(board2)); // false
    }
}

class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue;

                int d = ch - '1';
                int bit = 1 << d;
                int b = (r / 3) * 3 + (c / 3);

                if ((rows[r] & bit) != 0) return false;
                if ((cols[c] & bit) != 0) return false;
                if ((boxes[b] & bit) != 0) return false;

                rows[r]  |= bit;
                cols[c]  |= bit;
                boxes[b] |= bit;
            }
        }
        return true;
    }
}

/*
解題思路（完整）：
- 目標：驗證 9x9 盤是否「目前為止」符合數獨規則（列/行/九宮格不重複）。
- 做法：bitmask（位元遮罩）快速記錄 1~9 是否出現。
  rows[i] 追蹤第 i 列；cols[j] 追蹤第 j 行；boxes[b] 追蹤第 b 個 3x3 宮。
- 每格不是 '.' 就計算：
  bit = 1 << (digit-1)，b = (r/3)*3 + (c/3)
  若該 bit 在 rows/cols/boxes 任一已被設為 1，代表重複 → false。
  否則把 bit 置 1，繼續檢查。
- 複雜度：時間 O(81)，空間 O(1)。
*/

