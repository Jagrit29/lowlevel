package practice.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

enum Symbol {
    X,
    O,
    E
}

class Player {
    private final String name;
    private final Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}

class Move {
   int x;
    int y;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Board {
    int size;
    Symbol[][] grid;

    public Board(int size) {
        grid = new Symbol[size][size];

        for(Symbol childGrid[]:  grid) {
            Arrays.fill(childGrid, Symbol.E);
        }
    }

    public void makeMove(Player player, Move move) {
        // valid if the move is within bounds
        grid[move.x][move.y] = player.getSymbol();

    }

    public boolean isGameOver() {

        // check rows
        for(int i=0;i<size;i++) {
            boolean isSame = true;
            for(int j=1;j<size;j++) {
                if(grid[i][j] != Symbol.E && grid[i][j-1] != grid[i][j]) {
                    isSame = false;
                }
            }

            if(isSame) return isSame;
        }

        // check cols

        // check diagonals

        return false;
    }
}

class Game {
    List<Player> players;
    int currentPlayerIndex;
    Board board;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = new ArrayList<>(players);
    }

    public void playGame() {
        Scanner sc = new Scanner(System.in);
        while(!board.isGameOver()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(players.get(currentPlayerIndex).getName() +" "+ "please make the move");
            int x = sc.nextInt();
            int y = sc.nextInt();
            board.makeMove(currentPlayer, new Move(x, y));
            currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        }
    }

}
public class TicTacToeDemo {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Prabhat", Symbol.X);
        Player player2 = new Player("Jagrit", Symbol.O);

        players.add(player1);
        players.add(player2);

        Board board = new Board(3);

        Game game = new Game(board, players);

        game.playGame();

    }
}


// #