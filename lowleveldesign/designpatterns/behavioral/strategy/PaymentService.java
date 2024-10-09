package lowleveldesign.designpatterns.behavioral.strategy;

public class PaymentService {
    PaymentStrategy paymentStrategy;
    private static PaymentService paymentService;

    private PaymentService() {}

    public static synchronized PaymentService getPaymentService() {
        if(paymentService == null) {
            paymentService = new PaymentService();
        }

        return paymentService;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        paymentStrategy.pay(amount);
    }
}
