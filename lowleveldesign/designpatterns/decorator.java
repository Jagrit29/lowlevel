package lowleveldesign.designpatterns;

// decorator is a structural pattern where you kind of structure or combine together multiple patterns to enhance more functionality.
// decorator is the one where you add more functionality to the existing object. For example, You already had coffee created and you want to add milk to it etc etc;


// Simple interface to create various coffee having different prices;
interface CoffeeInterface {
    int getPrice();
    String getDescription();
}

// Simple coffee class to create multiple coffees
class Coffee implements CoffeeInterface {
    @Override
    public int getPrice() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "Coffee is ready";
    }
}

// Now I can create a coffee but I want to add some more things into the above coffee like adding sugar, milk etc for that I need a decorator to decorate the exisitng object;
abstract class CoffeeDecorator implements CoffeeInterface {
    // now this decorator needs to have the interface on which it has be applied, like has a a relaptionship;
    protected CoffeeInterface coffee;

    public CoffeeDecorator(CoffeeInterface coffeeInterface) {
        this.coffee = coffeeInterface;
    }
    // now this can have same function has interface;
    @Override
    public int getPrice() {
        return coffee.getPrice(); // 2rs milk added
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

}

// now I can create multiple decorators;
class AddMilk extends CoffeeDecorator {
    public AddMilk(CoffeeInterface coffee) {
        super(coffee);
    }

    public int getPrice() {
        return super.getPrice() + 2;
    }

    public String getDescription() {
        return super.getDescription() + ", Added milk";
    }
}

public class decorator {
    public static void main(String args[]) {
        // now first I can create coffe;
        CoffeeInterface coffee1 = new Coffee(); // this is my plain coffee;
        System.out.println(coffee1.getDescription() + ":::" + coffee1.getPrice());

        // now I want to add milk into it, see I can use coffee1 object because of the has-a reference b/w decoration. the addmilk decorator has-a relation with coffeeinterface;
        coffee1 = new AddMilk(coffee1);
        System.out.println(coffee1.getDescription() + ":::" + coffee1.getPrice());

    }
}
