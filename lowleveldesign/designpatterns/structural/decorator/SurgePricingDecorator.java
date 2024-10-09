package lowleveldesign.designpatterns.structural.decorator;

public class SurgePricingDecorator extends RidePricingDecorator{
    private double surgeMultiplier;

    public SurgePricingDecorator(RidePricing ride, double surgeMultiplier) {
        super(ride);
        this.surgeMultiplier = surgeMultiplier;
    }

    @Override
    public String getDescription() {
        return decoratedRide.getDescription() + ", with Surge Pricing (x" + surgeMultiplier + ")";
    }

    @Override
    public double getCost() {
        return decoratedRide.getCost() * surgeMultiplier;
    }
}
