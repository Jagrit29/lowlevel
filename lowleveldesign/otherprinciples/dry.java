package lowleveldesign.otherprinciples;

// Wrong way - because the similar method is repeated a lot;
class TaxCalculator {
    public int calcClothingTax(int amount) {
        return amount * 2;
    }

    public int calcFoodTax(int amount) {
        return amount * 3;
    }

    public int calcCarTax(int amount) {
        return amount * 5;
    }
}

// correct way  - Here simply we used 1 method for all
class TaxCalculator2 {
    public int calcTax(int amount, int rate) {
        return amount*rate;
    }
}

public class dry {
}
