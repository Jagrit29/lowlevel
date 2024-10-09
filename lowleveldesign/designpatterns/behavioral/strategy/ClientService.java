package lowleveldesign.designpatterns.behavioral.strategy;

public class ClientService {
    public static void main(String args[]) {
        PaymentService paymentService = PaymentService.getPaymentService();

        // make payment via Cash, This is where we have acheived extensiblity as we are passing strategy instead of fixed;
        paymentService.setPaymentStrategy(new CashPaymentStrategy());
        paymentService.executePayment(100);


        // Changed the strategy based on our need;
        paymentService.setPaymentStrategy(new CardPaymentStrategy());
        paymentService.executePayment(200);
    }
}
