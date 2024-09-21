package lowleveldesign.systems.snakeandladder;

import java.util.ArrayList;
import java.util.List;

public class Board {
    // board will have a size
    private static final int BOARD_SIZE = 100;
    // It will have list of snakes and ladders on various positions;

    List<Snake> snakes;
    List<Ladder> ladders;


    public Board() {
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
        initializeSnakesAndLadders();

        // initialize board like add some snakes to them
    }

    private void initializeSnakesAndLadders() {
        // Initialize snakes
        snakes.add(new Snake(16, 6));
        snakes.add(new Snake(48, 26));
        snakes.add(new Snake(64, 60));
        snakes.add(new Snake(93, 73));

        // Initialize ladders
        ladders.add(new Ladder(1, 38));
        ladders.add(new Ladder(4, 14));
        ladders.add(new Ladder(9, 31));
        ladders.add(new Ladder(21, 42));
        ladders.add(new Ladder(28, 84));
        ladders.add(new Ladder(51, 67));
        ladders.add(new Ladder(80, 99));
    }

    // now if we for anyposition if it's snake or ladder we need to return new position;


    public int getBoardSize() {
        return BOARD_SIZE;
    }


    // Java Doc
    /**
     * This method checks if the player's current position corresponds to the start of a snake
     * or a ladder and returns the updated position.
     *
     * @param position the current position of the player on the board
     * @return the new position after moving down a snake or up a ladder, or the same position if unchanged
     */
    public int checkPositionChange(int position) {
        // This will check if there is any position change for this particular positin

        for(Snake snake: snakes) {
            if(snake.getStart() == position) {
                // this is starting of the snake so move down;
                return snake.getEnd(); // this is the new positon
            }
        }

        for(Ladder ladder: ladders) {
            if(ladder.getStart() == position) {
                return ladder.getEnd();
            }
        }

        return position; // no position
    }
}
