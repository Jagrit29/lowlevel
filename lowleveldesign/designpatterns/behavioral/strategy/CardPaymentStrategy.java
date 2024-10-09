package lowleveldesign.designpatterns.behavioral.strategy;

public class CardPaymentStrategy implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Payment done via Card "+ amount);
    }
}
