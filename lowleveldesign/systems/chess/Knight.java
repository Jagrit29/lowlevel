package lowleveldesign.systems.chess;

public class Knight extends Piece{
    public Knight(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean canMove(Board board, int destRow, int destCol) {
        // moves in a L shape;
        // either row 1 and col 2
        // either row 2 and col 1;

        int rowDiff = Math.abs(row - destRow);
        int colDiff = Math.abs(col - destCol);

        boolean op1 = rowDiff == 1 && colDiff == 2;
        boolean op2 = rowDiff == 2 && colDiff == 1;

        return op1 || op2;
    }
}
