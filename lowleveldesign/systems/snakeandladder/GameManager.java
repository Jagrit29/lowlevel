package lowleveldesign.systems.snakeandladder;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Game> games; // all the ongoing games;
    private static GameManager instance;

    private GameManager() {
        games = new ArrayList<>();
    }

    public static synchronized GameManager getInstance() {
        if( instance == null) {
            instance = new GameManager();
        }

        return instance;
    }

    public void startNewGame(List<String> playerNames) {
        Game game = new Game(playerNames);
        games.add(game);
        // this is one way;
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                game.play();
//            }
//        });
        new Thread(game::play); // this another wya


    }
}
