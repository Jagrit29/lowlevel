package lowleveldesign.systems.bitgo;

import java.util.*;
import java.util.List;

// Snake class
class Snake {
    private int head;
    private int tail;

    public Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }
}

class Ladder {
    private int start;
    private int end;

    public Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }
}

class Player {
    private String name;
    private int position;

    public Player(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }
}

class Board {
    int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    // Board
    // O(n) --> O(1)
    // 40 --
    // snakeMap -> HashMap key is the head;
    // ladderMap -> Ladder key is the start;
    private HashMap<Integer, Integer> snakesMap;
    private HashMap<Integer, Integer> laddersMap;

    public Board(int size, List<Snake> snakes, List<Ladder> ladders) {
        this.size = size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.snakesMap = new HashMap<>();
        this.laddersMap = new HashMap<>();

        for(Snake snake: snakes) {
            snakesMap.put(snake.getHead(), snake.getTail());
            this.snakes.add(snake);
        }

        for(Ladder ladder: ladders) {
            laddersMap.put(ladder.getStart(), ladder.getEnd());
            this.ladders.add(ladder);
        }
    }

    public int validateMove(int initialPosition, int add) {

        // invalid move
        int position = initialPosition + add;
        if(position > size) {
            return initialPosition;
        }

        /*

        // Snake  55 -> 40
        // Either
        // Snake  40 -> 30;
        // Ladder 30 -> 80
         */
        // position;
        int newPosition = position;

        while(snakesMap.containsKey(position) || laddersMap.containsKey(position)) {
//            int newPosition = position;
            int snakeImpact = snakesMap.containsKey(position) ?  snakesMap.get(position) : 0;

            if(snakeImpact !=0) {
                System.out.println("Player hit a snake");
                newPosition = snakeImpact;
            }

            int ladderImpact = laddersMap.containsKey(position) ? laddersMap.get(position) : 0;

            if(ladderImpact != 0) {
                System.out.println("Player hit a ladder");
                newPosition = ladderImpact;
            }

            position = newPosition;
        }



        return newPosition;


//        for(Snake snake: snakes) {
//            if(snake.getHead() == position) {
//                System.out.println("Player hit a snake");
//                return snake.getTail();
//            }
//        }
//
//        for(Ladder ladder: ladders) {
//            if(ladder.getStart() == position) {
//                System.out.println("Player hit a ladder");
//                return ladder.getEnd();
//            }
//        }
//
//        return position;
    }
}

class Dice {
    private int size;
    public Dice(int size) {
        this.size = size;
    }

    public int rollDice() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(size+1) % size;
        return randomNumber;
    }
}

class Game {
    private List<Player> players;
    // this is index of player
    private int currentPlayer;
    private Board board;
    private Dice dice;

    public Game(Board board, Dice dice, List<Player> players, int currentPlayer) {
        this.board = board;
        this.dice = dice;
        this.players = new ArrayList<>();
        for(Player player: players) {
            this.players.add(player);
        }
        this.currentPlayer = currentPlayer;
    }

    public void startGame() {
        System.out.println("Game started");
        while(true) {
            Player player = players.get(currentPlayer);

            int initialPosition = player.getPosition();
            int getNumber = dice.rollDice();
            int newPosition = board.validateMove(initialPosition, getNumber);

            // add log a statement
            // Format: <player_name> rolled a <dice_value> and moved from <initial_position> to <final_position>
            System.out.println(player.getName()+" rolled a dice "+ getNumber + " and moved from "+ initialPosition+" to "+ newPosition);
            player.setPosition(newPosition);

            if(isGameOver()) {
                System.out.println(players.get(currentPlayer).getName()+" won the game");
                break;
            }

            currentPlayer = (currentPlayer + 1) % players.size();
        }

//        System.out.println(players.get(currentPlayer).getName()+" won the game");
    }

    public boolean isGameOver() {
        return players.get(currentPlayer).getPosition() == board.size;
    }
}

public class SnakeAndLadderGame {
    public static void main(String args[]) {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Jagrit", 0);
        Player player2 = new Player("Zeeshan", 0);
        Player player3 = new Player("abc", 0);
        players.add(player1);
        players.add(player2);
        players.add(player3);

        // Snake  55 -> 40
        // Either
        // Snake  40 -> 30;
        // Ladder 40 -> 80

        List<Snake> snakes = new ArrayList<>();
        Snake snake1 = new Snake(55, 40);
        Snake snake2 = new Snake(40, 30);
        Snake snake3 = new Snake(70, 35);
        snakes.add(snake1);
        snakes.add(snake2);
        snakes.add(snake3);

        List<Ladder> ladders = new ArrayList<>();
//        Ladder ladder1 = new Ladder(10, 60);
//        Ladder ladder2 = new Ladder(70, 85);
//        Ladder ladder3 = new Ladder(30, 80);
        Ladder ladder4 = new Ladder(1, 55);
        Ladder ladder5 = new Ladder(2, 55);
        Ladder ladder6 = new Ladder(3, 55);
        Ladder ladder7 = new Ladder(4, 55);
        Ladder ladder8 = new Ladder(5, 55);
        Ladder ladder9 = new Ladder(6, 55);

        ladders.add(ladder4);
        ladders.add(ladder5);
        ladders.add(ladder6);
        ladders.add(ladder7);
        ladders.add(ladder8);
        ladders.add(ladder9);

        Dice dice = new Dice(6);

        Board board = new Board(100, snakes, ladders);

        Game game = new Game(board, dice, players, 0);

        game.startGame();
    }
}
