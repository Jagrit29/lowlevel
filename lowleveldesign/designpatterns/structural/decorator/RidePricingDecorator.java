package lowleveldesign.designpatterns.structural.decorator;

public abstract class RidePricingDecorator implements RidePricing {
    protected RidePricing decoratedRide;

    public RidePricingDecorator(RidePricing ride) {
        this.decoratedRide = ride;
    }

    @Override
    public String getDescription() {
        return decoratedRide.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedRide.getCost();
    }
}
