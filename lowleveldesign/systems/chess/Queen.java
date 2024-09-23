package lowleveldesign.systems.chess;

/*
The Queen can move diagonally like a Bishop (when rowDiff == colDiff).
The Queen can move horizontally or vertically like a Rook (when row == destRow or col == destCol).
Examples of Valid Moves:
Horizontal move: From (4, 4) to (4, 7) (same row).
Vertical move: From (4, 4) to (1, 4) (same column).
Diagonal move: From (4, 4) to (7, 7) (same diagonal, since rowDiff == colDiff).
 */
public class Queen extends Piece{
    public Queen(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean canMove(Board board, int destRow, int destCol) {
        int rowDiff = Math.abs(row - destRow);
        int colDiff = Math.abs(col - destCol);

        boolean diagonalMove = rowDiff == colDiff;
        boolean verticalMove = col == destCol;
        boolean horizontalMove = row == destRow;

        return diagonalMove || verticalMove || horizontalMove;
    }
}
