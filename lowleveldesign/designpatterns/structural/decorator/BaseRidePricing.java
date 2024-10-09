package lowleveldesign.designpatterns.structural.decorator;

public class BaseRidePricing implements RidePricing{
    @Override
    public String getDescription() {
        return "Base Ride Pricing";
    }

    @Override
    public double getCost() {
        return 10.0; // this is the base price
    }
}

