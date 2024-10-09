package lowleveldesign.designpatterns.structural.decorator;

public class UberClientDemo {
    public static void main(String[] args) {
        RidePricing ride = new BaseRidePricing(); // base simple class with interface and just a class that implements;
        System.out.println("Base Ride" +" "+ ride.getDescription()+" "+"Cost "+ride.getCost());

        // now we want to add surge on top of it;
        ride = new SurgePricingDecorator(ride, 2);

        System.out.println("Ride with surge" +" "+ ride.getDescription()+" "+"Cost "+ride.getCost());
    }
}
