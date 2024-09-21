package lowleveldesign.systems.snakeandladder;

import java.util.ArrayList;
import java.util.List;

// This is a single game
// Now single game need board, list of players and a dice;
// also current player;
// you can click on playGame to start hte game;
public class Game {
    private final Board board;
    private List<Player> players;
    private Dice dice;
    private int currentPlayerIndex;


    public Game(List<String> playerNames) {
        board = new Board();
        players = new ArrayList<>();
        dice = new Dice();

        for(String player: playerNames) {
            players.add(new Player(player));
        }

        currentPlayerIndex = 0; // we can just do this by mod of totla players;

    }

    public void play() {
        // while game is not over
        while(true) {
            Player currentPlayer = players.get(currentPlayerIndex);

            int diceNumber = dice.roll();
            int newPosition = currentPlayer.getPosition() + diceNumber;

            // we can add a check to see if the newPositon is going out of bound too

            if(newPosition <= board.getBoardSize()) {
                // this is good now see if it is either a snaker or lader;
                newPosition = board.checkPositionChange(newPosition);
                // now this is our new position;
                currentPlayer.setPosition(newPosition);
                System.out.println(currentPlayer.getName() + "rolled" + diceNumber + " and reached" + newPosition);
            }

            if(currentPlayer.getPosition() == board.getBoardSize()) {
                // this means now he won;
                System.out.println(currentPlayer.getName()+"wins");
                break;
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); // new player turns;
        }
    }
}
