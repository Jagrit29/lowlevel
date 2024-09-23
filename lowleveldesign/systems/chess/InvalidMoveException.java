package lowleveldesign.systems.chess;

public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException(final String message) {
        super(message);
    }
}
