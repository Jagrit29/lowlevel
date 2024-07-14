package lowleveldesign.solidprinciples;
/*
Liskov Substitution Principle (LSP)
The Liskov Substitution Principle states that objects of a superclass should be replaceable with objects of a subclass
without affecting the correctness of the program.
In simpler terms, if a class is a subclass of another class, it should be usable wherever the parent class is expected, without any issues.
 */

class Bird {
    public void fly() {
        System.out.println("Bird can fly");
    }
}

// Correct
class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow Fly");
    }
}

// Wrong as it doesn't do justice to the fly of its parent class. It should respect it;
class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches can't fly.");
    }
}

public class LisKov {
    public static void main() {

    }
}
