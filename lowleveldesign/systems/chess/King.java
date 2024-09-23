package lowleveldesign.systems.chess;

/*
Vertical movement: The King can move up or down one square (rowDiff <= 1).
Horizontal movement: The King can move left or right one square (colDiff <= 1).
Diagonal movement: The King can move one square diagonally, which happens when both rowDiff and colDiff are equal to 1.
 */
public class King extends Piece {
    public King(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean canMove(Board board, int destRow, int destCol) {
        int rowDiff = Math.abs(row - destRow);
        int colDiff = Math.abs(row - destCol);

        return rowDiff <= 1 && colDiff <= 1;
    }
}
