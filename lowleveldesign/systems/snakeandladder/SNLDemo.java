package lowleveldesign.systems.snakeandladder;

import java.util.Arrays;
import java.util.List;

public class SNLDemo {
    public static void main(String args[]) {
        GameManager gameManager = GameManager.getInstance();

        List<String> players1 = Arrays.asList("p1", "p2", "p3");
        List<String> players2 = Arrays.asList("pp1", "pp2", "pp3");

        gameManager.startNewGame(players1);
        gameManager.startNewGame(players2);
    }
}
