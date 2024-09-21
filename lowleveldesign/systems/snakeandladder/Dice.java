package lowleveldesign.systems.snakeandladder;

public class Dice {
    private static final int min = 1;
    private static final int max = 6;

    public int roll() {
        // Math.random() generates a random double between 0.0 (inclusive) and 1.0 (exclusive).
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
