package lowleveldesign.amazonlldsystemdesign.payment;

public class CreditCardPayment implements Payment{
    public boolean processPayment( double amount) {
        System.out.println("Payment via credit card is porcess"+" "+amount);
        return true;
    }
}
