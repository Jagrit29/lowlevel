package lowleveldesign.systems.chess;

/*
Pawns move forward one square (or two squares on their first move).
They capture diagonally by moving one square forward to the side.
The movement differs for white and black pawns because they move in opposite directions on the board
 */

public class Pawn extends Piece{
    // constructor can be just cal to the top;

    public Pawn(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean canMove(Board board, int destRow, int destCol) {
        int rowDiff = destRow - row;
        int colDiff = Math.abs(destCol - col); // can't change col;

        // assuming white is the bottoms ide they can move up;

        if(color == Color.WHITE) {
            boolean normalForwardMove = rowDiff == 1 && colDiff == 0;
            boolean firstDoubleMove = row == 1 && rowDiff == 2 && colDiff == 0;
            boolean killMove = rowDiff == 1 && colDiff == 1 && board.getPiece(destRow, destCol) != null;

            return normalForwardMove || firstDoubleMove || killMove;
        } else {
            // coming down;
            boolean normalDownMove = rowDiff == -1 && colDiff == 0;
            boolean firstDoubleMove = row == 6 && rowDiff == -2 && colDiff == 0;
            boolean killMove = rowDiff == -1 && colDiff == 1 && board.getPiece(destRow, destCol)!=null;
        }

        return false;
    }
}
