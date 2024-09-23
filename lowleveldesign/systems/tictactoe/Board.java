package lowleveldesign.systems.tictactoe;

import java.util.Arrays;

// board of type Symbol
public class Board {
    private Symbol[][] board;
    private int movesCount;

    public Board() {
        board = new Symbol[3][3];
        movesCount = 0;
        for(Symbol[] row : board) {
            Arrays.fill(row, Symbol.N);
        }
    }

    public boolean isFull() {
        return movesCount == 9;
    }

    public synchronized void makeMove(int row, int col, Symbol symbol) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != symbol.N) {
            throw new IllegalArgumentException("Invalid move!");
        }
        board[row][col] = symbol;
        movesCount++;
    }

    public boolean isWinner() {
        // each row;
        for(int row=0;row<3;row++) {
            // all the elements of first row;
            if(board[row][0] != Symbol.N && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }

        for(int col=0;col<3;col++) {
            // all the elements of first row;
            if(board[0][col] != Symbol.N && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }

        if (board[0][0] != Symbol.N && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                return true;
        }

        if (board[0][2] != Symbol.N && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;

    }

}
