package lowleveldesign.designpatterns.behavioral.strategy;

public class CashPaymentStrategy implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Payment done via Cash "+ amount);
    }
}
