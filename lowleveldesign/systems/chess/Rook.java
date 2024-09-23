package lowleveldesign.systems.chess;

public class Rook extends Piece{
    public Rook(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean canMove(Board board, int destRow, int destCol) {
        // rook can either move left o right or st or ;
        int colDiff = Math.abs(col - destCol);
        int rowDiff = Math.abs(row - destRow);

        boolean vertical = col == destCol;
        boolean horizontal = row == destRow;

        // now either or the above should be both can't be true;
        // if both are true that means it has no move

        return vertical || horizontal;
    }
}
