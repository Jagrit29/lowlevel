package lowleveldesign.systems.pizzaevaluator;

import java.util.HashMap;

enum Base {
    THIN_CRUST,
    THIN_CRUST2
}

class BaseClass {

}

class Price {
    String name;
    Base base;
    int size;
    HashMap<Integer, Topping> toppings;
}

class Topping {
    String name;
    int price;
}

// Google, Amazon

// ToppingDecrotr
// OnionToppingDecorro
//public class PizzaPriceService {
//    public void getPizza();
//}
