package lowleveldesign.systems.chess;

public class Move {
    private final Piece piece;
    private final int destRow;
    private final int destCol;

    public Move(Piece piece, int destRow, int destCol) {
        this.piece = piece;
        this.destCol = destCol;
        this.destRow = destRow;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getDestRow() {
        return destRow;
    }

    public int getDestCol() {
        return destCol;
    }


}
