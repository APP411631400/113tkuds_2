public class TicTacToeBoard {
    private char[][] board;

    public TicTacToeBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    public boolean place(int row, int col, char player) {
        if (row < 0 || row > 2 || col < 0 || col > 2) return false;
        if (board[row][col] != ' ') return false;
        board[row][col] = player;
        return true;
    }

    public char checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0];
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i];
        }
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0];
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2];
        return ' ';
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    public boolean isGameOver() {
        return checkWin() != ' ' || isFull();
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("---+---+---");
        }
    }

    public static void main(String[] args) {
        TicTacToeBoard t = new TicTacToeBoard();
        t.printBoard();
        t.place(0, 0, 'X');
        t.place(1, 1, 'O');
        t.place(0, 1, 'X');
        t.place(2, 2, 'O');
        t.place(0, 2, 'X');
        System.out.println();
        t.printBoard();
        System.out.println();
        char winner = t.checkWin();
        if (winner != ' ')
            System.out.println("Winner: " + winner);
        else if (t.isGameOver())
            System.out.println("Draw");
        else
            System.out.println("Game continues");
    }
}

